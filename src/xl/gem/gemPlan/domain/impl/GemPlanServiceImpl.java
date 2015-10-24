package xl.gem.gemPlan.domain.impl;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import xl.gem.gemPlan.model.TgemPlanListInfoV;
import xl.gem.gemPlan.model.TgemPlanListV;
import xl.gem.gemPlan.model.TgemPlanV;
import xl.gem.gemPlan.model.TgemPlanQueryV;
import xl.gem.gemPlan.domain.GemPlanServiceI;
import xl.gem.gemPlan.model.GemPlan;

import com.xl.dao.BaseDaoI;
import com.xl.pageModel.base.PageFilter;
import com.xl.pageModel.base.ReturnValue;
import com.xl.pageModel.base.SessionInfo;

@Service
@SuppressWarnings("unchecked")
public class GemPlanServiceImpl implements GemPlanServiceI {

	@Autowired
	private BaseDaoI<TgemPlanV> gemPlanVDao;
	@Autowired
	private BaseDaoI<TgemPlanQueryV> gemPlanVsDao;
	
	@Autowired
	private BaseDaoI<TgemPlanListInfoV> gemPlanListInfoVDao;

	@Override
	public String add(GemPlan gemPlan,SessionInfo sessionInfo) {
		String title = gemPlan.getTitle();
		String gemModelBomid = gemPlan.getGemModelBomId();
		String organizationId = gemPlan.getOrganizationId();
		String zjUserIds = gemPlan.getZjUserIds();
		String zjUserNames = gemPlan.getZjUserNames();
		List list = new ArrayList();
		list.add(title != null && !"".equals(title) ? title : "");
		list.add(gemModelBomid != null && !"".equals(gemModelBomid) ? gemModelBomid
						: "");
		list.add(organizationId != null && !"".equals(organizationId) ? organizationId
						: "");
		list.add(zjUserIds != null && !"".equals(zjUserIds) ? zjUserIds
				.replaceAll(",", ";")
				+ ";" : "");
		list.add(zjUserNames != null && !"".equals(zjUserNames) ? zjUserNames
				.replaceAll(",", ";") : "");
		list.add("");
		list.add("0");
		list.add("");
		ReturnValue rv = gemPlanVDao.callProcedure(
				"gem_plan_insert_p", list);
		String id = rv.getReturnString();
		return id;
	}

	@Override
	public Long count(GemPlan gemPlan, PageFilter ph, SessionInfo sessionInfo) {
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = "select count(*) from TgemPlanV t "
				+ whereHql(gemPlan, params, sessionInfo);
		return gemPlanVDao.count(hql, params);
	}

	@Override
	public List<GemPlan> dataGrid(GemPlan gemPlan, PageFilter ph,
			SessionInfo sessionInfo) {
		List<GemPlan> gss = new ArrayList<GemPlan>();
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from TgemPlanV t "
				+ whereHql(gemPlan, params, sessionInfo) + orderHql(ph);
		List<TgemPlanV> l = gemPlanVDao.find(hql, params, ph.getPage(), ph
				.getRows());
		for (TgemPlanV t : l) {
			GemPlan u = new GemPlan();
			BeanUtils.copyProperties(t, u);
			gss.add(u);
		}
		return gss;
	}

	@Override
	public Long count2(GemPlan gemPlan, PageFilter ph, SessionInfo sessionInfo) {
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = "select count(*) from TgemPlanVs t "
				+ whereHql(gemPlan, params, sessionInfo);
		return gemPlanVsDao.count(hql, params);
	}

	@Override
	public List<GemPlan> dataGrid2(GemPlan gemPlan, PageFilter ph,
			SessionInfo sessionInfo) {
		List<GemPlan> gss = new ArrayList<GemPlan>();
		Map<String, Object> params = new HashMap<String, Object>();
		String hql = " from TgemPlanQueryV t "
				+ whereHql(gemPlan, params, sessionInfo) + orderHql(ph);
		List<TgemPlanQueryV> l = gemPlanVsDao.find(hql, params, ph.getPage(),
				ph.getRows());
		for (TgemPlanQueryV t : l) {
			GemPlan u = new GemPlan();
			BeanUtils.copyProperties(t, u);
			gss.add(u);
		}
		return gss;
	}

