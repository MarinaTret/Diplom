package ru.iteco.fmhandroid.ui.Test;


import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;

import static ru.iteco.fmhandroid.ui.DataHelper.Rand.randomCategory;
import static ru.iteco.fmhandroid.ui.DataHelper.dateInFuture;
import static ru.iteco.fmhandroid.ui.DataHelper.dateInPast;
import static ru.iteco.fmhandroid.ui.DataHelper.getCurrentDate;
import static ru.iteco.fmhandroid.ui.DataHelper.getCurrentTime;

import android.view.View;

import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Description;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.DataHelper;
import ru.iteco.fmhandroid.ui.PageObject.AuthorizationPage;
import ru.iteco.fmhandroid.ui.PageObject.ControlPanelNews;
import ru.iteco.fmhandroid.ui.PageObject.CreateNewsPage;
import ru.iteco.fmhandroid.ui.PageObject.EditNewsPage;
import ru.iteco.fmhandroid.ui.PageObject.MainPage;
import ru.iteco.fmhandroid.ui.PageObject.NewsPage;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class CreateAndEditNewsTest {
    MainPage mainPage = new MainPage();
    AuthorizationPage authorizationPage = new AuthorizationPage();
    ControlPanelNews controlPanelNews = new ControlPanelNews();
    CreateNewsPage createNewsPage = new CreateNewsPage();
    EditNewsPage editNewsPage = new EditNewsPage();
    NewsPage newsPage = new NewsPage();
    private View decorView;

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void setUp() {
        Espresso.onView(isRoot()).perform(DataHelper.waitDisplayed(mainPage.getAppBarFragmentMain(), 100000));
        if (!mainPage.isDisplayedButtonProfile()) {
            authorizationPage.successfulAuthorization();
        }
    }

    @Description("Успешное создание новости. тест-кейс №57")
    @Test
    public void successfulCreateNews() {
        String publicationDate = getCurrentDate();
        String publicationTime = getCurrentTime();
        String title = "News 555";
        String description = "Описание 555";
        mainPage.openNewsPage();
        newsPage.openControlPanelNews();
        controlPanelNews.addNews();
        createNewsPage.createNews(randomCategory(), title, publicationDate,
                publicationTime, description);
        createNewsPage.pressSave();
        controlPanelNews.searchNewsWithTitle(title);
    }

    @Description("Создание новости с датой публикации в прошлом. тест-кейс №66")
    @Test
    public void CreateNewsInPast() {
        String publicationDate = dateInPast();
        String publicationTime = getCurrentTime();
        String title = "News 333";
        String description = "Описание 333";

        mainPage.openNewsPage();
        newsPage.openControlPanelNews();
        controlPanelNews.addNews();
        createNewsPage.createNews(randomCategory(), title, publicationDate,
                publicationTime, description);
        createNewsPage.pressSave();
        controlPanelNews.searchNewsWithTitle(title);
    }

    @Description("Создание новости с пустыми данными. тест-кейс №73")
    @Test
    public void createEmptyNews() {
        mainPage.openNewsPage();
        newsPage.openControlPanelNews();
        controlPanelNews.addNews();
        createNewsPage.pressSave();
        createNewsPage.checkToastMessageText("Fill empty fields", decorView);
    }

    //тест падает
    @Description("Редактирование новости. тест-кейс №77")
    @Test
    public void editNews() {

        String publicationDate = getCurrentDate();
        String publicationTime = getCurrentTime();
        String title = "News 777";
        String newTitle = "News 777 new";
        String description = "Описание 777";
        String newDescription = "Описание 777 изменено";
        mainPage.openNewsPage();
        newsPage.openControlPanelNews();
        controlPanelNews.addNews();
        createNewsPage.createNews(randomCategory(), title, publicationDate,
                publicationTime, description);
        createNewsPage.pressSave();

        controlPanelNews.searchNewsWithTitle(title);
        controlPanelNews.clickEditNews(title);
        editNewsPage.editNewsFields(randomCategory(), newTitle, dateInFuture(),
                publicationTime, newDescription);

        editNewsPage.clickSave();
        controlPanelNews.searchNewsWithTitle(newTitle);
    }


    //тест падает
    @Description("Удаление новости. тест-кейс №53")
    @Test
    public void deleteNews() {
        String publicationDate = getCurrentDate();
        String publicationTime = getCurrentTime();
        String title = "News 1";
        String description = "description";

        mainPage.openNewsPage();
        newsPage.openControlPanelNews();
        controlPanelNews.addNews();

        createNewsPage.createNews(randomCategory(), title, publicationDate,
                publicationTime, description);
        createNewsPage.pressSave();

        controlPanelNews.clickDeleteNews(title);
        controlPanelNews.checkIfNoNews(title);

    }
}