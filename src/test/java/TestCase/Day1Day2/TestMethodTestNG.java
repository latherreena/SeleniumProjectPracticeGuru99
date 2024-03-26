package TestCase.Day1Day2;

import Utility.connection;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(TestCase.ListenerTest.class)

public class TestMethodTestNG {

    public static WebDriver driver;

    @BeforeTest
    public static void DriverAndLoginSetUp() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.get(connection.baseUrl);
        System.out.println(driver.getTitle());
    }
    //Test to pass as to verify listeners.
    @Test
    public void TestSetUp()  {

        driver.findElement(By.name("uid")).sendKeys(connection.user);
        driver.findElement(By.name("password")).sendKeys(connection.pass);
        driver.findElement(By.name("btnLogin")).click();
       // driver.wait(2000);
    }

    @Test(dependsOnMethods = "TestSetUp")
    public void TestAssert()
    {
        String expectedTitle = connection.EXPECT_TITLE;
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle);
        System.out.println(driver.getTitle());

    }
   @AfterTest
    public void endSession()
    {
        driver.close();
    }

  }
