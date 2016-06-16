package factory.stringCaseUtil;

/**
 * String 转化工具类
 * 
 * @author huangkai
 * 
 */
public class StringCaseUtil {
	
	public static String upcaseFirstChar(String s){
		if (s == null || s.equals("")) {
			System.out.println("转换字符参数错误");
			return "";
		}
		
		StringBuilder sb = new StringBuilder(s);
		sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
		return sb.toString();
	}
	
	public static String lowcaseFirstChar(String s){
		if (s == null || s.equals("")) {
			System.out.println("转换字符参数错误");
			return "";
		}
		
		StringBuilder sb = new StringBuilder(s);
		sb.setCharAt(0, Character.toLowerCase(sb.charAt(0)));
		return sb.toString();
	}
	
	public static String upcaseAll(String s){
		if (s == null || s.equals("")) {
			System.out.println("转换字符参数错误 ");
			return "";
		}
		StringBuffer sb = new StringBuffer();
		char[] c = s.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] >= 97) {
				sb.append((c[i] + "").toUpperCase());
			} else {
				sb.append(c[i]);
			}
		}
		return sb.toString();
	}
	
	public static String lowcaseAll(String s){
		if (s == null || s.equals("")) {
			System.out.println("转换字符参数错误 ");
			return "";
		}
		StringBuffer sb = new StringBuffer();
		char[] c = s.toCharArray();
		for (int i = 0; i < c.length; i++) {
			if (c[i] < 97) {
				sb.append((c[i] + "").toLowerCase());
			} else {
				sb.append(c[i]);
			}
		}
		return sb.toString();
	}

	public static boolean toBoolean(String s) {
		if (s == null || s.length() == 0) {
			return false;
		}
		if (s.equals("0")) {
			return false;
		} else if (s.equals("1")) {
			return true;
		}
		return Boolean.parseBoolean(s);
	}
	
	public static int toInt(String s) {
		if (s == null || s.length() == 0) {
			return 0;
		}
		return Integer.parseInt(s);
	}
}
