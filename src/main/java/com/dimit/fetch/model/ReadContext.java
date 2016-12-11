package com.dimit.fetch.model;

import java.util.ArrayList;
import java.util.List;

/**
 * excel读取上下文
 * 
 * @author dimit
 */
public class ReadContext {
	private List<DataDefine> defines = new ArrayList<>();

	/**
	 * valueOf
	 * @param defines
	 * @return
	 */
	public static ReadContext valueOf(List<DataDefine> defines) {
		ReadContext ctx = new ReadContext();
		ctx.defines = defines;
		return ctx;
	}
}
