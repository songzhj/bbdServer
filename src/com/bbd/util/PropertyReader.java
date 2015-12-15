package com.bbd.util;

import java.io.IOException;

import java.io.InputStream;

import java.util.Properties;

/**
 * 读取properties文件
 * @author szhj
 *
 */

public class PropertyReader implements java.io.Serializable {
	
	private Properties prop;
	private InputStream is;
	
	public PropertyReader(String filename)
	{
		prop = new Properties();
		is = getClass().getResourceAsStream(filename);
		try {
			prop.load(is);
			if (is != null)
				is.close();
		} catch (IOException e) {
			System.out.print(e.getMessage());
			e.printStackTrace();
		}
	}

	public String getProperties(String PropertyName)
	{
		return prop.getProperty(PropertyName);
	}
	
}