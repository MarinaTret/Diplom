package ru.iteco.fmhandroid.ui.PageObject;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.action.ViewActions.swipeDown;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.matcher.ViewMatchers.hasDescendant;
import static androidx.test.espresso.matcher.ViewMatchers.isClickable;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
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
import ru.iteco.fmhandroid.ui.DataHelper;

public class ControlPanelNews {

    CreateNewsPage createNewsPage = new CreateNewsPage();
    EditNewsPage editNewsPage = new EditNewsPage();
    FilterNewsPage filterNewsPage = new FilterNewsPage();

    public int buttonAddNews = R.id.add_news_image_view;
    public ViewInteraction buttonOk = onView(withId(android.R.id.button1));
    private final ViewInteraction recyclerView = onView(withId(R.id.news_list_recycler_view));

    public ViewInteraction newsWithTitle(String title) {
        return onView(allOf(
                withId(R.id.news_item_title_text_view), withText(title),
                withParent(withParent(withId(R.id.news_item_material_card_view))), isDisplayed()));
    }

    public ViewInteraction buttonEditNews(String title) {
        return onView(allOf(withId(R.id.edit_news_item_image_view),
                withParent(withParent(allOf(withId(R.id.news_item_material_card_view),
                        withChild(withChild(withText(title))))))));
    }

    public ViewInteraction buttonDeleteNews(String title) {
        return onView(allOf(withId(R.id.delete_news_item_image_view),
                withParent(withParent(allOf(withId(R.id.news_item_material_card_view),
                        withChild(withChild(withText(title))))))));
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
    public void clickEditNews(String fakeTitle) {
        Allure.step("Клик на кнопку 'Редактировать новость'");
        buttonEditNews(fakeTitle).check(matches(allOf(isDisplayed(), isClickable())));
        buttonEditNews(fakeTitle).perform(click());
    }

    @Step("Удаление новости")
    public void clickDeleteNews(String title) {
        Allure.step("Удалить новость с указанным заголовком");
        //newsWithTitle(title).check(matches(isDisplayed()));
        buttonDeleteNews(title).perform(click());
        buttonOk.check(matches(isDisplayed()));

    }

    @Step("Проверка наличия новости с указанным заголовком")
    public void searchNewsWithTitle(String title) {
        Allure.step("Проверка наличия новости с указанным заголовком");
        ViewInteraction newsWithTitle = onView(allOf(withText(title), withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }

    @Step("Проверка, что новости с указанным заголовком нет")
    public void checkIfNoNews(String text) {
        Allure.step("Проверка, что новости с указанным заголовком нет");
        onView(withText(text)).check(doesNotExist());
    }

    public void scrollNewsPage(String title) {
        Allure.step("Скролить до нужного заголовка");
        recyclerView.perform(RecyclerViewActions.scrollTo(hasDescendant(withText(title))))
                .check(matches(isDisplayed()));
    }
}

