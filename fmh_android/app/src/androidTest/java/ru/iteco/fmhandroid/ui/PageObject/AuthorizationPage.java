package ru.iteco.fmhandroid.ui.PageObject;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressBack;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.RootMatchers.withDecorView;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withHint;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.endsWith;
import static org.hamcrest.Matchers.not;

import android.view.View;

import androidx.test.espresso.ViewInteraction;

import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.DataHelper;


public class AuthorizationPage {
    MainPage mainPage = new MainPage();

    @Step("Отображение заголовка Authorization")
    public void title() {
        Allure.step("Отображение заголовка Authorization");
        ViewInteraction ViewAuth = onView(withText("Authorization"));
        ViewAuth.check(matches(isDisplayed()));
        ViewAuth.check(matches(withText(endsWith("Authorization"))));
    }

    @Step("Ввод логина")
    public void inputLogin(String login) {
        Allure.step("Ввод логина");
        ViewInteraction inputLogin = onView(withHint("Login"));
        inputLogin.perform(replaceText(login));
        closeSoftKeyboard();
    }

    @Step("Ввод пароля")
    public void inputPassword(String password) {
        Allure.step("Ввод пароля");
        ViewInteraction inputPassword = onView(withHint("Password"));
        inputPassword.perform(replaceText(password), closeSoftKeyboard());
        pressImeActionButton();
        pressBack();
    }

    @Step("Клик на кнопку")
    public void clickButton() {
        Allure.step("Клик на кнопку");
        ViewInteraction buttonSingIn = onView(withId(R.id.enter_button));
        buttonSingIn.check(matches(isDisplayed()));
        buttonSingIn.perform(click());
    }

    @Step("Успешная авторизация")
    public void successfulAuthorization() {
        Allure.step("Успешная авторизация");
        inputLogin("login2");
        inputPassword("password2");
        clickButton();
        onView(isRoot()).perform(DataHelper.waitDisplayed(mainPage.getClickProfile(), 50000));
        mainPage.visabilityElements();
    }

    @Step("Проверка сообщения об ошибке")
    public void checkToastMessageText(String text, View decorView) {
        Allure.step("Проверка сообщения об ошибке");
        onView(withText(text))
                .inRoot(withDecorView(not(decorView)))
                .check(matches(isDisplayed()));
    }
}