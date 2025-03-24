package org.example;

import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class HomePage extends BasePage {

    private final String logoCSS = "#nava > img";
    private final String homeButtonCSS = "#navbarExample > ul > li.nav-item.active > a";
    private final String slideBarNextButtonCSS = "#carouselExampleIndicators > a.carousel-control-next > span.carousel-control-next-icon";
    private final String slideBarPreviousButtonCSS = "#carouselExampleIndicators > a.carousel-control-prev > span.carousel-control-prev-icon";
    private final String nextButtonCSS = "#next2";
    private final String previousButtonCSS = "#prev2";

    //-------------------------- Logo ---------------------------
    public void logoAndWebsiteNameValidation() throws InterruptedException {
        waitThenClickCSS(logoCSS, 10);
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.demoblaze.com/index.html");
        driver.navigate().refresh();
        System.out.println("⚠️ اختبار النقر علي اللوجو والانتقال للرابط الخاص به: ");
        if(driver.getCurrentUrl().equals("https://www.demoblaze.com/index.html")) System.out.println("✅ الاختبار ناجح: تم الانتقال للرابط الصحيح");
        else  System.out.println("❌ الاختبار فشل: لم يتم الانتقال للرابط الصحيح");
    }

    //-------------------------- Home Button ---------------------------
    public void homeButtonValidation() throws InterruptedException {
        waitThenClickCSS(homeButtonCSS, 10);
        Assert.assertEquals(driver.getCurrentUrl(),"https://www.demoblaze.com/index.html");
        driver.navigate().refresh();
        System.out.println("⚠️ اختبار النقر علي اسم الموقع والانتقال للرابط الخاص به: ");
        if(driver.getCurrentUrl().equals("https://www.demoblaze.com/index.html")) System.out.println("✅ الاختبار ناجح: تم الانتقال للرابط الصحيح");
        else  System.out.println("❌ الاختبار فشل: لم يتم الانتقال للرابط الصحيح");
    }

    //-------------------------- Slide Bar ---------------------------
    public void slideBarButtonsValidation() throws InterruptedException {
        boolean nextCheck = waitThenClickCSS(slideBarNextButtonCSS, 10);
        Thread.sleep(2000);
        boolean previousCheck = waitThenClickCSS(slideBarPreviousButtonCSS, 10);
        System.out.println("⚠️ اختبار النقر علي زر التالي والسابق للسلايد بار: ");
        if(nextCheck)
        {
            Assert.assertTrue(true);
            System.out.println("✅ زر التالي يعمل بشكل صحيح");
        }
        if(previousCheck)
        {
            Assert.assertTrue(true);
            System.out.println("✅ زر السابق يعمل بشكل صحيح");
        }
        if(!nextCheck)
        {
            Assert.fail();
            System.out.println("❌ الاختبار فشل: زر التالي لا يعمل");
        }
        if(!previousCheck)
        {
            Assert.fail();
            System.out.println("❌ الاختبار فشل: زر السابق لا يعمل");
        }

    }


    //-------------------------- Category Filtering ---------------------------
    public void categoryFilteringValidation(String categoryName) throws InterruptedException {

        if(categoryName.equals("Phones"))
        {
            clickByLinkText("Phones");
            assertProductsBelongToCategory(new String[]{"Samsung galaxy s6", "Nokia lumia 1520", "Nexus 6", "Samsung galaxy s7", "Iphone 6 32gb", "Sony xperia z5", "HTC One M9"}, "card-title");
        }
        else if (categoryName.equals("Laptops"))
        {
            clickByLinkText("Laptops");
            assertProductsBelongToCategory(new String[]{"Sony vaio i5", "Sony vaio i7", "MacBook air", "MacBook air", "Dell i7 8gb", "2017 Dell 15.6 Inch", "MacBook Pro"},"card-title");
        }
        else if (categoryName.equals("Monitors"))
        {
            clickByLinkText("Monitors");
            assertProductsBelongToCategory(new String[]{"Apple monitor 24", "ASUS Full HD"},"card-title");
        }

    }


    //-------------------------- Next & Previous Buttons of the Page at the Bottom ---------------------------
    public void nextButtonValidation() throws InterruptedException {
        Thread.sleep(3000);
        waitThenClickCSS(nextButtonCSS,10);
        Thread.sleep(3000);
        assertProductsBelongToCategory(new String[]{"Apple monitor 24", "MacBook air", "Dell i7 8gb", "2017 Dell 15.6 Inch", "ASUS Full HD", "MacBook Pro"},"card-title");
        System.out.println("✅ الأختبار ناجح زر Next يعمل بشكل صحيح وجميع المنتجات تخص الصفحه.");
    }
    public void previousButtonValidation() throws InterruptedException {
        Thread.sleep(3000);
        waitThenClickCSS(previousButtonCSS,10);
        Thread.sleep(3000);
        assertProductsBelongToCategory(new String[]{"Nokia lumia 1520", "Nexus 6", "Samsung galaxy s7", "Iphone 6 32gb", "Sony xperia z5", "HTC One M9", "Sony vaio i5", "Sony vaio i7", "Apple monitor 24"},"card-title");
        System.out.println("✅ الأختبار ناجح زر Previous يعمل بشكل صحيح وجميع المنتجات تخص الصفحه.");
    }

}

