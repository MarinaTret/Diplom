package ru.iteco.fmhandroid.ui.PageObject;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;

import androidx.test.espresso.NoMatchingViewException;
import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.DataHelper;

public class MainPage {
    NewsPage newsPage = new NewsPage();

    public int containerListNews = R.id.container_list_news_include_on_fragment_main;
    public ViewInteraction mainMenu = onView(allOf(withId(android.R.id.title), withText("Main")));
    public ViewInteraction aboutOfMenu = onView(allOf(withId(android.R.id.title), withText("About")));
    public ViewInteraction logOutButton = onView(withText("Log out"));
    public ViewInteraction buttonMainMenu = onView(withId(R.id.main_menu_image_button));
    public ViewInteraction newsMenu = onView(allOf(withId(android.R.id.title), withText("News")));
    public ViewInteraction allNews = onView(withText("All news"));
    public ViewInteraction buttonOurMission = onView(withId(R.id.our_mission_image_button));
    public ViewInteraction buttonAllNews = onView(withId(R.id.all_news_text_view));
    public ViewInteraction profileButton = onView(withId(R.id.authorization_image_button));
    int appBarFragmentMain = R.id.container_custom_app_bar_include_on_fragment_main;
    public int clickProfile = R.id.authorization_image_button;

    public int getContainerListNews() {
        return containerListNews;
    }

    public int getAppBarFragmentMain() {
        return appBarFragmentMain;
    }

    public int getClickProfile() {
        return clickProfile;
    }

    @Step("Проверка видимости элементов")
    public void visabilityElements() {
        Allure.step("Проверка видимости элементов");
        buttonMainMenu.check(matches(isDisplayed()));
        buttonAllNews.check(matches(isDisplayed()));
        profileButton.check(matches(isDisplayed()));
    }

    @Step("Клик на кнопку 'Все новости'")
    public void clickButtonAllNews() {
        Allure.step("Клик на кнопку 'Все новости'");
        allNews.perform(click());
    }

    @Step("Переход на страницу 'О приложении'")
    public void openAboutPage() {
        Allure.step("Переход на страницу 'О приложении'");
        buttonMainMenu.check(matches(isDisplayed()));
        buttonMainMenu.perform(click());
        aboutOfMenu.check(matches(isDisplayed()));
        aboutOfMenu.perform(click());
    }

    @Step("Переход на страницу с цитатами")
    public void openOurMissionPage() {
        Allure.step("Переход на страницу с цитатами");
        buttonOurMission.check(matches(isDisplayed()));
        buttonOurMission.perform(click());
    }

    @Step("Переход на страницу 'Новости'")
    public void openNewsPage() {
        Allure.step("Переход на страницу 'Новости'");
        buttonMainMenu.check(matches(isDisplayed()));
        buttonMainMenu.perform(click());
        newsMenu.check(matches(isDisplayed()));
        newsMenu.perform(click());
    }

    @Step("Выход из приложения")
    public void logOut() {
        Allure.step("Выход из приложения");
        profileButton.perform(click());
        logOutButton.perform(click());
    }

    @Step("Видимость главной страницы")
    public Boolean isDisplayedButtonProfile() {
        Allure.step("Видимость главной страницы");
        try {
            onView(withId(containerListNews)).check(matches(isDisplayed()));
            return true;
        } catch (NoMatchingViewException noMatchingViewException) {
            return false;
        }
    }
}

