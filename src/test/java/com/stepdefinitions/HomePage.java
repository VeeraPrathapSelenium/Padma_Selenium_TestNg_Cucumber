package com.stepdefinitions;

import com.genericmethods.GenericMethods;
import cucumber.api.java.en.Given;
import ru.yandex.qatools.allure.annotations.Severity;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.model.SeverityLevel;

public class HomePage extends GenericMethods {
    @Given("launch Browser")
    @Severity(SeverityLevel.NORMAL)
    public void openApplication(){
        launchBrowser("chrome","https://demo.nopcommerce.com/");
    }

}
