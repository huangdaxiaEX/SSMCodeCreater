package factory.parse.parseExcel;

import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import factory.entity.Entity;
import factory.entity.Field;
import factory.parse.IParse;
import factory.stringCaseUtil.StringCaseUtil;

/**
 * 解析excel
 * 
 * @author huangkai
 * 
 */
public class ParseExcel implements IParse {
	private HSSFWorkbook hssfWorkbook = null;
	private XSSFWorkbook xssfWorkbook = null;

	public ParseExcel(HSSFWorkbook hssfWorkbook) {
		// TODO Auto-generated constructor stub
		this.hssfWorkbook = hssfWorkbook;
	}

	public ParseExcel(XSSFWorkbook xssfWorkbook) {
		this.xssfWorkbook = xssfWorkbook;
	}

	@Override
	public List<Entity> startParse() {
		// TODO Auto-generated method stub
		if (this.hssfWorkbook != null) {
			return parseXls();
		} else if (this.xssfWorkbook != null) {
			return parseXlsx();
		} else {
			System.out.println("解析失败");
			return null;
		}

	}
	
	/**
	 * 解析xlsx格式的excel
	 * 
	 * @return
	 */
	public List<Entity> parseXlsx() {
		List<Entity> entityList = new ArrayList<Entity>();
		// 读取 sheet
		for (int sheetCount = 0; sheetCount < xssfWorkbook.getNumberOfSheets(); sheetCount++) {
			XSSFSheet xssfSheet = xssfWorkbook.getSheetAt(sheetCount);
			if (xssfSheet == null) {
				continue;
			}
			// Read the Row
			Entity entity = new Entity();
			List<Field> fieldList = new ArrayList<Field>();
			for (int rowNum = 1; rowNum <= xssfSheet.getLastRowNum(); rowNum++) {
				XSSFRow xssfRow = xssfSheet.getRow(rowNum);
				if (xssfRow != null) {
					int cellNumber = xssfRow.getRowNum();
					switch (cellNumber) {
					// 表名
					case 2:
						entity.setEntityName(getValue(xssfRow.getCell(3)));
						break;
					case 3:
						break;
					case 4:
						entity.setEntitySteate(getInt(getValue(xssfRow
								.getCell(3))));
						break;
					case 5:
						entity.setAuthor(getValue(xssfRow.getCell(3)));
						break;
					case 6:
						break;

					default:
						Field field = new Field();
						field.setFieldName(getValue(xssfRow.getCell(0)));
						field.setFieldTag(getValue(xssfRow.getCell(1)));
						field.setFieldType(getValue(xssfRow.getCell(2)));
						field.setFieldTypeCount(getValue(xssfRow.getCell(3)));
						String editTag = getValue(xssfRow.getCell(4));
						if (editTag.equals("9")) {
							field.getEditConstraints().add("p");
						}
						field.setPrimaryKey(getBoolean(editTag));
						editTag = getValue(xssfRow.getCell(5));
						if (editTag.equals("9")) {
							field.getEditConstraints().add("u");
						}
						field.setUnique(getBoolean(editTag));
						editTag = getValue(xssfRow.getCell(6));
						if (editTag.equals("9")) {
							field.getEditConstraints().add("n");
						}
						field.setNotNull(getBoolean(editTag));
						editTag = getValue(xssfRow.getCell(7));
						if (editTag.equals("9")) {
							field.getEditConstraints().add("f");
						}
						field.setForignKey(getBoolean(editTag));
						field.setForignCondition(getValue(xssfRow.getCell(8)));
						editTag = getValue(xssfRow.getCell(9));
						if (editTag.equals("9")) {
							field.getEditConstraints().add("c");
						}
						field.setCheck(getBoolean(editTag));
						field.setCheckCondition(getValue(xssfRow.getCell(10)));
						editTag = getValue(xssfRow.getCell(11));
						if (editTag.equals("9")) {
							field.getEditConstraints().add("d");
						}
						field.setDefault(getBoolean(editTag));
						field.setDefaultCondition(getValue(xssfRow.getCell(12)));

						fieldList.add(field);
						break;
					}
				}// end if

			} // end for
			entity.setFields(fieldList);
			entityList.add(entity);
		}

		return entityList;
	}

