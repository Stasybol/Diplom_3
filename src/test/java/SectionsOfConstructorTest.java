import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import site.nomoreparties.stellarburgers.factory.WebDriverFactory;
import site.nomoreparties.stellarburgers.page.object.HomePage;
import static site.nomoreparties.stellarburgers.constant.Browser.BROWSER_CHROME;


public class SectionsOfConstructorTest {
    private WebDriver driver;
    private HomePage homePage;
    private WebElement sectionElement;

    @Before
    @DisplayName("Инициализация драйвера и страницы, открытие стартовой страницы")
    @Description("Метод настраивает WebDriver для браузера, создает экземпляр страницы HomePage, открывает стартовую страницу и увеличивает окно браузера до максимального размера")
    public void setUp() {
        driver = WebDriverFactory.createForName(BROWSER_CHROME);
        homePage = new HomePage(driver);
        homePage.openingHomePage();
        driver.manage().window().maximize();
    }

    @Test
    @DisplayName("Проверка перехода к разделу \"Булки\"")
    @Description("Тест проверяет, что текст раздела \"Булки\" находится в области видимости.")
    public void isDisplayedSectionBunsTest() {
        homePage.clickSectionSauces();
        homePage.clickSectionBuns();
        sectionElement = homePage.elementTextBuns();
        Assert.assertTrue("Текст \"Булки\" не находится в области видимости!", homePage.isElementInViewport(sectionElement));
    }

    @Test
    @DisplayName("Проверка перехода к разделу \"Соусы\"")
    @Description("Тест проверяет, что текст раздела \"Соусы\" находится в области видимости.")
    public void isDisplayedSectionSaucesTest() {
        homePage.clickSectionSauces();
        sectionElement = homePage.elementTextSauces();
        Assert.assertTrue("Текст \"Соусы\" не находится в области видимости!", homePage.isElementInViewport(sectionElement));
    }

    @Test
    @DisplayName("Проверка перехода к разделу \"Начинки\"")
    @Description("Тест проверяет, что текст раздела \"Начинки\" находится в области видимости.")
    public void isDisplayedSectionFillingsTest() {
        homePage.clickSectionFillings();
        sectionElement = homePage.elementTextFillings();
        Assert.assertTrue("Текст \"Начинки\" не находится в области видимости!", homePage.isElementInViewport(sectionElement));
    }

    @After
    @DisplayName("Закрытие браузера")
    @Description("Метод закрывает браузер")
    public void tearDown() {
        driver.quit();

    }
}
