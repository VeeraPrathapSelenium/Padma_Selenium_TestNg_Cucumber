package com.genericmethods;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Reporter;
import ru.yandex.qatools.allure.annotations.Attachment;
import ru.yandex.qatools.allure.annotations.Step;

import static java.util.concurrent.TimeUnit.SECONDS;

public class GenericMethods {

    public static WebDriver driver;

    /**
     * @param browserType
     * @param url
     * @return boolean
     * @discription This method is used to launch the browser specified by the user
     */
    @Step("Launch the browser {0}, with the url {1}")
    public boolean launchBrowser(String browserType,String url){
        boolean status=true;
        try
        {
            switch (browserType.toLowerCase())
            {
                case "chrome":
                    WebDriverManager.chromedriver().setup();
                    driver=new ChromeDriver();
                    break;
                case "firefox":
                    WebDriverManager.firefoxdriver().setup();
                    driver=new FirefoxDriver();
                    break;

            }
            //maximize the browser
            driver.manage().window().maximize();
            driver.get(url);
            driver.manage().timeouts().implicitlyWait(10, SECONDS);
            getScreenShot();
            Reporter.log("Testing......................!!!!!!");

        }catch(Exception e)
        {
            status=false;
        }
        return status;

    }

    /**
     *
     * @param pageName
     * @param elementName
     * @param element
     * @return boolean
     */
    public boolean clickElement(String pageName, String elementName, WebElement element){
        boolean status=true;
        try{
            Actions acc=new Actions(driver);
            acc.moveToElement(element).click(element).build().perform();

        }catch(Exception e){
            status=false;
        }
        return status;
    }

    /**
     *
     * @param pageName
     * @param elementName
     * @param element
     * @return
     */
    public boolean sendData(String pageName, String elementName, WebElement element,String data){
        boolean status=true;
        try{
            Actions acc=new Actions(driver);
            acc.moveToElement(element).click(element).build().perform();
            element.clear();
            element.sendKeys(data);
        }catch(Exception e){
            status=false;
        }
        return status;
    }

    /**
     *
     * @param pageName
     * @param elementName
     * @param element
     * @param data
     * @return
     */
    public boolean selectDropDownByText(String pageName, String elementName, WebElement element,String data){
        boolean status=true;
        try{
            Select select=new Select(element);
            select.selectByVisibleText(data);
        }catch(Exception e){
            status=false;
        }
        return status;
    }

    /**
     *
     * @param pageName
     * @param elementName
     * @param element
     * @param data
     * @return
     */
    public boolean selectDropDownByValue(String pageName, String elementName, WebElement element,String data){
        boolean status=true;
        try{
            Select select=new Select(element);
            select.selectByValue(data);
        }catch(Exception e){
            status=false;
        }
        return status;
    }

    /**
     *
     * @param pageName
     * @param elementName
     * @param element
     * @param data
     * @return
     */
    public boolean selectDropDownByIndex(String pageName, String elementName, WebElement element,int data){
        boolean status=true;
        try{
            Select select=new Select(element);
            select.selectByIndex(data);
        }catch(Exception e){
            status=false;
        }
        return status;
    }

    public boolean waitForEementToBeClickable(String pageName, String elementName,WebElement element,int sec){
        boolean status=true;
        try{
            WebDriverWait wait=new WebDriverWait(driver,sec);
            wait.until(ExpectedConditions.elementToBeClickable(element));

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return status;
    }

    public boolean waitForElementToDisplay(String pageName, String elementName,WebElement element,int sec){
        boolean status=true;
        try{
            WebDriverWait wait=new WebDriverWait(driver,sec);
            wait.until(ExpectedConditions.visibilityOf(element));

        }catch(Exception e){
            System.out.println(e.getMessage());
        }
        return status;
    }
    @Attachment(value = "Page screenshot", type = "image/png")
    public byte[] getScreenShot(){
        TakesScreenshot sht=(TakesScreenshot)driver;
       return sht.getScreenshotAs(OutputType.BYTES);
    }
}
