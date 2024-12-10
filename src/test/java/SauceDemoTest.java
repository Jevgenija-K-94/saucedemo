import com.saucedemo.page_object.CartPage;
import com.saucedemo.page_object.HeaderPage;
import com.saucedemo.page_object.InventoryPage;
import com.saucedemo.page_object.LoginPage;
import org.apache.commons.configuration2.Configuration;
import org.apache.commons.configuration2.builder.fluent.Configurations;
import org.apache.commons.configuration2.ex.ConfigurationException;
import org.assertj.core.api.Assertions;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SauceDemoTest {

    ChromeDriver driver;
    LoginPage loginPage;
    InventoryPage inventoryPage;
    HeaderPage headerPage;
    CartPage cartPage;

    Configurations configs;
    Configuration config;

    @BeforeMethod
    public void setUp() throws ConfigurationException {
        driver = new ChromeDriver();
        loginPage = new LoginPage(driver);
        inventoryPage = new InventoryPage(driver);
        headerPage = new HeaderPage(driver);
        cartPage = new CartPage(driver);

        configs = new Configurations();
        config = configs.properties("config.properties");

        driver.get(config.getString("web.url"));
    }

    @Test
    public void sauceDemoLoginTest() {
        loginPage.authorize(config.getString("username"), config.getString("password"));
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");
    }

    @Test
    public void sauceDemoAddItemToTheCartTest() {
        loginPage.authorize(config.getString("username"), config.getString("password"));
        Assert.assertEquals(driver.getCurrentUrl(), "https://www.saucedemo.com/inventory.html");

        inventoryPage.selectItemByName("Backpack");
        inventoryPage.selectItemByName("Bike Light");
        Assertions.assertThat(headerPage.getShoppingCartBadge().getText()).isEqualTo("2");

        headerPage.getShoppingCartLink().click();
        Assertions.assertThat(cartPage.getCartItems().size()).isEqualTo(1);
        //классический
        Assertions.assertThat(cartPage.getCartItems().get(0).getText()).contains("Backpack");
        Assertions.assertThat(cartPage.getCartItems().get(0).getText()).contains("Bike Light");

        //стиль функционального программирования
        Assertions.assertThat(cartPage.getCartItems()).extracting(WebElement::getText).anyMatch(text -> text.contains("Backpack"));
        Assertions.assertThat(cartPage.getCartItems()).extracting(WebElement::getText).anyMatch(text -> text.contains("Bike Light"));

    }

    @AfterMethod
    public void tearDown() {
        driver.close();
        driver.quit();
    }

}