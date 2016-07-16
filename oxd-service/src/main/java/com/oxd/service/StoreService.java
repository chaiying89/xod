package com.oxd.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oxd.dao.StoreInfoRepository;
import com.oxd.dao.StoreManagerRepository;
import com.oxd.exception.OxdException;
import com.oxd.model.StoreInfoModel;
import com.oxd.model.StoreManagerModel;
import com.oxd.util.Constants;
import com.oxd.vo.PageVo;
import com.oxd.vo.StoreInfoVo;
import com.oxd.vo.TouziVo;

@Service
@Transactional(rollbackFor = Exception.class)
public class StoreService extends AbstractService {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private StoreManagerRepository storeManagerRepository;
	
	@Autowired
	private StoreInfoRepository storeInfoRepository;

	/**
	 * 添加或更新资讯信息
	 * 
	 * @param news
	 */
	public void saveOrUpdate(StoreInfoModel news) {
		this.fillEntity(news);
		storeInfoRepository.save(news);
	}
	
	/**
	 * 设置置顶
	 * @param id
	 * @throws Exception
	 */
	public void top(int id) throws Exception {
		StoreInfoModel news = storeInfoRepository.findOne(id);
		Session session = entityManager.unwrap(Session.class);
		String countSql = "select count(0) as count from store_info_model n where n.top=1";
		int count = this.getCount(session, countSql, null);
		if(count>=4) {
			throw new OxdException("置顶已超过上限，请取消后再置顶");
		}
		news.setTop(Constants.TOP_1);
		storeInfoRepository.saveAndFlush(news);
	}
	
	/**
	 * 取消置顶
	 * @param id
	 * @throws Exception
	 */
	public void cancelTop(int id) throws Exception {
		StoreInfoModel news = storeInfoRepository.findOne(id);
		news.setTop(Constants.TOP_0);
		storeInfoRepository.saveAndFlush(news);
	}
	
	/**
	 * 删除资讯
	 * @param id
	 */
	public void delete(int id) {
		storeInfoRepository.delete(id);
	}
	
	/**
	 * 根据id查询单个对象
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = true)
	public StoreInfoModel findOne(int id) {
		return storeInfoRepository.findOne(id);
	}

	/**
	 * 分页查询新闻内容
	 * 
	 * @param title
	 * @param typeId
	 * @param pageNum
	 * @param pageSize
	 * @return
	 */
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public PageVo findPageByParam(int pageNum, int pageSize) throws Exception {
		PageVo page = new PageVo();
		try {
			Session session = entityManager.unwrap(Session.class);
			String[] columns = { "id", "title", "address",
					"prePictureUrl", "createTime", "updateTime",
					"createUser", "updateUser", "top" };

			String countSql = "select count(0) as count from store_info_model n ";

			String sql = "select n.id, n.top, n.title, n.address, "
					+ "n.pre_picture_url as prePictureUrl, "
					+ "DATE_FORMAT(n.create_time, '%Y-%m-%d %T') as createTime,"
					+ "DATE_FORMAT(n.update_time, '%Y-%m-%d %T') as updateTime, "
					+ "u.`name` as createUser, u1.`name` as updateUser from store_info_model n "
					+ "left join user_model u on n.create_user = u.id "
					+ "left join user_model u1 on n.update_user = u1.id ";

			StringBuilder where = new StringBuilder(" where 1=1 ");
			

			int count = this.getCount(session, countSql + where, null);
			page.setTotal(count);
			
			where.append(" order by n.top desc, n.create_time desc limit " + ((pageNum-1)*pageSize) + "," + pageSize);

			SQLQuery query = session.createSQLQuery(sql + where);
			this.setScalars(query, columns);
			List<StoreInfoVo> vos = query
					.setResultTransformer(Transformers
							.aliasToBean(StoreInfoVo.class)).list();
			page.setRows(vos);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return page;
	}
	
	
	
	
	
	
	
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<TouziVo> search() {
		try {
			Session session = entityManager.unwrap(Session.class);
			String[] columns = { "id", "type", "typeId", "content", "createTime", "updateTime",
					"createUser", "updateUser"};
	
			String sql = "select m.id as typeId, m.name as type, a.id, a.content, DATE_FORMAT(a.create_time, '%Y-%m-%d %T') as createTime, "
					+ " DATE_FORMAT(a.update_time, '%Y-%m-%d %T') as updateTime, u1.name as createUser, u2.name as updateUser from menu_model m "
					+ " left join store_manager_model a on a.m_id=m.id left join user_model u1 on u1.id=a.create_user left join user_model u2 on u2.id=a.update_user where m.parent_id=22 and m.has_child=0";
			SQLQuery query = session.createSQLQuery(sql);
			this.setScalars(query, columns);
			List<TouziVo> vos = query.setResultTransformer(Transformers.aliasToBean(TouziVo.class)).list();
			return vos;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 删除
	 * @param id
	 */
	public void deleteManager(int id) {
		storeManagerRepository.delete(id);
	}
	
	/**
	 * 新增或修改
	 * @param news
	 */
	public void saveOrUpdateManager(StoreManagerModel entity) {
		this.fillEntity(entity);
		storeManagerRepository.save(entity);
	}

}
