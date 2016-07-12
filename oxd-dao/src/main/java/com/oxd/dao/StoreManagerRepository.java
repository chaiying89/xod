package com.oxd.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oxd.model.StoreManagerModel;


@Repository
public interface StoreManagerRepository extends JpaRepository<StoreManagerModel, Integer> {
}
