package xl.sys.dictionary.domain;

import java.util.List;

import com.xl.pageModel.base.PageFilter;

import xl.sys.dictionary.model.Dictionary;



public interface DictionaryServiceI {

	public List<Dictionary> dataGrid(Dictionary dictionary, PageFilter ph);

	public Long count(Dictionary dictionary, PageFilter ph);

	public void add(Dictionary dictionary);

	public void delete(String id);

	public void edit(Dictionary dictionary);

	public Dictionary get(String id);

	public List<Dictionary> combox(String code);

	public Dictionary checkUnique(Dictionary dictionary);

}
