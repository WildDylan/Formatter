package cn.dylan.formatter.utils;

import java.util.Map;

/**
 * 2018/10/18 cn.dylan.formatter.utils
 *
 * @author dylan
 */
public class StringUtils {

	public static boolean notEmpty(String... strings) {
		boolean notEmpty = true;
		for (String s: strings) {
			if ("".equals(s) || s == null) {
				notEmpty = false;
				break;
			}
		}
		return notEmpty;
	}

	public static String replace(String originalStr, String oldStr, String newStr) {
		int p = originalStr.indexOf(oldStr);
		if (p == -1) {
			return originalStr;
		} else {
			StringBuilder result = new StringBuilder();
			result.append(originalStr, 0, p);
			result.append(newStr);
			int q = p + oldStr.length();
			while ((p = originalStr.indexOf(oldStr, q)) != -1) {
				result.append(originalStr, q, p);
				result.append(newStr);
				q = p + oldStr.length();
			}
			result.append(originalStr, q, originalStr.length());
			return result.toString();
		}
	}

}
