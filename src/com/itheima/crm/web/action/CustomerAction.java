package com.itheima.crm.web.action;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;

import com.itheima.crm.domain.Customer;
import com.itheima.crm.domain.PageBean;
import com.itheima.crm.service.ICustomerService;
import com.itheima.crm.utils.UploadUtils;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class CustomerAction extends ActionSupport implements ModelDriven<Customer> {

	private Customer customer = new Customer();
	private ICustomerService customerService;
	private Integer currPage;
	private Integer pageSize;
	private String uploadFileName; // 文件名称
	private File upload; // 上传文件
	private String uploadContentType; // 文件类型

	public void setCustomer(Customer customer) {
		this.customer = customer;
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

	@Override
	public Customer getModel() {
		return this.customer;
	}

	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}

	public void setUpload(File upload) {
		this.upload = upload;
	}

	public void setUploadContentType(String uploadContentType) {
		this.uploadContentType = uploadContentType;
	}

	public String saveUI() {
		return "saveUI";
	}

	public String save() throws IOException {
		if (upload != null) {
			String path = "D:/upload";
			String uuidFileName = UploadUtils.getUuidFileName(uploadFileName);
			String realPath = UploadUtils.getPath(uuidFileName);
			String url = path + realPath;
			File file = new File(url);
			if (!file.exists()) {
				file.mkdirs();
			}
			File dictFile = new File(url + "/" + uuidFileName);
			FileUtils.copyFile(upload, dictFile);
			customer.setCust_image(url + "/" + uuidFileName);
		}
		customerService.save(customer);
		return "saveSuccess";
	}

	public String findAll() {
		// 分页
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(Customer.class);
		// 设置条件
		if (customer.getCust_name() != null) {
			detachedCriteria.add(Restrictions.like("cust_name", "%" + customer.getCust_name() + "%"));
		}
		PageBean<Customer> pageBean = customerService.findByPage(detachedCriteria, currPage, pageSize);
		ActionContext.getContext().getValueStack().push(pageBean);
		return "findAll";
	}

	public String delete() {
		customer = customerService.findById(customer.getCust_id());
		if (customer.getCust_image() != null) {
			File file = new File(customer.getCust_image());
			file.delete();
		}
		customerService.delete(customer);
		return "deleteSuccess";
	}

	public String edit() {
		customer = customerService.findById(customer.getCust_id());
		System.out.println(customer.getBaseDictLevel().getDict_id());
		// 手动压栈
		ActionContext.getContext().getValueStack().push(customer);
		return "editSuccess";
	}

	public String update() throws IOException {
		if (upload != null) {
			String cust_image = customer.getCust_image();
			if (cust_image != null || !"".equals(cust_image)) {
				if (cust_image == null) {
					uploadImage();
				}
				File file = new File(customer.getCust_image());
				file.delete();
			}
			uploadImage();
		}
		customerService.update(customer);
		return "updateSuccess";
	}

	public void uploadImage() throws IOException {
		String path = "D:/upload";
		String uuidFileName = UploadUtils.getUuidFileName(uploadFileName);
		String realPath = UploadUtils.getPath(uuidFileName);
		String url = path + realPath;
		File file = new File(url);
		if (!file.exists()) {
			file.mkdirs();
		}
		File dictFile = new File(url + "/" + uuidFileName);
		FileUtils.copyFile(upload, dictFile);
		customer.setCust_image(url + "/" + uuidFileName);
	}
}
