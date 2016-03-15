package ko.instagram.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Страница списка списка новостей
 *
 * @author timestamp
 */
public class FeedPage extends MainPage {

    public FeedPage(WebDriver driver) {
        super(driver);
    }

    String likeButton = ".//*[@id='react-root']//article[%d]//*[@role='button']";
    String feedListElement = "[data-reactid='.0.1']";
    String searchResultElement = ".-cx-PRIVATE-Search__result:nth-of-type(%d) .-cx-PRIVATE-Search__resultLink";

    @FindBy(css = ".-cx-PRIVATE-FeedPage__root")
    public WebElement feedsListElement;
    @FindBy(css = "[data-reactid='.0.1.0.1.2.0']")
    public WebElement moreLinkButton;
    @FindBy(css = ".-cx-PRIVATE-SearchBox__inactiveLabel")
    public WebElement searchFieldElement;

    /**
     * Проверяем, что страница новостей отрылась
     *
     * @return boolean
     */
    public boolean feedPageIsPresent() {
        return waitForElementPresent(By.cssSelector(feedListElement));
    }

    /**
     * Проверяем, что сердечко новости отображается по индексу
     *
     * @param index
     * @return boolean
     */
    public boolean likeButtonByIndexFeedIsPresent(int index) {
        String locator = String.format(likeButton, index);
        return waitForElementPresent(By.xpath(locator));
    }

    /**
     * Нажимаем Like по индексу новости
     *
     * @param index
     */
    public void clickLikeByIndexFeed(int index) {
        String locator = String.format(likeButton, index);
        element(locator).click();
    }

    /**
     * Проверяем наличие кнопки "Загрузить еще"
     *
     * @return boolean
     */
    public boolean moreLinkButtonIsPresent() {
        return waitForElementPresent(moreLinkButton);
    }

    /**
     * Нажимаем "Загрузить еще"
     */
    public void clickMoreLinkButton() {
        moreLinkButton.click();
    }

    /**
     * Нажимаем Like на новостях c периодом подгрузки в 12 новостей
     * подходит под ограничение 100 лайков в час (от 36 секунд между лайками)
     */
    public void clickLikeAllFeed() {
        for (int i = 1; i <= 12; i++) {
            likeButtonByIndexFeedIsPresent(i);
            clickLikeByIndexFeed(i);
        }
        moreLinkButtonIsPresent();
        clickMoreLinkButton();
        int j = 0;
        int timeout = (int) (Math.random()*5000+36000);
        for (int i = 13; i <= 96; i++) {
            likeButtonByIndexFeedIsPresent(i);
            clickLikeByIndexFeed(i);
            waitABit(timeout);
            j++;
//            if (j > 12) {
//                j = 0;
//                scrollPageDown();
//            }
        }
    }

    /**
     * Проверяем наличие поля поиска
     *
     * @return boolean
     */
    public boolean searchFieldIsPresent() {
        return waitForElementPresent(searchFieldElement);
    }

    /**
     * Заполняем поле поиска
     *
     * @param value
     */
    public void setSearchField(String value) {
        searchFieldElement.click();
        searchFieldElement.sendKeys(value);
    }

    /**
     * Проверяем наличие результата поиска по индексу
     *
     * @param index
     * @return boolean
     */
    public boolean searchResultIsPresent(int index) {
        String selector = String.format(searchResultElement, index);
        return waitForElementPresent(By.cssSelector(selector));
    }

    /**
     * Нажимаем результат поиска по индексу
     *
     * @param index
     */
    public void clickSearchResultByIndex(int index) {
        String locator = String.format(searchResultElement, index);
        element(locator).click();
    }
}
