package com.itheima.crm.service;

import org.hibernate.criterion.DetachedCriteria;

import com.itheima.crm.domain.Linkman;
import com.itheima.crm.domain.PageBean;

public interface ILinkmanService {

	PageBean<Linkman> findAll(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize);

	void save(Linkman linkman);

	Linkman findById(Long lkm_id);

	void update(Linkman linkman);

	void delete(Linkman linkman);

}
