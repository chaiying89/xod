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
import org.springframework.util.StringUtils;

import com.oxd.dao.NewsRepository;
import com.oxd.model.NewsModel;
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
		repository.save(news);
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
	@SuppressWarnings("unchecked")
	public PageVo findPageByParam(final String title, final int typeId,
			int pageNum, int pageSize) {
		PageVo page = new PageVo();
		try {
			Session session = entityManager.unwrap(Session.class);
			List<Object> params = new ArrayList<Object>();
			String[] columns = { "id", "title", "introduction",
					"prePictureUrl", "type", "createTime", "updateTime",
					"createUser", "updateUser" };

			String countSql = "select count(0) as count from news_model n left join menu_model e on n.type=e.id ";

			String sql = "select n.id, n.title, n.introduction, "
					+ "n.pre_picture_url as prePictureUrl, e.`name` as type, "
					+ "DATE_FORMAT(n.create_time, '%Y-%m-%d %T') as createTime,"
					+ "DATE_FORMAT(n.update_time, '%Y-%m-%d %T') as updateTime, "
					+ "u.`name` as createUser, u1.`name` as updateUser from news_model n "
					+ "left join menu_model e on n.type=e.id left join user_model u on n.create_user = u.id "
					+ "left join user_model u1 on n.update_user = u1.id ";

			StringBuilder where = new StringBuilder(" where 1=1 ");
			if (!StringUtils.isEmpty(title)) {
				params.add(title);
				where.append(" and n.title like ? ");
			}
			if (typeId > 0) {
				params.add(title);
				where.append(" and e.id = ? ");
			}

			int count = this.getCount(session, countSql + where);
			page.setTotal(count);
			
			where.append(" limit " + ((pageNum-1)*pageSize) + "," + pageSize);

			SQLQuery query = session.createSQLQuery(sql + where);
			this.setScalarsAndParams(query, columns, params);
			List<NewsVo> vos = query
					.setResultTransformer(Transformers
							.aliasToBean(NewsVo.class)).list();
			page.setRows(vos);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return page;
	}

}
