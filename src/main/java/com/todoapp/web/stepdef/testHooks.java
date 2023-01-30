package com.todoapp.web.stepdef;

import com.todoapp.web.wrapper.wrapperMethods;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import java.util.concurrent.TimeUnit;

public class testHooks extends wrapperMethods {
    public static Scenario scenario;

    @Before
    public void beforeScenario(Scenario scenario) {
        testHooks.scenario = scenario;
        driver = openBrowser("chrome");
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    @After
    public void afterScenario(Scenario scenario) throws InterruptedException{
        try {
            driver.quit();
            driver = null;
        }catch (Exception e){

        }
    }
}
