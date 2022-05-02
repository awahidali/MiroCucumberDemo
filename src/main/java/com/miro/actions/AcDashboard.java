package com.miro.actions;

import com.miro.report.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.List;

public class AcDashboard {

    public static void openBoard(WebDriver driver, String name) {
        String xpath = "//span[text()='" + name + "']/../../..";
        WebElement element = driver.findElement(By.xpath(xpath));
        element.click();
        ExtentCucumberAdapter.addTestStepLog("Open " + name + " board successfully");

    }

    public static void openMoreTools(WebDriver driver) {
        String xpath = "//div[@tooltip=\"more tools\"]";
        WebElement element = driver.findElement(By.xpath(xpath));
        element.click();
        ExtentCucumberAdapter.addTestStepLog("Open more tools from left tool box");
    }

    public static void selectSearchedItem(WebDriver driver) {
        String xpath = "//div[@class=\"library-root__item AT__library--EMOJI_ICONS library-root__item--list\"]";
        WebElement element = driver.findElement(By.xpath(xpath));
        element.click();
        ExtentCucumberAdapter.addTestStepLog("Clicked on stickers icon successfully");
    }

    public static void clickStickerTab(WebDriver driver) {
        String xpath = "//div[text()='Stickers']";
        WebElement element = driver.findElement(By.xpath(xpath));
        element.click();
        ExtentCucumberAdapter.addTestStepLog("Clicked on stickers tab successfully");
    }

    public static void clickSharebutton(WebDriver driver) {
        String xpath = "//button[@data-testid='share-board-button']";
        WebElement element = driver.findElement(By.xpath(xpath));
        element.click();
        ExtentCucumberAdapter.addTestStepLog("Clicked on share button successfully");
    }

    public static void clickRenameSave(WebDriver driver) {
        String xpath = "//button[@data-testid='rename-board-modal__submit-button']";
        WebElement element = driver.findElement(By.xpath(xpath));
        element.click();
        ExtentCucumberAdapter.addTestStepLog("Clicked on Save button  successfully");
    }

    public static void createSticker(WebDriver driver) {
        String xpath = "//div[@class=\"stickers-pack__sticker-2-2E_\"]";
        List<WebElement> elements = driver.findElements(By.xpath(xpath));
        elements.get(1).click();
        ExtentCucumberAdapter.addTestStepLog("Created sticker on canvas successfully");
    }

    public static void renameNewBoard(WebDriver driver, String name) {
        String xpath = "//input[@data-testid='rename-board-modal__name-input']";
        WebElement element = driver.findElement(By.xpath(xpath));
        element.clear();
        element.sendKeys(name);
        ExtentCucumberAdapter.addTestStepLog("Rename the new board successfully as " + name);
    }


}
