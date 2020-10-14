package com.itheima.crm.dao;

import java.util.List;

import com.itheima.crm.domain.BaseDict;

public interface IBaseDictDao {

	List<BaseDict> findByTypeCode(String dict_type_code);

}
