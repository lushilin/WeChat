package com.tcps.pay.controller.check;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class SignCheck {
	private static String token = "yyfwechat";

	public static boolean CheckSignature(String signature, String timestamp,
			String nonce) {
		// 1.进行字典排序
		String[] paramArr = { token, timestamp, nonce };
		Arrays.sort(paramArr);

		// 2.进行字符串拼接。
		String linkParam = paramArr[0].concat(paramArr[1]).concat(paramArr[2]);

		// 进行sha-1加密
		/*
		 * String result = null; try { MessageDigest md =
		 * MessageDigest.getInstance("SHA-1"); byte[] digest =
		 * md.digest(linkParam.toString().getBytes()); result =
		 * bytesToString(digest); } catch (NoSuchAlgorithmException e) {
		 * e.printStackTrace(); }
		 */
		String result = SHA1(linkParam);

		// 验证result是否和signature一致
		return result != null ? (result.equals(signature.toUpperCase()) || result
				.equals(signature.toLowerCase())) : false;
	}

	// string的SHA-1加密
	public static String SHA1(String decript) {
		try {
			MessageDigest digest = MessageDigest.getInstance("SHA-1");
			byte messageDigest[] = digest.digest();
			// Create Hex String
			StringBuffer hexString = new StringBuffer();
			// 字节数组转换为 十六进制 数
			for (int i = 0; i < messageDigest.length; i++) {
				String shaHex = Integer.toHexString(messageDigest[i] & 0xFF);
				if (shaHex.length() < 2) {
					hexString.append(0);
				}
				hexString.append(shaHex);
			}
			return hexString.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		return "";
	}

}
