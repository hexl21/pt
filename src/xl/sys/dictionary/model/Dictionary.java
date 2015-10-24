package xl.sys.dictionary.model;


public class Dictionary  implements java.io.Serializable{
	
	private String id;
	private String code;
	private String text;
	private String dictionarytypeId;
	private String dictionarytypeName;
	private Integer seq;
	private Integer state; // 状态 0启用 1停用
	private Integer isdefault; // 是否默认
	
	public Dictionary(){
		
	}
	
	public Dictionary(String code, String text,Integer seq, Integer state,
			Integer isdefault) {
		super();
		this.code = code;
		this.text = text;
		this.seq = seq;
		this.state = state;
		this.isdefault = isdefault;
	}
	

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public Integer getState() {
		return state;
	}

	public void setState(Integer state) {
		this.state = state;
	}

	public Integer getIsdefault() {
		return isdefault;
	}

	public void setIsdefault(Integer isdefault) {
		this.isdefault = isdefault;
	}

	
	public String getDictionarytypeId() {
		return dictionarytypeId;
	}

	public void setDictionarytypeId(String dictionarytypeId) {
		this.dictionarytypeId = dictionarytypeId;
	}

	public String getDictionarytypeName() {
		return dictionarytypeName;
	}

	public void setDictionarytypeName(String dictionarytypeName) {
		this.dictionarytypeName = dictionarytypeName;
	}

	

}
