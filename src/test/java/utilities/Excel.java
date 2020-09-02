package utilities;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Iterator;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class Excel 
{
	static String filePath = "0";
	static XSSFWorkbook wb;
	
	public static void setPath(String filePath) throws IOException
	{
		File InputsFile = new File(filePath);
		FileInputStream fip = new FileInputStream(InputsFile);
		wb = new XSSFWorkbook(fip); //HSSFWorkbook for xls format, XSSFWorkbook for xlsx format
	}
	
	public static String read(int rowNumber, int columnNumber) throws IOException
	{
		String cellData = "2";
		//Excel Initialization
	/*	
		String filePath = System.getProperty("user.dir")+"/TestCases/"+"Regression.xlsx";
		File inputsFile = new File(filePath);
		FileInputStream fip = new FileInputStream(inputsFile);
		xwb = new XSSFWorkbook(fip); //HSSFWorkbook for xls format, XSSFWorkbook for xlsx format
	*/	
		Sheet sheet = wb.getSheetAt(0);
		Row row = sheet.getRow(rowNumber);
		//Iterator<Row> rowIt = sheet.rowIterator();
		
		//while(rowIt.hasNext())
		//{
			//row = rowIt.next();
			Cell cell = row.getCell(columnNumber);
			//Iterator<Cell> cellIt = row.cellIterator();

			if (cellData != null && cell != null) //for NullPoiterException
				cellData = cell.getStringCellValue().toString();
			//while(cellIt.hasNext())
			//{
				//cell = cellIt.next();
			//}
		//}
		return cellData;
	}
	//Loop method to be used in data provider
	public static Object[][] provideData(String filePath, int startRow, int endRow, int startCol, int endCol) throws IOException
	{
		setPath(filePath);
		int totalRows = endRow-startRow+1;
		int totalCols = endCol-startCol+1;
		String[][] dataArray=new String[totalRows][totalCols];
		for(int i = 0; i<totalRows; i++)
		{
			for(int j = 0; j<totalCols; j++)
			{
				dataArray[i][j] = Excel.read(i+startRow, j+startCol); 
			}	
		}
        return dataArray;
	}
	
	public static void main( String[] args ) throws IOException
    {
		String filePath = System.getProperty("user.dir")+"/TestCases/"+"Regression.xlsx";
		setPath(filePath);
		
    	Excel excelObj = new Excel();
    	String cellDataValue = "1";
    	for (int i = 2; i< 7; i++)
    	{
    		for (int j = 3; j< 5; j++)
    		{
        		cellDataValue = excelObj.read(2, j);
        		System.out.println(cellDataValue);
    		}
    	}
    }
}
