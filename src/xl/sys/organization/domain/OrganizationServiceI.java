package xl.sys.organization.domain;

import java.util.List;

import xl.sys.organization.model.Organization;


import com.xl.pageModel.base.Tree;



public interface OrganizationServiceI {

	public List<Organization> treeGrid();

	public void add(Organization organization);

	public void delete(String id);

	public void edit(Organization organization);

	public Organization get(String id);

	public List<Tree> tree();

}
