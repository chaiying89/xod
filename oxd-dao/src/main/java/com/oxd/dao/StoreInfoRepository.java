package com.oxd.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oxd.model.StoreInfoModel;


@Repository
public interface StoreInfoRepository extends JpaRepository<StoreInfoModel, Integer> {
}
