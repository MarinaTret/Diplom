package ru.iteco.fmhandroid.ui.PageObject;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.endsWith;
import static androidx.test.espresso.assertion.ViewAssertions.matches;

import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;


import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.matcher.ViewMatchers;

import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.DataHelper;

public class ControlPanelNews {

    CreateNewsPage createNews = new CreateNewsPage();
    EditNewsPage editNewsPage = new EditNewsPage();
    FilterNewsPage filterNews = new FilterNewsPage();

    public int buttonAddNews = R.id.add_news_image_view;
    public int buttonEditNews = R.id.edit_news_item_image_view;
    public int buttonDeleteNews = R.id.delete_news_item_image_view;
    public ViewInteraction buttonOk = onView(withId(android.R.id.button1));

    public int getButtonAddNews() {
        return buttonAddNews;
    }

    @Step("Клик на кнопку создания новости")
    public void addNews() {
        Allure.step("Клик на кнопку создания новости");
        onView(withId(buttonAddNews)).check(matches(allOf(isDisplayed(), isClickable())));
        onView(withId(buttonAddNews)).perform(click());
    }

    @Step("Клик на кнопку 'Редактировать новость'")
    public void clickEditNews() {
        Allure.step("Клик на кнопку 'Редактировать новость'");
        onView(withId(buttonEditNews)).check(matches(allOf(isDisplayed(), isClickable())));
        onView(withId(buttonEditNews)).perform(click());
    }

    @Step("Удаление новости")
    public void clickDeleteNews() {
        Allure.step("Удаление новости");
        onView(withId(buttonDeleteNews)).check(matches(allOf(isDisplayed(), isClickable())));
        onView(withId(buttonDeleteNews)).perform(scrollTo()).perform(click());
        buttonOk.check(matches(isDisplayed()));
        buttonOk.perform(click());
    }

    @Step("Проверка наличия новости с указанным заголовком")
    public void searchNewsWithTitle(String text) {
        Allure.step("Проверка наличия новости с указанным заголовком");
        ViewInteraction title = onView(allOf(withText(text), withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }

    @Step("Проверка, что новости с указанным заголовком нет")
    public void checkIfNoNews(String text) {
        Allure.step("Проверка, что новости с указанным заголовком нет");
        onView(withText(text)).check(doesNotExist());
    }
}
