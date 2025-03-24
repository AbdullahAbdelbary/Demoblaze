package org.example;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.Assert;
import org.testng.annotations.DataProvider;

import java.io.IOException;
import java.time.Duration;

public class LoginPage extends BasePage {

    public String loginButtonCSS = "#login2";
    public String usernameTextBoxCSS = "#loginusername";
    public String passwordTextBoxCSS = "#loginpassword";
    public String internalLoginButtonCSS = "#logInModal > div > div > div.modal-footer > button.btn.btn-primary";
    public String closeButtonCSS = "#logInModal > div > div > div.modal-footer > button.btn.btn-secondary";
    public String logoutButtonCSS = "#logout2";


    public void login(String username, String password)
    {
        waitThenClickCSS(loginButtonCSS,10);
        waitThenWrite(usernameTextBoxCSS, username, 10);
        waitThenWrite(passwordTextBoxCSS, password, 10);
        waitThenClickCSS(internalLoginButtonCSS, 10);
    }

    public void erase()
    {
        driver.findElement(By.cssSelector(usernameTextBoxCSS)).clear();
        driver.findElement(By.cssSelector(passwordTextBoxCSS)).clear();
    }


    public void loginValidation(String username, String password, String testType) throws InterruptedException {

        switch (testType) {
            case "valid":
                login(username, password);
                System.out.println("⚠️ اختبار تسجيل دخول لمستخدم موجود وكلمة سر صحيحه: " + username + " --- " + password);
                Assert.assertTrue(true);
                System.out.println("✅ تسجيل الدخول ناجح للمستخدم: " + username);
                break;

            case "invalidUsername":
                waitThenClickCSS(logoutButtonCSS,10);
                login(username, password);
                System.out.println("⚠️ اختبار تسجيل دخول لمستخدم غير موجود: " + username);
                WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
                Alert alert = wait.until(ExpectedConditions.alertIsPresent());
                Assert.assertEquals(alert.getText(),"User does not exist.");
                if(alert.getText().equals("User does not exist.")) System.out.println("✅ الاختبار ناجح: تم رفض تسجيل مستخدم غير موجود");
                else  System.out.println("❌ الاختبار فشل: تم تسجيل مستخدم غير موجود");
                alert.accept();
                erase();
                break;

            case "invalidPassword":
                waitThenWrite(usernameTextBoxCSS, username, 10);
                waitThenWrite(passwordTextBoxCSS, password, 10);
                waitThenClickCSS(internalLoginButtonCSS, 10);
                System.out.println("⚠️ اختبار تسجيل دخول بكلمة سر غير صحيحه: " + username);
                WebDriverWait wait2 = new WebDriverWait(driver, Duration.ofSeconds(10));
                Alert alert2 = wait2.until(ExpectedConditions.alertIsPresent());
                Assert.assertEquals(alert2.getText(),"Wrong password.");
                if(alert2.getText().equals("Wrong password.")) System.out.println("✅ الاختبار ناجح: تم رفض تسجيل دخول بكلمة سر غير صحيحه");
                else  System.out.println("❌ الاختبار فشل: تم تسجيل مستخدم غير موجود");
                alert2.accept();
                erase();
                break;

            case "invalidUsernameFormat":
                waitThenWrite(usernameTextBoxCSS, username, 10);
                waitThenWrite(passwordTextBoxCSS, password, 10);
                waitThenClickCSS(internalLoginButtonCSS, 10);
                System.out.println("⚠️ اختبار تسجيل دخول باسم مستخدم بفورمات غير صحيح: " + username);
                if(isAlertPresent())
                {
                    WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(10));
                    Alert alert3 = wait3.until(ExpectedConditions.alertIsPresent());
                    Assert.assertEquals(alert3.getText(),"Login Successful.");
                    if(alert3.getText().equals("Login Successful.")) System.out.println("✅ الأختبار نجح: تم رفض تسجيل دخول باسم مستخدم بفورمات غير صحيح");
                    else  System.out.println("❌ الأختبار فشل: تم تسجيل دخول بأسم مستخدم بفورمات غير صحيح");
                    alert3.accept();
                    erase();
                }
                else
                {
                    System.out.println("❌ الأختبار فشل: تم تسجيل دخول باسم مستخدم بفورمات غير صحيح");
                    Assert.fail();
                }
                break;

            case "invalidPasswordFormat":
                waitThenClickCSS(logoutButtonCSS,10);
                //Thread.sleep(2000);
                login(username, password);
                System.out.println("⚠️ اختبار تسجيل دخول بكلمة سر بفورمات غير صحيح: " + username);
                if(isAlertPresent())
                {
                    WebDriverWait wait3 = new WebDriverWait(driver, Duration.ofSeconds(10));
                    Alert alert3 = wait3.until(ExpectedConditions.alertIsPresent());
                    Assert.assertEquals(alert3.getText(),"Login Successful.");
                    if(alert3.getText().equals("Login Successful.")) System.out.println("✅ الأختبار نجح: تم رفض تسجيل دخول بكلمة سر بفورمات غير صحيح");
                    else  System.out.println("❌ الأختبار فشل: تم تسجيل دخول بكلمة سر بفورمات غير صحيح");
                    alert3.accept();
                    erase();
                }
                else
                {
                    System.out.println("❌ الأختبار فشل: تم تسجيل دخول بكلمة سر بفورمات غير صحيح");
                    waitThenClickCSS(logoutButtonCSS,10);
                    Assert.fail();
                }
                break;

            default:
                System.out.println("❌ نوع الاختبار غير معروف: " + testType);
                Assert.fail("TestType غير معرف في البيانات!");
                break;
        }
    }
}
