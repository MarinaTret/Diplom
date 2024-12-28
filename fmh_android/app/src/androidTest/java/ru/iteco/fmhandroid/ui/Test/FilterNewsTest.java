package ru.iteco.fmhandroid.ui.Test;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;

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
import ru.iteco.fmhandroid.ui.PageObject.FilterNewsPage;
import ru.iteco.fmhandroid.ui.PageObject.MainPage;
import ru.iteco.fmhandroid.ui.PageObject.NewsPage;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class FilterNewsTest {
    FilterNewsPage filterNewsPage = new FilterNewsPage();
    NewsPage newsPage = new NewsPage();
    MainPage mainPage = new MainPage();
    AuthorizationPage authorizationPage = new AuthorizationPage();

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void setUp() {
        onView(isRoot()).perform(DataHelper.waitDisplayed(mainPage.getAppBarFragmentMain(), 100000));
        if (!mainPage.isDisplayedButtonProfile()) {
            authorizationPage.successfulAuthorization();
        }
    }

    @Description("Фильтрация новостей с пустой формой. тест-кейс №39")
    @Test
    public void filterNewsEmptyForm() {
        mainPage.openNewsPage();
        newsPage.openFilterNews();
        //filterNewsPage.addCategory("");
        //filterNewsPage.setDateStart("");
        //filterNewsPage.setDateEnd("");
        filterNewsPage.confirmFilter();
        newsPage.visabilityNews();
    }

    @Description("Фильтрация новостей отдельно по категории. тест-кейс №47")
    @Test
    public void filterNewsOnlyCategory() {
        mainPage.openNewsPage();
        newsPage.openFilterNews();
        filterNewsPage.addCategory("Объявление");
        filterNewsPage.confirmFilter();
        newsPage.visabilityNews();
    }
}

