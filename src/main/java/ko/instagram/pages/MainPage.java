package ko.instagram.pages;

import net.serenitybdd.core.annotations.findby.FindBy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

/**
 * Общие методы работы со страницей
 * 
 * @author timestamp
 */
public class MainPage extends BasePage {

    @FindBy(css = ".-cx-PRIVATE-Shell__nav")
    private WebElement headline;
    @FindBy(css = ".-cx-PRIVATE-Shell__footer")
    private WebElement footer;

    public MainPage(WebDriver driver) {
        super(driver);
    }

    /**
     * Прокручиваем скролл вверх страницы
     */
    public void scrollPageUp() {
        int y = element(headline).getLocation().getY();
        evaluateJavascript("window.scrollTo(0," + y + ")");
    }

    /**
     * Прокручиваем скролл вниз страницы
     */
    public void scrollPageDown() {
        int y = element(footer).getLocation().getY();
        evaluateJavascript("window.scrollTo(0," + y + ")");
    }

    /**
     * Проверяет что скролл вверху страницы
     * @return
     */
    public boolean isScrollOnTop() {
        return ((Number) evaluateJavascript("return $(window).scrollTop();")).intValue() == 0;
    }
}
