package ko.instagram.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

/**
 * Страница просмотра поста
 * 
 * @author timestamp
 */
public class PostPage extends  MainPage{

    public PostPage(WebDriver driver) {
        super(driver);
    }
    private String commentOfUser = "//*[@title='%s']/following-sibling::*//*[contains(text(), '%s')]";
    private String deletingButton = "//*[@title='%s']/following-sibling::*//*[contains(text(), '%s')]/preceding::button[@class ='_4vltl']";

    @FindBy(css = ".y09")
    public WebElement postHeader;
    @FindBy(css = "[data-reactid='.1.1.0.0.2.2.0']")
    public WebElement likeButton;
    @FindBy(css = ".coreSpriteRightPaginationArrow")
    public WebElement rightArrow;
    @FindBy(css = "[data-reactid='.1.1.0.0.0.2.0']")
    public WebElement followButton;   
    @FindBy(xpath = "//*[@class='_7uiwk _qy55y' and @type='text']")
    public WebElement commentField;
    @FindBy(xpath = "//button[@class='_4y3e3 _q7p6k']")
    public WebElement confirmatiomDeleteButton;

     /**
     * Проверяем, что страница поста отрылась
     *
     * @return boolean
     */
    public boolean postHeaderIsPresent() {
        return waitForElementPresent(postHeader);
    }
    
    /**
     * Проверяем, что сердечко отображается
     *
     * @return boolean
     */
    public boolean likeButtonIsPresent() {
        return waitForElementPresent(likeButton);
    }
    
    /**
     * Нажимаем Сердечко с заданным периодом
     * 
     * @param timeout
     */
    public void clickLikeButton(int timeout) {
        likeButton.click();
        waitTimeout(timeout);
    }
    
    /**
     * Проверяем, что Подписка отображается
     *
     * @return boolean
     */
    public boolean followButtonIsPresent() {
        return waitForElementPresent(followButton);
    }
    
    /**
     * Нажимаем Подписку с заданным периодом
     * 
     * @param timeout
     */
    public void clickFollowButton(int timeout) {
        followButton.click();
        waitTimeout(timeout);
    }
    
    /**
     * Нажимаем Далее
     */
    public void clickRightArrowButton() {
        rightArrow.click();
    }

    /**
     * Проверяем наличие поля для комментирования
     *
     * @return boolean
     */
    public boolean commentFieldIsPresent() { return waitForElementPresent(commentField); }

    /**
     * Проверяем наличие оставленного комментария
     *
     * @param userName
     * @param leavedComment
     * @return boolean
     */
    public boolean leavedCommentedIsPresent(String userName, String leavedComment) {
        String locator = String.format(commentOfUser, userName, leavedComment);
        return waitForElementPresent(By.xpath(locator));
    }

    /**
     * Удаление оставленного комментария
     *
     * @param userName
     * @param leavedComment
     */
    public void deleteLeavedComment(String userName, String leavedComment){
        String locator = String.format(deletingButton, userName, leavedComment);
        element(locator).click();
        confirmatiomDeleteButton.click();
    }

}
