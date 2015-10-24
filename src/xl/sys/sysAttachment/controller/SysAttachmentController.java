package xl.sys.sysAttachment.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Date;
import java.util.Iterator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.xl.pageModel.base.Json;

import xl.mvc.controller.BaseController;
import xl.sys.sysAttachment.domain.SysAttachmentServiceI;
import xl.sys.sysAttachment.model.SysAttachment;



@Controller
@RequestMapping("/sysAttachment")
public class SysAttachmentController extends BaseController {

	@Autowired
	private SysAttachmentServiceI sysArrachmentService;
	
	@RequestMapping("/manager")
	public String manager() {
		return "/sysAttachent/sysAttachent_list";
	}
	
	@RequestMapping("/save")
	@ResponseBody
	public void save(String id,String tableName, HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		if (multipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			String filePath = request.getSession().getServletContext()
					.getRealPath("/WEB-INF/upload/temp");
			File file = new File(filePath);
			if (!file.exists()) {
				file.mkdir();
			}
			Iterator<String> iter = multiRequest.getFileNames();
			while (iter.hasNext()) {
				MultipartFile f = multiRequest.getFile((String) iter.next());
				if (file != null) {
					String fileName = f.getOriginalFilename();
					String path = filePath + "\\" + fileName;
					File localFile = new File(path);
					f.transferTo(localFile);
					SysAttachment sa = new SysAttachment();
					sa.setTableId(id);
					sa.setTableName(tableName);
					sa.setFileName(fileName);
					sa.setCreatedtime(new Date());
					InputStream in = null;
		            in = new FileInputStream(path);
		            byte[] b = new byte[in.available()];
		            in.read(b);
		            in.close();
		            sa.setFileContent(b);
		            sysArrachmentService.add(sa);
		            localFile.delete();
				}
			}
		}
	}
	
	@RequestMapping("/get")
	@ResponseBody
	public void get(String id, HttpServletRequest request, 
			HttpServletResponse response)throws Exception {
		SysAttachment sa = sysArrachmentService.get(id);
		String fileName = sa.getFileName();
		byte[] fileContent = sa.getFileContent();
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
			response.setContentType("application/x-msdownload;");
			response.setHeader("Content-Disposition", "attachment;filename="
					+ new String(fileName.getBytes("gb2312"), "ISO8859-1"));
			response.setHeader("Content-Length", String.valueOf(fileContent.length));
		BufferedOutputStream bos = new BufferedOutputStream(response.getOutputStream());
		bos.write(fileContent, 0, fileContent.length);
		bos.close();
	}
	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(String id)throws Exception {
		Json j = new Json();
			sysArrachmentService.delete(id);
			j.setSuccess(true);
			j.setMsg("删除成功！");
		return j;
	}
}
