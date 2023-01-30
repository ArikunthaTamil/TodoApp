package com.todoapp.runner;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

    @CucumberOptions(
            features = "src/test/resources/features",
            tags = "@TodoApp",
            glue = {"classpath:com.todoapp.web.stepdef"},
            plugin = {"pretty", "html:target/cucumber.html", "json:target/cucumber.json"}
    )
    public class testRunner extends AbstractTestNGCucumberTests {

        @BeforeClass
        public static void setUp() {
            //Todo: if any pre-requisites
        }

        @AfterClass
        public static void tearDown() {
            //Todo: if any thing to reset/close
        }
    }
