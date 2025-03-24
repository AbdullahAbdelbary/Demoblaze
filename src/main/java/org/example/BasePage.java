package org.example;

import com.google.common.base.Function;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import java.time.Duration;
import java.util.List;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.testng.AssertJUnit.assertTrue;

public class BasePage {

    public static WebDriver driver;

    public void setDriver(WebDriver driver)
    {
        BasePage.driver = driver;
    }

    public boolean click(By locator){
        WebElement button = driver.findElement(locator);
        if (button.isDisplayed() && button.isEnabled()) {
            button.click();
            return true;
        } else {
            System.out.println(" الزر غير متاح.");
            return false;
        }
    }

    public boolean clickByLinkText(String buttonCSS) {
        WebElement button = driver.findElement(By.linkText(buttonCSS));
        if (button.isDisplayed() && button.isEnabled()) {
            button.click();
            return true;
        } else {
            System.out.println(" الزر غير متاح.");
            return false;
        }
    }


    public boolean isElementVisibleXpath(String locatorXpath, int timeInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeInSeconds));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorXpath)));
            return true;
        } catch (Exception e) {
            return false;
        }
    }
    public boolean isElementVisibleID(String locatorXpath, int timeInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeInSeconds));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(locatorXpath)));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean isElementPresentXpath(String locatorXpath, int timeInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeInSeconds));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locatorXpath)));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static void waitThenClickXpath(String buttonXpath, int timeInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeInSeconds));
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(buttonXpath))); // انتظر حتى يصبح العنصر قابلًا للنقر
        button.click();
    }

    /*public static void waitThenClickCSS(String buttonCSS, int timeInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeInSeconds));
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(buttonCSS))); // انتظر حتى يصبح العنصر قابلًا للنقر
        button.click();
    }*/

    public boolean waitThenClickCSS(String buttonCSS, int timeInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeInSeconds));
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(buttonCSS))); // انتظر حتى يصبح العنصر قابلًا للنقر
        if (button.isDisplayed() && button.isEnabled()) {
            button.click();
            return true;
        } else {
            System.out.println(" الزر غير متاح.");
            return false;
        }
    }

    public boolean waitThenClickXPATH(String buttonXPATH, int timeInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeInSeconds));
        WebElement button = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(buttonXPATH))); // انتظر حتى يصبح العنصر قابلًا للنقر
        if (button.isDisplayed() && button.isEnabled()) {
            button.click();
            return true;
        } else {
            System.out.println(" الزر غير متاح.");
            return false;
        }
    }

    public static void waitAndClickElement(WebElement element, int timeInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeInSeconds));
        wait.until(ExpectedConditions.elementToBeClickable(element));  // انتظار أن يكون العنصر قابلاً للنقر
        element.click();  // النقر على العنصر
    }

    public void waitThenWrite(String textBox_Position_CSS, String data, int timeInSeconds)
    {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeInSeconds));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(textBox_Position_CSS))).sendKeys(data);
    }

    public static boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true; // تم العثور على Alert
        } catch (NoAlertPresentException e) {
            return false; // لا يوجد Alert
        }
    }

    public void verifyAlertEqual(String expectedMessage) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        Assert.assertEquals(alert.getText(), expectedMessage, "❌ رسالة التنبيه غير صحيحة!");
        alert.accept();
    }

    public void dismissUnexpectedAlert() {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            System.out.println("⚠️ تم العثور على تنبيه: " + alert.getText());
            alert.accept(); // إغلاق التنبيه
            System.out.println("✅ تم إغلاق التنبيه بنجاح.");
        } catch (TimeoutException e) {
            // لا يوجد تنبيه، لا تفعل شيئًا
        } catch (NoAlertPresentException e) {
            // لم يتم العثور على تنبيه
        }
    }

    public void verifyAlertNotEqual(String expectedMessage) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Alert alert = wait.until(ExpectedConditions.alertIsPresent());
        Assert.assertNotEquals(alert.getText(), expectedMessage, "❌ رسالة التنبيه غير صحيحة!");
        alert.accept();
    }


    public boolean isElementPresentCSS(String locatorCSS, int timeInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeInSeconds));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(locatorCSS)));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public static String extractTextBetweenWords(String text, String word1, String word2) {
        // إنشاء نمط Regex لاستخراج النص بين كلمتين
        String regex = Pattern.quote(word1) + "\\s*(.*?)\\s*" + Pattern.quote(word2);
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        if (matcher.find()) {
            return matcher.group(1).trim(); // استخراج النص بين الكلمتين مع إزالة المسافات الزائدة
        }
        return null; // في حالة عدم العثور على النص
    }

    public static Integer extractNumberBetweenWords(String text, String word1, String word2) {
        // إنشاء نمط Regex لاستخراج الرقم بين كلمتين
        String regex = word1 + "\\s*(\\d+)\\s*" + word2;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1)); // تحويل الرقم من String إلى int
        }
        return null; // في حالة عدم العثور على الرقم
    }

    public static Integer extractNumberAfterWord(String text, String word) {
        // إنشاء نمط Regex لاستخراج الرقم بعد الكلمة المطلوبة
        String regex = Pattern.quote(word) + "\\s*(\\d+)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(text);

        if (matcher.find()) {
            return Integer.parseInt(matcher.group(1)); // تحويل النص المستخرج إلى int
        }
        return null; // في حالة عدم العثور على الرقم
    }

    //------------------------------- product matching ---------------------------------
    public void matchingAssert(int productNumber) throws InterruptedException {

    //---------------- External Data Positions ----------
    List<WebElement> externalImagesElement = driver.findElements(By.cssSelector(".card.h-100 img.card-img-top"));
    List<WebElement> externalTitlesElement = driver.findElements(By.cssSelector("a.hrefch"));
    List<WebElement> externalPricesElement = driver.findElements(By.cssSelector("div.card-block h5"));
    List<WebElement> externalDescriptionsElement = driver.findElements(By.id("article"));
    //---------------- External Data Contents ----------
    String externalImageData = externalImagesElement.get(productNumber).getAttribute("src");
    String externalTitleData = externalTitlesElement.get(productNumber).getText();
    String externalPriceData = externalPricesElement.get(productNumber).getText();
    String externalDescriptionData = externalDescriptionsElement.get(productNumber).getText();

    // الانتقال للصفحه الداخليه للمنتج
    waitAndClickElement(externalTitlesElement.get(productNumber),10);
    Thread.sleep(2000);

    //---------------- Internal Data Contents ----------
    String internalImageData  = driver.findElement(By.cssSelector("div.item.active img")).getAttribute("src");
    String internalTitleData = driver.findElement(By.cssSelector("h2.name")).getText();
    String internalPriceData = driver.findElement(By.cssSelector("h3.price-container")).getText().replace(" *includes tax", "");
    String internalDescriptionData = driver.findElement(By.cssSelector("#more-information > p")).getText();

    // المقارانات
    Assert.assertEquals(externalImageData, internalImageData, "❌ الصور غير متطابقة!");
    Assert.assertEquals(externalTitleData, internalTitleData, "❌ العنوان لا يتطابق!");
    Assert.assertEquals(externalPriceData, internalPriceData, "❌ السعر لا يتطابق!");
    Assert.assertEquals(externalDescriptionData, internalDescriptionData, "❌ الوصف لا يتطابق!");

    // الضغط على "إضافة للسلة" والتحقق من الرسالة في التنبيه
    WebElement addToCartButton = driver.findElement(By.cssSelector("a.btn.btn-success.btn-lg"));
    waitAndClickElement(addToCartButton,10);
    verifyAlertEqual("Product added");

    // العوده للصفحه الرئيسيه
    WebElement homeButton = driver.findElement(By.xpath("//*[@id=\"navbarExample\"]/ul/li[1]/a"));
    waitAndClickElement(homeButton,10);
}

