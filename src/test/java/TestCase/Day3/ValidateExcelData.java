package TestCase.Day3;

import Utility.connection;
import com.google.gson.JsonObject;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONTokener;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.apache.poi.ss.usermodel.*;

import java.io.*;

public class ValidateExcelData {

    public static WebDriver driver;

    public ValidateExcelData() throws FileNotFoundException {
    }

    public static void DriverAndLoginSetUp1() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.get(connection.baseUrl);
    }

    // retreive data from Excel file

    @Test
    public void dataRecordsExcel() throws IOException {


        String excelFilePath = ".//Test.xlsx";   // location of data file in excel
        FileInputStream inputStream = new FileInputStream(excelFilePath); // open the stream for xml file

        // read data from excel file
        XSSFWorkbook workbook = new XSSFWorkbook(inputStream); //open the workbook
        XSSFSheet sheet = workbook.getSheetAt(0); // sheet from workbook contains rows and cells

        // read the data and use for loop for all the rows and column
        int rows= sheet.getLastRowNum(); // Number of rows
        int cols = sheet.getRow(1).getLastCellNum(); // Find number of columns

        for(int r = 0; r<=rows; r++ ) // representing rows // 0 rows initially
        {
            // represent the rows in excel sheet in each row
            XSSFRow row = sheet.getRow(r); // sheet object and add the first row from the sheet object into row object variable., type of row is XSSFrow

            for (int c=0; c<cols; c++) // inner loop representing columns, for each row this for loop will work
            {
                XSSFCell cell= row.getCell(c); // get the data from cell object.

                switch(cell.getCellType()) // using switch to check data type in the cell,  to know the cell type here if multiple data type int he excel file
                {
                    case STRING:
                        System.out.println(cell.getStringCellValue()); break;
                    case NUMERIC:
                        System.out.println(cell.getNumericCellValue()); break;
                    case BOOLEAN:
                        System.out.println(cell.getBooleanCellValue()); break;
                }
            }
            System.out.println();
        }
    }


    @Test(priority =0)
    public void validusepass()  {

        driver.findElement(By.name("uid")).sendKeys(connection.user);
        driver.findElement(By.name("password")).sendKeys(connection.pass);
        driver.findElement(By.name("btnLogin")).click();
        // driver.wait(2000);
    }
     //Test to pass as to verify listeners.
    @Test(priority =1)
    public void InvalidUserid()
    {
        driver.findElement(By.name("uid")).sendKeys(connection.invaliduser);
        driver.findElement(By.name("password")).sendKeys(connection.pass);
        driver.findElement(By.name("btnLogin")).click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
    }

    @Test(priority = 2)
    public void InvalidPassword()  {

        driver.findElement(By.name("uid")).sendKeys(connection.user);
        driver.findElement(By.name("password")).sendKeys(connection.invalidpass);
        driver.findElement(By.name("btnLogin")).click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
        // driver.wait(2000);
    }

    @Test(priority = 3)
    public void InvalidUserIDAndInvalidPassword()  {

        driver.findElement(By.name("uid")).sendKeys(connection.invaliduser);
        driver.findElement(By.name("password")).sendKeys(connection.invalidpass);
        driver.findElement(By.name("btnLogin")).click();
        Alert alert = driver.switchTo().alert();
        alert.accept();
        // driver.wait(2000);
    }

    @AfterTest
    public void endSession()
    {
        driver.close();
    }

}






