package site.nomoreparties.stellarburgers.factory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class WebDriverFactory {

    public static WebDriver createForName(String browserName) {
        if (browserName.equals("CHROME")) {
            return createChromeDriver();
        } else if (browserName.equals("YANDEX")) {
            return createYandexDriver();
        } else {
            throw new RuntimeException("Нераспознанный браузер: " + browserName);
        }
    }

    public static WebDriver createChromeDriver() {
        WebDriverManager.chromedriver().setup();
        return new ChromeDriver();
    }

    public static WebDriver createYandexDriver() {
        String yandexBrowserPath = System.getenv("YANDEX_BROWSER_PATH");

        if (yandexBrowserPath == null || yandexBrowserPath.isEmpty()) {
            throw new RuntimeException("Переменная среды YANDEX_BROWSER_PATH не установлена.");
        }

        WebDriverManager.chromedriver().driverVersion("132.0.6834.111").setup();

        ChromeOptions options = new ChromeOptions();
        options.setBinary(yandexBrowserPath);
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--remote-debugging-port=9222");
        options.addArguments("--disable-gpu");

        return new ChromeDriver(options);
    }
}


