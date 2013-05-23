package cn.haohaowo.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class StringUtils {
	public static boolean isEmpty(String content) {
		return content == null || "".equals(content.trim());
	}
	
	public static boolean isNotEmpty(String content) {
		return !isEmpty(content);
	}
	
	public static String likeString(String content) {
		if (isEmpty(content)) content = "";
		
		return "%" + content.trim() + "%";
	}
	
	public static String generateOrderNumber() {
		return new SimpleDateFormat("yyyyMMddwDFHHmmssSSS").format(new Date());
	}
	
	public static String returnEmpty(String content) {
		if (isEmpty(content)) content = "";
		return content;
	}
}
