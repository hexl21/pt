package xl.sys.user.controller;


import javax.servlet.http.HttpServletRequest;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import xl.mvc.controller.BaseController;
import xl.sys.dictionary.domain.DictionaryServiceI;
import xl.sys.user.domain.UserServiceI;
import xl.sys.user.model.User;

import com.alibaba.fastjson.JSON;
import com.xl.framework.constant.GlobalConstant;
import com.xl.pageModel.base.Grid;
import com.xl.pageModel.base.Json;
import com.xl.pageModel.base.PageFilter;
import com.xl.pageModel.base.SessionInfo;
import com.xl.service.base.ServiceException;

@Controller
@RequestMapping("/user")
public class UserController extends BaseController {

	@Autowired
	private UserServiceI userService;
	
	@Autowired
	private DictionaryServiceI dictionaryService;

	
	@RequestMapping("/manager")
	public String manager(HttpServletRequest request) {
		request.setAttribute("usertypeJson",JSON.toJSONString(dictionaryService.combox("usertype")));
		return "/admin/user";
	}
	
	@RequestMapping("/dataGrid")
	@ResponseBody
	public Grid dataGrid(User user, PageFilter ph) {
		Grid grid = new Grid();
		grid.setRows(userService.dataGrid(user, ph));
		grid.setTotal(userService.count(user, ph));
		return grid;
	}
	
	
	@RequestMapping("/editPwdPage")
	public String editPwdPage(HttpServletRequest request) {
		return "/admin/userEditPwd";
	}
	
	@RequestMapping("/editUserPwd")
	@ResponseBody
	public Json editUserPwd(HttpServletRequest request,String oldPwd, String pwd) {
		SessionInfo sessionInfo = (SessionInfo) request.getSession().getAttribute(GlobalConstant.SESSION_INFO);
		Json j = new Json();
		try {
			userService.editUserPwd(sessionInfo, oldPwd, pwd);
			j.setSuccess(true);
			j.setMsg("密码修改成功！");
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
	@RequestMapping("/addPage")
	public String addPage(HttpServletRequest request) {
		request.setAttribute("sexList", GlobalConstant.sexlist);
		return "/admin/userAdd";
	}
	
	@RequestMapping("/add")
	@ResponseBody
	public Json add(User user) {
		Json j = new Json();
		User u = userService.getByLoginName(user);
		if(u!=null){
			j.setMsg("用户名已存在!");
		}else{
			try {
				userService.add(user);
				j.setSuccess(true);
				j.setMsg("添加成功！");
			} catch (Exception e) {
				j.setMsg(e.getMessage());
			}
			
		}
		return j;
	}
	
	@RequestMapping("/get")
	@ResponseBody
	public User get(String id) {
		return userService.get(id);
	}
	
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(String id) {
		Json j = new Json();
		try {
			userService.delete(id);
			j.setMsg("删除成功！");
			j.setSuccess(true);
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}
	
	@RequestMapping("/editPage")
	public String editPage(HttpServletRequest request, String id) {
		User u = userService.get(id);
		request.setAttribute("user", u);
		request.setAttribute("sexList", GlobalConstant.sexlist);
		return "/admin/userEdit";
	}
	
	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(User user) {
		Json j = new Json();
		try {
			userService.edit(user);
			j.setSuccess(true);
			j.setMsg("编辑成功！");
		} catch (ServiceException e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}

}
