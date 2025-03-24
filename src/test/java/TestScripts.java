import org.example.ExcelUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import java.io.IOException;


public class TestScripts extends BaseTest{
/*
    //---------------------- Home Page: test logo & website name ----------------------
    @Test(priority = 1)
    public void testLogoAndWebsiteName() throws InterruptedException {
        homePage.logoAndWebsiteNameValidation();
    }

    // --------------------- Home Page: test home button ----------------------
    @Test(priority = 2)
    public void testHomeButton() throws InterruptedException {
        homePage.homeButtonValidation();
    }
*/
    //----------------------------------------------- Contact ----------------------------------------------
    @DataProvider(name = "ContactData")
    public Object[][] getContactData() throws IOException {
        String filePath = "D:\\Testing\\Technical\\Java\\Projects\\GraduationProject3\\demoBlaseGraduation.xlsx";
        return ExcelUtils.readExcelData(filePath, "Sheet3");
    }

    @Test(priority = 3, dataProvider = "ContactData")
    public void testContact(String contactEmail, String contactName, String message, String testType) throws InterruptedException {
        contactPage.contactValidation(contactEmail, contactName, message, testType);
    }

    @Test(priority = 4)
    public void testContactClosingViaXsign() throws InterruptedException {
        contactPage.closingValidation("x");
    }

    @Test(priority = 5)
    public void testContactClosingViaCloseButton() throws InterruptedException {
        contactPage.closingValidation("close");
    }

    @Test(priority = 6)
    public void testContactClickingOnSendMessageButtonWithEmptyData() throws InterruptedException {
        contactPage.clickingOnSendMessageButtonWithEmptyDataValidation();
    }
/*
    //----------------------------------------------- Aboutus ----------------------------------------------
    @Test(priority = 7)
    public void testAboutus() throws InterruptedException {
        aboutusPage.aboutusValidation("X");
    }

    //---------------------- Product Page: Internal & External data matching for Products ----------------------
    // 1-اختبار تطابق البيانات الخارجيه والداخليه لكل منتج من ال 14 منتج
    //2-اختبار الضغط علي ذر "Add to Cart" لكل منتج
    //3-الذهاب لصفحة ال Cart والتأكد من أن عدد المنتجات التي تمت اضافتها للسله مطابق لعدد المنتجات في صفحة ال Cart
    @Test(priority = 8)
    public void testDataMatchingAndAddToCart() throws InterruptedException {
        productPage.dataMatchingAndAddToCartValidation();
    }

    //----------------------------------------------- Cart Page ----------------------------------------------
    @Test(priority = 9)
    public void testPlacingOrderOnCartPage() throws InterruptedException {
        cartPage.placeOrderValidation("Abdullah", "Egypt", "6 October City", "123", "March", "2025");
    }

    //---------------------- Home Page: test slide bar ----------------------
    @Test(priority = 10)
    public void testSlideBarButtons() throws InterruptedException {
        homePage.slideBarButtonsValidation();
    }

    //---------------------- Home Page: test filtering Categories ----------------------
    @Test(priority = 11)
    public void testCategoryFilteringPhones() throws InterruptedException {
        homePage.categoryFilteringValidation("Phones");
    }

    @Test(priority = 12)
    public void testCategoryFilteringLaptops() throws InterruptedException {
        homePage.categoryFilteringValidation("Laptops");
    }

    @Test(priority = 13)
    public void testCategoryFilteringMonitors() throws InterruptedException {
        homePage.categoryFilteringValidation("Monitors");
    }

    //---------------------- Home Page: test navigating to the Next & Previous Pages ----------------------
    @Test(priority = 14)
    public void testCategoryFilteringNextAndPrevious() throws InterruptedException {
        homePage.nextButtonValidation();
    }

    @Test(priority = 15)
    public void testCategoryFilteringNextAndPrevious2() throws InterruptedException {
        homePage.previousButtonValidation();
    }

    //----------------------------------------------- Login ----------------------------------------------
    @DataProvider(name = "LoginData")
    public Object[][] getLoginData() throws IOException {
        String filePath = "D:\\Testing\\Technical\\Java\\Projects\\GraduationProject3\\demoBlaseGraduation.xlsx";
        return ExcelUtils.readExcelData(filePath, "Sheet1");
    }

    @Test(priority = 16, dataProvider = "LoginData")
    public void testLogin(String username, String password, String testType) throws InterruptedException {
        loginPage.loginValidation(username, password, testType);
    }

    //----------------------------------------------- Signup ----------------------------------------------
    @DataProvider(name = "SignupData")
    public Object[][] getSignupData() throws IOException {
        String filePath = "D:\\Testing\\Technical\\Java\\Projects\\GraduationProject3\\demoBlaseGraduation.xlsx";
        return ExcelUtils.readExcelData(filePath, "Sheet2");
    }

    @Test(priority = 17, dataProvider = "SignupData")
    public void testSignup(String username, String password, String testType) throws InterruptedException {
        signupPage.signupValidation(username, password, testType);
    }
    */
}
