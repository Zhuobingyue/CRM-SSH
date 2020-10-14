package com.itheima.crm.dao.impl;

import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import com.itheima.crm.dao.IUserDao;
import com.itheima.crm.domain.User;

public class UserDaoImpl extends HibernateDaoSupport implements IUserDao {

	@Override
	public void regist(User user) {
		this.getHibernateTemplate().save(user);
	}

	@Override
	public User login(User user) {
		@SuppressWarnings("unchecked")
		List<User> list = (List<User>) this.getHibernateTemplate().find(
				"FROM User WHERE user_code= ? AND user_password = ?", user.getUser_code(), user.getUser_password());
		if (list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

}
