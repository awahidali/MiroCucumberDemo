package com.miro.common;

import com.miro.report.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * Created by Abdul Wahid
 */
public class Common {

    public static void openNewTab(WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("window.open()");
        ArrayList<String> tabs = new ArrayList<String>(driver.getWindowHandles());
        driver.switchTo().window(tabs.get(1));
       // ExtentTestManager.getTest().log(Status.PASS, "New blank tab opened successfully");
    }

    public static void captureScreenshot(WebDriver driver) {
        Date date = new Date();
        SimpleDateFormat dtf = new SimpleDateFormat("dd_MM_yyyy");
        String dateTime = dtf.format(date);
        TakesScreenshot ts = (TakesScreenshot) driver;
        String filePath = System.getProperty("user.dir") + "\\screenshots\\sticker_" + dateTime + ".png";
        File des = new File(filePath);
        try {
            File src = ts.getScreenshotAs(OutputType.FILE);
            File target = new File(filePath);
            FileUtils.copyFile(src, target);
            ExtentCucumberAdapter.addTestStepLog("Captured screenshot path as "+filePath);
            ExtentCucumberAdapter.addTestStepScreenCaptureFromPath("C:\\N\\MiroCucumberDemo\\screenshots\\sticker_02_05_2022.png");
           ExtentCucumberAdapter.addTestStepScreenCaptureFromPath(System.getProperty("user.dir") + "\\screenshots","sticker_" + dateTime);
        } catch (Exception e) {
            ExtentCucumberAdapter.addFailTestStepLog("Not Capture the visible sticker");
        }
    }
}
