package com.dimit.model;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 应用上下文对象
 * 
 * @author dimit
 */
public enum ApplicationContext {
	INSTANT;

	private static final Logger logger = LoggerFactory.getLogger(ApplicationContext.class);
	public static final String CONFIG_NAME = "config.properties";
	private Properties properties;

	private ApplicationContext() {
		init();
	}

	/**
	 * 初始化上下文
	 */
	private void init() {
		properties = new Properties();
		try {
			properties.load(new InputStreamReader(ApplicationContext.class.getClassLoader().getResourceAsStream(CONFIG_NAME), "UTF-8"));
		} catch (IOException e) {
			logger.error("加载[{}]配置文件时出现位置异常", CONFIG_NAME);
			e.printStackTrace();
		}
	}

	/**
	 * 获取字符串配置
	 * @param key
	 * @return
	 */
	public String getStringProperty(String key) {
		return properties.getProperty(key);
	}
}
