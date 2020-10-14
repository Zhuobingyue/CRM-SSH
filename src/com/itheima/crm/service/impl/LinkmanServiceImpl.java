package com.itheima.crm.service.impl;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.transaction.annotation.Transactional;

import com.itheima.crm.dao.ILinkmanDao;
import com.itheima.crm.domain.Customer;
import com.itheima.crm.domain.Linkman;
import com.itheima.crm.domain.PageBean;
import com.itheima.crm.service.ILinkmanService;

@Transactional
public class LinkmanServiceImpl implements ILinkmanService {
	private ILinkmanDao linkmanDao;

	public void setLinkmanDao(ILinkmanDao linkmanDao) {
		this.linkmanDao = linkmanDao;
	}

	@Override
	public PageBean<Linkman> findAll(DetachedCriteria detachedCriteria, Integer currPage, Integer pageSize) {
		PageBean<Linkman> pageBean = new PageBean<Linkman>();
		pageBean.setCurrPage(currPage);
		pageBean.setPageSize(pageSize);
		Integer totalCount = linkmanDao.findCount(detachedCriteria);
		pageBean.setTotalCount(totalCount);
		Double doubleValue = totalCount.doubleValue();
		System.out.println(pageSize);
		Double ceil = Math.ceil(doubleValue / pageSize);
		pageBean.setTotalPage(ceil.intValue());
		// 封裝每页显示数据的集合
		Integer begin = (currPage - 1) * pageSize;
		List<Linkman> list = linkmanDao.findAll(detachedCriteria, begin, pageSize);
		pageBean.setList(list);
		return pageBean;
	}

	@Override
	public void save(Linkman linkman) {
		this.linkmanDao.save(linkman);
	}

	@Override
	public Linkman findById(Long lkm_id) {
		return this.linkmanDao.findById(lkm_id);
	}

	@Override
	public void update(Linkman linkman) {
		this.linkmanDao.update(linkman);
	}

	@Override
	public void delete(Linkman linkman) {
		this.linkmanDao.delete(linkman);
	}

}
