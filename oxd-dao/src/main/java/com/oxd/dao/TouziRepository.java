package com.oxd.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oxd.model.TouziModel;


@Repository
public interface TouziRepository extends JpaRepository<TouziModel, Integer> {
}
