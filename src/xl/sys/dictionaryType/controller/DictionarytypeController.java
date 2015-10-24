package xl.sys.dictionaryType.controller;

import java.util.List;

import javax.servlet.http.HttpSession;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import xl.mvc.controller.BaseController;
import xl.sys.dictionaryType.domain.DictionarytypeServiceI;
import xl.sys.dictionaryType.model.Dictionarytype;

import com.xl.pageModel.base.Json;
import com.xl.pageModel.base.Tree;

@Controller
@RequestMapping("/dictionarytype")
public class DictionarytypeController extends BaseController {

	@Autowired
	private DictionarytypeServiceI dictionarytypeService;

	
	@RequestMapping("/tree")
	@ResponseBody
	public List<Tree> tree(HttpSession session) {
		return dictionarytypeService.tree();
	}

	@RequestMapping("/add")
	@ResponseBody
	public Json add(Dictionarytype dictionarytype) {
		Json j = new Json();
		try {
			dictionarytypeService.add(dictionarytype);
			j.setSuccess(true);
			j.setMsg("添加成功！");
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}

	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(Long id) {
		Json j = new Json();
		dictionarytypeService.delete(id);
		j.setMsg("删除成功！");
		j.setSuccess(true);
		return j;
	}

	@RequestMapping("/get")
	@ResponseBody
	public Dictionarytype get(Long id)  {
		return dictionarytypeService.get(id);
	}

	@RequestMapping("/edit")
	@ResponseBody
	public Json edit(Dictionarytype dictionarytype) {
		Json j = new Json();
		try {
			dictionarytypeService.edit(dictionarytype);
			j.setSuccess(true);
			j.setMsg("编辑成功！");
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}

}
