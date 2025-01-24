package ru.iteco.fmhandroid.ui.PageObject;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.assertion.ViewAssertions.matches;

import static ru.iteco.fmhandroid.ui.DataHelper.waitDisplayed;

import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;
import io.qameta.allure.kotlin.Allure;

public class EditNewsPage {

    public ViewInteraction editCategory = onView(withId(R.id.news_item_category_text_auto_complete_text_view));
    public ViewInteraction editTitle = onView(withId(R.id.news_item_title_text_input_edit_text));
    public ViewInteraction editTime = onView(withId(R.id.news_item_publish_time_text_input_edit_text));
    public ViewInteraction editDate = onView(withId(R.id.news_item_publish_date_text_input_edit_text));
    public ViewInteraction editDescription = onView(withId(R.id.news_item_description_text_input_edit_text));
    public ViewInteraction save = onView(withId(R.id.save_button));
    private final int buttonSave = R.id.save_button;

    @Step("Редактирование категории")
    public void editCategory(String text) {
        Allure.step("Редактирование категории");
        editCategory.check(matches(isDisplayed()));
        editCategory.perform(replaceText(text), closeSoftKeyboard());
    }

    @Step("Редактирование заголовка")
    public void editTitle(String text) {
        Allure.step("Редактирование заголовка");
        editTitle.check(matches(isDisplayed()));
        editTitle.perform(replaceText(text), closeSoftKeyboard());
    }

    @Step("Редактирование даты")
    public void editDate(String text) {
        Allure.step("Редактирование даты");
        editDate.check(matches(isDisplayed()));
        editDate.perform(replaceText(text), closeSoftKeyboard());
    }

    @Step("Редактирование времени")
    public void editTime(String text) {
        Allure.step("Редактирование времени");
        editTime.check(matches(isDisplayed()));
        editTime.perform(replaceText(text), closeSoftKeyboard());
    }

    @Step("Редактирование описания")
    public void editDescription(String text) {
        Allure.step("Редактирование описания");
        editDescription.check(matches(isDisplayed()));
        editDescription.perform(replaceText(text), closeSoftKeyboard());
    }

    //public void editNewsFields(String category, String title, String publicationDate,
    //                           String publicationTime, String description) {
    //    Allure.step("Редактирование всей новости");
    //    editCategory(category);
    //    editTitle(title);
    //    editDescription(description);
    //    editDate(publicationDate);
    //    editTime(publicationTime);
    //}

    @Step("Клик на кнопку 'ОК'")
    public void clickSave() {
        Allure.step("Клик на кнопку 'ОК'");
        onView(isRoot()).perform(waitDisplayed(buttonSave, 5000));
        save.check(matches(isDisplayed()));
        save.perform(scrollTo()).perform(click());
    }
}

