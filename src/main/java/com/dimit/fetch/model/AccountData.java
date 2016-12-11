package com.dimit.fetch.model;

import com.dimit.fetch.anno.FiledDec;

/**
 * 账户数据
 * 
 * @author dimit
 */
public class AccountData {
	/** 联系人名称 */
	@FiledDec(row = 2, col = 4)
	private String contacts;

	/** 邮箱 */
	@FiledDec(row = 4, col = 4)
	private String email;

	/** 账户余额 */
	@FiledDec(rowModel = false, rowHead = "合计", col = 10)
	private double balance;

	// getter ...
	public String getContacts() {
		return contacts;
	}

	public String getEmail() {
		return email;
	}

	public double getBalance() {
		return balance;
	}

}
