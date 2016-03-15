package ko.instagram;

import org.junit.Assert;
import org.junit.Test;
import ko.instagram.pages.*;

/**
 * Подписка по тегу
 * @author Natalya Seregina <n.seregina@corp.mail.ru>
 */
public class FollowByTag_Test extends BaseTestClass {
    
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
        tagsPage.openAt("https://instagram.com/explore/tags/нижнийновгород");
        tagsPage.tagsPageIsPresent();

        // Открываем первый пост
        tagsPage.clickPostByIndex(1);

        PostPage postPage = new PostPage(driver);
        postPage.postHeaderIsPresent();

      /*  *//* В цикле подписываемся под рандомным кол-вом постов (от 60 до 80)
        * чтобы не попасть под фильтр бана
        *//*
        int i = 1;
        int j = (int) (random()*20+20);
        int timeout = (int) (Math.random()*5000+36000);
        while (i < j) {
            i = i + 1;

            // Подписываемся
            postPage.followButtonIsPresent();
            postPage.clickFollowButton(timeout);
            postPage.postHeaderIsPresent();

            // Переходим далее
            postPage.clickRightArrowButton();
            postPage.postHeaderIsPresent();
        }*/
    }

}
