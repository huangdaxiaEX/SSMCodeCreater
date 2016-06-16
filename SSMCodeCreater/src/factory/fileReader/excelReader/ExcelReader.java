package factory.fileReader.excelReader;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import factory.entity.Entity;
import factory.fileReader.IFileReader;
import factory.parse.IParse;
import factory.parse.parseExcel.ParseExcel;

/**
 * 操作Excel表格的功能类
 * 
 * @author huangkai
 * 
 */
public class ExcelReader implements IFileReader{
	public static final String OFFICE_EXCEL_2003_POSTFIX = "xls";
	public static final String OFFICE_EXCEL_2010_POSTFIX = "xlsx";
	public static final String NOT_EXCEL_FILE = " : Not the Excel file!";
	
	@Override
	public List<Entity> readFile(String path) throws IOException {
		// TODO Auto-generated method stub
		if (path == null || path.length() == 0) {
			System.out.println("请输入文件路径");
			return null;
		}
		String[] filePathArr = path.split("\\.");
		String pathType = filePathArr[filePathArr.length - 1];
		if (pathType.equals(OFFICE_EXCEL_2003_POSTFIX)) {
			return readXls(path);
		} else if (pathType.equals(OFFICE_EXCEL_2010_POSTFIX)) {
			return readXlsx(path);
		}
		System.out.println(NOT_EXCEL_FILE);
		return null;
	}
	
	public List<Entity> readXls(String path) throws IOException {
		InputStream is = new FileInputStream(path);
		HSSFWorkbook hssfWorkbook = new HSSFWorkbook(is);
		IParse parse = new ParseExcel(hssfWorkbook);
		return parse.startParse();
	}
	
	public List<Entity> readXlsx(String path) throws IOException {
		InputStream is = new FileInputStream(path);
		XSSFWorkbook xssfWorkbook = new XSSFWorkbook(is);
		IParse parse = new ParseExcel(xssfWorkbook);
		return parse.startParse();
	}
}
