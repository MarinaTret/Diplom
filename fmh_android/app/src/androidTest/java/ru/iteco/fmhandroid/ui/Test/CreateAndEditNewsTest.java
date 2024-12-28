package ru.iteco.fmhandroid.ui.Test;

import static androidx.test.espresso.matcher.ViewMatchers.isRoot;

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
    public void createNews() {
        mainPage.openNewsPage();
        newsPage.openControlPanelNews();
        controlPanelNews.addNews();
        createNewsPage.addCategory("Объявление");
        createNewsPage.addTitle("Новость тест");
        createNewsPage.addDate(DataHelper.currentDate());
        createNewsPage.addTime("16:30");
        createNewsPage.addDescription("Описание новости тест");
        createNewsPage.pressSave();
        controlPanelNews.searchNewsWithTitle("Новость тест");
    }

    @Description("Создание новости с датой публикации в прошлом. тест-кейс №66")
    @Test
    public void CreateNewsInPast() {
        mainPage.openNewsPage();
        newsPage.openControlPanelNews();
        controlPanelNews.addNews();
        createNewsPage.addCategory("Объявление");
        String text = "Новость из прошлого";
        createNewsPage.addTitle(text);
        String pastDate = DataHelper.dateInPast();
        createNewsPage.addDate(pastDate);
        createNewsPage.addTime("20:25");
        createNewsPage.addDescription("тест");
        createNewsPage.pressSave();
        controlPanelNews.checkIfNoNews("Новость из прошлого");
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

    //тест падает - edit_news_item_image_view matches 4 views in  the hierarchy, нет более точного id
    @Description("Редактирование новости. тест-кейс №77")
    @Test
    public void editNews() {
        mainPage.openNewsPage();
        newsPage.openControlPanelNews();
        controlPanelNews.searchNewsWithTitle("Новость тест");
        controlPanelNews.clickEditNews();
        editNewsPage.editTitle("Отредактированная новость");
        String dateInFuture = DataHelper.dateInFuture();
        editNewsPage.editDate(dateInFuture);
        editNewsPage.editCategory("Зарплата");
        editNewsPage.editTime("23:40");
        editNewsPage.editDescription("отредактированное описание");
        editNewsPage.clickSave();
    }


    //тест падает - delete_news_item_image_view matches 4 views in  the hierarchy, нет более точного id
    @Description("Удаление новости. тест-кейс №53")
    @Test
    public void deleteNews() {
        mainPage.openNewsPage();
        newsPage.openControlPanelNews();
        controlPanelNews.searchNewsWithTitle("Новость тест");
        controlPanelNews.clickDeleteNews();
        controlPanelNews.checkIfNoNews("Новость тест");
    }
}