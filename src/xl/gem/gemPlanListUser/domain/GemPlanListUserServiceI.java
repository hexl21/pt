package xl.gem.gemPlanListUser.domain;

import java.util.List;
import java.util.Map;

import xl.gem.gemPlanListUser.model.GemPlanListUser;

import com.xl.pageModel.base.PageFilter;
import com.xl.pageModel.base.SessionInfo;

public interface GemPlanListUserServiceI {
	
	public List<GemPlanListUser> dataGrid(GemPlanListUser gemPlanListUser, PageFilter ph);

	public Long count(GemPlanListUser gemScoreStandard, PageFilter ph);
	
	public String saveScore(String id,String score,String gemPlanListId);
	public List<GemPlanListUser> dataGrid2(GemPlanListUser gemPlanListUser, PageFilter ph);
	
	public Long count2(GemPlanListUser gemScoreStandard, PageFilter ph);
	
	public String saveXtScore(String id,String score,String gemPlanListId);
	
	public Map getPlanStrList(SessionInfo sessionInfo);
	
	public Map getPlanStrList2(SessionInfo sessionInfo);
	
	public List gemPlanListUserSummary(String id);
}
