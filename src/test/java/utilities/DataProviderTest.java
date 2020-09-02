package utilities;

import java.io.IOException;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import pages.GoogleHome;

public class DataProviderTest extends WebTestBase
{
	static int StartRow = 3;
	static int EndRow = 6;
	static int TotalRows = EndRow-StartRow+1;
	static int StartCol = 3;
	static int EndCol = 4;
	static int TotalCols = EndCol-StartCol+1;
	static String Path = System.getProperty("user.dir") + "/Inputs/" + "Google.xlsx";
	
	@DataProvider(name = "GoogleSearch")
	public static Object[][] credentials() throws IOException 
	{
		return Excel.provideData(Path, StartRow, EndRow, StartCol, EndCol);
	/*	Excel.setPath(Path);
		String[][] DataArray=new String[TotalRows][TotalCols];
		for(int i = 0; i<TotalRows; i++)
		{
			for(int j = 0; j<TotalCols; j++)
			{
				DataArray[i][j] = Excel.read(i+StartRow, j+StartCol); 
			}	
		}
        return DataArray;*/
        //return new Object[][] {{"Marvel","Avengers"}, {"DC","Batman"}};
	}

	// Here we are calling the Data Provider object with its Name
	@Test(dataProvider = "GoogleSearch")
	public void TrialTest(String sUsername, String sPassword) 
	{
		GoogleHome GoogleHomeElementObject = new GoogleHome(driver);
		driver.get("https://www.google.com");
		GoogleHome.searchBox.sendKeys(sUsername + " " + sPassword);
		GoogleHome.searchButton.click();
	}}
