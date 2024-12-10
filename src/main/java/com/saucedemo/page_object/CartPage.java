package com.saucedemo.page_object;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class CartPage {
    public CartPage(WebDriver driver){
        PageFactory.initElements(driver, this);
    }
    @FindBy(how = How.CLASS_NAME, using = "cart_item")
    private List<WebElement> cartItems;

    public List<WebElement> getCartItems(){
        return  cartItems;
    }

}
