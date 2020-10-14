package com.itheima.crm.service.impl;

import org.springframework.transaction.annotation.Transactional;
import com.itheima.crm.dao.IUserDao;
import com.itheima.crm.domain.User;
import com.itheima.crm.service.IUserService;
import com.itheima.crm.utils.MD5Utils;

@Transactional(readOnly = false)
public class UserServiceImpl implements IUserService {
	private IUserDao userDao;

	public void setUserDao(IUserDao userDao) {
		this.userDao = userDao;
	}

	@Override
	public void regist(User user) {

		// 加密密码
		user.setUser_password(MD5Utils.md5(user.getUser_password()));
		user.setUser_state("1");
		userDao.regist(user);
	}

	@Override
	public User login(User user) {
		// 加密密码
		user.setUser_password(MD5Utils.md5(user.getUser_password()));
		return userDao.login(user);
	}

}
