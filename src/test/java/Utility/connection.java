package Utility;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.BeforeMethod;

public class connection {

    // Setting Base URL
    public static String baseUrl = "http://www.demo.guru99.com/V4/";

    // Valid account for login
    public static final String user = "mngr558727";
    public static final String pass = "juvAnEp";

    // InValid account for login
    public static final String invaliduser = "uytuy7867";
    public static final String invalidpass = "sadf";


    // Time to wait when searching for a GUI element
    public static final int WAIT_TIME = 30;

    // Expected output
    public static final String EXPECT_TITLE = "Guru99 Bank Manager HomePage";

    public static WebDriver driver;
    public static void DriverAndLoginSetUp1() {
        WebDriverManager.firefoxdriver().setup();
        driver = new FirefoxDriver();
        driver.get(connection.baseUrl);
    }
}