public void testProduct(int productNumber) throws InterruptedException
{
    Thread.sleep(2000);

    if(productNumber == 9 || productNumber == 10 || productNumber == 11 || productNumber == 12 || productNumber == 13 || productNumber == 14)
    {
        if (isElementVisibleID("next2",5)) {
            driver.findElement(By.id("next2")).click();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(".card.h-100 img.card-img-top")));
            Thread.sleep(2000); // تأخير إضافي لضمان تحميل DOM بالكامل
            if(productNumber == 9)          matchingAssert(0);
            else if(productNumber == 10)    matchingAssert(1);
            else if(productNumber == 11)    matchingAssert(2);
            else if(productNumber == 12)    matchingAssert(3);
            else if(productNumber == 13)    matchingAssert(4);
            else if(productNumber == 14)    matchingAssert(5);
        }
    }
    else
    {
        matchingAssert(productNumber);
    }
}

    public static boolean isVideoClickableAndPlaying(String videoSelector)
    {
        WebElement videoElement = driver.findElement(By.cssSelector(videoSelector));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        boolean flag = true;
        Object errorState = js.executeScript("return arguments[0].error ? arguments[0].error.code : null;", videoElement);
        // التأكد من أن الفيديو مرئي وقابل للنقر وليس به اخطاء
        if (!videoElement.isDisplayed() || !videoElement.isEnabled() || errorState != null) flag = false;
        return flag;
    }

    public static void scrollToElementByLinkText(String elementLinkTextPosition) {
        WebElement element = driver.findElement(By.linkText(elementLinkTextPosition));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
    }

    public static void scrollToElementByCSS(String elementCSSPosition) {
        WebElement element = driver.findElement(By.linkText(elementCSSPosition));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", element);
    }

    public void assertProductsBelongToCategory(String[] expectedProducts, String class_name) {
        List<WebElement> productElements = driver.findElements(By.className(class_name));
        for (WebElement product : productElements) {
            String productName = product.getText();
            boolean found = false;
            for (String expectedProduct : expectedProducts) {
                if (productName.equals(expectedProduct)) {
                    found = true;
                    break;
                }
            }
            assertTrue("المنتج غير متوقع: " + productName, found);
            System.out.println( " المنتج " + productName + " يخص الصفحه ");
        }
    }



}
