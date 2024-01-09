/**
 * 
 */
package com.seda.commons.regex;

import java.text.MessageFormat;

/**
 * @author dbadm
 *
 */
public class RegexManagerUnitTest {

	private final static String message="Regex {0} tested for \"{1}\": {2}";
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new RegexManagerUnitTest(args);
	}

	

	public RegexManagerUnitTest(String[] args) {
//		test(RegexManager.css_simple, "/bap/manager/user.do?action=new&customerKey_filter=R0>%22%27><img%20src%3d%22javascript:alert(16128)%22>");
//		test(RegexManager.css_simple, "<SCRIPT>");		
//		test(RegexManager.css_simple, "%3C%73%63%72%69%70%74%3E");		
//		test(RegexManager.css_simple, "%3c%73%63%72%69%70%74%3e");		
//		test(RegexManager.css_img,"<img src");
//		test(RegexManager.css_paranoid,"R0>\"\'><img src=\"javascript:alert(16128)\">>");
//		test(RegexManager.sql_meta_character,"; -- \n select * from pippo");
//		test(RegexManager.sql_union,"'union a"); 
//		test(RegexManager.sql_or,"'or'1'='1'");
//		
////		System.out.println("<img".matches("((\\%3C)|<)((\\%69)|i|(\\%49))((\\%6D)|m|(\\%4D))((\\%67)|g|(\\%47))[^\n]+((\\%3E)|>)"));
//		System.out.println("<img".matches("img"));		
	}	
	
	
	public void test(RegexManager regex, String value) {
		String name = regex.name();
		boolean result = regex.matches(value);
		print(name,value,result);
	}

	private void print(Object... arguments){
		System.out.println(MessageFormat.format(message, arguments));
	}
}
