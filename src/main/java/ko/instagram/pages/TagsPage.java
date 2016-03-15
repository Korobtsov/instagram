package ko.instagram.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Страница новостей по тегу
 * 
 * @author timestamp
 */
public class TagsPage extends MainPage {

    public TagsPage(WebDriver driver) {
        super(driver);
    }
    
    String postInfo = "[data-reactid='.0.1.0.1:$topSection/=1$topPosts/=010.1'] [data-reactid='.0.1.0.1:$topSection/=1$topPosts/=010.1.$0'] a:nth-of-type(%d)";
    
    @FindBy(css = "-cx-PRIVATE-TagPage__tagName")
    public WebElement tagNameElement;    
    
    /**
     * Проверяем, что страница новостей по тегу отрылась
     *
     * @return boolean
     */
    public boolean tagsPageIsPresent() {
        return waitForElementPresent(tagNameElement);
    }
    
    /**
     * Открываем пост по индексу
     *
     * @param index
     */
    public void clickPostByIndex(int index) {
        String locator = String.format(postInfo, index);
        element(locator).click();
    }
}
