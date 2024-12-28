package ru.iteco.fmhandroid.ui.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

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
import ru.iteco.fmhandroid.ui.PageObject.AuthorizationPage;
import ru.iteco.fmhandroid.ui.PageObject.MainPage;
import ru.iteco.fmhandroid.ui.DataHelper;
import ru.iteco.fmhandroid.ui.PageObject.NewsPage;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class MainPageTest {
    AuthorizationPage authorizationPage = new AuthorizationPage();
    MainPage mainPage = new MainPage();
    NewsPage newsPage = new NewsPage();

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

    @Description("Открытие навигационного меню. тест-кейс №23")
    @Test
    public void openNavigationMenu() {
        mainPage.buttonMainMenu.perform(click());
        onView(withText("Main")).check(matches(isDisplayed()));
        onView(withText("News")).check(matches(isDisplayed()));
        onView(withText("About")).check(matches(isDisplayed()));
    }

    @Description("Переход к новостям через меню. тест-кейс №24")
    @Test
    public void openPageNews() {
        mainPage.openNewsPage();
        newsPage.visabilityNews();
    }

    @Description("Переход к странице About. тест-кейс №25")
    @Test
    public void openAboutPage() {
        mainPage.openAboutPage();
    }


    @Description("Переход к странице LoveIsAll. тест-кейс №26")
    @Test
    public void openPageLoveIsAll() {
        mainPage.openOurMissionPage();
    }

    @Description("Отображение всех новостей на главной. тест-кейс №28")
    @Test
    public void OpenAllNews() {
        mainPage.clickButtonAllNews();
    }

    @Description("Выход из приложения. тест-кейс №30")
    @Test
    public void logOut() {
        mainPage.logOut();
    }
}

