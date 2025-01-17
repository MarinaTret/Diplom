package ru.iteco.fmhandroid.ui.PageObject;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.action.ViewActions.pressBack;
import static androidx.test.espresso.action.ViewActions.pressImeActionButton;
import static androidx.test.espresso.action.ViewActions.replaceText;
import static androidx.test.espresso.action.ViewActions.typeText;
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

import androidx.test.espresso.DataInteraction;
import androidx.test.espresso.ViewInteraction;

import java.security.cert.CertPathChecker;

import io.qameta.allure.kotlin.Allure;
import io.qameta.allure.kotlin.Step;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.DataHelper;


public class AuthorizationPage {
    MainPage mainPage = new MainPage();
    public ViewInteraction inputLogin = onView(withHint("Login"));
    public ViewInteraction inputPassword = onView(withHint("Password"));
    public ViewInteraction buttonSingIn = onView(withId(R.id.enter_button));


    @Step("Отображение заголовка Authorization")
    public void title() {
        Allure.step("Отображение заголовка Authorization");
        ViewInteraction ViewAuth = onView(withText("Authorization"));
        ViewAuth.check(matches(isDisplayed()));
        ViewAuth.check(matches(withText(endsWith("Authorization"))));
    }

    /**
     * @Step("Ввод логина")
     * public void inputLogin(String login) {
     * Allure.step("Ввод логина");
     * ViewInteraction inputLogin = onView(withHint("Login"));
     * inputLogin.perform(replaceText(login));
     * closeSoftKeyboard();
     * }
     * @Step("Ввод пароля")
     * public void inputPassword(String password) {
     * Allure.step("Ввод пароля");
     * ViewInteraction inputPassword = onView(withHint("Password"));
     * inputPassword.perform(replaceText(password), closeSoftKeyboard());
     * pressImeActionButton();
     * pressBack();
     * }
     */

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
        DataHelper helper = new DataHelper();
        inputLogin.perform(typeText(helper.getValidUser().getLogin()), closeSoftKeyboard());
        inputPassword.perform(typeText(helper.getValidUser().getPassword()), closeSoftKeyboard());
        clickButton();
        onView(isRoot()).perform(DataHelper.waitDisplayed(mainPage.getClickProfile(), 50000));
        mainPage.visabilityElements();
    }

    @Step("Авторизация с невалидными данными")
    public void authorizationWithInvalidData() {
        Allure.step("Авторизация с невалидными данными");
        DataHelper helper = new DataHelper();
        inputLogin.perform(typeText(helper.getNotValidUser().getLogin()), closeSoftKeyboard());
        inputPassword.perform(typeText(helper.getNotValidUser().getPassword()), closeSoftKeyboard());
        clickButton();
    }

    @Step("Авторизация с пустыми полями")
    public void emptyData() {
        Allure.step("Авторизация с пустыми полями");
        clickButton();
    }

    @Step("Авторизация с пробелами вместо логина и пароля")
    public void authorizationWithSpaceInFields() {
        Allure.step("Авторизация с пробелами вместо логина и пароля");
        DataHelper helper = new DataHelper();
        inputLogin.perform(typeText(helper.getSpacesInsteadOfUser().getLogin()), closeSoftKeyboard());
        inputPassword.perform(typeText(helper.getSpacesInsteadOfUser().getPassword()), closeSoftKeyboard());
        clickButton();
    }

    @Step("Авторизация с пробелами перед логином и паролем")
    public void authorizationWithSpaceBeforeData() {
        Allure.step("Авторизация с пробелами перед логином и паролем");
        DataHelper helper = new DataHelper();
        inputLogin.perform(typeText(helper.getSpacesBeforeLoginAndPasswordUser().getLogin()), closeSoftKeyboard());
        inputPassword.perform(typeText(helper.getSpacesBeforeLoginAndPasswordUser().getPassword()), closeSoftKeyboard());
        clickButton();
    }

    @Step("Проверка сообщения об ошибке")
    public void checkToastMessageText(String text, View decorView) {
        Allure.step("Проверка сообщения об ошибке");
        onView(withText(text))
                .inRoot(withDecorView(not(decorView)))
                .check(matches(isDisplayed()));
    }
}