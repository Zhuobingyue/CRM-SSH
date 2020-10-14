package com.itheima.crm.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.crm.dao.ICustomerDao;
import com.itheima.crm.domain.Customer;
import com.itheima.crm.domain.PageBean;
import com.itheima.crm.service.ICustomerService;

@Transactional
public class CustomerServiceImpl implements ICustomerService {

	private ICustomerDao customerDao;

	public void setCustomerDao(ICustomerDao customerDao) {
		this.customerDao = customerDao;
	}

	@Override
	public void save(Customer customer) {
		this.customerDao.save(customer);
	}

	@Override
	public PageBean<Customer> findByPage(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize) {
		PageBean<Customer> pageBean = new PageBean<Customer>();
		pageBean.setCurrPage(currPage);
		pageBean.setPageSize(pageSize);
		Integer totalCount = customerDao.findCount(detachedCriteria);
		pageBean.setTotalCount(totalCount);
		Double doubleValue = totalCount.doubleValue();
		System.out.println(pageSize);
		Double ceil = Math.ceil(doubleValue / pageSize);
		pageBean.setTotalPage(ceil.intValue());
		// 封裝每页显示数据的集合
		Integer begin = (currPage - 1) * pageSize;
		List<Customer> list = customerDao.findByPage(detachedCriteria, begin, pageSize);
		pageBean.setList(list);
		return pageBean;
	}

	@Override
	public Customer findById(Long cust_id) {
		return customerDao.findById(cust_id);
	}

	@Override
	public void delete(Customer customer) {
		customerDao.delete(customer);
	}

	@Override
	public void update(Customer customer) {
		customerDao.update(customer);
	}

	@Override
	public List<Customer> findAll() {
		return customerDao.findAll();
	}

}
