package com.hbs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.hbs.entities.Admin;

@Repository
public interface IAdminRepository extends JpaRepository<Admin, Integer> {

}
