package xl.sys.resource.domain;

import java.util.List;

import com.xl.pageModel.base.SessionInfo;
import com.xl.pageModel.base.Tree;

import xl.sys.resource.model.Resource;


public interface ResourceServiceI {

	public List<Resource> treeGrid();

	public void add(Resource resource);

	public void delete(String id);

	public void edit(Resource resource);

	public Resource get(String id);

	public List<Tree> tree(SessionInfo sessionInfo);

	public List<Tree> allTree(boolean flag);

	public List<String> resourceAllList();

}
