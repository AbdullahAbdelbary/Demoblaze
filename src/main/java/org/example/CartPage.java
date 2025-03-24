package org.example;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class CartPage extends BasePage{

    public String cartButtonXpath = "//*[@id=\"navbarExample\"]/ul/li[4]/a";
    public String placeOrderButtonXpath = "//*[@id=\"page-wrapper\"]/div/div[2]/button";
    public String purchaseButtonXpath = "//*[@id=\"orderModal\"]/div/div/div[3]/button[2]";
    public String okButtonXpath = "/html/body/div[10]/div[7]/div/button";
    public String okButtonCSS = "body > div.sweet-alert.showSweetAlert.visible > div.sa-button-container > div > button";
    public String closeButtonXpath = "//*[@id=\"orderModal\"]/div/div/div[3]/button[1]";
    public String nameTextBoxCss = "#name";
    public String countryTextBoxCss = "#country";
    public String cityTextBoxCss = "#city";
    public String creditCardTextBoxCss = "#card";
    public String monthTextBoxCss = "#month";
    public String yearTextBoxCss = "#year";
    public String totalAmountXpath = "//*[@id=\"totalp\"]";
    private final String homeButtonCSS = "#navbarExample > ul > li.nav-item.active > a";
    public String confirmationMessageTextBoxCss = "body > div.sweet-alert.showSweetAlert.visible > h2";
    public String confirmationDataTextBoxXpath = "/html/body/div[10]/p";

    public void placeOrderValidation(String name, String country, String city, String creditCard, String month, String year)
    {
        waitThenClickXpath(cartButtonXpath,10);
        waitThenClickXpath(placeOrderButtonXpath,10);
        waitThenWrite(nameTextBoxCss, name,10);
        waitThenWrite(countryTextBoxCss, country,10);
        waitThenWrite(cityTextBoxCss, city,10);
        waitThenWrite(creditCardTextBoxCss, creditCard,10);
        waitThenWrite(monthTextBoxCss, month,10);
        waitThenWrite(yearTextBoxCss, year,10);
        waitThenClickXpath(purchaseButtonXpath,10);
        isElementPresentCSS(confirmationMessageTextBoxCss,5);
        String confirmationMessage = driver.findElement(By.cssSelector(confirmationMessageTextBoxCss)).getText();
        String confirmationData = driver.findElement(By.xpath(confirmationDataTextBoxXpath)).getText();
        //int totalAmount = Integer.parseInt(driver.findElement(By.xpath(totalAmountXpath)).getText());
        String totalAmount = driver.findElement(By.xpath(totalAmountXpath)).getText();
        //int confirmationDataAmount = extractNumberBetweenWords(confirmationData, "Amount:", "USD");
        String confirmationDataAmount = extractTextBetweenWords(confirmationData, "Amount:", "USD");
        String confirmationDataCardNumber = extractTextBetweenWords(confirmationData, "Number:", "Name");
        String confirmationDataName = extractTextBetweenWords(confirmationData, "Name:", "Date");

        if(confirmationMessage.equals("Thank you for your purchase!") && confirmationDataAmount.equals(totalAmount) && confirmationDataCardNumber.equals(creditCard) && confirmationDataName.equals(name))
        {
            System.out.println("رسالة التأكيد صحيحه والبيانات صحيحه في صفحة تأكيد الشراء: كلا من الكميه ورقم الفيزا والاسم صحيحا");
            Assert.assertTrue(true);
        }
        else {
            System.out.println("خطأ في البيانات في صفحة تأكيد الشراء");
            Assert.fail();
        }

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement okButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='OK']")));
        okButton.click();
    }
}
