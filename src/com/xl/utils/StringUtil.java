package com.xl.utils;

import java.sql.Clob;
import java.sql.SQLException;
import java.util.StringTokenizer;
import java.util.Vector;

import org.hibernate.id.UUIDHexGenerator;

public class StringUtil {

	/**
	 * 格式化字符串
	 * 
	 * 例：formateString("xxx{0}bbb",1) = xxx1bbb
	 * 
	 * @param str
	 * @param params
	 * @return
	 */
	public static String formateString(String str, String... params) {
		for (int i = 0; i < params.length; i++) {
			str = str.replace("{" + i + "}", params[i] == null ? "" : params[i]);
		}
		return str;
	}
	
	/**
	 * java中的空格转换成页面的空格格式
	 * @param str
	 * @return
	 */
	public static String replaceSpace2nbsp(String str) {
        String str1 = str;
        if (str1 == null || str1.trim().equals("") ||
            str1.toLowerCase().equals("null"))
            str1 = "&nbsp;";
        return str1.trim();
    }

	/**
	 * null转换成空格
	 * @param s
	 * @return
	 */
    public static String replaceNull2Space(String s) {
        if (s == null)
            return "";
        if (s.trim().toUpperCase().equals("NULL"))
            return "";
        return s.trim();
    }

    /** 替换字符串中的子字符串，如果oldString和newString为null或者为空的话，不会进行字符串替换操作
	 *  @param line 要进行替换操作的字符串
	 *  @param oldString 要被替换的字符串
	 *  @param newString 要被替换为的字符串
	 *  @return 返回替换后的字符串
	 */
	public static final String replace(String line,String oldString,String newString){
	  String temp="";
	  int i=0;
	  int j=0;
	  if(oldString ==null || oldString.equals(""))
		return line;
	  if(newString == null || newString.equals(""))
		return line;
	  int oldLength= oldString.length();
	  int srcLength= line.length();
	  int totalLength= srcLength;
	  while(i<totalLength){
		j = line.indexOf(oldString,i);
		if( j==-1){
		  temp += line.substring(i,srcLength);
		  break;
		}
		temp += line.substring(i,j)+newString;
		i =j+oldLength;
		j = i;
	  }
	  return temp;
	}
    
	/** 得到相应长度的字符串，被截掉的文字用．．．来代替
	*  @param input 要显示的文字字符串
	*  @length 要显示多少个文字
	*  @return 得到相应长度的字符串
	*/
	private static final String getLimitedLengthString(String input,int length){
	  String tempStr="";
	  int iEnd;
	  tempStr = input;
	  if(tempStr==null || tempStr.equals(""))
		tempStr = "&nbsp;";
	  if( length < 0 )
		return tempStr;
	  iEnd = tempStr.length();
	  if(iEnd >length){
		tempStr = tempStr.substring(0,length);
		tempStr = tempStr+"．．．";
	  }
	  return tempStr;
	}
	
	/**
	 * 将一个整数，转换成固定长度的字符串
	 * @param input 整数
	 * @param length 长度
	 * @return 转换后的字符串
	 */
	public static final String formatIntLength(int input,int length){
	  String strResult=null;
	  if(java.lang.Integer.toString(input).length()>=length){
		strResult=java.lang.Integer.toString(input);
	  }else{
		String zero="";
		for(int i=0;i<length-java.lang.Integer.toString(input).length();i++){
		  zero+="0";
		}
		strResult=zero+input;
	  }
	  return strResult;
	}
	
	/**
	 * 从一个字符串的队列中，找到某个字符串的位置
	 * @param strArr 字符串队列
	 * @param str 某个特定的字符串
	 * @return 字符串的位置，如果队列中没有此字符串，则返回-1
	 */
	public static final int getPosition(String[] strArr,String str){
	  int result=-1;
	  for(int i=0;i<strArr.length;i++){
		if(strArr[i].equalsIgnoreCase(str)){
		  result=i;
		  break;
		}
	  }
	  return result;
	}
	
	/**
	 * 删除一个String集合中的一个String子串
	 * @param strTotal String集合
	 * @param strDel 想要删除的子字符串
	 * @param splitSign 分割标识符
	 * @return String 删除后的字符串集合
	 */
	public static  String delSubStr(String strTotal,String strDel,String splitSign){
	  String resultStr="";
	  String strTemp;
	  StringTokenizer st = new StringTokenizer(strTotal,splitSign);
	  while (st.hasMoreElements()) {
		strTemp = (String) st.nextElement();
		if(!strTemp.equals(strDel)){
		  resultStr+=strTemp+";";
		}
	  }
	  resultStr = resultStr.substring(0, resultStr.length() - 1);
	  return resultStr;
	}
	
	/**
	 * 判断一个String集合包含的子串个数
	 * @param strTotal String集合
	 * @param splitSign 分割标识符
	 * @return 包含返回true，不包含返回false
	 */
	public static  int getElementsNUM(String strTotal,String splitSign){
	  int k=0;
	  StringTokenizer st = new StringTokenizer(strTotal,splitSign);
	  while (st.hasMoreElements()) {
		k++;
		st.nextElement();
	  }
	  return k;
	}
	
	/**
	 * 删除String集合的相同的元素
	 * @param strTotal String集合
	 * @param splitSign 分割标识符
	 * @return String 重新整理过的字符串
	 */
	public static String delSameElement(String strTotal,String splitSign){
	  String resultStr="";
	  String strtmp="";
	  Vector v=new Vector();
	  if(strTotal==null||strTotal.equals("")||splitSign==null||splitSign.equals("")){
		return "";
	  }
	  int find;
	  String[] strlist=strTotal.split(splitSign);
	  for(int i=0;i<strlist.length;i++){
		find=0;
		strtmp=strlist[i];
		for(int j=0;j<v.size();j++){
		  if(v.elementAt(j).toString().equals(strtmp)){
			find=1;
			break;
		  }
		}
		if(find==0){
		  v.addElement(strtmp);
		}
	  }
	  for(int k=0;k<v.size();k++){
		resultStr+=";"+(String)v.elementAt(k);
	  }
	  if(!resultStr.equals("")){
		resultStr=resultStr.substring(1,resultStr.length());
	  }
	  return resultStr;
	}
	public static String getRandomStr(){
		UUIDHexGenerator uuid = new UUIDHexGenerator();
        return (String) uuid.generate(null, uuid);
	}
	/**
	 * 将串"str1;str2;str3"转化为 'str1','str2','str3'形式
	 * @param strList
	 * @return
	 */
	 public static String changeStrInQuery(String strList,String regex) {
	        if (null == strList || "".equals(strList)) {
	            return null;
	        }
	        strList = strList.trim();
	        while(strList.indexOf(regex)==0){
	        	strList = strList.substring(1,strList.length());
	        }
	        while((strList.substring(strList.length()-1)).equals(regex)){
	        	strList = strList.substring(0, (strList.length()-1));
	        }
	        strList = "'" +replace(strList, regex, "','") + "'";
	        return strList;
	    }
}
