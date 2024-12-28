package ru.iteco.fmhandroid.ui.Test;

import static androidx.test.espresso.matcher.ViewMatchers.isRoot;

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
import ru.iteco.fmhandroid.ui.PageObject.MainPage;
import ru.iteco.fmhandroid.ui.PageObject.NewsPage;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class ControlPanelTest {
    AuthorizationPage authorizationPage = new AuthorizationPage();
    NewsPage newsPage = new NewsPage();
    MainPage mainPage = new MainPage();
    ControlPanelNews controlPanelNews = new ControlPanelNews();

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void setUp() {
        Espresso.onView(isRoot()).perform(DataHelper.waitDisplayed(mainPage.getAppBarFragmentMain(), 50000));
        if (!mainPage.isDisplayedButtonProfile()) {
            authorizationPage.successfulAuthorization();
        }
    }

    @Description("Сортировка новостей. тест-кейс №49")
    @Test
    public void sortingNews() {
        mainPage.openNewsPage();
        newsPage.openControlPanelNews();
        newsPage.buttonSortingNews();
    }

    @Description("Открытие формы создание новости. тест-кейс №52")
    @Test
    public void CreateNews() {
        mainPage.openNewsPage();
        newsPage.openControlPanelNews();
        controlPanelNews.addNews();
    }
}
