package com.tcps.pay.common;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class PayUtill {
	public static String randomString(int length) {
		// 规定长度
		if (length > 30 || length < 1) {
			length = 30;
		}
		String template = "abcdefghijklmnopqrstuvwxyz0123456789";
		StringBuffer result = new StringBuffer();

		Random random = new Random();

		for (int i = 0; i < length; i++) {
			result.append(template.charAt(random.nextInt(i + 2)));
		}
		return result.toString();
	}

	/**
	 * 返回yyyyMMddHHmmss格式的时间
	 * 
	 * @return String：返回当下格式为yyyyMMddHHmmss的时间
	 */
	public static String getNowFormalTime() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
				"yyyyMMddHHmmss");
		return simpleDateFormat.format(new Date());
	}

	/**
	 * 返回hhmmss格式的时间
	 * 
	 * @return String：返回当下格式为hhmmss的时间
	 */
	public static String getNowSimpleTime() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HHmmss");
		return simpleDateFormat.format(new Date());
	}

	/**
	 * 获得标准北京时间，时区为东八区，自1970年1月1日 0点0分0秒以来的秒数
	 * 
	 * @return long:返回了1970年1月1日 0点0分0秒以来的秒数
	 */
	public static long getTimeStamp() {
		return (System.currentTimeMillis() / 1000);
	}

	public static Map<String, String> toMap(Object object) {
		Map<String, String> map = new HashMap<String, String>();
		Field[] fields = object.getClass().getDeclaredFields();
		for (Field field : fields) {
			String a;
			try {
				a = (String) field.get(object);
				if (a != null) {
					map.put(field.getName(), a);
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		return map;
	}
}
