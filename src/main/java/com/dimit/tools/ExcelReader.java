package com.dimit.tools;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.helpers.FormattingTuple;
import org.slf4j.helpers.MessageFormatter;

import com.dimit.fetch.anno.FiledDec;
import com.dimit.fetch.model.AccountData;
import com.dimit.fetch.model.DataDefine;

/**
 * Excel格式的静态资源读取器
 * 
 * @author dimit
 */
public class ExcelReader {
	private final static Logger logger = LoggerFactory.getLogger(ExcelReader.class);

	/** 字段定义集合 */
	private static List<DataDefine> defeins = new ArrayList<>();

	/**
	 * 初始化excel读取器
	 * 
	 * @param clz
	 */
	public static <E> void init(Class<E> clz) {
		ReflectUtil.doWithDeclaredFields(AccountData.class, (e) -> {
			FiledDec annotation = e.getAnnotation(FiledDec.class);
			DataDefine define = DataDefine.valueOf(annotation);
			defeins.add(define);
		} , (e) -> {
			FiledDec annotation = e.getAnnotation(FiledDec.class);
			return annotation != null;
		});
	}

	public static AccountData read(InputStream input, String name) {
//		// 基本信息获取
//		Workbook wb = getWorkbook(input, name);
//		Sheet[] sheets = getSheets(wb, name);
//		// 创建返回数据集
//		for (Sheet sheet : sheets) {
//			logger.debug("正在加载资源[{}] - [{}] ...", name, sheet.getSheetName());
//			for (Row row : sheet) {
//				for(DataDefine define : defeins) {
//				}
//			}
//			
//			
//			boolean start = false;
//			int rowNum = 0;
//			// 清除上次表头信息
//			for (Row row : sheet) {
//				// 忽略空行
//				if (row == null) {
//					continue;
//				}
//				// 判断数据行开始没有
//				if (!start) {
//					Cell cell = row.getCell(0);
//					if (cell == null) {
//						continue;
//					}
//					String content = getCellContent(cell);
//					if (content == null) {
//						continue;
//					}
//					if (content.equalsIgnoreCase(tagRow)) {
//						start = true;
//					}
//					continue;
//				}
				// 跳过忽略行
//				rowNum++;
//				if (rowNum <= startRow) {
//					continue;
//				}

				// 读取表头信息
//				if (infos == null) {
//					infos = getCellInfos(sheet, clz);
//				}

				// 忽略单行
//				Cell cell = row.getCell(0);
//				if (cell != null) {
//					String content = getCellContent(cell);
//					if (content != null) {
//						if (content.equalsIgnoreCase(ROW_IGNORE)) {
//							continue;
//						} else if (content.equalsIgnoreCase(ROW_CLIENT)) {
//							continue;
//						} else if (content.equalsIgnoreCase(ROW_MANAGER)) {
//							continue;
//						} else if (content.equalsIgnoreCase(ROW_SERVER)) {
//							continue;
//						} else if (content.equalsIgnoreCase(ROW_END_BEFORE)) {
//							// 忽略本行并结束
//							break;
//						}
//					}
//				}

				// 生成返回对象
//				E instance = newInstance(clz);
//				for (FieldInfo info : infos) {
//					cell = row.getCell(info.index);
//					if (cell == null) {
//						continue;
//					}
//					String content = getCellContent(cell);
//					if (StringUtils.isEmpty(content)) {
//						continue;
//					}
//					try {
//						inject(row, instance, info.field, content);
//					} catch (Exception e) {
//						logger.error("数值表[{}]的[{}]分页第[{}]行的配置内容[{}]错误",
//								new Object[] { clz.getSimpleName(), sheet.getSheetName(), row.getRowNum(), content });
//						throw e;
//					}
//				}
//				if (idGetter.getValue(instance) == null) {
//					logger.error("数值表[{}]的[{}]分页第[{}]行的配置内容[{}]错误 - 主键列NULL", new Object[] { clz.getSimpleName(),
//							sheet.getSheetName(), row.getRowNum(), JsonUtils.object2String(instance) });
//				}
//				result.add(instance);
//
//				// 结束处理
//				cell = row.getCell(0);
//				if (cell == null) {
//					continue;
//				}
//				String content = getCellContent(cell);
//				if (content == null) {
//					continue;
//				}
//				if (content.equalsIgnoreCase(ROW_END)) {
//					break;
//				}
//			}
//		}
		return null;
	}

	/**
	 * 获取字符串形式的单元格内容
	 * 
	 * @param cell
	 * @return
	 */
	private static String getCellContent(Cell cell) {
		int cellType = cell.getCellType();
		if (cellType == Cell.CELL_TYPE_FORMULA) {
			// // 公式类型的强制设置单元格为文本格式
			// cell.setCellType(Cell.CELL_TYPE_STRING);
			// return cell.getStringCellValue();
			cellType = cell.getCachedFormulaResultType();
		}
		switch (cellType) {
		case Cell.CELL_TYPE_BLANK:
			return null;
		case Cell.CELL_TYPE_STRING:
			return cell.getStringCellValue();
		case Cell.CELL_TYPE_BOOLEAN:
			return String.valueOf(cell.getBooleanCellValue());
		case Cell.CELL_TYPE_NUMERIC:
			double v1 = cell.getNumericCellValue();
			Double v2 = Double.valueOf(v1);
			if (v1 == v2.intValue()) {
				return String.valueOf(v2.intValue());
			} else if (v1 == v2.longValue()) {
				return String.valueOf(v2.longValue());
			} else {
				return v2.toString();
			}
		default:
			cell.setCellType(Cell.CELL_TYPE_STRING);
			return cell.getStringCellValue();
		}
	}

