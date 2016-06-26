package com.oxd.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.oxd.model.User;


@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	@Query("select u from User u where u.username = ?1")
	public User findByName(String name);
}
