package ko.instagram;

import ko.instagram.pages.*;
import org.junit.Assert;
import org.junit.Test;

import static java.lang.Math.random;

/**
 * Добавляет лайки новостям по тегам
 *
 * @author timestamp
 */
public class AddLikeByTags_Test extends BaseTestClass {

    @Test
    public void add_like_by_tags() {

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
        tagsPage.openAt("https://instagram.com/explore/tags/ресницынижнийновгород");
        tagsPage.tagsPageIsPresent();

        // Открываем первый пост
        tagsPage.clickPostByIndex(1);

        PostPage postPage = new PostPage(driver);
        postPage.postHeaderIsPresent();

        /* В цикле ставим лайки рандомному кол-ву постов (от 60 до 80)
        * чтобы не попасть под фильтр бана
        */
        int i = 1;
        int j = (int) (random()*20+60);
        int timeout = (int) (Math.random()*5000+36000);
        while (i < j) {
            i = i + 1;

            // Ставим лайк
            postPage.likeButtonIsPresent();
            postPage.clickLikeButton(timeout);
            postPage.postHeaderIsPresent();

            // Переходим далее
            postPage.clickRightArrowButton();
            postPage.postHeaderIsPresent();
        }
    }
}
