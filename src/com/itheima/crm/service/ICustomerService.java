package com.itheima.crm.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.itheima.crm.domain.Customer;
import com.itheima.crm.domain.Linkman;
import com.itheima.crm.domain.PageBean;

public interface ICustomerService {

	PageBean<Customer> findByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize);

	void save(Customer customer);
	
	Customer findById(Long cust_id);

	void delete(Customer customer);

	void update(Customer customer);

	List<Customer> findAll();

}
