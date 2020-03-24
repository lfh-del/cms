package com.briup.demo.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.briup.demo.bean.Customer;

public interface CustomerDao extends JpaRepository<Customer, Integer>{
	
	@Query("select c.password from Customer c where c.username=?1")
	String findPasswordByName(String name) throws Exception;

}
