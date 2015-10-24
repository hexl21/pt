package xl.sys.sysAttachment.domain.impl;
import java.io.Serializable;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.xl.dao.BaseDaoI;
import xl.sys.sysAttachment.domain.SysAttachmentServiceI;
import xl.sys.sysAttachment.model.SysAttachment;
import xl.sys.sysAttachment.model.TsysAttachment;

@Service
@SuppressWarnings("unchecked")
public class SysAttachmentServiceImpl implements SysAttachmentServiceI {

	@Autowired
	private BaseDaoI<TsysAttachment> sysAttachmentDao;
	
	
	@Override
	public Serializable add(SysAttachment sysAttachment) {
		TsysAttachment tss = new TsysAttachment();
		BeanUtils.copyProperties(sysAttachment, tss);
		Serializable s = sysAttachmentDao.save(tss);
		return s;
	}
	@Override
	public SysAttachment get(String id) {
		SysAttachment sa = new SysAttachment();
		TsysAttachment tsa = sysAttachmentDao.get(
				TsysAttachment.class, id);
		BeanUtils.copyProperties(tsa, sa);
		return sa;
	}
	@Override
	public void delete(String id) {
		TsysAttachment tsa = sysAttachmentDao.get(
				TsysAttachment.class, id);
		sysAttachmentDao.delete(tsa);
	}
	
}