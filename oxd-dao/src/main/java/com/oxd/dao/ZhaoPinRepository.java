package com.oxd.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oxd.model.ZhaoPinModel;


@Repository
public interface ZhaoPinRepository extends JpaRepository<ZhaoPinModel, Integer> {
}
