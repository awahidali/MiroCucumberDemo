package com.miro.page;

import com.miro.report.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class PgLogin {

    public static void verifyUsername(WebDriver driver) {
        String id = "email";
        WebElement element = driver.findElement(By.id(id));
        if (element.isEnabled()) {
            ExtentCucumberAdapter.addTestStepLog("Username field is displaying as enabled");
        }
    }
}
