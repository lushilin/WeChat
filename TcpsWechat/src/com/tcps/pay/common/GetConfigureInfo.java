package com.tcps.pay.common;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;
import java.util.ResourceBundle;

import org.omg.CORBA.portable.ValueBase;

public class GetConfigureInfo {

	/**
	 * 通过java.util.resourceBundle来解析properties文件。
	 * 
	 * @param String
	 *            path：properties文件的路径
	 * @param String
	 *            key： 获取对应key的属性
	 * @return String：返回对应key的属性，失败时候为空。
	 */
	public static String getPropertyByName1(String path, String key) {
		String result = null;
		try {
			result = ResourceBundle.getBundle(path).getString(key).trim();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}

	/**
	 * 解析properties文件。
	 * 
	 * @param String
	 *            path：properties文件的路径
	 * @param String
	 *            key： 获取对应key的属性
	 * @return String：返回对应key的属性，失败时候为null。
	 */
	public String getPropertyByName2(String path, String key) {
		String result = null;

		Properties properties = new Properties();
		try {
			InputStream inputStream = this.getClass().getClassLoader()
					.getResourceAsStream(path);
			if (inputStream != null) {
				properties.load(inputStream);
				result = properties.getProperty(key).trim();
				inputStream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return result;
	}

	/**
	 * 写入.properties文件， key=content
	 * 
	 * @param String
	 *            path：写入文件路径
	 * @param String
	 *            key：写入的key
	 * @param String
	 *            content：写入的key的对应内容
	 * @return void
	 */
	public void setProperty(String path, String key, String content) {
		Properties properties = new Properties();
		System.out.println(content);
		try {
			properties.setProperty(key, content);
			// true表示追加。
			if ((new File(path)).exists()) {
				FileOutputStream fileOutputStream = new FileOutputStream(path,
						true);
				properties.store(fileOutputStream, "just for a test of write");
				System.out.println("Write done");
				fileOutputStream.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
