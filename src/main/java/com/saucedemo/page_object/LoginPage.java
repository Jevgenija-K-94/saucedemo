package com.saucedemo.page_object;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;


public class LoginPage {
    WebDriverWait wait;

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        wait = new WebDriverWait(driver, Duration.ofSeconds(2));
    }

    // PageFactory
    @FindBy(id = "user-name")
    public WebElement usernameField;

    @FindBy(name = "password")
    public WebElement passwordField;

    @FindBy(xpath = "//input[@data-test='login-button']")
    public WebElement loginButton;

    public void authorize(String username, String password) {
        usernameField.sendKeys(username);
        passwordField.sendKeys(password);
        //explicitly wait element to be clickable
        wait.until(ExpectedConditions.elementToBeClickable(loginButton));
        loginButton.click();
    }
/*
    public  String getErrorMessage(){
        if (getErrorMessage.isDisplayed()){
            return getErrorMessage.getText();
        }
        return null;
    }
 */
}