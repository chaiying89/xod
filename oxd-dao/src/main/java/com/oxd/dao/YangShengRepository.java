package com.oxd.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oxd.model.YangShengModel;



@Repository
public interface YangShengRepository extends JpaRepository<YangShengModel, Integer> {
}