	/**
	 * 解析xls格式的excel
	 * 
	 * @return
	 */
	public List<Entity> parseXls() {
		List<Entity> entityList = new ArrayList<Entity>();
		// 读取 sheet
		for (int sheetCount = 0; sheetCount < hssfWorkbook.getNumberOfSheets(); sheetCount++) {
			HSSFSheet hssfSheet = hssfWorkbook.getSheetAt(sheetCount);
			if (hssfSheet == null) {
				continue;
			}
			// Read the Row
			Entity entity = new Entity();
			List<Field> fieldList = new ArrayList<Field>();
			for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++) {
				HSSFRow hssfRow = hssfSheet.getRow(rowNum);
				if (hssfRow != null) {
					int cellNumber = hssfRow.getRowNum();
					switch (cellNumber) {
					// 表名
					case 2:
						entity.setEntityName(getValue(hssfRow.getCell(3)));
						break;
					case 3:
						break;
					case 4:
						entity.setEntitySteate(getInt(getValue(hssfRow
								.getCell(3))));
						break;
					case 5:
						entity.setAuthor(getValue(hssfRow.getCell(3)));
						break;
					case 6:
						break;

					default:
						Field field = new Field();
						field.setFieldName(getValue(hssfRow.getCell(0)));
						field.setFieldTag(getValue(hssfRow.getCell(1)));
						field.setFieldType(getValue(hssfRow.getCell(2)));
						field.setFieldTypeCount(getValue(hssfRow.getCell(3)));
						String editTag = getValue(hssfRow.getCell(4));
						if (editTag.equals("9")) {
							field.getEditConstraints().add("p");
						}
						field.setPrimaryKey(getBoolean(editTag));
						editTag = getValue(hssfRow.getCell(5));
						if (editTag.equals("9")) {
							field.getEditConstraints().add("u");
						}
						field.setUnique(getBoolean(editTag));
						editTag = getValue(hssfRow.getCell(6));
						if (editTag.equals("9")) {
							field.getEditConstraints().add("n");
						}
						field.setNotNull(getBoolean(editTag));
						editTag = getValue(hssfRow.getCell(7));
						if (editTag.equals("9")) {
							field.getEditConstraints().add("f");
						}
						field.setForignKey(getBoolean(editTag));
						field.setForignCondition(getValue(hssfRow.getCell(8)));
						editTag = getValue(hssfRow.getCell(9));
						if (editTag.equals("9")) {
							field.getEditConstraints().add("c");
						}
						field.setCheck(getBoolean(editTag));
						field.setCheckCondition(getValue(hssfRow.getCell(10)));
						editTag = getValue(hssfRow.getCell(11));
						if (editTag.equals("9")) {
							field.getEditConstraints().add("d");
						}
						field.setDefault(getBoolean(editTag));
						field.setDefaultCondition(getValue(hssfRow.getCell(12)));

						fieldList.add(field);
						break;
					}
				}// end if

			} // end for
			entity.setFields(fieldList);
			entityList.add(entity);
		}

		return entityList;
	}

	/**
	 * 获取xlsx格式的excel单元值
	 * 
	 * @param xssfRow
	 * @return
	 */
	@SuppressWarnings("static-access")
	private String getValue(XSSFCell xssfRow) {
		try {
			if (xssfRow.getCellType() == xssfRow.CELL_TYPE_BOOLEAN) {
				
				return String.valueOf(xssfRow.getBooleanCellValue()).split("\\.")[0];
			} else if (xssfRow.getCellType() == xssfRow.CELL_TYPE_NUMERIC) {
				return String.valueOf(xssfRow.getNumericCellValue()).split("\\.")[0];
			} else {
				return String.valueOf(xssfRow.getStringCellValue()).split("\\.")[0];
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "";
	}

	/**
	 * 获取xls格式的excel单元值
	 * 
	 * @param hssfCell
	 * @return
	 */
	@SuppressWarnings("static-access")
	private String getValue(HSSFCell hssfCell) {
		try {
			if (hssfCell.getCellType() == hssfCell.CELL_TYPE_BOOLEAN) {
				return String.valueOf(hssfCell.getBooleanCellValue()).split("\\.")[0];
			} else if (hssfCell.getCellType() == hssfCell.CELL_TYPE_NUMERIC) {
				return String.valueOf(hssfCell.getNumericCellValue()).split("\\.")[0];
			} else {
				return String.valueOf(hssfCell.getStringCellValue()).split("\\.")[0];
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		return "";
	}

	/**
	 * 辅助转化boolean
	 * 
	 * @param s
	 * @return
	 */
	public boolean getBoolean(String s) {
		return StringCaseUtil.toBoolean(s);
	}

	/**
	 * 辅助转化int
	 * 
	 * @param s
	 * @return
	 */
	public int getInt(String s) {
		return StringCaseUtil.toInt(s);
	}
}
