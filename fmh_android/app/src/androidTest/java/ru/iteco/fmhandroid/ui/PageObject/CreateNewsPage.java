package ru.iteco.fmhandroid.ui.PageObject;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import static org.hamcrest.Matchers.not;

import static ru.iteco.fmhandroid.ui.DataHelper.waitDisplayed;

import android.view.View;

import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;

public class CreateNewsPage {

    public ViewInteraction category = onView(withId(R.id.news_item_category_text_auto_complete_text_view));
    public ViewInteraction title = onView(withId(R.id.news_item_title_text_input_edit_text));
    public ViewInteraction time = onView(withId(R.id.news_item_publish_time_text_input_edit_text));
    public ViewInteraction date = onView(withId(R.id.news_item_publish_date_text_input_edit_text));
    public ViewInteraction descriptionText = onView(withId(R.id.news_item_description_text_input_edit_text));
    public ViewInteraction save = onView(withId(R.id.save_button));
    public int buttonSave = R.id.save_button;
    public ViewInteraction cancelButton = onView(withId(R.id.cancel_button));
    public ViewInteraction okButtonMessage = onView(withText("OK"));

   @Step("Ввод данных в поле категории")
    public void addCategory(String text) {
        Allure.step("Ввод данных в поле категории");
        category.check(matches(isDisplayed()));
        category.perform(replaceText(text), closeSoftKeyboard());
    }

    @Step("Ввод данных в поле заголовка")
    public void addTitle(String text) {
        Allure.step("Ввод данных в поле заголовка");
        title.check(matches(isDisplayed()));
        title.perform(replaceText(text), closeSoftKeyboard());
    }

    @Step("Ввод данных в поле даты публикации")
    public void addDate(String text) {
        Allure.step("Ввод данных в поле даты публикации");
        date.check(matches(isDisplayed()));
        date.perform(replaceText(text), closeSoftKeyboard());
    }

    @Step("Ввод данных в поле Время")
    public void addTime(String text) {
        Allure.step("Ввод данных в поле Время");
        time.check(matches(isDisplayed()));
        time.perform(replaceText(text), closeSoftKeyboard());

    }

    @Step("Ввод данных в поле описания")
    public void addDescription(String text) {
        Allure.step("Ввод данных в поле описания");
        descriptionText.check(matches(isDisplayed()));
        descriptionText.perform(replaceText(text), closeSoftKeyboard());
    }

    @Step("Клик на кнопку Сохранить")
    public void pressSave() {
        Allure.step("Клик на кнопку Сохранить");
        onView(isRoot()).perform(waitDisplayed(buttonSave, 20000));
        save.check(matches(isDisplayed()));
        save.perform(scrollTo()).perform(click());
        //save.perform(click());
    }

    @Step("Проверка сообщения")
    public void checkToastMessageText(String text, View decorView) {
        Allure.step("Проверка сообщения");
        onView(withText(text))
                .inRoot(withDecorView(not(decorView)))
                .check(matches(isDisplayed()));
    }
}
