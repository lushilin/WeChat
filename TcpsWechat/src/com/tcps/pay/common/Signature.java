package com.tcps.pay.common;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Map;

public class Signature {
	/*
	 * 从map中的数据生成sign,检查微信支付中的签名
	 * q
	 * @param map 回应xml的map形式
	 */
	public static String getSign(Map<String, String> map) {
		ArrayList<String> list = new ArrayList<String>();
		for (Map.Entry<String, String> entry : map.entrySet()) {
			if (entry.getValue() != null && !("".equals(entry.getValue()))) {
				list.add(entry.getKey() + "=" + entry.getValue() + "&");
			}
		}
		int size = list.size();
		String[] arrayToSort = list.toArray(new String[size]);
		Arrays.sort(arrayToSort, String.CASE_INSENSITIVE_ORDER);
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < size; i++) {
			sb.append(arrayToSort[i]);
		}
		String result = sb.toString();
		result += "key=" + Configure.getAppkey();
		String sign = MD5.MD5Encode(result).toUpperCase();
		return sign;
	}

	/*
	 * 检查sign和生成的sign是否一致，不一致说明数据可能被篡改
	 * 
	 * @param respString 返回的xml的string形式
	 */
	public static boolean checkSign(String respString) {
		Map<String, String> map = XMLHandler.XMLStrToMap(respString);
		String sign = map.get("sign").toString();

		if (sign == null || "".equals(sign)) {
			return false;
		} else {
			map.remove("sign");
			String ourSign = getSign(map);
			if (sign.equals(ourSign))
				return true;
		}
		return false;
	}
}
