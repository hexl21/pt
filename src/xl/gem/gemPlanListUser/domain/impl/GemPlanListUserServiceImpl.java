package xl.gem.gemPlanListUser.domain.impl;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import xl.gem.gemPlanListUser.domain.GemPlanListUserServiceI;
import xl.gem.gemPlanListUser.model.GemPlanListUser;
import xl.gem.gemPlanListUser.model.TgemPlanListUserV;
import xl.gem.gemPlanListUser.model.TgemPlanListUserV2;

import com.xl.dao.BaseDaoI;
import com.xl.pageModel.base.PageFilter;
import com.xl.pageModel.base.ReturnValue;
import com.xl.pageModel.base.SessionInfo;

@Service
@SuppressWarnings("unchecked")
public class GemPlanListUserServiceImpl implements GemPlanListUserServiceI {

	@Autowired
	private BaseDaoI<TgemPlanListUserV> gemPlanListUserVDao;
	@Autowired
	private BaseDaoI<TgemPlanListUserV2> gemPlanListUserV2Dao;

	@Override
	public Long count(GemPlanListUser gemPlanListUser, PageFilter ph) {
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = "select count(*) from TgemPlanListUserV t "
				+ whereHql(gemPlanListUser, params);
		return gemPlanListUserVDao.count(hql, params);
	}

	@Override
	public List<GemPlanListUser> dataGrid(GemPlanListUser gemPlanListUser,
			PageFilter ph) {
		List<GemPlanListUser> gss = new ArrayList<GemPlanListUser>();
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from TgemPlanListUserV t "
				+ whereHql(gemPlanListUser, params) + orderHql(ph);
		List<TgemPlanListUserV> l = gemPlanListUserVDao.find(hql, params, ph
				.getPage(), ph.getRows());
		for (TgemPlanListUserV t : l) {
			GemPlanListUser u = new GemPlanListUser();
			BeanUtils.copyProperties(t, u);
			gss.add(u);
		}
		return gss;
	}

	private String whereHql(GemPlanListUser gemPlanListUser,
			Map<String, Object> params) {
		String hql = "";
		if (gemPlanListUser != null) {
			hql += " where 1=1 ";
			//hql += " and t.status = '0' ";
			if(gemPlanListUser.getGemPlanId()!=null && !"".equals(gemPlanListUser.getGemPlanId())){
				hql += " and t.gemPlanId ='"+gemPlanListUser.getGemPlanId()+"' ";
			}else{
				hql += " and 1=2 ";
			}
			if(gemPlanListUser.getSysUserId()!=null && !"".equals(gemPlanListUser.getSysUserId())){
				hql += " and t.sysUserId ='"+gemPlanListUser.getSysUserId()+"' ";
			}
			
		}
		return hql;
	}
	
	@Override
	public String saveScore(String id, String score ,String gemPlanListId) {
		List list  = new ArrayList();
		list.add(id!=null&&!"".equals(id)?id:"");
		list.add(gemPlanListId!=null&&!"".equals(gemPlanListId)?gemPlanListId:"");
		list.add(score!=null&&!"".equals(score)?score:"");
		ReturnValue rv = gemPlanListUserVDao.callProcedure("gem_plan_p.gem_plan_list_user_update", list);
		return rv.getReturnString();
	}
	@Override
	public Long count2(GemPlanListUser gemPlanListUser, PageFilter ph) {
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = "select count(*) from TgemPlanListUserV2 t "
			+ whereHql(gemPlanListUser, params);
		return gemPlanListUserVDao.count(hql, params);
	}
	
	@Override
	public List<GemPlanListUser> dataGrid2(GemPlanListUser gemPlanListUser,
			PageFilter ph) {
		List<GemPlanListUser> gss = new ArrayList<GemPlanListUser>();
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from TgemPlanListUserV2 t "
			+ whereHql2(gemPlanListUser, params) + orderHql(ph);
		List<TgemPlanListUserV2> l = gemPlanListUserV2Dao.find(hql, params, ph
				.getPage(), ph.getRows());
		for (TgemPlanListUserV2 t : l) {
			GemPlanListUser u = new GemPlanListUser();
			BeanUtils.copyProperties(t, u);
			gss.add(u);
		}
		return gss;
	}
	
