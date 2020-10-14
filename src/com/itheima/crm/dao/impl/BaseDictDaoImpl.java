package com.itheima.crm.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.itheima.crm.dao.IBaseDictDao;
import com.itheima.crm.domain.BaseDict;

public class BaseDictDaoImpl extends HibernateDaoSupport implements IBaseDictDao {

	@SuppressWarnings("unchecked")
	@Override
	public List<BaseDict> findByTypeCode(String dict_type_code) {
		return (List<BaseDict>) this.getHibernateTemplate().find("from BaseDict where dict_type_code = ?", dict_type_code);
	}

}
