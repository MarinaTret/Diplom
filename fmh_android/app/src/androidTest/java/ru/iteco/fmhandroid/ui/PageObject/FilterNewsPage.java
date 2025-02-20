package ru.iteco.fmhandroid.ui.PageObject;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static ru.iteco.fmhandroid.ui.DataHelper.waitDisplayed;

import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.DataHelper;


public class FilterNewsPage {
    public ViewInteraction categoryText = onView(withId(R.id.news_item_category_text_auto_complete_text_view));
    private final ViewInteraction buttonFilter = onView(withId(R.id.filter_button));

    private final int filter = R.id.filter_button;

    @Step("Выбор категории")
    public void addCategory(String text) {
        Allure.step("Выбор категории" + text);
        categoryText.check(matches(isDisplayed()));
        categoryText.perform(replaceText(text), closeSoftKeyboard());
    }

    @Step("Клик на кнопку фильтра")
    public void confirmFilter() {
        Allure.step("Клик на кнопку фильтра");
        onView(isRoot()).perform(waitDisplayed(filter, 20000));
        buttonFilter.check(matches(isDisplayed()));
        buttonFilter.perform(click());
    }
}


