package com.testerfabrik.commons;

import com.testerfabrik.utils.LocatorType;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import java.util.concurrent.TimeUnit;

public class Commons {
    public WebDriver driver;
    String xpathLoc = ".//*[contains(text(),'Note: Your user name is')]";

    //Accesor
    public WebDriver getDriver(){
        if(driver == null){
            System.setProperty("webdriver.chrome.driver", System.getProperty("user.dir") + "/drivers/chromedriver.exe");
            driver = new ChromeDriver();
        }
        return driver;
    }

    // Funciones communes
    // Método para navegar a la página de demo auto new tours
    public void navigateTo(){
        getDriver();
        getDriver().get("http://newtours.demoaut.com/");
        getDriver().manage().window().maximize();
        getDriver().manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
    }

    // Método para escribir en un cuadro de texto
    public void typeInTextBox(LocatorType locatorType, String locator, String textToType ){
        switch(locatorType.toString()){
            case "Name":
                getDriver().findElement(By.name(locator)).sendKeys(textToType);
                break;
            case "Id":
                getDriver().findElement(By.id(locator)).sendKeys(textToType);
                break;
        }
    }

    // Método para hacer clic en un elemento
    public void clickOnLink(LocatorType locatorType, String locator){
        switch(locatorType.toString()){
            case "LinkText":
                getDriver().findElement(By.linkText(locator)).click();
                break;
        }
    }
    // Método para obtener el texto de un elemento
    public String getElementText(){
        return getDriver().findElement(By.xpath(xpathLoc)).getText();
    }

    // Método para seleccionar un item de una lista desplegable
    public void selectFromDropDown(LocatorType locatorType, String locator,String text){
        switch(locatorType.toString()){
            case "Name":
                new Select(getDriver().findElement(By.name(locator))).selectByVisibleText(text);
                break;
            case "Id":
                new Select(getDriver().findElement(By.name(locator))).selectByVisibleText(text);
                break;
        }
    }
}
