package com.dimit.fetch.model;

import com.dimit.fetch.anno.FiledDec;

/**
 * excel数据定义对象
 * 
 * @author dimit
 */
public class DataDefine {
	/** 行 */
	private int row;
	/** 列 */
	private int col;
	/** 是否是行模式 */
	private boolean rowModel;
	/** 头内容(仅在非行模式下才生效) */
	private String headContent;

	// getter...
	public int getRow() {
		return row;
	}

	public int getCol() {
		return col;
	}

	public boolean isRowModel() {
		return rowModel;
	}

	public String getHeadContent() {
		return headContent;
	}

	/**
	 * @param row
	 * @param col
	 * @param rowModel
	 * @param headContent
	 * @return
	 */
	public static DataDefine valueOf(FiledDec dec) {
		DataDefine define = new DataDefine();
		define.row = dec.row();
		define.col = dec.col();
		define.rowModel = dec.rowModel();
		define.headContent = dec.rowHead();
		return define;
	}
}
