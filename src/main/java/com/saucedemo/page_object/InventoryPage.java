package com.saucedemo.page_object;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import java.util.List;

public class InventoryPage {

    public InventoryPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    @FindBy(className = "inventory_item")
    private List<WebElement> inventoryItemList;

    private final By inventoryButton = By.xpath(".//button[contains(@class,'btn_inventory')]");

    public void selectItemByName(String itemName) {
        for (WebElement webElement : inventoryItemList) {
            if (webElement.getText().contains(itemName)) {
                webElement.findElement(inventoryButton).click();
            }
        }
    }
}