package com.runner;

import cucumber.api.CucumberOptions;
import cucumber.api.testng.AbstractTestNGCucumberTests;

@CucumberOptions(features = {"src/test/resources/"},
glue = {"com.stepdefinitions"})
public class Runner extends AbstractTestNGCucumberTests {

}
