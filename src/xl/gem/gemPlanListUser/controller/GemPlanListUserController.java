package xl.gem.gemPlanListUser.controller;



import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.xl.pageModel.base.Grid;
import com.xl.pageModel.base.Json;
import com.xl.pageModel.base.PageFilter;
import com.xl.pageModel.base.SessionInfo;

import xl.gem.gemPlanListUser.domain.GemPlanListUserServiceI;
import xl.gem.gemPlanListUser.model.GemPlanListUser;
import xl.mvc.controller.BaseController;


@Controller
@RequestMapping("/gemPlanListUser")
public class GemPlanListUserController extends BaseController {

	@Autowired
	private GemPlanListUserServiceI gemPlanListUserService;
	
	
	@RequestMapping("/gemPlanListUser_list")
	public String manager(HttpSession session,HttpServletRequest request) {
		SessionInfo sessionInfo = (SessionInfo)session.getAttribute("sessionInfo");
		request.setAttribute("gemPlanStrList", gemPlanListUserService.getPlanStrList(sessionInfo));
		return "/gem/gemPlanListUser/gemPlanListUser_list";
	}
	@RequestMapping("/gemPlanListUser_list2")
	public String manager2(HttpSession session,HttpServletRequest request) {
		SessionInfo sessionInfo = (SessionInfo)session.getAttribute("sessionInfo");
		request.setAttribute("gemPlanStrList", gemPlanListUserService.getPlanStrList2(sessionInfo));
		return "/gem/gemPlanListUser/gemPlanListUser_list2";
	}
	
	@RequestMapping("/dataGrid")
	@ResponseBody
	public Grid dataGrid(HttpServletRequest request,GemPlanListUser gemPlanListUser, PageFilter ph,HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo)session.getAttribute("sessionInfo");
		gemPlanListUser.setSysUserId(sessionInfo.getId());
		Grid grid = new Grid();
		grid.setRows(gemPlanListUserService.dataGrid(gemPlanListUser, ph));
		grid.setTotal(gemPlanListUserService.count(gemPlanListUser, ph));
		return grid;
	}
	@RequestMapping("/dataGrid2")
	@ResponseBody
	public Grid dataGrid2(HttpServletRequest request,GemPlanListUser gemPlanListUser, PageFilter ph,HttpSession session) {
		SessionInfo sessionInfo = (SessionInfo)session.getAttribute("sessionInfo");
		gemPlanListUser.setSysUserId(sessionInfo.getId());
		Grid grid = new Grid();
		grid.setRows(gemPlanListUserService.dataGrid2(gemPlanListUser, ph));
		grid.setTotal(gemPlanListUserService.count2(gemPlanListUser, ph));
		return grid;
	}
	@RequestMapping("/saveScore")
	@ResponseBody
	public Json saveScore(String id,String score,String gemPlanListId) {
			Json j = new Json();
			try {
				gemPlanListUserService.saveScore(id, score, gemPlanListId);
				j.setSuccess(true);
				j.setMsg("执行成功！");
			} catch (Exception e) {
				j.setMsg(e.getMessage());
			}
			return j;
		}
	@RequestMapping("/saveScore2")
	@ResponseBody
	public Json saveScore2(String param) {
		String id = "";
		String score = "";
		String gemPlanListId = "";
		Json j = new Json();
		if(param!=null){
			String[] arr = param.split("@");
			for (int i = 0; i < arr.length; i++) {
				String[] str = arr[i].split(";");
				id =  str[0];
				score = str[1];
				gemPlanListId = str[2];
				gemPlanListUserService.saveScore(id, score, gemPlanListId);
			}
		}
		try {
			j.setSuccess(true);
			j.setMsg("执行成功！");
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}
	@RequestMapping("/saveXtScore")
	@ResponseBody
	public Json saveXtScore(String id,String score,String gemPlanListId) {
			Json j = new Json();
			try {
				gemPlanListUserService.saveXtScore(id, score, gemPlanListId);
				j.setSuccess(true);
				j.setMsg("执行成功！");
			} catch (Exception e) {
				j.setMsg(e.getMessage());
			}
			return j;
		}
	@RequestMapping("/saveXtScore2")
	@ResponseBody
	public Json saveXtScore2(String param) {
		String id = "";
		String score = "";
		String gemPlanListId = "";
		Json j = new Json();
		if(param!=null){
			String[] arr = param.split("@");
			for (int i = 0; i < arr.length; i++) {
				String[] str = arr[i].split(";");
				id =  str[0];
				score = str[1];
				gemPlanListId = str[2];
				gemPlanListUserService.saveXtScore(id, score, gemPlanListId);
			}
		}
		try {
			j.setSuccess(true);
			j.setMsg("执行成功！");
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}
	@RequestMapping("/gemPlanListUserSummary_list")
	public String manager(HttpServletRequest request,String id) {
		request.setAttribute("id",id);
		return "/gem/gemPlanListUser/gemPlanListUser_summary";
	}
	@RequestMapping("/gemPlanListUserSummary_data")
	@ResponseBody
	public List<List> gemPlanListUserSummary(String id) {
		return gemPlanListUserService.gemPlanListUserSummary(id);
	}
}
