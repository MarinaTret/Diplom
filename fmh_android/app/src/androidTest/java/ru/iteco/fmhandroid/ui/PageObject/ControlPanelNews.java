package ru.iteco.fmhandroid.ui.PageObject;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.swipeDown;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.withChild;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withParent;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.endsWith;


import static org.hamcrest.Matchers.allOf;
import static androidx.test.espresso.assertion.ViewAssertions.matches;

import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;


import static kotlinx.coroutines.flow.FlowKt.withIndex;

import android.view.View;

import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.ViewMatchers;

import org.hamcrest.Matcher;

import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;

public class ControlPanelNews {

    CreateNewsPage createNewsPage = new CreateNewsPage();
    EditNewsPage editNewsPage = new EditNewsPage();
    FilterNewsPage filterNewsPage = new FilterNewsPage();

    public int buttonAddNews = R.id.add_news_image_view;
    public ViewInteraction buttonOk = onView(withId(android.R.id.button1));


    public ViewInteraction newsWithTitle(String titleText) {
        return onView(allOf(
                withId(R.id.news_item_title_text_view),
                withText(titleText)));

        //return onView(allOf(
        //        withId(R.id.news_item_title_text_view), withText(titleText),
        //        withParent(withParent(withId(R.id.news_item_material_card_view))),isDisplayed()));
    }

    public ViewInteraction buttonEditNews(String newsTitle) {
        return onView(allOf(withId(R.id.edit_news_item_image_view),
                withParent(withParent(allOf(withId(R.id.news_item_material_card_view),
                        withChild(withChild(withText(newsTitle))))))));
    }

    public ViewInteraction buttonDeleteNews(String newsTitle) {
        return onView(allOf(withId(R.id.delete_news_item_image_view),
                withParent(withParent(allOf(withId(R.id.news_item_material_card_view),
                        withChild(withChild(withText(newsTitle))))))));
    }

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
    public void clickEditNews(String titleText) {
        Allure.step("Клик на кнопку 'Редактировать новость'");
        //buttonEditNews(newsTitle).check(matches(allOf(isDisplayed(), isClickable())));
        buttonEditNews(titleText).perform(click());
    }

    @Step("Удаление новости")
    public void clickDeleteNews(String titleText) {
        Allure.step("Удалить новость с указанным заголовком");
        buttonDeleteNews(titleText).perform(click());
        createNewsPage.clickOKButton();
    }

    @Step("Проверка наличия новости с указанным заголовком")
    public void searchNewsWithTitle(String titleText) {
        Allure.step("Проверка наличия новости с указанным заголовком");
        //ViewInteraction textTitle = onView(allOf(withText(titleText), withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        ViewInteraction newsWithTitle = onView(allOf(withText(titleText), withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        //ViewInteraction textTitle = onView(allOf(withId(R.id.news_item_title_text_view), withText(titleText)));
    }

    @Step("Проверка, что новости с указанным заголовком нет")
    public void checkIfNoNews(String text) {
        Allure.step("Проверка, что новости с указанным заголовком нет");
        onView(withText(text)).check(doesNotExist());
    }
}

