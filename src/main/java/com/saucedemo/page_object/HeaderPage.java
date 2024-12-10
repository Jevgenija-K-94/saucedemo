package com.saucedemo.page_object;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

public class HeaderPage {

    public HeaderPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
    @FindBy(how = How.CLASS_NAME, using = "shopping_cart_link")
    private WebElement shoppingCartLink;

    @FindBy(how = How.CLASS_NAME, using = "shopping_cart_badge")
    private WebElement shoppingCartBadge;

    public WebElement getShoppingCartLink() {
        return shoppingCartLink;
    }

    public WebElement getShoppingCartBadge() {
        return shoppingCartBadge;
    }
}