	private String whereHql2(GemPlanListUser gemPlanListUser,
			Map<String, Object> params) {
		String hql = "";
		if (gemPlanListUser != null) {
			hql += " where 1=1 ";
			//hql += " and t.status = '0' ";
			if(gemPlanListUser.getGemPlanId()!=null && !"".equals(gemPlanListUser.getGemPlanId())){
				hql += " and t.gemPlanId ='"+gemPlanListUser.getGemPlanId()+"' ";
			}else{
				hql += " and 1=2 ";
			}
			if(gemPlanListUser.getSysUserId()!=null && !"".equals(gemPlanListUser.getSysUserId())){
				hql += " and t.sysUserId ='"+gemPlanListUser.getSysUserId()+"' ";
			}
			
		}
		return hql;
	}
	
	@Override
	public String saveXtScore(String id, String score ,String gemPlanListId) {
		List list  = new ArrayList();
		list.add(id!=null&&!"".equals(id)?id:"");
		list.add(gemPlanListId!=null&&!"".equals(gemPlanListId)?gemPlanListId:"");
		list.add(score!=null&&!"".equals(score)?score:"");
		ReturnValue rv = gemPlanListUserVDao.callProcedure("gem_plan_p.gem_plan_list_user_update2", list);
		return rv.getReturnString();
	}

	private String orderHql(PageFilter ph) {
		String orderString = "";
		return orderString;
	}

	@Override
	public Map getPlanStrList(SessionInfo sessionInfo) {
		Map map = new HashMap();
		StringBuilder sb = new StringBuilder();
		sb.append(" select gp.id, so.name||'--'||gmb.name||'--评分计划' as name from gem_plan gp ");
		sb.append(" left join gem_model_bom gmb on gmb.id = gp.gem_model_bom_id ");
		sb.append(" left join sys_organization so on so.id = gp.sys_organization_id");
		sb.append(" where exists (select 1 from gem_plan_list gpl ");
		sb.append(" where gpl.gem_plan_id = gp.id and gp.type = '0'");
		sb.append(" and exists (select 1 from gem_plan_list_user gplu ");
		sb.append(" where gplu.gem_plan_list_id = gpl.id and gpl.status = '0' ");
		sb.append(" and gp.gem_plan_head_id is null ");
		sb.append(" and gplu.sys_user_id ='").append(sessionInfo.getId()).append("'))");
		List<Object[]> list = gemPlanListUserVDao.findBySql(sb.toString());
		for (Object[] obj : list) {
			map.put(obj[0].toString(), obj[1].toString());
		}
		return map;
	}
	@Override
	public Map getPlanStrList2(SessionInfo sessionInfo) {
		Map map = new HashMap();
		StringBuilder sb = new StringBuilder();
		sb.append(" select gp.id, '核心('||gph.organization_name||')--卫星('||So.Name ||')--' || Gmb.Name || '--评分计划' as name from gem_plan gp ");
		sb.append(" inner join gem_plan_head_v gph on gph.id = gp.gem_plan_head_id ");
		sb.append(" left join gem_model_bom gmb on gmb.id = gp.gem_model_bom_id ");
		sb.append(" left join sys_organization so on so.id = gp.sys_organization_id");
		sb.append(" where exists (select 1 from gem_plan_list gpl ");
		sb.append(" where gpl.gem_plan_id = gp.id and gp.type = '0' ");
		sb.append(" and exists (select 1 from gem_plan_list_user gplu ");
		sb.append(" where gplu.gem_plan_list_id = gpl.id and gpl.status = '0' ");
		sb.append(" and gplu.sys_user_id ='").append(sessionInfo.getId()).append("'))");
		List<Object[]> list = gemPlanListUserVDao.findBySql(sb.toString());
		for (Object[] obj : list) {
			map.put(obj[0].toString(), obj[1].toString());
		}
		return map;
	}
	
	@Override
	public List gemPlanListUserSummary(String id) {
		List<List>  ll = new ArrayList<List>();
		List param = new ArrayList();
		int count  = 0;
		param.add(id);
		ReturnValue rv = gemPlanListUserVDao.callProcedure("gem_plan_p.Gem_Plan_list_user_summary", param);
		count = Integer.parseInt(rv.getReturnString());
		if(count>0){
			StringBuilder sb = new StringBuilder();
			sb.append("select * from gem_Plan_list_user_summary");
			List<Object[]> list = gemPlanListUserVDao.findBySql(sb.toString());
			for (Object[] obj : list) {
				List l = new ArrayList();
				for (int i = 0; i <= count; i++) {
					l.add(obj[i]!=null?obj[i].toString():"");
				}
				ll.add(l);
			}
		}
		return ll;
	}
}
