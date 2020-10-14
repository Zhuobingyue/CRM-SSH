package com.itheima.crm.web.action;

import org.apache.struts2.ServletActionContext;

import com.itheima.crm.domain.User;
import com.itheima.crm.service.IUserService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class UserAction extends ActionSupport implements ModelDriven<User> {

	private User user = new User();
	private IUserService userService;

	@Override
	public User getModel() {
		return user;
	}

	public void setUserService(IUserService userService) {
		this.userService = userService;
	}

	public String regist() {
		userService.regist(user);
		return LOGIN;
	}

	public String login() {
		System.out.println(user.toString());
		User existUser = userService.login(user);
		if (existUser != null) {
//			ServletActionContext.getRequest().getSession().setAttribute("existUser", existUser);
			ActionContext.getContext().getSession().put("existUser", existUser);
			return SUCCESS;
		} else {
			this.addActionError("用户名或密码错误！");
			return LOGIN;
		}
	}
}
