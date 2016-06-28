package com.oxd.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.oxd.model.MenuModel;


@Repository
public interface MenuRepository extends JpaRepository<MenuModel, Integer> {

	@Query("select menu from MenuModel menu where menu.name = ?1")
	public MenuModel findByName(String name);
}