	/**
	 * 给实例注入属性
	 * 
	 * @param instance
	 * @param field
	 * @param content
	 */
	/*private void inject(Row row, Object instance, Field field, String content) {
		// Class<?> clz = field.getType();
		try {
			TypeDescriptor targetType = new TypeDescriptor(field);
			Object value = conversionService.convert(content, sourceType, targetType);
			field.set(instance, value);
		} catch (ConverterNotFoundException e) {
			FormattingTuple message = MessageFormatter.format("静态资源[{}]属性[{}]的转换器不存在",
					instance.getClass().getSimpleName(), field.getName());
			logger.error(message.getMessage(), e);
			throw new IllegalStateException(message.getMessage(), e);
		} catch (Exception e) {
			FormattingTuple message = MessageFormatter.format("属性[{}]注入失败", field);
			logger.error(message.getMessage());
			throw new IllegalStateException(message.getMessage(), e);
		}
	}*/

	/**
	 * 实例化资源
	 * 
	 * @param <E>
	 * @param clz
	 * @return
	 */
	private <E> E newInstance(Class<E> clz) {
		try {
			return clz.newInstance();
		} catch (Exception e) {
			FormattingTuple message = MessageFormatter.format("资源[{}]无法实例化", clz);
			logger.error(message.getMessage());
			throw new RuntimeException(message.getMessage());
		}
	}

	/**
	 * 获取表格信息
	 * 
	 * @param sheet
	 * @param clz
	 * @param evaluator
	 * @return
	 */
	/*private Collection<FieldInfo> getCellInfos(Sheet sheet, Class<?> clz) {
		// 获取属性控制行
		Row fieldRow = getFieldRow(sheet, clz);
		if (fieldRow == null) {
			FormattingTuple message = MessageFormatter.format("无法获取资源[{}]的EXCEL文件的属性控制列", clz);
			logger.error(message.getMessage());
			throw new IllegalStateException(message.getMessage());
		}

		// 获取属性信息集合
		List<FieldInfo> result = new ArrayList<FieldInfo>();
		for (int i = 1; i < fieldRow.getLastCellNum(); i++) {
			Cell cell = fieldRow.getCell(i);
			if (cell == null) {
				continue;
			}

			String name = getCellContent(cell);
			if (StringUtils.isBlank(name)) {
				continue;
			}

			try {
				Field field = clz.getDeclaredField(name);
				FieldInfo info = new FieldInfo(i, field);
				result.add(info);
			} catch (Exception e) {
				FormattingTuple message = MessageFormatter.arrayFormat("资源类[{}]分页[{}]的声明属性[{}]无法获取",
						new Object[] { clz, sheet.getSheetName(), name });
				logger.error(message.getMessage());
				throw new IllegalStateException(message.getMessage(), e);
			}
		}
		return result;
	}*/

	/**
	 * 获取属性控制行
	 * 
	 * @param sheet
	 * @param clz
	 * @return
	 */
	/*private Row getFieldRow(Sheet sheet, Class<?> clz) {
		for (Row row : sheet) {
			Cell cell = row.getCell(0);
			if (cell == null) {
				continue;
			}
			String content = getCellContent(cell);
			if (content != null && content.equals(tagRow)) {
				return row;
			}
		}
		return null;
	}*/

	/**
	 * 获取资源类型对应的工作簿
	 * 
	 * @param wb
	 *            Excel Workbook
	 * @param clz
	 *            资源类型
	 * @return
	 */
	/*private static Sheet[] getSheets(Workbook wb, String name) {
		try {
			List<Sheet> result = new ArrayList<Sheet>();
			// 处理多Sheet数据合并
			for (int i = 0; i < wb.getNumberOfSheets(); i++) {
				Sheet sheet = wb.getSheetAt(i);
				result.add(sheet);
			}
			if (result.size() > 0) {
				return result.toArray(new Sheet[0]);
			}
		} catch (Exception e) {
			throw new IllegalArgumentException("无法获取资源类[" + name + "]对应的Excel数据表", e);
		}
	}*/

	/**
	 * 通过输入流获取{@link Workbook}
	 * 
	 * @param input
	 * @return
	 */
	private static Workbook getWorkbook(InputStream input, String fileName) {
		try {
			return WorkbookFactory.create(input);
		} catch (InvalidFormatException e) {
			throw new RuntimeException("静态资源[" + fileName + "]异常,无效的文件格式", e);
		} catch (IOException e) {
			throw new RuntimeException("静态资源[" + fileName + "]异常,无法读取文件", e);
		}
	}
}
