package com.dimit;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dimit.fetch.model.AccountData;
import com.dimit.model.ApplicationContext;
import com.dimit.model.Folder;
import com.dimit.tools.ExcelReader;
import com.dimit.tools.FileScanUtil;

/**
 * 入口类
 * 
 * @author dimit
 *
 */
public class Main {
	private static final Logger logger = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) {
		logger.debug("autoSearch开启");
		ApplicationContext ctx = ApplicationContext.INSTANT;
		List<Folder> folders = FileScanUtil.scan(ctx);
		ExcelReader.init(AccountData.class);
		folders.forEach(e -> {
			logger.debug(e.toString());
			e.load();
		});
	}
}
