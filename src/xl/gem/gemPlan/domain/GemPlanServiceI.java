package xl.gem.gemPlan.domain;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import xl.gem.gemPlan.model.GemPlan;

import com.xl.pageModel.base.PageFilter;
import com.xl.pageModel.base.ReturnValue;
import com.xl.pageModel.base.SessionInfo;

public interface GemPlanServiceI {
	
	public List<GemPlan> dataGrid(GemPlan gemPlan, PageFilter ph,SessionInfo sessionInfo);

	public Long count(GemPlan GemPlan, PageFilter ph,SessionInfo sessionInfo);
	
	public List<GemPlan> dataGrid2(GemPlan gemPlan, PageFilter ph,SessionInfo sessionInfo);
	
	public Long count2(GemPlan GemPlan, PageFilter ph,SessionInfo sessionInfo);
	
	public List<GemPlan> dataGridFile(String id, PageFilter ph);
	
	public Long countFile(String id, PageFilter ph);

	public String add(GemPlan gemPlan,SessionInfo sessionInfo);

	public void delete(String id);
	
	public Map getModelList(SessionInfo sessionInfo);
	
	public List<GemPlan> detailTreeGrid(String id);
	
	public ReturnValue saveUpLoadFile(String id,MultipartFile file)throws IOException;
	
	public void deleteSysAtt(String id);
	
	public ReturnValue calculateZdScore(String ids);

	public List<GemPlan> detailTableGrid(String id);
	
	public List gemPlanListSummary(String id);

	public String getGemPlanScoreStr(String id);
}
