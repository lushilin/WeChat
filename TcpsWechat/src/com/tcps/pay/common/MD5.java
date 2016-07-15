package com.tcps.pay.common;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5 {
	public static final String[] hexArray = { "0", "1", "2", "3", "4", "5",
			"6", "7", "8", "9", "a", "b", "c", "d", "e", "f" };

	public static String MD5Encode(String origin) {
		String result = "";
		try {
			MessageDigest md5Digest = MessageDigest.getInstance("MD5");
			result = byteArrayToHexString(md5Digest.digest(origin.getBytes("utf-8")));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

	public static String byteArrayToHexString(byte[] digest) {
		StringBuilder sb = new StringBuilder();
		for (byte b : digest) {
			sb.append(byteToHexString(b));
		}
		return sb.toString();
	}

	public static String byteToHexString(byte b) {
		int temp = b;
		if (temp < 0) {
			temp += 256;
		}
		return (hexArray[temp / 16] + hexArray[temp % 16]);
	}
}
