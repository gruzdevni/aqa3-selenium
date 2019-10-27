import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
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

    @Test
    void nameFieldValidation() {
        driver.get("http://localhost:9999");
        List <WebElement> fields = driver.findElements(By.cssSelector("[class='input__control']"));
        fields.get(0).sendKeys("Ivan Petrov");
        driver.findElement(By.cssSelector("[class='button button_view_extra button_size_m button_theme_alfa-on-white']")).submit();
        String actualText1 = driver.findElement(By.cssSelector("[class='input__sub']")).getText();
        assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.",actualText1);
        field0Clear(fields);

        fields.get(0).sendKeys("46789645");
        driver.findElement(By.cssSelector("[class='button button_view_extra button_size_m button_theme_alfa-on-white']")).submit();
        String actualText2 = driver.findElement(By.cssSelector("[class='input__sub']")).getText();
        assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.",actualText2);
        field0Clear(fields);

        fields.get(0).sendKeys("Иван_Петров");
        driver.findElement(By.cssSelector("[class='button button_view_extra button_size_m button_theme_alfa-on-white']")).submit();
        String actualText3 = driver.findElement(By.cssSelector("[class='input__sub']")).getText();
        assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.",actualText3);
        field0Clear(fields);

        fields.get(0).sendKeys("Иван ;рупин");
        driver.findElement(By.cssSelector("[class='button button_view_extra button_size_m button_theme_alfa-on-white']")).submit();
        String actualText4 = driver.findElement(By.cssSelector("[class='input__sub']")).getText();
        assertEquals("Имя и Фамилия указаные неверно. Допустимы только русские буквы, пробелы и дефисы.",actualText4);
    }

    @Test
    void phoneFieldValidation() {
        driver.get("http://localhost:9999");
        List <WebElement> fields = driver.findElements(By.cssSelector("[class='input__control']"));
        fields.get(0).sendKeys("Иван Петров-Водкин");
        fields.get(1).sendKeys("71111591234");
        driver.findElement(By.cssSelector("[class='button button_view_extra button_size_m button_theme_alfa-on-white']")).submit();
        List <WebElement> subFields = driver.findElements(By.cssSelector("[class='input__sub']"));
        String actualText1 = subFields.get(1).getText();
        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.",actualText1);
        field1Clear(fields);

        fields.get(1).sendKeys("711115912345");
        driver.findElement(By.cssSelector("[class='button button_view_extra button_size_m button_theme_alfa-on-white']")).submit();
        String actualText2 = subFields.get(1).getText();
        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.",actualText2);
        field1Clear(fields);

        fields.get(1).sendKeys("-11115912345");
        driver.findElement(By.cssSelector("[class='button button_view_extra button_size_m button_theme_alfa-on-white']")).submit();
        String actualText3 = subFields.get(1).getText();
        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.",actualText3);
        field1Clear(fields);

        fields.get(1).sendKeys("wqeerrerteryt");
        driver.findElement(By.cssSelector("[class='button button_view_extra button_size_m button_theme_alfa-on-white']")).submit();
        String actualText4 = subFields.get(1).getText();
        assertEquals("Телефон указан неверно. Должно быть 11 цифр, например, +79012345678.",actualText4);
        field1Clear(fields);
    }

    private void field0Clear(List<WebElement> fields) {
        fields.get(0).sendKeys(Keys.CONTROL + "a");
        fields.get(0).sendKeys(Keys.DELETE);
    }

    private void field1Clear(List<WebElement> fields) {
        fields.get(1).sendKeys(Keys.CONTROL + "a");
        fields.get(1).sendKeys(Keys.DELETE);
    }
}
