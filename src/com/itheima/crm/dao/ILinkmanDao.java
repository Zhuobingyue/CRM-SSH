package com.itheima.crm.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import com.itheima.crm.domain.Linkman;

public interface ILinkmanDao {

	Integer findCount(DetachedCriteria detachedCriteria);

	List<Linkman> findAll(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize);

	void save(Linkman linkman);

	Linkman findById(Long lkm_id);

	void update(Linkman linkman);

	void delete(Linkman linkman);

}
