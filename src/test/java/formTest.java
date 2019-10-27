import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class formTest {

    private WebDriver driver;

    @BeforeAll
    static void setUpAll() {
// убедитесь, что файл chromedriver.exe расположен именно в каталоге C:\tmp
        System.setProperty("webdriver.chrome.driver", "C:\\aqa\\3\\driver\\chromedriver.exe");
    }

    @BeforeEach
    void setUp() {
        driver = new ChromeDriver();
    }

    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;
    }

    @Test
    void happyPath() {
        driver.get("http://localhost:9999");
        List <WebElement> fields = driver.findElements(By.cssSelector("[class='input__control']"));
        fields.get(0).sendKeys("Иван Петров-Водкин");
        fields.get(1).sendKeys("+71111591234");
        driver.findElement(By.cssSelector("[class='checkbox__box']")).click();
        driver.findElement(By.cssSelector("[class='button button_view_extra button_size_m button_theme_alfa-on-white']")).submit();
        String actualText = driver.findElement(By.cssSelector("[class='paragraph paragraph_theme_alfa-on-white']")).getText();
        assertEquals("  Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.",actualText);
    }
}
