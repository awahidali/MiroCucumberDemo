package com.miro.common;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.miro.report.extentreports.cucumber.adapter.ExtentCucumberAdapter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

//import org.testng.TestListenerAdapter;
;

/**
 * Created by Abdul Wahid.
 */
public class BaseTest {
    public static String URL;
    public static String authURL;
    public static String cpath;
    public static String reportConfigPath;

    public static String env;
    public static SimpleDateFormat dtf = null;
    public static ExtentReports extent;
    public static String currentDateTime;
  //  public static ExtentXReporter extentXR = null;
    //public static ExtentHtmlReporter htmlReporter;
    private static ThreadLocal parentTest = new ThreadLocal();
    private static ThreadLocal test = new ThreadLocal();
    ExtentTest logger;
    public static String username1;
    public static String username2;
    public static String password1;
    public static String password2;

    public BaseTest() {
        File file = new File("./src/main/resources/config.properties");

        FileInputStream fileInput = null;
        FileInputStream fileInputCredential = null;
        try {
            fileInput = new FileInputStream(file);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Properties prop = new Properties();
        try {
            prop.load(fileInput);
        } catch (IOException e) {
            e.printStackTrace();
        }
        cpath = prop.getProperty("chromeDriver");
        URL = prop.getProperty("URL");
        ExtentCucumberAdapter.addTestStepLog("App url : "+URL);
        authURL = prop.getProperty("AuthURL");
        reportConfigPath = prop.getProperty("reportConfigPath");
        readJSONFile(System.getProperty("user.dir") + "\\src\\main\\resources\\credentials.json");

        System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + cpath);
        ExtentCucumberAdapter.addTestStepLog("Set the chrome property : "+cpath);

        dtf = new SimpleDateFormat("dd_MM_yyyy_HH_mm_ss");
        Date date = new Date();
        currentDateTime = dtf.format(date);


    }


    public static void readJSONFile(String jsonFile) {
        JSONParser jsonParser = new JSONParser();

        try (FileReader reader = new FileReader(jsonFile)) {
            //Read JSON file
            Object obj = jsonParser.parse(reader);
            JSONArray userList = (JSONArray) obj;
            JSONObject user1 = (JSONObject) ((JSONObject) userList.get(0)).get("user");
            username1 = (String) user1.get("username");
            password1 = (String) user1.get("password");
            JSONObject user2 = (JSONObject) ((JSONObject) userList.get(1)).get("user");
            username2 = (String) user2.get("username");
            password2 = (String) user2.get("password");

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
