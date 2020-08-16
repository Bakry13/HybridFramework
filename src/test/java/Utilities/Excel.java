package Utilities;

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
	
	public static String read(int RowNumber, int ColumnNumber) throws IOException
	{
		String CellData = "2";
		//Excel Initialization
	/*	
		String filePath = System.getProperty("user.dir")+"/TestCases/"+"Regression.xlsx";
		File InputsFile = new File(filePath);
		FileInputStream fip = new FileInputStream(InputsFile);
		Xwb = new XSSFWorkbook(fip); //HSSFWorkbook for xls format, XSSFWorkbook for xlsx format
	*/	
		Sheet sheet = wb.getSheetAt(0);
		Row row = sheet.getRow(RowNumber);
		//Iterator<Row> rowIt = sheet.rowIterator();
		
		//while(rowIt.hasNext())
		//{
			//row = rowIt.next();
			Cell cell = row.getCell(ColumnNumber);
			//Iterator<Cell> cellIt = row.cellIterator();

			if (CellData != null && cell != null) //for NullPoiterException
				CellData = cell.getStringCellValue().toString();
			//while(cellIt.hasNext())
			//{
				//cell = cellIt.next();
			//}
		//}
		return CellData;
	}
	//Loop method to be used in data provider
	public static Object[][] ProvideData(String filePath, int StartRow, int EndRow, int StartCol, int EndCol) throws IOException
	{
		setPath(filePath);
		int TotalRows = EndRow-StartRow+1;
		int TotalCols = EndCol-StartCol+1;
		String[][] DataArray=new String[TotalRows][TotalCols];
		for(int i = 0; i<TotalRows; i++)
		{
			for(int j = 0; j<TotalCols; j++)
			{
				DataArray[i][j] = Excel.read(i+StartRow, j+StartCol); 
			}	
		}
        return DataArray;
	}
	
	public static void main( String[] args ) throws IOException
    {
		String filePath = System.getProperty("user.dir")+"/TestCases/"+"Regression.xlsx";
		setPath(filePath);
		
    	Excel ExcelObj = new Excel();
    	String CellDataValue = "1";
    	for (int i = 2; i< 7; i++)
    	{
    		for (int j = 3; j< 5; j++)
    		{
        		CellDataValue = ExcelObj.read(2, j);
        		System.out.println(CellDataValue);
    		}
    	}
    }
}
