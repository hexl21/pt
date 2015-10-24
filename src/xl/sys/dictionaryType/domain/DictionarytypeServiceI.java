package xl.sys.dictionaryType.domain;

import java.util.List;

import com.xl.pageModel.base.Tree;

import xl.sys.dictionaryType.model.Dictionarytype;





public interface DictionarytypeServiceI {


	public void add(Dictionarytype dictionarytype);

	public void delete(Long id);

	public void edit(Dictionarytype dictionarytype);

	public Dictionarytype get(Long id);

	public List<Tree> tree();


}
