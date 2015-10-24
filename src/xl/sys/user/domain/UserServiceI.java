package xl.sys.user.domain;

import java.util.List;

import xl.sys.user.model.User;

import com.xl.pageModel.base.PageFilter;
import com.xl.pageModel.base.SessionInfo;



public interface UserServiceI {

	public List<User> dataGrid(User user, PageFilter ph);

	public Long count(User user, PageFilter ph);

	public void add(User user);

	public void delete(String id);

	public void edit(User user);

	public User get(String id);

	public User login(User user);

	public List<String> resourceList(String id);

	public boolean editUserPwd(SessionInfo sessionInfo, String oldPwd, String pwd);

	public User getByLoginName(User user);

}
