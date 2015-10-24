package xl.sys.role.domain;

import java.util.List;

import com.xl.pageModel.base.PageFilter;
import com.xl.pageModel.base.Tree;

import xl.sys.role.model.Role;



public interface RoleServiceI {

	public List<Role> dataGrid(Role role, PageFilter ph);

	public Long count(Role role, PageFilter ph);

	public void add(Role role);

	public void delete(String id);

	public void edit(Role role);

	public Role get(String id);

	public void grant(Role role);

	public List<Tree> tree();

}
