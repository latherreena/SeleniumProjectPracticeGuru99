package TestCase.Day4;

import Utility.connection;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.*;

@Listeners(TestCase.ListenerTest.class)

public class InvalidTestMethodTestNG {

    public WebDriver driver;

        @BeforeMethod
        public void DriverAndLoginSetUp1() {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
            driver.get(connection.baseUrl);
        }
        //Test to pass as to verify listeners.
        @Test(priority =0)
        public void InvalidUserid()
        {
            driver.findElement(By.name("uid")).sendKeys(connection.invaliduser);
            driver.findElement(By.name("password")).sendKeys(connection.pass);
            driver.findElement(By.name("btnLogin")).click();
            Alert alert = driver.switchTo().alert();
            alert.accept();
        }

        @Test(priority = 1)
        public void InvalidPassword()  {

            driver.findElement(By.name("uid")).sendKeys(connection.user);
            driver.findElement(By.name("password")).sendKeys(connection.invalidpass);
            driver.findElement(By.name("btnLogin")).click();
            Alert alert = driver.switchTo().alert();
            alert.accept();
            // driver.wait(2000);
        }

        @Test(priority = 2)
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





