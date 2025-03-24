import org.example.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeClass;

public class BaseTest {
    private static final Logger log = LoggerFactory.getLogger(BaseTest.class);
    public WebDriver driver;
    public BasePage basePage;
    public LoginPage loginPage;
    public HomePage homePage;
    public SignupPage signupPage;
    public ContactPage contactPage;
    public AboutusPage aboutusPage;
    public ProductPage productPage;
    public CartPage cartPage;
    private String url = "https://www.demoblaze.com/";

    @BeforeClass
    public void setUp()
    {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(url);
        basePage = new BasePage();
        basePage.setDriver(driver);
        homePage = new HomePage();
        contactPage = new ContactPage();
        aboutusPage = new AboutusPage();
        signupPage = new SignupPage();
        productPage = new ProductPage();
        cartPage = new CartPage();
        loginPage = new LoginPage();

    }

    /*
    @BeforeClass
    public void loadApplication()
    {
        driver.get(url);
        basePage = new BasePage();
        basePage.setDriver(driver);
        setUtilityDriver();
        loginPage = new LoginPage();
        homePage = new HomePage();
    }
     */

    /*
    @AfterClass
    public void tearDown()
    {
        //we call it tear down since we tear down the test after each execution
        driver.quit();    //close all tabs of the browser
    }

     */
}
