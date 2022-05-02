package com.miro.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(
        features = "Features"
        ,glue={"com.miro.stepDefinition"}
        ,plugin = {"com.miro.report.extentreports.cucumber.adapter.ExtentCucumberAdapter:"})
public class RunTest extends AbstractTestNGCucumberTests {

}
