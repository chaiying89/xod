package com.oxd.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oxd.model.ActivityModel;


@Repository
public interface ActivityRepository extends JpaRepository<ActivityModel, Integer> {
}
