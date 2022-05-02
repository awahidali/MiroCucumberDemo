package com.miro.actions;

import com.miro.report.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class AcLogin {

    public static void enterUsername(WebDriver driver, String usn) {
        String id = "email";
        WebElement element = driver.findElement(By.id(id));
        element.clear();
        element.sendKeys(usn);
        ExtentCucumberAdapter.addTestStepLog("Enter the Username successfully as : "+usn);

    }

    public static void enterPassword(WebDriver driver, String pass) {
        String id = "password";
        WebElement element = driver.findElement(By.id(id));
        element.sendKeys(pass);
        ExtentCucumberAdapter.addTestStepLog("Enter the password successfully");
    }

    public static void clickSignIn(WebDriver driver) {
        String xpath = "//button[@data-testid=\"mr-form-login-btn-signin-1\"]";
        WebElement element = driver.findElement(By.xpath(xpath));
        element.click();
        ExtentCucumberAdapter.addTestStepLog("Click on the sign In button successfully");
    }


}
