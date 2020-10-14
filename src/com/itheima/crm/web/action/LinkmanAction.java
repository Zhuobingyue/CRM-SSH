package com.itheima.crm.web.action;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.itheima.crm.domain.Customer;
import com.itheima.crm.domain.Linkman;
import com.itheima.crm.domain.PageBean;
import com.itheima.crm.service.ICustomerService;
import com.itheima.crm.service.ILinkmanService;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class LinkmanAction extends ActionSupport implements ModelDriven<Linkman> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Linkman linkman = new Linkman();
	private ILinkmanService linkmanService;
	private ICustomerService customerService;
	private Integer currPage;
	private Integer pageSize;

	public void setLinkman(Linkman linkman) {
		this.linkman = linkman;
	}

	@Override
	public Linkman getModel() {
		return this.linkman;
	}

	public void setLinkmanService(ILinkmanService linkmanService) {
		this.linkmanService = linkmanService;
	}

	public void setCustomerService(ICustomerService customerService) {
		this.customerService = customerService;
	}

	public void setCurrPage(Integer currPage) {
		if (currPage == null) {
			currPage = 1;
		}
		this.currPage = currPage;
	}

	public void setPageSize(Integer pageSize) {
		if (pageSize == null) {
			pageSize = 3;
		}
		this.pageSize = pageSize;
	}

	public String findAll() {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Linkman.class);
		if (linkman.getLkm_name() != null) {
			detachedCriteria.add(Restrictions.like("lkm_name", "%" + linkman.getLkm_name() + "%"));
		}
		PageBean<Linkman> pageBean = linkmanService.findAll(detachedCriteria, currPage, pageSize);
		ActionContext.getContext().getValueStack().push(pageBean);
		return "findAll";
	}

	public String saveUI() {
		List<Customer> list = customerService.findAll();
		ActionContext.getContext().getValueStack().set("list", list);
		return "saveUI";
	}

	public String save() {
		linkmanService.save(linkman);
		return "saveSuccess";
	}

	public String edit() {
		List<Customer> list = customerService.findAll();
		linkman = linkmanService.findById(linkman.getLkm_id());
		ActionContext.getContext().getValueStack().set("list", list);
		ActionContext.getContext().getValueStack().push(linkman);
		return "editSuccess";
	}

	public String update() {
		linkmanService.update(linkman);
		return "updateSuccess";
	}

	public String delete() {
		linkman = linkmanService.findById(linkman.getLkm_id());
		linkmanService.delete(linkman);
		return "deleteSuccess";
	}
}