	@Override
	public List<GemPlan> detailTreeGrid(String id) {
		List<GemPlan> lr = new ArrayList<GemPlan>();
		StringBuilder sb = new StringBuilder();
		sb.append("SELECT t.id,t.gem_model_bom_name,t.gem_plan_id,");
		sb.append("t.value,t.pid,t.pname,t.status_name,t.icon,t.score FROM ");
		sb.append("(select * from gem_plan_list_v a ");
		sb.append(" where a.gem_plan_id = '").append(id).append("') t ");
		sb.append("start with t.pid is null connect by prior t.gem_model_bom_id = t.pid");
		List<Object[]> list = gemPlanVDao.findBySql(sb.toString());
		for (Object[] obj : list) {
			GemPlan gp = new GemPlan();
			String id1 = obj[0] != null ? obj[0].toString() : "";
			String gemModelBomName = obj[1] != null ? obj[1].toString() : "";
			String gemPlanId = obj[2] != null ? obj[2].toString() : "";
			String value = obj[3] != null ? obj[3].toString() : "";
			String pid = obj[4] != null ? obj[4].toString() : "";
			String pname = obj[5] != null ? obj[5].toString() : "";
			String statusName = obj[6] != null ? obj[6].toString() : "";
			String icon = obj[7] != null ? obj[7].toString() : "";
			String score = obj[8] != null ? obj[8].toString() : "";
			gp.setId(id1);
			gp.setGemModelBomName(gemModelBomName);
			gp.setGemPlanId(gemPlanId);
			gp.setValue(value);
			gp.setPid(pid);
			gp.setPname(pname);
			gp.setStatusName(statusName);
			gp.setIconCls(icon);
			gp.setScore(score);
			lr.add(gp);
		}
		// List<TgemPlanListV> l = gemPlanListVDao
		// .find("from TgemPlanListV t left join fetch t.gemPlan where
		// t.gemPlanId='"+id+"'");
		// if ((l != null) && (l.size() > 0)) {
		// for (TgemPlanListV t : l) {
		// GemPlan r = new GemPlan();
		// BeanUtils.copyProperties(t, r);
		// if (t.getGemPlan()!=null) {
		// r.setPid(t.getGemPlan().getGemModelBomId());
		// r.setPname(t.getGemPlan().getGemModelBomName());
		// }
		// r.setIconCls(t.getIcon());
		// lr.add(r);
		// }
		// }
		return lr;
	}

	@Override
	public void delete(String id) {
		List list = new ArrayList();
		list.add(id != null && !"".equals(id) ? id : "");
		gemPlanVDao.callProcedure("gem_plan_delete_p", list);
	}

	private String whereHql(GemPlan gemPlan, Map<String, Object> params,
			SessionInfo sessionInfo) {
		String hql = "";
		if (gemPlan != null) {
			hql += " where 1=1 ";
			hql += " and t.type= '0' ";
			//hql += " and t.loginUserOrganizationId='"+sessionInfo.getUserDeptId()+"'";
			if (gemPlan.getGemPlanId() != null
					&& !"".equals(gemPlan.getGemPlanId())) {
				hql += " and t.gemPlanId ='" + gemPlan.getGemPlanId() + "' ";
			}
			if (gemPlan.getGemModelBomId() != null
					&& !"".equals(gemPlan.getGemModelBomId())) {
				hql += " and t.gemModelBomId ='" + gemPlan.getGemModelBomId()
						+ "' ";
			}
			if (gemPlan.getTitle() != null && !"".equals(gemPlan.getTitle())) {
				hql += " and t.title like '%" + gemPlan.getTitle() + "%' ";
			}
			if (gemPlan.getGemPlanHeadId() != null
					&& !"".equals(gemPlan.getGemPlanHeadId())) {
				hql += " and t.gemPlanHeadId ='" + gemPlan.getGemPlanHeadId()
						+ "' ";
			} else {
				hql += " and t.gemPlanHeadId = ''";
			}
		}
		return hql;
	}

