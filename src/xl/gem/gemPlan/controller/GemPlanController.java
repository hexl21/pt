package xl.gem.gemPlan.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;
import com.xl.pageModel.base.Grid;
import com.xl.pageModel.base.Json;
import com.xl.pageModel.base.PageFilter;
import com.xl.pageModel.base.ReturnValue;
import com.xl.pageModel.base.SessionInfo;

import xl.gem.gemPlan.domain.GemPlanServiceI;
import xl.gem.gemPlan.model.GemPlan;
import xl.mvc.controller.BaseController;

@Controller
@RequestMapping("/gemPlan")
public class GemPlanController extends BaseController {

	@Autowired
	private GemPlanServiceI gemPlanService;

	@RequestMapping("/gem_list")
	public String manager(HttpSession session, String gemPlanHeadId,
			HttpServletRequest request) {
		SessionInfo sessionInfo = (SessionInfo) session
				.getAttribute("sessionInfo");
		request.setAttribute("modelList", gemPlanService
				.getModelList(sessionInfo));
		request.setAttribute("gemPlanHeadId", gemPlanHeadId);
		return "/gem/gemPlan/gemPlan_list";
	}

	@RequestMapping("/gem_query")
	public String managerQuery(HttpSession session, String gemPlanHeadId,
			HttpServletRequest request) {
		SessionInfo sessionInfo = (SessionInfo) session
				.getAttribute("sessionInfo");
		request.setAttribute("modelList", gemPlanService
				.getModelList(sessionInfo));
		request.setAttribute("gemPlanHeadId", gemPlanHeadId);
		return "/gem/gemPlan/gemPlan_query";
	}

	@RequestMapping("/gem_list_query")
	public String query(HttpSession session, String gemPlanHeadId,
			HttpServletRequest request) {
		SessionInfo sessionInfo = (SessionInfo) session
				.getAttribute("sessionInfo");
		request.setAttribute("modelList", gemPlanService
				.getModelList(sessionInfo));
		request.setAttribute("gemPlanHeadId", gemPlanHeadId);
		return "/gem/gemPlan/gem_list_query";
	}

	@RequestMapping("/dataGrid")
	@ResponseBody
	public Grid dataGrid(HttpSession session, GemPlan gemPlan, PageFilter ph,
			String gemPlanHeadId) {
		SessionInfo sessionInfo = (SessionInfo) session
				.getAttribute("sessionInfo");
		gemPlan.setGemPlanHeadId(gemPlanHeadId);
		Grid grid = new Grid();
		grid.setRows(gemPlanService.dataGrid(gemPlan, ph, sessionInfo));
		grid.setTotal(gemPlanService.count(gemPlan, ph, sessionInfo));
		return grid;
	}

	@RequestMapping("/dataGrid2")
	@ResponseBody
	public Grid dataGrid2(HttpSession session, GemPlan gemPlan, PageFilter ph,
			String gemPlanHeadId) {
		SessionInfo sessionInfo = (SessionInfo) session
				.getAttribute("sessionInfo");
		gemPlan.setGemPlanHeadId(gemPlanHeadId);
		Grid grid = new Grid();
		grid.setRows(gemPlanService.dataGrid2(gemPlan, ph, sessionInfo));
		grid.setTotal(gemPlanService.count2(gemPlan, ph, sessionInfo));
		return grid;
	}

	@RequestMapping("/addPage")
	public String addPage(HttpSession session, HttpServletRequest request) {
		SessionInfo sessionInfo = (SessionInfo) session
				.getAttribute("sessionInfo");
		request.setAttribute("modelList", gemPlanService
				.getModelList(sessionInfo));
		return "/gem/gemPlan/gemPlan_add";
	}

