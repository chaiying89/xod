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

import com.oxd.dao.MenuRepository;
import com.oxd.model.MenuModel;
import com.oxd.vo.MenuVo;

@Service
@Transactional(rollbackFor = Exception.class)
public class MenuService extends AbstractService {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Autowired
	private MenuRepository repository;
	
	@Transactional(readOnly = true)
	public MenuModel getByName(String name) {
		return repository.findByName(name);
	}
	
	@SuppressWarnings("unchecked")
	public List<MenuVo> selectQuery(String parentName) {
		try {
			Session session = entityManager.unwrap(Session.class);
			List<Object> params = new ArrayList<Object>();
			String[] columns = { "id", "name"};
			params.add(parentName);
	
			String sql = "select n.id, n.name from menu_model n left join menu_model p on p.id=n.parent_id where p.name=?";
			SQLQuery query = session.createSQLQuery(sql);
			this.setScalarsAndParams(query, columns, params);
			List<MenuVo> vos = query.setResultTransformer(Transformers.aliasToBean(MenuVo.class)).list();
			
			return vos;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 添加或更新资讯信息
	 * @param news
	 */
	public void saveOrUpdate(MenuModel news) {
		repository.save(news);
	}

}
