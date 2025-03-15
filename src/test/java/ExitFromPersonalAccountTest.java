import io.qameta.allure.Description;
import io.qameta.allure.junit4.DisplayName;
import io.restassured.response.ValidatableResponse;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import site.nomoreparties.stellarburgers.client.BurgerServiceClient;
import site.nomoreparties.stellarburgers.factory.WebDriverFactory;
import site.nomoreparties.stellarburgers.model.Credentials;
import site.nomoreparties.stellarburgers.model.User;
import site.nomoreparties.stellarburgers.page.object.*;
import static org.apache.http.HttpStatus.SC_ACCEPTED;
import static org.hamcrest.CoreMatchers.equalTo;
import static site.nomoreparties.stellarburgers.constant.Browser.BROWSER_CHROME;
import static site.nomoreparties.stellarburgers.constant.UrlAndDuration.PAGE_URL;

public class ExitFromPersonalAccountTest {
    private User user;
    private BurgerServiceClient client;
    private String token;
    private Credentials credentials;
    private WebDriver driver;
    private HomePage homePage;
    private HeaderPage headerPage;
    private LoginPage loginPage;
    private PersonalAccountPage personalAccountPage;

    @Before
    @DisplayName("Подготовка данных: создание пользователя")
    @Description("Создание пользователя перед каждым тестом. Предполагается, что пользователь успешно создан и получен его token.")
    public void createUser(){
        client = new BurgerServiceClient(PAGE_URL);
        user = User.allField();
        ValidatableResponse responseCreate = client.createUser(user);
        responseCreate.assertThat().body("success", equalTo(true));
        token = responseCreate.extract().header("authorization");
        credentials = Credentials.fromUser(user);
    }

    @Test
    @DisplayName("Проверка выхода из личного кабинета")
    @Description("Тест проверяет выход из личного кабинета с предварительной авторизацией и переходом в личный кабинет")
    public void exitFromPersonalAccountTest(){
        driver = WebDriverFactory.createForName(BROWSER_CHROME);
        homePage = new HomePage(driver);
        homePage.openingHomePage();
        headerPage = new HeaderPage(driver);
        headerPage.clickButtonPersonalAccount();
        loginPage = new LoginPage(driver);
        Assert.assertTrue(loginPage.openingLoginForm());
        loginPage.login(credentials.getEmail(), credentials.getPassword());
        Assert.assertTrue(homePage.homePageAfterAuthorization());
        headerPage.clickButtonPersonalAccount();
        personalAccountPage = new PersonalAccountPage(driver);
        Assert.assertTrue(personalAccountPage.openingPersonalAccount());
        personalAccountPage.clickButtonExit();
        Assert.assertTrue(loginPage.openingLoginForm());
    }

    @After
    @DisplayName("Закрытие браузера и удаление пользователя")
    @Description("Метод закрывает браузер и удаляет пользователя после выполнения каждого теста, если пользователь был успешно создан")
    public void tearDown(){
        driver.quit();
        ValidatableResponse responseDelete = client.deleteUser(token);
        responseDelete.assertThat().statusCode(SC_ACCEPTED).body("success", equalTo(true));
    }
}
