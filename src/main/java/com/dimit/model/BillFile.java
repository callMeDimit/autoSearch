package com.dimit.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import com.dimit.fetch.model.AccountData;
import com.dimit.tools.ExcelReader;

/**
 * 账单文件
 * 
 * @author dimit
 */
public class BillFile {
	public static final String EXCLE_SUFFIX = ".xls";
	public static final String EXCLE_EXTENT_SUFFIX = ".xlsx";
	/** 文件路径 */
	private String path;

	/** 文件名称 */
	private String name;

	/** 文件后缀名 */
	private String suffix;

	/** 账户数据 */
	private AccountData data;

	// logical ...
	/**
	 * 是否是excel文件
	 * 
	 * @return
	 */
	public boolean isExcel() {
		return this.suffix.equals(EXCLE_SUFFIX) || this.suffix.equals(EXCLE_EXTENT_SUFFIX);
	}

	/**
	 * 加载数据
	 * @throws FileNotFoundException 
	 */
	public void load() throws FileNotFoundException {
		File file = new File(path);
		InputStream is = new FileInputStream(file);
		data = ExcelReader.read(is, name);
	}

	// getter ...
	public String getName() {
		return name;
	}

	public String getSuffix() {
		return suffix;
	}

	public AccountData getData() {
		return data;
	}

	public String getPath() {
		return path;
	}

	@Override
	public String toString() {
		return "文件路径:" + path + "   文件名:" + name + "  后缀:" + suffix;
	}

	/**
	 * valueOf...
	 * 
	 * @param name
	 * @param suffix
	 * @return
	 */
	public static BillFile valueOf(String path, String name, String suffix) {
		BillFile file = new BillFile();
		file.name = name;
		file.suffix = suffix;
		return file;
	}

	/**
	 * valueOf...
	 * 
	 * @param name
	 * @param suffix
	 * @return
	 */
	public static BillFile valueOf(File file) {
		BillFile billFile = new BillFile();
		billFile.name = file.getName();
		billFile.path = file.getPath();
		int indexOf = billFile.name.indexOf('.');
		if (indexOf > 0) {
			billFile.suffix = billFile.name.substring(indexOf);
		}
		return billFile;
	}
}
