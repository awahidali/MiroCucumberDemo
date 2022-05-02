package com.miro.apiCalls;


import com.jayway.restassured.response.Response;
import com.miro.actions.AcLogin;
import com.miro.report.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import org.json.JSONObject;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;


import java.io.IOException;
import java.util.Map;

import static com.jayway.restassured.RestAssured.given;

public class APIsCall {

    public static String baseURL = "https://api.miro.com";
    public static String boardId;
    public static Map<String, String> map;

    public static String getAuthToken(WebDriver driver, String username, String password) throws InterruptedException {
        Thread.sleep(2000);
        driver.findElement(By.xpath("//span[text()='Sign in']")).click();
        AcLogin.enterUsername(driver, username);
        AcLogin.enterPassword(driver, password);
        AcLogin.clickSignIn(driver);
        Thread.sleep(4000);
        Thread.sleep(4000);

        driver.findElement(By.xpath("//div[text()='AW App']")).click();
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("javascript:window.scrollBy(250,350)");
        Thread.sleep(4000);
        driver.findElement(By.xpath("//span[text()='Install app and get OAuth token']")).click();
        Thread.sleep(4000);
        driver.switchTo().frame("marketplace-iframe");
        Thread.sleep(4000);
        driver.findElement(By.xpath("//div[@id='teamSelect']")).click();
        driver.findElement(By.xpath("//span[text()='Abdul wahid Team' and @class=\"selectTeam__dropdownItemName\"]")).click();
        js.executeScript("javascript:window.scrollBy(0,1000)");

        Thread.sleep(4000);
        // driver.findElement(By.xpath("//span[text()='Authorize']")).click();
        driver.findElement(By.xpath("//span[@class=\"button__content\"]")).click();
        String token = driver.findElement(By.xpath("//h2[text()='Access token:']/..")).getText();
        return token.replace("Access token:", "");

    }

    public static boolean createBoard(String token) {

        boolean flag = true;
        String endPoint = "/v1/boards";
        String body = "{\n" +
                "     \"name\": \"Untitled\",\n" +
                "     \"sharingPolicy\": {\n" +
                "          \"access\": \"private\",\n" +
                "          \"teamAccess\": \"edit\"\n" +
                "     },\n" +
                "     \"description\": \"string\"\n" +
                "}";
        ExtentCucumberAdapter.addTestStepLog("Request body :" + body);
        try {
            Response response = given()
                    .header("Accept", "application/json")
                    .header("Authorization", "Bearer " + token)
                    .contentType("application/json")
                    .log().all()
                    .body(body)
                    .when()
                    .post(baseURL + endPoint)
                    .then()
                    .log().status()
                    .extract().response();
            String responseInfo = response.andReturn().asString();
            JSONObject obj = new JSONObject(responseInfo);
            String id = obj.getString("id");
            boardId = id;

            ExtentCucumberAdapter.addTestStepLog("New blank board is created with id : " + boardId);
            ExtentCucumberAdapter.addTestStepLog(response.andReturn().asString());

        } catch (Exception e) {
            flag = false;
            ExtentCucumberAdapter.addFailTestStepLog(e.getMessage());
        }
        return flag;
    }


    public static void invite(String user, String token) throws IOException {

        String requestBody = "{\"emails\":[\"" + user + "\"],\"role\":\"EDITOR\",\"message\":\"\",\"teamInvitationStrategy\": \"off\"}";
        String operation = "https://miro.com/api/v1/boards/" + boardId + "/share";

        String endPoint = "/v1/boards/" + boardId + "/share";
        String requestedURL = baseURL + endPoint;
        ExtentCucumberAdapter.addTestStepLog("Requested URL : " + requestedURL);


        try {
            Response response = given()
                    .header("Accept", "application/json")
                    .header("Authorization", "Bearer " + token)
                    .contentType("application/json")
                    .body(requestBody)
                    .log().all()
                    .when()
                    .post(requestedURL)
                    .then()
                    .log().ifStatusCodeIsEqualTo(200)
                    .extract().response();

            ExtentCucumberAdapter.addTestStepLog("Invite has been send to the " + user);
            ExtentCucumberAdapter.addTestStepLog(response.andReturn().asString());

        } catch (Exception e) {
            e.printStackTrace();
            ExtentCucumberAdapter.addFailTestStepLog(e.getMessage());
        }
    }
}
