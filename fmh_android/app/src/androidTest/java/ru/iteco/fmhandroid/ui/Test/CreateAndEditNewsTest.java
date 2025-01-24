package ru.iteco.fmhandroid.ui.Test;


import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;

import static ru.iteco.fmhandroid.ui.DataGeneration.Rand.randomCategory;
import static ru.iteco.fmhandroid.ui.DataGeneration.dateInFuture;
import static ru.iteco.fmhandroid.ui.DataGeneration.dateInPast;
import static ru.iteco.fmhandroid.ui.DataGeneration.getCurrentDate;
import static ru.iteco.fmhandroid.ui.DataGeneration.getCurrentTime;
import static ru.iteco.fmhandroid.ui.DataHelper.errorMasssageFillEmptyFields;

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
import ru.iteco.fmhandroid.ui.DataGeneration;
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
    DataGeneration dataGeneration = new DataGeneration();
    DataHelper dataHelper = new DataHelper();
    private View decorView;

    String fakeTitle;
    String fakeDescription;
    String editTitle;
    String editDescription;

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void setUp() {

        fakeTitle = dataGeneration.generateFakeTitle();
        fakeDescription = dataGeneration.generateFakeDescription();
        editTitle = dataGeneration.generateFakeEditTitle();
        editDescription = dataGeneration.generateFakeEditDescription();

        Espresso.onView(isRoot()).perform(DataHelper.waitDisplayed(mainPage.getAppBarFragmentMain(), 100000));
        if (!mainPage.isDisplayedButtonProfile()) {
            authorizationPage.successfulAuthorization();
        }

        mainPage.openNewsPage();
        newsPage.openControlPanelNews();
        controlPanelNews.addNews();
    }


    @Description("Успешное создание новости. тест-кейс №57")
    @Test
    public void successfulCreateNews() {
        createNewsPage.addCategory(randomCategory());
        createNewsPage.addTitle(fakeTitle);
        createNewsPage.addDate(getCurrentDate());
        createNewsPage.addTime(getCurrentTime());
        createNewsPage.addDescription(fakeDescription);
        createNewsPage.pressSave();
        controlPanelNews.scrollNewsPage(fakeTitle);
        controlPanelNews.searchNewsWithTitle(fakeTitle);
    }

    @Description("Создание новости с датой публикации в прошлом. тест-кейс №66")
    @Test
    public void CreateNewsInPast() {
        String publicationDate = dateInPast();
        createNewsPage.addCategory(randomCategory());
        createNewsPage.addTitle(fakeTitle);
        createNewsPage.addDate(dateInPast());
        createNewsPage.addTime(getCurrentTime());
        createNewsPage.addDescription(fakeDescription);
        createNewsPage.pressSave();
        controlPanelNews.scrollNewsPage(fakeTitle);
        controlPanelNews.searchNewsWithTitle(fakeTitle);
    }

    @Description("Создание новости с пустыми данными. тест-кейс №73")
    @Test
    public void createEmptyNews() {

        createNewsPage.pressSave();
        dataHelper.waitForErrorMassage(errorMasssageFillEmptyFields);
        //createNewsPage.checkToastMessageText("Fill empty fields", decorView);
    }

    @Description("Редактирование новости. тест-кейс №77")
    @Test
    public void editNews() {
        createNewsPage.addCategory(randomCategory());
        createNewsPage.addTitle(fakeTitle);
        createNewsPage.addDate(getCurrentDate());
        createNewsPage.addTime(getCurrentTime());
        createNewsPage.addDescription(fakeDescription);
        createNewsPage.pressSave();
        controlPanelNews.scrollNewsPage(fakeTitle);

        controlPanelNews.clickEditNews(fakeTitle);

        editNewsPage.editTitle(editTitle);
        editNewsPage.editDate(dateInFuture());
        editNewsPage.editTime(getCurrentTime());
        editNewsPage.editDescription(editDescription);

        editNewsPage.clickSave();
        controlPanelNews.scrollNewsPage(editTitle);

        controlPanelNews.searchNewsWithTitle(editTitle);
    }

    @Description("Удаление новости. тест-кейс №53")
    @Test
    public void deleteNews() {
        createNewsPage.addCategory(randomCategory());
        createNewsPage.addTitle(fakeTitle);
        createNewsPage.addDate(getCurrentDate());
        createNewsPage.addTime(getCurrentTime());
        createNewsPage.addDescription(fakeDescription);
        createNewsPage.pressSave();
        controlPanelNews.scrollNewsPage(fakeTitle);
        controlPanelNews.clickDeleteNews(fakeTitle);
        controlPanelNews.checkIfNoNews(fakeTitle);
    }
}