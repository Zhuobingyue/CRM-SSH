package com.itheima.crm.dao.impl;

import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import com.itheima.crm.dao.ICustomerDao;
import com.itheima.crm.domain.Customer;

public class CustomerDaoImpl extends HibernateDaoSupport implements ICustomerDao {

	@Override
	public void save(Customer customer) {
		this.getHibernateTemplate().save(customer);
	}

	// 查询总记录数
	@Override
	public Integer findCount(DetachedCriteria detachedCriteria) {
		detachedCriteria.setProjection(Projections.rowCount());
		@SuppressWarnings("unchecked")
		List<Long> list = (List<Long>) this.getHibernateTemplate().findByCriteria(detachedCriteria);
		if (list.size() > 0) {
			return list.get(0).intValue();
		}
		return 0;
	}

	// 分页查询
	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> findByPage(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize) {
		detachedCriteria.setProjection(null);
		return (List<Customer>) this.getHibernateTemplate().findByCriteria(detachedCriteria, begin, pageSize);
	}

	@Override
	public Customer findById(Long cust_id) {
		return this.getHibernateTemplate().get(Customer.class, cust_id);
	}

	@Override
	public void delete(Customer customer) {
		this.getHibernateTemplate().delete(customer);
	}

	@Override
	public void update(Customer customer) {
		this.getHibernateTemplate().update(customer);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Customer> findAll() {
		return (List<Customer>) this.getHibernateTemplate().find("from Customer");
	}

}
