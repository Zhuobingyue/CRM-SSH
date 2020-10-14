package com.itheima.crm.dao.impl;

import java.util.List;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Projections;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import com.itheima.crm.dao.ILinkmanDao;
import com.itheima.crm.domain.Linkman;

public class LinkmanDaoImpl extends HibernateDaoSupport implements ILinkmanDao {

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

	@SuppressWarnings("unchecked")
	@Override
	public List<Linkman> findAll(DetachedCriteria detachedCriteria, Integer begin, Integer pageSize) {
		detachedCriteria.setProjection(null);
		return (List<Linkman>) this.getHibernateTemplate().findByCriteria(detachedCriteria, begin, pageSize);
	}

	@Override
	public void save(Linkman linkman) {
		this.getHibernateTemplate().save(linkman);
	}

	@Override
	public Linkman findById(Long lkm_id) {
		return (Linkman) this.getHibernateTemplate().get(Linkman.class, lkm_id);
	}

	@Override
	public void update(Linkman linkman) {
		this.getHibernateTemplate().update(linkman);
	}

	@Override
	public void delete(Linkman linkman) {
		this.getHibernateTemplate().delete(linkman);
	}

}
