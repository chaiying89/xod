package com.oxd.service;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
	
	@Transactional(readOnly = true)
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
	
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public List<MenuVo> siteMenuQuery() {
		try {
			Session session = entityManager.unwrap(Session.class);
			String[] columns = { "id", "name", "url"};
	
			String sql = "select n.id, n.name, n.url from menu_model n where n.level=1";
			SQLQuery query = session.createSQLQuery(sql);
			this.setScalars(query, columns);
			List<MenuVo> vos = query.setResultTransformer(Transformers.aliasToBean(MenuVo.class)).list();
			
			return vos;
		} catch(Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	@Transactional(readOnly = true)
	@SuppressWarnings("unchecked")
	public Map<Integer, List<MenuVo>> site2lMenuQuery() {
		try {
			Map<Integer, List<MenuVo>> map = new LinkedHashMap<Integer, List<MenuVo>>();
			Session session = entityManager.unwrap(Session.class);
			String[] columns = { "id", "name", "url", "pid"};
	
			String sql = "select n.id, n.name, n.url, n.parent_id as pid from menu_model n where n.level=2";
			SQLQuery query = session.createSQLQuery(sql);
			this.setScalars(query, columns);
			List<MenuVo> vos = query.setResultTransformer(Transformers.aliasToBean(MenuVo.class)).list();
			for(MenuVo vo : vos) {
				List<MenuVo> list = map.get(vo.getPid());
				if(list == null) {
					list = new ArrayList<MenuVo>();
					map.put(vo.getPid(), list);
				}
				list.add(vo);
			}
			return map;
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
