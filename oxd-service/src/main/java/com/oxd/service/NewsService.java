package com.oxd.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.oxd.dao.NewsRepository;
import com.oxd.exception.OxdException;
import com.oxd.model.MenuModel;
import com.oxd.model.NewsModel;
import com.oxd.util.Constants;
import com.oxd.vo.NewsVo;
import com.oxd.vo.PageVo;

@Service
@Transactional(rollbackFor = Exception.class)
public class NewsService extends AbstractService {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private NewsRepository repository;

	@Transactional(readOnly = true)
	public NewsModel getByName(String name) {
		return repository.findByName(name);
	}

	/**
	 * 添加或更新资讯信息
	 * 
	 * @param news
	 */
	public void saveOrUpdate(NewsModel news) {
		this.fillEntity(news);
		repository.save(news);
	}
	
	/**
	 * 设置置顶
	 * @param id
	 * @throws Exception
	 */
	public void top(int id) throws Exception {
		NewsModel news = repository.findOne(id);
		MenuModel menu = news.getType();
		Session session = entityManager.unwrap(Session.class);
		List<Object> params = new ArrayList<Object>();
		String countSql = "select count(0) as count from news_model n where n.top=1 and n.type=?";
		params.add(menu.getId());
		int count = this.getCount(session, countSql, params);
		if(count>=4) {
			throw new OxdException("置顶已超过上限，请取消后再置顶");
		}
		news.setTop(Constants.TOP_1);
		repository.saveAndFlush(news);
	}
	
	/**
	 * 取消置顶
	 * @param id
	 * @throws Exception
	 */
	public void cancelTop(int id) throws Exception {
		NewsModel news = repository.findOne(id);
		news.setTop(Constants.TOP_0);
		repository.saveAndFlush(news);
	}
	
	/**
	 * 删除资讯
	 * @param id
	 */
	public void delete(int id) {
		repository.delete(id);
	}
	
	/**
	 * 根据id查询单个对象
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = true)
	public NewsModel findOne(int id) {
		return repository.findOne(id);
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
	public PageVo findPageByParam(int pageNum, int pageSize, int type) throws Exception {
		PageVo page = new PageVo();
		try {
			Session session = entityManager.unwrap(Session.class);
			List<Object> params = new ArrayList<Object>();
			String[] columns = { "id", "title", "introduction",
					"prePictureUrl", "type", "typeId", "createTime", "updateTime",
					"createUser", "updateUser", "top" };

			String countSql = "select count(0) as count from news_model n left join menu_model e on n.type=e.id ";

			String sql = "select n.id, n.top, n.title, n.introduction, "
					+ "n.pre_picture_url as prePictureUrl, e.`name` as type, n.type as typeId, "
					+ "DATE_FORMAT(n.create_time, '%Y-%m-%d %T') as createTime,"
					+ "DATE_FORMAT(n.update_time, '%Y-%m-%d %T') as updateTime, "
					+ "u.`name` as createUser, u1.`name` as updateUser from news_model n "
					+ "left join menu_model e on n.type=e.id left join user_model u on n.create_user = u.id "
					+ "left join user_model u1 on n.update_user = u1.id ";

			StringBuilder where = new StringBuilder(" where 1=1 ");
			if (type > 0) {
				params.add(type);
				where.append(" and e.parent_id = ? ");
			}

			int count = this.getCount(session, countSql + where, params);
			page.setTotal(count);
			
			where.append(" order by n.top desc, n.create_time desc limit " + ((pageNum-1)*pageSize) + "," + pageSize);

			SQLQuery query = session.createSQLQuery(sql + where);
			this.setScalarsAndParams(query, columns, params);
			List<NewsVo> vos = query
					.setResultTransformer(Transformers
							.aliasToBean(NewsVo.class)).list();
			page.setRows(vos);
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		}
		return page;
	}

}
