import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import site.nomoreparties.stellarburgers.factory.WebDriverFactory;
import site.nomoreparties.stellarburgers.page.object.HeaderPage;
import site.nomoreparties.stellarburgers.page.object.HomePage;
import site.nomoreparties.stellarburgers.page.object.LoginPage;
import static site.nomoreparties.stellarburgers.constant.Browser.BROWSER_CHROME;

public class PersonalAccountTest {
    private WebDriver driver;
    private HomePage homePage;
    private HeaderPage headerPage;
    private LoginPage loginPage;

    @Before
    @DisplayName("Инициализация драйвера и страниц перед тестом")
    @Description("Метод настраивает WebDriver для браузера и создает экземпляры страниц (HomePage, HeaderPage и LoginPage) перед выполнением каждого теста.")
    public void setUp() {
        driver = WebDriverFactory.createForName(BROWSER_CHROME);
        homePage = new HomePage(driver);
        headerPage = new HeaderPage(driver);
        loginPage = new LoginPage(driver);
    }

    @Test
    @DisplayName("Проверка перехода в личный кабинет")
    @Description("Тест проверяет переход в личный кабинет при нажатии на кнопку \"Личный кабинет\" со стартовой странице")
    public void checkingTransitionClickingOnPersonalAccount() {
        homePage.openingHomePage();
        headerPage.clickButtonPersonalAccount();
        Assert.assertTrue(loginPage.openingLoginForm());
    }

    @After
    @DisplayName("Закрытие браузера")
    @Description("Метод закрывает браузер")
    public void tearDown(){
        driver.quit();
    }

}