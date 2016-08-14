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

import com.oxd.dao.ActivityRepository;
import com.oxd.exception.OxdException;
import com.oxd.model.ActivityModel;
import com.oxd.util.Constants;
import com.oxd.vo.ActivityVo;

@Service
@Transactional(rollbackFor = Exception.class)
public class ActivityService extends AbstractService {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private ActivityRepository repository;
	
	/**
	 * 新增或修改
	 * @param news
	 */
	public void saveOrUpdate(ActivityModel entity) {
		this.fillEntity(entity);
		repository.save(entity);
	}
	
	/**
	 * 设置置顶
	 * @param id
	 * @throws Exception
	 */
	public void top(int id) throws Exception {
		ActivityModel news = repository.findOne(id);
		Session session = entityManager.unwrap(Session.class);
		String countSql = "select count(0) as count from activity_model n where n.top=1";
		int count = this.getCount(session, countSql, null);
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
		ActivityModel news = repository.findOne(id);
		news.setTop(Constants.TOP_0);
		repository.saveAndFlush(news);
	}
	
	
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<ActivityVo> search() {
		try {
			Session session = entityManager.unwrap(Session.class);
			String[] columns = { "id", "title", "imgUrl", "top", "createTime", "updateTime",
					"createUser", "updateUser"};
	
			String sql = "select m.id, m.title, m.img_url as imgUrl, m.top,"
					+ " DATE_FORMAT(m.create_time, '%Y-%m-%d %T') as createTime, "
					+ " DATE_FORMAT(m.update_time, '%Y-%m-%d %T') as updateTime, "
					+ " u.name as createUser, u1.name as updateUser from activity_model m "
					+ " left join user_model u on m.create_user = u.id "
					+ " left join user_model u1 on m.update_user = u1.id ";
			SQLQuery query = session.createSQLQuery(sql);
			this.setScalars(query, columns);
			List<ActivityVo> vos = query.setResultTransformer(Transformers.aliasToBean(ActivityVo.class)).list();
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
	public void delete(int id) {
		repository.delete(id);
	}
	
	public ActivityModel findOneModel(int id) {
		return repository.findOne(id);
	}
	
	/**--------------------------------网站相关查询-------------------------------**/
	
	/**
	 * 网站页面内容查询
	 * @param id
	 * @return
	 */
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public ActivityVo findOne(int id) {
		Session session = entityManager.unwrap(Session.class);
		String[] columns = { "content"};
		String sql = "select a.content from activity_model a where a.id=?";
		List<Object> params = new ArrayList<Object>(1);
		params.add(id);
		SQLQuery query = session.createSQLQuery(sql);
		this.setScalarsAndParams(query, columns, params);
		List<ActivityVo> vos = query.setResultTransformer(Transformers.aliasToBean(ActivityVo.class)).list();
		if(vos.size() > 0) {
			return vos.get(0);
		}
		return null;
	}
	
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<ActivityVo> searchByPage() {
		try {
			Session session = entityManager.unwrap(Session.class);
			String[] columns = {"imgUrl", "id"};
	
			String sql = "select img_url as imgUrl, id from activity_model where top=1";
			SQLQuery query = session.createSQLQuery(sql);
			this.setScalars(query, columns);
			List<ActivityVo> vos = query.setResultTransformer(Transformers.aliasToBean(ActivityVo.class)).list();
			return vos;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}

}
