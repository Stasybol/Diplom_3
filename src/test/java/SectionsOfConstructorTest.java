import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.Rectangle;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import site.nomoreparties.stellarburgers.factory.WebDriverFactory;
import site.nomoreparties.stellarburgers.page.object.HomePage;
import static site.nomoreparties.stellarburgers.constant.Browser.BROWSER_CHROME;
import static site.nomoreparties.stellarburgers.constant.UrlAndDuration.DEFAULT_TIMEOUT;


public class SectionsOfConstructorTest {
    private WebDriver driver;
    private HomePage homePage;

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
        WebElement sectionElement = homePage.elementTextBuns();
        boolean isElementInViewport = new WebDriverWait(driver, DEFAULT_TIMEOUT)
                .until(driver -> {
                    Rectangle rect = sectionElement.getRect();
                    Dimension windowSize = driver.manage().window().getSize();

                    return rect.getX() >= 0
                            && rect.getY() >= 0
                            && rect.getX() + rect.getWidth() <= windowSize.getWidth()
                            && rect.getY() + rect.getHeight() <= windowSize.getHeight();
                });
        Assert.assertTrue("Текст \"Булки\" не находится в области видимости!", isElementInViewport);
    }

    @Test
    @DisplayName("Проверка перехода к разделу \"Соусы\"")
    @Description("Тест проверяет, что текст раздела \"Соусы\" находится в области видимости.")
    public void isDisplayedSectionSaucesTest() {
        homePage.clickSectionSauces();
        WebElement sectionElement = homePage.elementTextSauces();
        boolean isElementInViewport = new WebDriverWait(driver, DEFAULT_TIMEOUT)
                .until(driver -> {
                    Rectangle rect = sectionElement.getRect();
                    Dimension windowSize = driver.manage().window().getSize();

                    return rect.getX() >= 0
                            && rect.getY() >= 0
                            && rect.getX() + rect.getWidth() <= windowSize.getWidth()
                            && rect.getY() + rect.getHeight() <= windowSize.getHeight();
                });
        Assert.assertTrue("Текст \"Соусы\" не находится в области видимости!", isElementInViewport);
    }

    @Test
    @DisplayName("Проверка перехода к разделу \"Начинки\"")
    @Description("Тест проверяет, что текст раздела \"Начинки\" находится в области видимости.")
    public void isDisplayedSectionFillingsTest() {
        homePage.clickSectionFillings();
        WebElement sectionElement = homePage.elementTextFillings();
        boolean isElementInViewport = new WebDriverWait(driver, DEFAULT_TIMEOUT)
                .until(driver -> {
                    Rectangle rect = sectionElement.getRect();
                    Dimension windowSize = driver.manage().window().getSize();

                    return rect.getX() >= 0
                            && rect.getY() >= 0
                            && rect.getX() + rect.getWidth() <= windowSize.getWidth()
                            && rect.getY() + rect.getHeight() <= windowSize.getHeight();
                });
        Assert.assertTrue("Текст \"Начинки\" не находится в области видимости!", isElementInViewport);
    }

    @After
    @DisplayName("Закрытие браузера")
    @Description("Метод закрывает браузер")
    public void tearDown() {
        driver.quit();

    }
}
