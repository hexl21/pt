package xl.gem.gemPlan.controller;

import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;

public class Test {
	public static void main(String[] args) {
		Document document = DocumentHelper.createDocument();
		Element root = document.addElement("chart");
		root.addAttribute("xAxisMinValue", "0");
		root.addAttribute("xAxisMaxValue", "100");
		root.addAttribute("yAxisMinValue", "0");
		root.addAttribute("yAxisMaxValue", "100");
		root.addAttribute("showFormBtn", "0");
		root.addAttribute("enableLinks", "1");
		root.addAttribute("viewMode", "0");
		root.addAttribute("is3D", "1");
		root.addAttribute("borderAlpha", "0");
		root.addAttribute("bgColor", "EEEEEE");
		root.addAttribute("bgAlpha", "0");
		root.addAttribute("canvasBgAlpha", "0");
		root.addAttribute("canvasBorderAlpha", "0");

		Element dataSet = root.addElement("dataset");
		dataSet.addAttribute("seriesName", "");
				Element set = dataSet.addElement("set");
						set.addAttribute("color", "999999");
						set.addAttribute("link","");
						set.addAttribute("color", "999999");
						set.addAttribute("x", "333");
						set.addAttribute("y", "3333");
						set.addAttribute("width", "80");
						set.addAttribute("height", "50");
						set.addAttribute("name", "3333333333");
						set.addAttribute("alpha", "80");
						set.addAttribute("id", "1");
						//画图形间的连接线
						Element connectors = root.addElement("connectors");
						connectors.addAttribute("color", "FF0000");
						connectors.addAttribute("stdThickness", "5");
						
						Element set1 = connectors.addElement("connector");
						set1.addAttribute("strength", "0.5");
						set1.addAttribute("label", "");
						set1.addAttribute("from", "2");
						set1.addAttribute("to", "1");
						set1.addAttribute("color", "BBBB00");
						set1.addAttribute("arrowAtEnd", "0");
						set1.addAttribute("arrowAtStart", "1");
		//设置字体样式
		Element styles = root.addElement("styles");
		Element definition = styles.addElement("definition");
		Element style = definition.addElement("style");
		style.addAttribute("name", "myHTMLFont");
		style.addAttribute("type", "font");
		style.addAttribute("font", "Verdana");
		style.addAttribute("size", "12");
		style.addAttribute("color", "000000");
		style.addAttribute("isHTML", "1");
		Element application = styles.addElement("application");
		Element apply = application.addElement("apply");
		apply.addAttribute("toObject", "DATALABELS");
		apply.addAttribute("styles", "myHTMLFont");
		apply.addAttribute("font", "Verdana");
		apply.addAttribute("size", "14");
		Element apply2 = application.addElement("apply");
		apply2.addAttribute("toObject", "TOOLTIP");
		apply2.addAttribute("styles", "myHTMLFont");
		apply2.addAttribute("font", "Verdana");
		apply2.addAttribute("size", "14");
		Element apply3 = application.addElement("apply");
		apply3.addAttribute("toObject", "CONNECTORLABELS");
		apply3.addAttribute("styles", "myHTMLFont");
		apply3.addAttribute("font", "Verdana");
		apply3.addAttribute("size", "14");
		String s = root.asXML();
		System.out.println(s);
	}
}
