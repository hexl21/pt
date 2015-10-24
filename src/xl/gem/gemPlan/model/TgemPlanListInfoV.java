package xl.gem.gemPlan.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import xl.mvc.model.base.IdEntity;

@SuppressWarnings("serial")
@Entity
@Table(name = "gem_plan_list_info1_v", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TgemPlanListInfoV extends IdEntity implements
		java.io.Serializable {
	
	private String gemModelBomId;
	private String gemModelBomId2;
	private String gemModelBomId3;
	private String gemModelBomId4;
	private String gemModelBomName;
	private String gemModelBomName2;
	private String gemModelBomName3;
	private String gemModelBomName4;
	private String gemPlanId;
	private String value;
	private String value2;
	private String value3;
	private String value4;

	private String llevel;
	private String llevel2;
	private String llevel3;
	private String llevel4;
	private String ct;
	private String ct2;
	private String ct3;
	private String ct4;

	private TgemPlanListInfoV gemPlan;
	private Set<TgemPlanListInfoV> gemPlans = new HashSet<TgemPlanListInfoV>(0);

	public TgemPlanListInfoV() {
		super();
	}
	
	public TgemPlanListInfoV(String gemModelBomId, String gemModelBomId2,String gemModelBomId3,String gemModelBomId4,
			String gemModelBomName,String gemModelBomName2,String gemModelBomName3,String gemModelBomName4,
			String gemPlanId, 
			String value,String value2,String value3,String value4,
			String ct,String ct2,String ct3,String ct4,
			String llevel,String llevel2,String llevel3,String llevel4,
			TgemPlanListInfoV gemPlan, Set<TgemPlanListInfoV> gemPlans) {
		super();
		this.gemPlanId = gemPlanId;
		
		this.gemModelBomId = gemModelBomId;
		this.gemModelBomName = gemModelBomName;
		this.value = value;
		this.llevel = llevel;
		this.ct = ct;
		
		
		this.gemModelBomId2 = gemModelBomId2;
		this.gemModelBomName2 = gemModelBomName2;
		this.value2 = value2;
		this.llevel2 = llevel2;
		this.ct2 = ct2;
		
		this.gemModelBomId3 = gemModelBomId3;
		this.gemModelBomName3 = gemModelBomName3;
		this.value3 = value3;
		this.llevel3 = llevel3;
		this.ct3 = ct3;
		
		this.gemModelBomId4 = gemModelBomId4;
		this.gemModelBomName4 = gemModelBomName4;
		this.value4 = value4;
		this.llevel4 = llevel4;
		this.ct4 = ct4;
		
		this.gemPlan = gemPlan;
		this.gemPlans = gemPlans;
	}

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pid")
	public TgemPlanListInfoV getGemPlan() {
		return gemPlan;
	}


	public void setGemPlan(TgemPlanListInfoV gemPlan) {
		this.gemPlan = gemPlan;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "gemPlan")
	public Set<TgemPlanListInfoV> getGemPlans() {
		return gemPlans;
	}


	public void setGemPlans(Set<TgemPlanListInfoV> gemPlans) {
		this.gemPlans = gemPlans;
	}


	@Column(name = "GEM_PLAN_ID")
	public String getGemPlanId() {
		return gemPlanId;
	}

	public void setGemPlanId(String gemPlanId) {
		this.gemPlanId = gemPlanId;
	}
	
	@Column(name = "VALUE_1")
	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}


	@Column(name = "L_LEVEL_1")
	public String getLlevel() {
		return llevel;
	}

	public void setLlevel(String llevel) {
		this.llevel = llevel;
	}

	@Column(name = "GEM_MODEL_BOM_NAME_1")
	public String getGemModelBomName() {
		return gemModelBomName;
	}

	public void setGemModelBomName(String gemModelBomName) {
		this.gemModelBomName = gemModelBomName;
	}


	@Column(name = "GEM_MODEL_BOM_ID_1")
	public String getGemModelBomId() {
		return gemModelBomId;
	}
	

	public void setGemModelBomId(String gemModelBomId) {
		this.gemModelBomId = gemModelBomId;
	}

	@Column(name = "GEM_MODEL_BOM_ID_2")
	public String getGemModelBomId2() {
		return gemModelBomId2;
	}

	public void setGemModelBomId2(String gemModelBomId2) {
		this.gemModelBomId2 = gemModelBomId2;
	}

	@Column(name = "GEM_MODEL_BOM_ID_3")
	public String getGemModelBomId3() {
		return gemModelBomId3;
	}

	public void setGemModelBomId3(String gemModelBomId3) {
		this.gemModelBomId3 = gemModelBomId3;
	}

	@Column(name = "GEM_MODEL_BOM_ID_4")
	public String getGemModelBomId4() {
		return gemModelBomId4;
	}

	public void setGemModelBomId4(String gemModelBomId4) {
		this.gemModelBomId4 = gemModelBomId4;
	}

	@Column(name = "GEM_MODEL_BOM_NAME_2")
	public String getGemModelBomName2() {
		return gemModelBomName2;
	}

	public void setGemModelBomName2(String gemModelBomName2) {
		this.gemModelBomName2 = gemModelBomName2;
	}

	@Column(name = "GEM_MODEL_BOM_NAME_3")
	public String getGemModelBomName3() {
		return gemModelBomName3;
	}

	public void setGemModelBomName3(String gemModelBomName3) {
		this.gemModelBomName3 = gemModelBomName3;
	}

	
	@Column(name = "GEM_MODEL_BOM_NAME_4")
	public String getGemModelBomName4() {
		return gemModelBomName4;
	}

	public void setGemModelBomName4(String gemModelBomName4) {
		this.gemModelBomName4 = gemModelBomName4;
	}

	@Column(name = "VALUE_2")
	public String getValue2() {
		return value2;
	}

	public void setValue2(String value2) {
		this.value2 = value2;
	}

	@Column(name = "VALUE_3")
	public String getValue3() {
		return value3;
	}

	public void setValue3(String value3) {
		this.value3 = value3;
	}

	@Column(name = "VALUE_4")
	public String getValue4() {
		return value4;
	}

	public void setValue4(String value4) {
		this.value4 = value4;
	}

	
	@Column(name = "L_LEVEL_2")
	public String getLlevel2() {
		return llevel2;
	}

	public void setLlevel2(String llevel2) {
		this.llevel2 = llevel2;
	}

	@Column(name = "L_LEVEL_3")
	public String getLlevel3() {
		return llevel3;
	}

	public void setLlevel3(String llevel3) {
		this.llevel3 = llevel3;
	}

	@Column(name = "L_LEVEL_4")
	public String getLlevel4() {
		return llevel4;
	}

	public void setLlevel4(String llevel4) {
		this.llevel4 = llevel4;
	}

	@Column(name = "CT1")
	public String getCt() {
		return ct;
	}

	public void setCt(String ct) {
		this.ct = ct;
	}

	@Column(name = "CT2")
	public String getCt2() {
		return ct2;
	}

	public void setCt2(String ct2) {
		this.ct2 = ct2;
	}

	@Column(name = "CT3")
	public String getCt3() {
		return ct3;
	}

	public void setCt3(String ct3) {
		this.ct3 = ct3;
	}

	@Column(name = "CT4")
	public String getCt4() {
		return ct4;
	}

	public void setCt4(String ct4) {
		this.ct4 = ct4;
	}

}