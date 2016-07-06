package com.oxd.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oxd.model.AboutModel;


@Repository
public interface AboutRepository extends JpaRepository<AboutModel, Integer> {
}
