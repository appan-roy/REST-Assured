package utility;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel_Utils {
	
	static XSSFWorkbook wrkbk;
	
	static XSSFSheet sht;
	
	public Excel_Utils(String excelPath, String sheetName) throws Exception {
		
		wrkbk = new XSSFWorkbook(excelPath);
		
		sht = wrkbk.getSheet(sheetName);
		
	}
	
	public int getRowCount() {
		
		int rowCount = sht.getPhysicalNumberOfRows();
		
		return rowCount;
		
	}
	
	public Object getCellData(int rowNum, int colNum) {
		
		DataFormatter formatter = new DataFormatter();
		
		Object val = formatter.formatCellValue(sht.getRow(rowNum).getCell(colNum));
		
		return val;
		
	}
	
}
