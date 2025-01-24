package ru.iteco.fmhandroid.ui.PageObject;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.swipeDown;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;

import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.DataHelper;

public class NewsPage {
    ControlPanelNews controlPanelNews = new ControlPanelNews();
    FilterNewsPage filterNewsPage = new FilterNewsPage();

    private final int ListNews = R.id.container_list_news_include;

    public ViewInteraction textNewsOnPage = onView(withText("News"));
    public int buttonSortingNews = R.id.sort_news_material_button;
    private final int buttonControlPanel = R.id.edit_news_material_button;
    private final int containerPageNews = R.id.container_list_news_include;
    public ViewInteraction buttonFilterNews = onView(withId(R.id.filter_news_material_button));

    public int containerControlPanel = R.id.layout_background_image_view;

    //public ViewInteraction newsWithTitle(String title) {
    //    return onView(allOf(withId(R.id.news_item_title_text_view), withText(title)));
    //}

    @Step("Видимость текста Новости")
    public void visabilityNews() {
        Allure.step("Видимость текста Новости");
        onView(withId(containerPageNews)).check(matches(ViewMatchers.isDisplayed()));
        textNewsOnPage.check(matches(withText("News")));
    }

    @Step("Видимость текста 'Control panel'")
    public void visibilityControlPanel() {
        Allure.step("Видимость текста 'Control panel'");
        onView(withId(containerControlPanel)).check(matches(ViewMatchers.isDisplayed()));
        onView(withId(R.id.layout_background_image_view))
                .check(matches(isDisplayed()));
        onView(isRoot()).perform(DataHelper.waitDisplayed(containerControlPanel, 5000));
    }

    @Step("Клик на кнопку сортировки")
    public void buttonSortingNews() {
        Allure.step("Клик на кнопку сортировки");
        onView(withId(buttonSortingNews)).check(matches(allOf(isDisplayed(), isClickable())));
        onView(withId(buttonSortingNews)).perform(ViewActions.click());
    }

    @Step("Клик на фильтр")
    public void openFilterNews() {
        Allure.step("Клик на фильтр");
        buttonFilterNews.check(matches(isDisplayed()));
        buttonFilterNews.perform(click());
    }

    @Step("Переход к Control Panel")
    public void openControlPanelNews() {
        Allure.step("Переход к Control Panel");
        onView(isRoot()).perform(DataHelper.waitDisplayed(buttonControlPanel, 5000));
        onView(withId(buttonControlPanel)).check(matches(allOf(isDisplayed(), isClickable())));
        onView(withId(buttonControlPanel)).perform(ViewActions.click());
        onView(isRoot()).perform(DataHelper.waitDisplayed(controlPanelNews.getButtonAddNews(), 5000));
    }
}
