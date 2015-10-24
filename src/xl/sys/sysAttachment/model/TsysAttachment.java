package xl.sys.sysAttachment.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import xl.mvc.model.base.IdEntityOralceUUID;
@Entity
@Table(name = "sys_attachment")
@DynamicInsert(true)
@DynamicUpdate(true)
public class TsysAttachment extends IdEntityOralceUUID implements java.io.Serializable {
	private String tableId;
	private String tableName;
	private String fileName;
	private Date createdtime;
	private byte[] fileContent;
	
	public TsysAttachment() {
		super();
	}

	public TsysAttachment(String tableId, String tableName, String fileName,
			Date createdtime, byte[] fileContent) {
		super();
		this.tableId = tableId;
		this.tableName = tableName;
		this.fileName = fileName;
		this.createdtime = createdtime;
		this.fileContent = fileContent;
	}
	
	@Column(name = "TABLE_ID")
	public String getTableId() {
		return tableId;
	}
	public void setTableId(String tableId) {
		this.tableId = tableId;
	}
	@Column(name = "TABLE_NAME")
	public String getTableName() {
		return tableName;
	}
	public void setTableName(String tableName) {
		this.tableName = tableName;
	}
	@Column(name = "FILE_NAME")
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public Date getCreatedtime() {
		return createdtime;
	}
	
	public void setCreatedtime(Date createdtime) {
		this.createdtime = createdtime;
	}
	@Column(name = "FILE_CONTENT")
	public byte[] getFileContent() {
		return fileContent;
	}
	public void setFileContent(byte[] fileContent) {
		this.fileContent = fileContent;
	}
	
}
