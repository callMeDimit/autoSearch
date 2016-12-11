package com.dimit.model;

import java.io.File;
import java.io.FileNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 账户文件夹
 * 
 * @author dimit
 */
public class Folder {
	public static final Logger logger = LoggerFactory.getLogger(Folder.class);
	/** 文件路径 */
	private String path;

	/** 文件名称 */
	private String name;

	/** 对账单文件 */
	private BillFile excel;

	/** 通话清单文件 */
	private BillFile callList;

	/**
	 * 设置excel文件
	 * 
	 * @param excel
	 */
	public void setExcelFile(BillFile excel) {
		this.excel = excel;
	}

	/**
	 * 设置通话清单文件
	 * 
	 * @param callList
	 */
	public void setCallList(BillFile callList) {
		this.callList = callList;
	}

	/**
	 * 加载数据
	 */
	public void load() {
		try {
			excel.load();
		} catch (FileNotFoundException e) {
			logger.error("加载文件[{}]时未找到文件", excel.getName());
			e.printStackTrace();
		}
	}

	// getter...
	public String getPath() {
		return path;
	}

	public String getName() {
		return name;
	}

	public BillFile getExcel() {
		return excel;
	}

	public BillFile getCallList() {
		return callList;
	}

	@Override
	public String toString() {
		return "文件夹名称: " + name + " excel 文件:" + excel.toString() + " 通话清单文件:" + callList.toString();
	}

	/**
	 * valueOf...
	 * 
	 * @param path
	 * @param name
	 * @param excel
	 * @param callList
	 * @return
	 */
	public static Folder valueOf(String path, String name, BillFile excel, BillFile callList) {
		Folder folder = new Folder();
		folder.path = path;
		folder.name = name;
		folder.excel = excel;
		folder.callList = callList;
		return folder;
	}

	/**
	 * valueOf...
	 * 
	 * @param file
	 * @return
	 */
	public static Folder valueOf(File file) {
		Folder folder = new Folder();
		folder.path = file.getPath();
		folder.name = file.getName();
		return folder;
	}
}
