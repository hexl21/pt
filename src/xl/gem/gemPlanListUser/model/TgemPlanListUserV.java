package xl.gem.gemPlanListUser.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import xl.mvc.model.base.IdEntityOralceUUID;

@SuppressWarnings("serial")
@Entity
@Table(name = "gem_plan_list_user_v", schema = "")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TgemPlanListUserV extends IdEntityOralceUUID implements
		java.io.Serializable {

	private String gemModelBomId;
	private String gemModelBomName;
	private String sysOrganizationId;
	private String sysOrganizationName;
	private String gemPlanListId;
	private String gemPlanId;
	private String name;
	private String code;
	private String value;
	private String seq;
	private String llevel;
	private String score;
	private String status;
	private String gainScore;
	private String sysUserId;
	
	public TgemPlanListUserV() {
		super();
	}
	
	public TgemPlanListUserV(String gemModelBomId, String gemModelBomName,
			String sysOrganizationId, String sysOrganizationName,
			String gemPlanListId, String gemPlanId, String name, String code,
			String value, String seq, String llevel, String score,
			String status, String gainScore, String sysUserId) {
		super();
		this.gemModelBomId = gemModelBomId;
		this.gemModelBomName = gemModelBomName;
		this.sysOrganizationId = sysOrganizationId;
		this.sysOrganizationName = sysOrganizationName;
		this.gemPlanListId = gemPlanListId;
		this.gemPlanId = gemPlanId;
		this.name = name;
		this.code = code;
		this.value = value;
		this.seq = seq;
		this.llevel = llevel;
		this.score = score;
		this.status = status;
		this.gainScore = gainScore;
		this.sysUserId = sysUserId;
	}
	
	@Column(name = "SYS_USER_ID")
	public String getSysUserId() {
		return sysUserId;
	}

	public void setSysUserId(String sysUserId) {
		this.sysUserId = sysUserId;
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
	
	@Column(name = "SYS_ORGANIZATION_ID")
	public String getSysOrganizationId() {
		return sysOrganizationId;
	}

	public void setSysOrganizationId(String sysOrganizationId) {
		this.sysOrganizationId = sysOrganizationId;
	}
	
	@Column(name = "SYS_ORGANIZATION_NAME")
	public String getSysOrganizationName() {
		return sysOrganizationName;
	}

	public void setSysOrganizationName(String sysOrganizationName) {
		this.sysOrganizationName = sysOrganizationName;
	}

	@Column(name = "GEM_PLAN_LIST_ID")
	public String getGemPlanListId() {
		return gemPlanListId;
	}

	public void setGemPlanListId(String gemPlanListId) {
		this.gemPlanListId = gemPlanListId;
	}
	
	@Column(name = "GEM_PLAN_ID")
	public String getGemPlanId() {
		return gemPlanId;
	}

	public void setGemPlanId(String gemPlanId) {
		this.gemPlanId = gemPlanId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}
	
	@Column(name = "l_LEVEL")
	public String getLlevel() {
		return llevel;
	}

	public void setLlevel(String llevel) {
		this.llevel = llevel;
	}

	public String getScore() {
		return score;
	}

	public void setScore(String score) {
		this.score = score;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Column(name = "GAIN_SCORE")
	public String getGainScore() {
		return gainScore;
	}

	public void setGainScore(String gainScore) {
		this.gainScore = gainScore;
	}

}
