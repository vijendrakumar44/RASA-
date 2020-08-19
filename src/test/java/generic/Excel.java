package generic;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel implements Auto_const {

	public static String readData(String sheetName, int row, int cell) {
		XSSFWorkbook workbook=null;
		String val="";
		try {
			FileInputStream fis = new FileInputStream(excel_path);
			workbook = new XSSFWorkbook(fis);
			 val = workbook.getSheet(sheetName).getRow(row).getCell(cell).getStringCellValue();
		} catch (Exception e) {

			System.out.println("failed to read data from excel");
		} finally {
			try {
				workbook.close();
			} catch (IOException e) {

				System.out.println("failed to close excel");
			}
		}
		return val;
	}

}
