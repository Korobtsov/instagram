package ko.instagram;

import ko.instagram.pages.*;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.Keys;

/**
 * Добавляет комментарии по тегу
 *
 * @author Korobtsov
 */

public class CommentByTag_Test extends BaseTestClass {

    public String tagName = "fun";
    public String comment = "Cool";

    @Test
    public void follow_by_tag() {
        LoginPage loginPage = new LoginPage(driver);
        // Авторизуемся
        loginPage.open();
        Assert.assertTrue("Страница авторизации не отображается",
                loginPage.loginPageIsPresent());
        loginPage.setLogin(Config.getLogin());
        loginPage.setPassword(Config.getPassword());
        loginPage.clickLogin();

        // Проверяем отображение новостей
        FeedPage feedPage = new FeedPage(driver);
        Assert.assertTrue("Страница списка новостей не отображается",
                feedPage.feedPageIsPresent());

        // Открываем страницу по тегу
        TagsPage tagsPage = new TagsPage(driver);
        tagsPage.openAt("https://instagram.com/explore/tags/" + tagName);
        tagsPage.tagsPageIsPresent();

        // Открываем первый пост из "Лучших публикаций"
        tagsPage.clickPostByIndex(1);

        PostPage postPage = new PostPage(driver);
        postPage.postHeaderIsPresent();

        // Проверяем наличие поля для ввода комментариев
        Assert.assertTrue("Поле ввода комментария не отображается" ,
                postPage.commentFieldIsPresent());

        // Комментируем
        postPage.commentField.click();
        postPage.commentField.sendKeys(comment);
        postPage.commentField.sendKeys(Keys.ENTER);

        // Проверяем появления комментария
        Assert.assertTrue("Комментарий не отображается",
                postPage.leavedCommentedIsPresent(Config.getLogin(), comment));
    }

}
