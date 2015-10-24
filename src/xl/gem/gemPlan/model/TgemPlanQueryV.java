package xl.gem.gemPlan.model;

import javax.persistence.Column;
import javax.persistence.Entity;

import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import xl.mvc.model.base.IdEntity;

@SuppressWarnings("serial")
@Entity
@Table(name = "gem_plan_query_v", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TgemPlanQueryV extends IdEntity implements
		java.io.Serializable {
	private String title;
	private String gemModelBomId;
	private String gemModelBomName;
	private String organizationName;
	private String score;
	private String zjUserNames;
	private String gemPlanHeadId;

	public TgemPlanQueryV() {
		super();
	}



	public TgemPlanQueryV(String title, String gemModelBomId,
			String gemModelBomName, String organizationName, String score,
			String zjUserNames, String gemPlanHeadId) {
		super();
		this.title = title;
		this.gemModelBomId = gemModelBomId;
		this.gemModelBomName = gemModelBomName;
		this.organizationName = organizationName;
		this.score = score;
		this.zjUserNames = zjUserNames;
		this.gemPlanHeadId = gemPlanHeadId;
	}


	@Column(name = "GEM_PLAN_HEAD_ID")
	public String getGemPlanHeadId() {
		return gemPlanHeadId;
	}



	public void setGemPlanHeadId(String gemPlanHeadId) {
		this.gemPlanHeadId = gemPlanHeadId;
	}



	@Column(name = "GEM_MODEL_BOM_ID")
	public String getGemModelBomId() {
		return gemModelBomId;
	}



	public void setGemModelBomId(String gemModelBomId) {
		this.gemModelBomId = gemModelBomId;
	}



	@Column(name = "GEM_MODEL_BOM_NAME")
	public String getGemModelBomName() {
		return gemModelBomName;
	}

	public void setGemModelBomName(String gemModelBomName) {
		this.gemModelBomName = gemModelBomName;
	}

	@Column(name = "ORGANIZATION_NAME")
	public String getOrganizationName() {
		return organizationName;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	@Column(name = "ZJ_USER_NAMES")
	public String getZjUserNames() {
		return zjUserNames;
	}

	public void setZjUserNames(String zjUserNames) {
		this.zjUserNames = zjUserNames;
	}



	public String getTitle() {
		return title;
	}



	public void setTitle(String title) {
		this.title = title;
	}

}