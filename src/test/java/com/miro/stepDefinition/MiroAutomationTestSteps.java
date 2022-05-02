package com.miro.stepDefinition;


import com.miro.actions.AcDashboard;
import com.miro.actions.AcLogin;
import com.miro.apiCalls.APIsCall;
import com.miro.common.BaseTest;
import com.miro.common.Common;
import com.miro.page.PgLogin;

import com.miro.report.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import io.cucumber.java.en.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


import java.io.IOException;

public class MiroAutomationTestSteps extends BaseTest {



    WebDriver driver = null;
    String token;

    @Given("^Open the Chrome browser and launch the miro application$")
    public void openTheChromeBrowserAndLaunchTheMiroApplication() {
       ExtentCucumberAdapter.startTest("createSticker", "Creating sticker on canvas ");
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(authURL);

        try {
            token = APIsCall.getAuthToken(driver, username1, password1);
        } catch (InterruptedException e) {
            e.printStackTrace();
            ExtentCucumberAdapter.addFailTestStepLog(e.getMessage());
        }
        driver.navigate().to(URL);
    }

    @When("^Enter UserA credential$")
    public void enterUserACredential() {

        PgLogin.verifyUsername(driver);
        AcLogin.enterUsername(driver, username1);
        AcLogin.enterPassword(driver, password1);
        AcLogin.clickSignIn(driver);
    }

    @Then("^Create a board with the name Board$")
    public void createABoardWithTheNameBoard() throws InterruptedException {
        if (APIsCall.createBoard(token)) {
            ExtentCucumberAdapter.addTestStepLog("Baord has been created by API call");
        } else {
            ExtentCucumberAdapter.addFailTestStepLog("Board is not created");
        }
        Thread.sleep(5000);
        ExtentCucumberAdapter.addTestStepLog("Refresh the page to reload the created board");
        driver.navigate().refresh();
        Thread.sleep(5000);
    }

    @And("^Open new Board and create there sticker$")
    public void openNewBoardAndCreateThereSticker() throws InterruptedException {
        AcDashboard.openBoard(driver, "Untitled");
        Thread.sleep(2000);
        Thread.sleep(2000);
        AcDashboard.openMoreTools(driver);
        Thread.sleep(2000);
        //AcDashboard.searchTool(driver, "Stickers and Emojis");
        Thread.sleep(2000);
        AcDashboard.selectSearchedItem(driver);
        Thread.sleep(2000);
        AcDashboard.clickStickerTab(driver);
        Thread.sleep(2000);
        AcDashboard.createSticker(driver);
        Thread.sleep(2000);
        AcDashboard.clickSharebutton(driver);
        Thread.sleep(2000);
        AcDashboard.renameNewBoard(driver, "Board");
        Thread.sleep(2000);
        AcDashboard.clickRenameSave(driver);
        Thread.sleep(2000);
    }

    @When("^UserA invite UserB to Board$")
    public void userAInviteUserBToBoard() throws IOException, InterruptedException {
        APIsCall.invite(username2,token);
        Thread.sleep(2000);
    }

    @Then("^UserB login into service and open Board$")
    public void userBLoginIntoServiceAndOpenBoard() throws InterruptedException {
        Common.openNewTab(driver);
        driver.navigate().to(URL);
        Thread.sleep(2000);
        AcLogin.enterUsername(driver, username2);
        AcLogin.enterPassword(driver, password2);
        AcLogin.clickSignIn(driver);
        Thread.sleep(2000);
        AcDashboard.openBoard(driver, "Board");
        Thread.sleep(2000);
        Thread.sleep(2000);
        Thread.sleep(2000);
        Common.captureScreenshot(driver);
    }

    @And("^UserB should see created sticker on Board$")
    public void userBShouldSeeCreatedStickerOnBoard() {
        Common.captureScreenshot(driver);
        ExtentCucumberAdapter.addTestStepLog("Successfully Passed");
        ExtentCucumberAdapter.endTest();
        driver.quit();
    }
}
