package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

public class ProductPage extends BasePage {

    public void dataMatchingAndAddToCartValidation() throws InterruptedException {
        /*for (int i = 0; i < 15; i++)
        {
            testProduct(i);
        }*/
        testProduct(0);
        testProduct(1);
        Thread.sleep(2000);
        if (isElementPresentXpath("//*[@id=\"navbarExample\"]/ul/li[4]/a",2)) {
            driver.findElement(By.xpath("//*[@id=\"navbarExample\"]/ul/li[4]/a")).click();
            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("#tbodyid > tr")));
            Thread.sleep(2000); // تأخير إضافي لضمان تحميل DOM بالكامل
            // التحقق من عدد المنتجات في السلة
            List<WebElement> cartRows = driver.findElements(By.xpath("//table/tbody/tr"));  ////*[@id="page-wrapper"]/div/div[1]/div/table/thead/tr
            if (cartRows.size() == 2) {
                System.out.println("جميع البيانات الخارجيه للمنتجات تطابق البينات الداخليه في صفحة المنتج وتمت إضافة جميع المنتجات بنجاح الي السله وتم التأكيد علي وجودها في صفحة ال Cart");
            } else {
                System.out.println("هناك خطأ، عدد المنتجات في السلة: " + cartRows.size() + " بدلاً من " + 14);
                Assert.fail();
            }
        }
    }
}

