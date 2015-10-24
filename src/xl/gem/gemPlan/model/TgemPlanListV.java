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
import xl.mvc.model.base.IdEntityOralceUUID;

@SuppressWarnings("serial")
@Entity
@Table(name = "gem_plan_list_v", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TgemPlanListV extends IdEntity implements
		java.io.Serializable {
	
	private String gemModelBomId;
	private String gemModelBomName;
	private String gemModelBomName2;
	private String gemModelBomName3;
	private String gemModelBomName4;
	private String gemPlanId;
	private String code;
	private String value;
	private String value2;
	private String value3;
	private String value4;
	private String score;
	private String status;
	private String statusName;
	private String llevel;
	private String icon;
	private TgemPlanListV gemPlan;
	private Set<TgemPlanListV> gemPlans = new HashSet<TgemPlanListV>(0);

	public TgemPlanListV() {
		super();
	}
	
	public TgemPlanListV(String gemModelBomId, String gemModelBomName,String gemModelBomName2,String gemModelBomName3,String gemModelBomName4,
			String gemPlanId, String name, String code, String value,String value2,String value3,String value4,
			String score, String status, String statusName, String llevel,
			String icon, TgemPlanListV gemPlan, Set<TgemPlanListV> gemPlans) {
		super();
		this.gemModelBomId = gemModelBomId;
		this.gemModelBomName = gemModelBomName;
		this.gemPlanId = gemPlanId;
		this.code = code;
		this.value = value;
		this.score = score;
		this.status = status;
		this.statusName = statusName;
		this.llevel = llevel;
		this.icon = icon;
		this.gemPlan = gemPlan;
		this.gemPlans = gemPlans;
	}


	public String getIcon() {
		return icon;
	}


	public void setIcon(String icon) {
		this.icon = icon;
	}


	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "pid")
	public TgemPlanListV getGemPlan() {
		return gemPlan;
	}


	public void setGemPlan(TgemPlanListV gemPlan) {
		this.gemPlan = gemPlan;
	}

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "gemPlan")
	public Set<TgemPlanListV> getGemPlans() {
		return gemPlans;
	}


	public void setGemPlans(Set<TgemPlanListV> gemPlans) {
		this.gemPlans = gemPlans;
	}


	@Column(name = "GEM_PLAN_ID")
	public String getGemPlanId() {
		return gemPlanId;
	}

	public void setGemPlanId(String gemPlanId) {
		this.gemPlanId = gemPlanId;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Column(name = "STATUS_NAME")
	public String getStatusName() {
		return statusName;
	}

	public void setStatusName(String statusName) {
		this.statusName = statusName;
	}

	@Column(name = "L_LEVEL")
	public String getLlevel() {
		return llevel;
	}

	public void setLlevel(String llevel) {
		this.llevel = llevel;
	}

	@Column(name = "GEM_MODEL_BOM_NAME")
	public String getGemModelBomName() {
		return gemModelBomName;
	}

	public void setGemModelBomName(String gemModelBomName) {
		this.gemModelBomName = gemModelBomName;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	@Column(name = "GEM_MODEL_BOM_ID")
	public String getGemModelBomId() {
		return gemModelBomId;
	}

	public void setGemModelBomId(String gemModelBomId) {
		this.gemModelBomId = gemModelBomId;
	}

}