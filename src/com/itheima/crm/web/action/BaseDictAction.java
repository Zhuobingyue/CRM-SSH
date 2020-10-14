package com.itheima.crm.web.action;

import java.io.IOException;
import java.util.List;
import org.apache.struts2.ServletActionContext;
import com.itheima.crm.domain.BaseDict;
import com.itheima.crm.service.IBaseDictService;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import net.sf.json.JSONArray;
import net.sf.json.JsonConfig;

public class BaseDictAction extends ActionSupport implements ModelDriven<BaseDict> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private BaseDict baseDict = new BaseDict();
//	private BaseDict baseDict;
	private IBaseDictService baseDictService;

	@Override
	public BaseDict getModel() {
		return this.baseDict;
	}

	public void setBaseDictService(IBaseDictService baseDictService) {
		this.baseDictService = baseDictService;
	}

	public String findByTypeCode() {
		System.out.println(baseDict.toString() + "---");
		List<BaseDict> findByTypeCode = baseDictService.findByTypeCode(baseDict.getDict_type_code());
		/* jacklib、facsjson */
		JsonConfig config = new JsonConfig();
		config.setExcludes(new String[] { "dict_sort", "dict_enabl", "dict_meno" });
		JSONArray jsonArray = JSONArray.fromObject(findByTypeCode, config);
		System.out.println(jsonArray.toString());
		ServletActionContext.getResponse().setContentType("text/html;charset=utf-8");
		try {
			ServletActionContext.getResponse().getWriter().print(jsonArray.toString());
		} catch (IOException e) {
			e.printStackTrace();
		}
		return NONE;
	}
}
