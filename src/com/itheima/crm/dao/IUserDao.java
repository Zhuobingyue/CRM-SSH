package com.itheima.crm.dao;

import com.itheima.crm.domain.User;

public interface IUserDao {
	void regist(User user);

	User login(User user);
}