	// 如果只是上传一个文件，则只需要MultipartFile类型接收文件即可，而且无需显式指定@RequestParam注解
	// 如果想上传多个文件，那么这里就要用MultipartFile[]类型来接收文件，并且还要指定@RequestParam注解
	// 并且上传多个文件时，前台表单中的所有<input
	// type="file"/>的name都应该是myfiles，否则参数里的myfiles无法获取到所有上传的文件
	// @RequestMapping(value = "/add", method = RequestMethod.POST)
	// @ResponseBody
	// public Json add(GemPlan gemPlan, @RequestParam
	// MultipartFile[] myfiles, HttpServletRequest request) throws IOException {
	//
	// Json j = new Json();
	// try {
	// String id = gemPlanService.add(gemPlan,);
	// saveUpLoadFile(myfiles, request, id);
	// j.setSuccess(true);
	// j.setMsg("添加成功！");
	// j.setStr(id);
	// } catch (Exception e) {
	// j.setMsg(e.getMessage());
	// }
	// return j;
	// }

	@RequestMapping(value = "/add2", method = RequestMethod.POST)
	@ResponseBody
	public Json add2(HttpSession session, GemPlan gemPlan,
			HttpServletRequest request) throws IOException {
		SessionInfo sessionInfo = (SessionInfo) session
				.getAttribute("sessionInfo");
		Json j = new Json();
		try {
			String id = gemPlanService.add(gemPlan, sessionInfo);
			request.setAttribute("id", id);
			j.setSuccess(true);
			j.setMsg("添加成功！");
			j.setStr(id);
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}

	@RequestMapping("/addFilePage")
	public String addFilePage(String id, HttpServletRequest request) {
		request.setAttribute("id", id);
		return "/gem/gemPlan/gemPlan_file_add";
	}

	@RequestMapping(value = "/addFileList", method = RequestMethod.POST)
	@ResponseBody
	public Json addFileList(String id, @RequestParam
	MultipartFile[] myfiles, HttpServletRequest request) {
		Json j = new Json();
		try {
			saveUpLoadFile(myfiles, request, id);
			j.setSuccess(true);
			j.setMsg("上传成功！");
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}

	@RequestMapping("/saveFile")
	@ResponseBody
	public void saveFile(GemPlan gemPlan, HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		if (multipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			String realPath = request.getSession().getServletContext()
					.getRealPath("/WEB-INF/upload");
			String fileRealPath = realPath + "\\" + gemPlan.getId();
			File file = new File(fileRealPath);
			if (!file.exists()) {
				file.mkdir();
			}
			Iterator<String> iter = multiRequest.getFileNames();
			while (iter.hasNext()) {
				MultipartFile f = multiRequest.getFile((String) iter.next());
				if (file != null) {
					String fileName = f.getOriginalFilename();
					String path = fileRealPath + "/" + fileName;
					File localFile = new File(path);
					f.transferTo(localFile);
					gemPlanService.saveUpLoadFile(gemPlan.getId(), f);
				}

			}
		}
	}

	@RequestMapping("/addFileList2")
	@ResponseBody
	public void addFileList2(String id, HttpServletResponse response,
			HttpServletRequest request) throws Exception {
		CommonsMultipartResolver multipartResolver = new CommonsMultipartResolver(
				request.getSession().getServletContext());
		if (multipartResolver.isMultipart(request)) {
			MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
			String realPath = request.getSession().getServletContext()
					.getRealPath("/WEB-INF/upload");
			String fileRealPath = realPath + "\\" + id;
			File file = new File(fileRealPath);
			if (!file.exists()) {
				file.mkdir();
			}
			Iterator<String> iter = multiRequest.getFileNames();
			while (iter.hasNext()) {
				MultipartFile f = multiRequest.getFile((String) iter.next());
				if (file != null) {
					String fileName = f.getOriginalFilename();
					String path = fileRealPath + "\\" + fileName;
					File localFile = new File(path);
					f.transferTo(localFile);
					ReturnValue rv = gemPlanService.saveUpLoadFile(id, f);
				}

			}
		}
	}

	@RequestMapping("/delete")
	@ResponseBody
	public Json delete(String id, HttpServletRequest request) {
		Json j = new Json();
		try {
			if (id != null) {
				String[] idsStr = id.split("@");
				for (String idstr : idsStr) {
					gemPlanService.delete(idstr);
				}
				j.setMsg("删除成功！");
				j.setSuccess(true);
			}
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}

	@RequestMapping("/toGemPlanListDetail")
	public String toGemPlanListDetail(HttpServletRequest request, String id) {
		request.setAttribute("id", id);
		return "/gem/gemPlan/gemPlanList_list";
	}

	@RequestMapping("/detailTreeGrid")
	@ResponseBody
	public List<GemPlan> treeGrid(String id) {
		return gemPlanService.detailTreeGrid(id);
	}

	private void saveUpLoadFile(MultipartFile[] myfiles,
			HttpServletRequest request, String id) throws IOException {
		for (MultipartFile myfile : myfiles) {
			if (!myfile.isEmpty()) {
				// 如果用的是Tomcat服务器，则文件会上传到\\%TOMCAT_HOME%\\webapps\\YourWebProject\\WEB-INF\\upload\\文件夹中
				String realPath = request.getSession().getServletContext()
						.getRealPath("/WEB-INF/upload");
				String fileRealPath = realPath + "\\" + id;
				File file = new File(fileRealPath);
				if (!file.exists()) {
					file.mkdir();
				}
				File saveFile = new File(fileRealPath, myfile
						.getOriginalFilename());
				if (!saveFile.exists()) {
					FileUtils.copyInputStreamToFile(myfile.getInputStream(),
							saveFile);
					gemPlanService.saveUpLoadFile(id, myfile);
				}
				// 这里不必处理IO流关闭的问题，因为FileUtils.copyInputStreamToFile()方法内部会自动把用到的IO流关掉，我是看它的源码才知道的
			}
		}
	}

	@RequestMapping("delFileList")
	@ResponseBody
	public Json delFileList(String id, String gemPlanId, String fileName,
			HttpServletRequest request) {
		Json j = new Json();
		try {
			String realPath = request.getSession().getServletContext()
					.getRealPath("/WEB-INF/upload");
			String fileRealPath = realPath + "\\" + gemPlanId + "\\" + fileName;
			gemPlanService.deleteSysAtt(id);
			File file = new File(fileRealPath);
			deleteFile(file);
			j.setSuccess(true);
			j.setMsg("执行成功！");
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}

	@RequestMapping("/toListFilePage")
	public String toListFilePage(String id, HttpServletRequest request) {
		request.setAttribute("id", id);
		return "/gem/gemPlan/gemPlan_file_list";
	}

	@RequestMapping("/dataGridFile")
	@ResponseBody
	public Grid dataGridFile(GemPlan gemPlan, String id, PageFilter ph) {
		if (gemPlan != null && id == null) {
			id = gemPlan.getGemPlanId();
		}
		Grid grid = new Grid();
		grid.setRows(gemPlanService.dataGridFile(id, ph));
		grid.setTotal(gemPlanService.countFile(id, ph));
		return grid;
	}

	@RequestMapping("/downLoadFile")
	@ResponseBody
	public void downLoadFile(String id, String fileName,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("UTF-8");
		java.io.BufferedInputStream bis = null;
		java.io.BufferedOutputStream bos = null;
		String realPath = request.getSession().getServletContext().getRealPath(
				"/WEB-INF/upload");
		String fileRealPath = realPath + "\\" + id + "\\" + fileName;
		try {
			long fileLength = new File(fileRealPath).length();
			response.setContentType("application/x-msdownload;");
			response.setHeader("Content-Disposition", "attachment;filename="
					+ new String(fileName.getBytes("gb2312"), "ISO8859-1"));
			response.setHeader("Content-Length", String.valueOf(fileLength));
			bis = new BufferedInputStream(new FileInputStream(fileRealPath));
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[2048];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			if (bis != null)
				bis.close();
			if (bos != null)
				bos.close();
		}
	}

	private void deleteFile(File file) {
		if (file.exists()) { // 判断文件是否存在
			if (file.isFile()) { // 判断是否是文件
				file.delete(); // delete()方法 你应该知道 是删除的意思;
			} else if (file.isDirectory()) { // 否则如果它是一个目录
				File files[] = file.listFiles(); // 声明目录下所有的文件 files[];
				for (int i = 0; i < files.length; i++) { // 遍历目录下所有的文件
					this.deleteFile(files[i]); // 把每个文件 用这个方法进行迭代
				}
			}
			file.delete();
		} else {
			System.out.println("所删除的文件不存在！" + '\n');
		}
	}

	@RequestMapping("/calculateZdScore")
	@ResponseBody
	public Json calculateZdScore(String ids, HttpServletRequest request) {
		Json j = new Json();
		try {
			if (ids != null) {
				ReturnValue rv = gemPlanService.calculateZdScore(ids);
				j.setMsg(rv.getReturnString());
				j.setSuccess(true);
			}
		} catch (Exception e) {
			j.setMsg(e.getMessage());
		}
		return j;
	}

	/**
	 * 徐修文修改
	 * 
	 * @param id
	 * @return
	 */

	@RequestMapping("/toGemPlanListDetailTable")
	public String toGemPlanListDetailTable(HttpServletRequest request, String id) {
		request.setAttribute("id", id);
		return "/gem/gemPlan/gemPlanListTable_list";
	}

	/**
	 * 徐修文修改
	 * 
	 * @param myfiles
	 * @param request
	 * @param id
	 * @throws IOException
	 */
	@RequestMapping("/detailTableGrid")
	@ResponseBody
	public List<GemPlan> tableGrid(String id) {
		return gemPlanService.detailTableGrid(id);
	}

	@RequestMapping("/gemPlanListSummary_list")
	public String gemPlanListSummary(HttpServletRequest request, String id) {
		request.setAttribute("id", id);
		return "/gem/gemPlan/gemPlanList_summary";
	}

	@RequestMapping("/gemPlanListSummary_data")
	@ResponseBody
	public List<List> gemPlanListUserSummary(String id) {
		return gemPlanService.gemPlanListSummary(id);
	}

	@RequestMapping(value = "/exportSummaryList")
	public void exportExcel(HttpServletRequest request,
			HttpServletResponse response, String id) throws Exception {
		HSSFWorkbook wb = export(id);
		String pName = "计算结果";
		response.reset();
		response.setContentType("application/x-msdownload");
		response.setHeader("Content-Disposition", "attachment; filename="
				+ new String(pName.getBytes("gb2312"), "ISO-8859-1") + ".xls");
		OutputStream ouputStream = response.getOutputStream();
		wb.write(ouputStream);
		ouputStream.flush();
		ouputStream.close();
	}

	public HSSFWorkbook export(String id) {
		List<List> list = gemPlanService.gemPlanListSummary(id);
		String excelTitle = gemPlanService.getGemPlanScoreStr(id);
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("计算结果");
		sheet.autoSizeColumn(0);
		
		HSSFCellStyle headerStyle1 = (HSSFCellStyle) wb .createCellStyle();// 创建标题样式1  
		headerStyle1.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  
		headerStyle1.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
		HSSFFont headerFont1 = (HSSFFont) wb.createFont();  
		headerFont1.setColor(HSSFFont.COLOR_RED);
		headerFont1.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 字体加粗  
		headerFont1.setFontName("Times New Roman");  
		headerFont1.setFontHeightInPoints((short) 12);  
		headerStyle1.setFont(headerFont1);  
		  
		HSSFCellStyle headerStyle2 = (HSSFCellStyle) wb .createCellStyle();// 创建标题样式2  
		headerStyle2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);  
		headerStyle2.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
		HSSFFont headerFont2 = (HSSFFont) wb.createFont();  
		headerFont2.setColor(HSSFFont.COLOR_RED);
		headerFont2.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);// 字体加粗  
		headerFont2.setFontName("Times New Roman");  
		headerFont2.setFontHeightInPoints((short) 12);  
		headerStyle2.setFont(headerFont2);  
		headerStyle2.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框  
		headerStyle2.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框  
		headerStyle2.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框  
		headerStyle2.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框 
		
		
		HSSFCellStyle cellstyle = (HSSFCellStyle) wb .createCellStyle();// 设置字体样式  
		cellstyle.setAlignment(HSSFCellStyle.ALIGN_CENTER);  
		cellstyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 垂直对齐居中  
		cellstyle.setWrapText(true); // 设置为自动换行  
		HSSFFont cell_Font = (HSSFFont) wb.createFont();  
		cell_Font.setFontName("宋体");  
		cell_Font.setFontHeightInPoints((short) 10);  
		cellstyle.setFont(cell_Font);  
		cellstyle.setBorderBottom(HSSFCellStyle.BORDER_THIN); // 下边框  
		cellstyle.setBorderLeft(HSSFCellStyle.BORDER_THIN);// 左边框  
		cellstyle.setBorderTop(HSSFCellStyle.BORDER_THIN);// 上边框  
		cellstyle.setBorderRight(HSSFCellStyle.BORDER_THIN);// 右边框 
		//sheet.addMergedRegion(new CellRangeAddress(beginRow, endRow, beginCol, endCol)); 合并单元格
		
		
		List dataHead = (List) list.get(0);
		int  colTols = dataHead.size();
		//初始化列宽度
		for (int i = 0; i < colTols; i++) {
			int width = 0;
			if(i%2==0){
				width = 8000;
			}else{
				width = 2000;
			}
			sheet.setColumnWidth(i,width);
		}
		//标题
		HSSFRow row = sheet.createRow(0);
		row.setHeight((short) 500);
		HSSFCell cell_00 = row.createCell(0);
		cell_00.setCellValue(excelTitle);
		cell_00.setCellStyle(headerStyle1);
		sheet.addMergedRegion(new CellRangeAddress(0, (short)0, 0, (short)(colTols-1)));
		//数据头数据
		row = sheet.createRow(1);
		row.setHeight((short) 500);
		for (int j = 0; j < colTols; j++) {
			HSSFCell cell = row.createCell(j);
			cell.setCellValue(dataHead.get(j).toString());
			cell.setCellStyle(headerStyle2);
		}
		
		String[] temp = new String[20];//对比数组
		int[] tempRow = new int[20];//行数组
		int flag = 0; //跟随合并开关
		int currentRowIndex = 0;
		for (int i = 1; i < list.size(); i++) {
			currentRowIndex = i+1;
			row = sheet.createRow(currentRowIndex);
			row.setHeight((short) 500);
			List data = (List) list.get(i);
			for (int j = 0; j < data.size(); j++) {
				String cellValue = data.get(j).toString();
				HSSFCell cell = row.createCell(j);
				cell.setCellStyle(cellstyle);
				if (currentRowIndex == 2) {
					temp[j] = cellValue;
					tempRow[j] = currentRowIndex;
					cell.setCellValue(cellValue);
				} else {
					if (j % 2 == 0) {
						if (cellValue.equals(temp[j])) {
							cell.setCellValue("");
							flag = 1;
							sheet.addMergedRegion(new CellRangeAddress(tempRow[j], (short) currentRowIndex, j,(short) j));
						} else {
							temp[j] = cellValue;
							tempRow[j] = currentRowIndex;
							cell.setCellValue(cellValue);
						}
					} else {
						if (flag == 1) {
							cell.setCellValue("");
							sheet.addMergedRegion(new CellRangeAddress(tempRow[j], (short) currentRowIndex, j,(short) j));
							flag = 0;
						} else {
							temp[j] = cellValue;
							cell.setCellValue(cellValue);
							tempRow[j] = currentRowIndex;
						}
					}

				}
			}
		}
		return wb;
	}
}
