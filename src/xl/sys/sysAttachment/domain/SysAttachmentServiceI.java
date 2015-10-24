package xl.sys.sysAttachment.domain;



import java.io.Serializable;

import xl.sys.sysAttachment.model.SysAttachment;


public interface SysAttachmentServiceI {
	public Serializable add(SysAttachment sysAttachment);
	public SysAttachment get(String id);
	public void delete(String id);
}
