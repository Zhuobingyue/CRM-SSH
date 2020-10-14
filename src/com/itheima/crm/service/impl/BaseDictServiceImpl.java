package com.itheima.crm.service.impl;

import java.util.List;

import com.itheima.crm.dao.IBaseDictDao;
import com.itheima.crm.domain.BaseDict;
import com.itheima.crm.service.IBaseDictService;

public class BaseDictServiceImpl implements IBaseDictService {
	private IBaseDictDao baseDictDao;

	public void setBaseDictDao(IBaseDictDao baseDictDao) {
		this.baseDictDao = baseDictDao;
	}

	@Override
	public List<BaseDict> findByTypeCode(String dict_type_code) {
		return baseDictDao.findByTypeCode(dict_type_code);
	}

}
