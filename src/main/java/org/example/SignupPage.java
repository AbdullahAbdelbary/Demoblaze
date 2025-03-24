package org.example;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;

public class SignupPage extends BasePage{
    //************************************** Elements Positions ***********************************************
    public String signupButtonCSS = "#signin2";
    public String usernameTextBoxCSS = "#sign-username";
    public String passwordTextBoxCSS = "#sign-password";
    public String internalSignupButtonCSS = "#signInModal > div > div > div.modal-footer > button.btn.btn-primary";
    public String closeButtonCSS = "#signInModal > div > div > div.modal-footer > button.btn.btn-secondary";


    public void signup(String username, String password)
    {
        //waitThenClickCSS(signupButtonCSS,10);
        waitThenWrite(usernameTextBoxCSS, username, 10);
        waitThenWrite(passwordTextBoxCSS, password, 10);
        waitThenClickCSS(internalSignupButtonCSS, 10);
        driver.findElement(By.cssSelector(usernameTextBoxCSS)).clear();
        driver.findElement(By.cssSelector(passwordTextBoxCSS)).clear();
    }

    public void signupValidation(String username, String password, String testType) throws InterruptedException {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

        try {
            // ✅ إغلاق المودال يدويًا إذا كان مفتوحًا
            try {
                WebElement modalCloseButton = driver.findElement(By.cssSelector(".modal.show .close"));
                modalCloseButton.click();
                wait.until(ExpectedConditions.invisibilityOfElementLocated(By.id("signInModal")));
            } catch (Exception e) {
                System.out.println("⚠️ لا يوجد مودال مفتوح.");
            }

            // ✅ استخدام JavaScript إذا لم يُغلق المودال
            if (driver.findElement(By.id("signInModal")).isDisplayed()) {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("document.getElementById('signInModal').style.display='none';");
            }

            // ✅ محاولة النقر على زر التسجيل مع JavaScript كبديل
            try {
                driver.findElement(By.cssSelector(signupButtonCSS)).click();
            } catch (ElementClickInterceptedException e) {
                JavascriptExecutor js = (JavascriptExecutor) driver;
                js.executeScript("arguments[0].click();", driver.findElement(By.cssSelector(signupButtonCSS)));
            }

            signup(username, password);
            System.out.println("⚠️ اختبار إنشاء حساب - النوع: " + testType + " | اسم المستخدم: " + username);

            Alert alert = wait.until(ExpectedConditions.alertIsPresent());
            String actualMessage = alert.getText();
            alert.accept();

            switch (testType) {
                case "valid":
                    Assert.assertEquals(actualMessage, "Sign up successful.");
                    System.out.println(actualMessage.equals("Sign up successful.")
                            ? "✅ الاختبار ناجح: تم إنشاء حساب جديد بنجاح"
                            : "❌ الاختبار فشل: لم يتم إنشاء الحساب الجديد");
                    break;

                case "alreadyExistUser":
                    Assert.assertEquals(actualMessage, "This user already exist.");
                    System.out.println(actualMessage.equals("This user already exist.")
                            ? "✅ الاختبار ناجح: تم رفض إنشاء حساب لمستخدم موجود بالفعل"
                            : "❌ الاختبار فشل: تم إنشاء حساب لمستخدم موجود بالفعل");
                    break;

                case "invalidUsernameFormat":
                case "invalidPasswordFormat":
                    Assert.assertNotEquals(actualMessage, "Sign up successful.");
                    System.out.println(!actualMessage.equals("Sign up successful.")
                            ? "✅ الاختبار ناجح: تم رفض إنشاء الحساب بسبب الفورمات غير الصحيح"
                            : "❌ الاختبار فشل: تم إنشاء حساب بفورمات غير صحيح");
                    break;

                default:
                    System.out.println("❌ نوع الاختبار غير معروف: " + testType);
                    //waitThenClickCSS(closeButtonCSS,10);
                    Assert.fail("TestType غير معرف في البيانات!");
                    break;
            }
        } catch (StaleElementReferenceException e) {
            System.out.println("⚠️ تم اكتشاف StaleElementReferenceException، سيتم إعادة المحاولة...");
            signupValidation(username, password, testType); // إعادة المحاولة مرة أخرى
        }
    }

}

