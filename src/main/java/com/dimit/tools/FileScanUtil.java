package com.dimit.tools;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.dimit.model.ApplicationContext;
import com.dimit.model.BillFile;
import com.dimit.model.ConfigConstants;
import com.dimit.model.Folder;

/**
 * 文件扫描工具类
 * 
 * @author dimit
 */
public class FileScanUtil {
	private static final Logger logger = LoggerFactory.getLogger(FileScanUtil.class);
	/**
	 * 文件扫描
	 * @param ctx
	 * @return
	 */
	public static List<Folder> scan(ApplicationContext ctx) {
		String path = ctx.getStringProperty(ConfigConstants.SCAN_PATH);
		File parentFile = new File(path);
		List<Folder> result = new ArrayList<>();
		if(parentFile.isDirectory()) {
			String[] list = parentFile.list();
			logger.warn("总共扫描文件个数:[{}]", list.length);
			for(String folderName : list) {
				logger.debug("folderName:" + folderName);
				File subFolder = new File(parentFile, folderName);
				Folder folder = Folder.valueOf(subFolder);
				String[] subList = subFolder.list();
				for(String sub : subList) {
					File file = new File(subFolder, sub);
					BillFile billFile = BillFile.valueOf(file);
					if(billFile.isExcel()) {
						folder.setExcelFile(billFile);
					} else {
						folder.setCallList(billFile);
					}
					logger.debug(billFile.toString());
				}
				result.add(folder);
			}
			return result;
		}
		throw new IllegalArgumentException("扫描更路径应该是一个路径不能是一个指定的文件");
	}
}
