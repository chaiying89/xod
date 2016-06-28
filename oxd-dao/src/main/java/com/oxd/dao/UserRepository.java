package com.oxd.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.oxd.model.UserModel;


@Repository
public interface UserRepository extends JpaRepository<UserModel, Integer> {

	@Query("select u from UserModel u where u.username = ?1")
	public UserModel findByName(String name);
}
