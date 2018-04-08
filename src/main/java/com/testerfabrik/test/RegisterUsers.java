package com.testerfabrik.test;

import com.testerfabrik.commons.Commons;
import com.testerfabrik.utils.LocatorType;
import com.testerfabrik.utils.TestData;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class RegisterUsers extends Commons {
    @BeforeTest
    public void setUp(){
        navigateTo();
    }

    @AfterTest
    public void tearDown(){
        getDriver().quit();
    }

    @BeforeMethod
    public void clickRegister(){
        clickOnLink(LocatorType.LinkText, "REGISTER");
    }

    @Test(dataProviderClass = TestData.class, dataProvider = "MySQL-provider")
    public void registerUser(String ... registerInfo){

        // Agregando la información de contacto
        typeInTextBox(LocatorType.Name, "firstName",registerInfo[1]);
        typeInTextBox(LocatorType.Name, "lastName", registerInfo[2]);
        typeInTextBox(LocatorType.Name, "phone", registerInfo[3]);
        typeInTextBox(LocatorType.Id, "userName",registerInfo[4]);

        // Agregando la información de correo
        typeInTextBox(LocatorType.Name, "address1", registerInfo[5]);
        typeInTextBox(LocatorType.Name, "city", registerInfo[6]);
        typeInTextBox(LocatorType.Name, "state", registerInfo[7]);
        typeInTextBox(LocatorType.Name, "postalCode", registerInfo[8]);
        selectFromDropDown(LocatorType.Name, "country", registerInfo[9]);

        // Agregando la información del usuario
        typeInTextBox(LocatorType.Id, "email", registerInfo[10]);
        typeInTextBox(LocatorType.Name, "password", registerInfo[11]);
        typeInTextBox(LocatorType.Name, "confirmPassword", registerInfo[11]);
        getDriver().findElement(By.name("confirmPassword")).submit();

        // Verificar si el nombre de usuario se encuentra 
        Assert.assertTrue(getElementText().contains(registerInfo[10]));
    }
}