	private String orderHql(PageFilter ph) {
		String orderString = "";
		if ((ph.getSort() != null) && (ph.getOrder() != null)) {
			orderString = " order by t." + ph.getSort() + " " + ph.getOrder();
		} else {
			orderString = " order by t.gemModelBomId , t.seq ";
		}
		return orderString;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Map getModelList(SessionInfo sessionInfo) {
		Map map = new HashMap();
		StringBuilder sb = new StringBuilder();
		sb.append("select t.id,t.name from gem_model_bom t  where t.pid = ''");
		List<Object[]> list = gemPlanVDao.findBySql(sb.toString());
		for (Object[] obj : list) {
			map.put(obj[0].toString(), obj[1].toString());
		}
		return map;
	}

	@Override
	public ReturnValue saveUpLoadFile(String id, MultipartFile file)
			throws IOException {
		ReturnValue rv = new ReturnValue();
		if (file != null && id != null) {
			List list = new ArrayList();
			list.add(id);
			list.add("GEM_PLAN");
			list.add(file.getOriginalFilename().replaceAll(",", "@"));
			list.add(String.valueOf(file.getSize()));
			rv = gemPlanVDao.callProcedure(
					"sys_common_p.sys_attachment_insert", list);
		}
		return rv;
	}

	@Override
	public Long countFile(String id, PageFilter ph) {
		Long l = 0l;
		StringBuilder sb = new StringBuilder();
		sb.append("select count(1) from sys_attachment t  ");
		sb.append(" where 1=1 ");
		if (id != null && !"".equals(id)) {
			sb.append("and t.table_id = '").append(id).append("'");
		} else {
			sb.append(" and 1=2 ");
		}
		BigInteger bi = gemPlanVDao.countBySql(sb.toString());
		return bi.longValue();
	}

	@Override
	public List<GemPlan> dataGridFile(String id, PageFilter ph) {
		List<GemPlan> gss = new ArrayList<GemPlan>();
		StringBuilder sb = new StringBuilder();
		sb.append("select t.id,t.file_name,t.table_id from sys_attachment t  ");
		sb.append(" where 1=1 ");
		if (id != null && !"".equals(id)) {
			sb.append("and t.table_id = '").append(id).append("'");
		} else {
			sb.append(" and 1=2 ");
		}
		List<Object[]> list = gemPlanVDao.findBySql(sb.toString());
		for (Object[] obj : list) {
			GemPlan gp = new GemPlan();
			gp.setId(obj[0].toString());
			gp.setFileName(obj[1].toString());
			gp.setGemPlanId(obj[2].toString());
			gss.add(gp);
		}
		return gss;
	}

	@Override
	public void deleteSysAtt(String id) {
		String sql = "delete from sys_attachment t  where t.id = '" + id + "'";
		gemPlanVDao.executeSql(sql);
	}

	@Override
	public ReturnValue calculateZdScore(String ids) {
		ReturnValue rv = new ReturnValue();
		String[] idsStr = ids.split("@");
		for (int i = 0; i < idsStr.length; i++) {
			List list = new ArrayList();
			list.add(idsStr[i] != null && !"".equals(idsStr[i]) ? idsStr[i]
					: "");
			rv = gemPlanVDao.callProcedure(
					"gem_plan_p.Gem_Plan_zd_Score_calculate", list);
		}
		return rv;
	}

	/**
	 * 徐修文修改
	 */

	@Override
	public List<GemPlan> detailTableGrid(String id) {
		List<GemPlan> lr = new ArrayList<GemPlan>();
		StringBuilder sb = new StringBuilder();
		sb.append("select t.gem_model_bom_id_1,t.gem_model_bom_name_1,t.value_1,t.ct1,");
		sb.append(" t.gem_model_bom_id_2,t.gem_model_bom_name_2,t.value_2,t.ct2,");
		sb.append(" t.gem_model_bom_id_3,t.gem_model_bom_name_3,t.value_3,t.ct3,");
		sb.append(" t.gem_model_bom_id_4,t.gem_model_bom_name_4,t.value_4,t.ct4");
		sb.append(" from gem_plan_list_info1_v t");
		sb.append(" where t.gem_plan_id='" + id + "'");
		System.out.println(sb);
		List<Object[]> list = gemPlanListInfoVDao.findBySql(sb.toString());
		for (Object[] obj : list) {
			GemPlan gp = new GemPlan();

			String gemModelBomId = obj[0] != null ? obj[0].toString() : "";
			String gemModelBomName = obj[1] != null ? obj[1].toString() : "";
			String value = obj[2] != null ? obj[2].toString() : "0";
			String ct = obj[3] != null ? obj[3].toString() : "";

			String gemModelBomId2 = obj[4] != null ? obj[4].toString() : "";
			String gemModelBomName2 = obj[5] != null ? obj[5].toString() : "";
			String value2 = obj[6] != null ? obj[6].toString() : "0";
			String ct2 = obj[7] != null ? obj[7].toString() : "";

			String gemModelBomId3 = obj[8] != null ? obj[8].toString() : "";
			String gemModelBomName3 = obj[9] != null ? obj[9].toString() : "";
			String value3 = obj[10] != null ? obj[10].toString() : "0";
			String ct3 = obj[11] != null ? obj[11].toString() : "";

			String gemModelBomId4 = obj[12] != null ? obj[12].toString() : "";
			String gemModelBomName4 = obj[13] != null ? obj[13].toString() : "";
			String value4 = obj[14] != null ? obj[14].toString() : "0";
			String ct4 = obj[15] != null ? obj[15].toString() : "";

			gp.setGemModelBomId(gemModelBomId);
			gp.setGemModelBomName(gemModelBomName);
			gp.setValue(value);
			gp.setCt(ct);

			gp.setGemModelBomId2(gemModelBomId2);
			gp.setGemModelBomName2(gemModelBomName2);
			gp.setValue2(value2);
			gp.setCt2(ct2);

			gp.setGemModelBomId3(gemModelBomId3);
			gp.setGemModelBomName3(gemModelBomName3);
			gp.setValue3(value3);
			gp.setCt3(ct3);

			gp.setGemModelBomId4(gemModelBomId4);
			gp.setGemModelBomName4(gemModelBomName4);
			gp.setValue4(value4);
			gp.setCt4(ct4);

			lr.add(gp);
		}

		return lr;
	}
	
	@Override
	public List gemPlanListSummary(String id) {
		List<List>  ll = new ArrayList<List>();
		List param = new ArrayList();
		int count  = 0;
		param.add(id);
		ReturnValue rv = gemPlanVDao.callProcedure("gem_plan_p.Gem_Plan_list_summary", param);
		count = Integer.parseInt(rv.getReturnString());
		if(count>0){
			StringBuilder sb = new StringBuilder();
			sb.append("select * from gem_Plan_list_summary");
			List<Object[]> list = gemPlanVDao.findBySql(sb.toString());
			for (Object[] obj : list) {
				List l = new ArrayList();
				for (int i = 0; i < count; i++) {
					l.add(obj[i]!=null?obj[i].toString():"");
				}
				ll.add(l);
			}
		}
		return ll;
	}
	//获得模型得分
	@Override
	public String getGemPlanScoreStr(String id) {
		String str = "";
		StringBuilder sb = new StringBuilder();
		sb.append("select t.organization_name||'--'||t.gem_model_bom_name||'--评分结果：'||t.score");
		sb.append(" from gem_plan_v t where t.id = '");
		sb.append(id).append("'");
		List list = gemPlanVDao.findBySql(sb.toString());
		str = list.get(0).toString();
		return str;
	}
}