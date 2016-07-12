package com.oxd.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oxd.model.ScopeModel;


@Repository
public interface ScopeRepository extends JpaRepository<ScopeModel, Integer> {
}
