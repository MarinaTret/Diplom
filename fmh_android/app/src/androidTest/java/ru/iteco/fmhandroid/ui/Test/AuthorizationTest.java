package ru.iteco.fmhandroid.ui.Test;

import static androidx.test.espresso.matcher.ViewMatchers.isRoot;

import android.view.View;

import androidx.test.espresso.Espresso;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import io.qameta.allure.android.runners.AllureAndroidJUnit4;
import io.qameta.allure.kotlin.Description;
import ru.iteco.fmhandroid.R;
import ru.iteco.fmhandroid.ui.AppActivity;
import ru.iteco.fmhandroid.ui.PageObject.AuthorizationPage;
import ru.iteco.fmhandroid.ui.DataHelper;
import ru.iteco.fmhandroid.ui.PageObject.MainPage;
//import ru.iteco.fmhandroid.ui.PageObject.MessageError;

@LargeTest
@RunWith(AllureAndroidJUnit4.class)
public class AuthorizationTest {

    AuthorizationPage authorizationPage = new AuthorizationPage();
    MainPage mainPage = new MainPage();
    //MessageError messageError = new MessageError();
    private View decorView;

    @Rule
    public ActivityScenarioRule<AppActivity> mActivityScenarioRule =
            new ActivityScenarioRule<>(AppActivity.class);

    @Before
    public void setUp() {
        Espresso.onView(isRoot()).perform(DataHelper.waitDisplayed(mainPage.getAppBarFragmentMain(), 30000));
        if (mainPage.isDisplayedButtonProfile()) {
            mainPage.logOut();
        }
    }

    @Description("Успешная авторизация. Тест-кейс №8")
    @Test
    public void successfulAuthorization() {
        authorizationPage.title();
        authorizationPage.successfulAuthorization();
        mainPage.isDisplayedButtonProfile();
    }

    @Description("Авторизация с пустыми полями. Тест-кейс №9")
    @Test
    public void authorizationWithEmptyData() {
        authorizationPage.title();
        authorizationPage.emptyData();
        authorizationPage.checkToastMessageText("Login and password cannot be empty", decorView);
    }

    @Description("Авторизация с пробелами вместо логина и пароля. Тест-кейс №10")
    @Test
    public void authorizationWithSpaceInFields() {
        authorizationPage.title();
        authorizationPage.authorizationWithSpaceInFields();
        authorizationPage.checkToastMessageText("Login and password cannot be empty", decorView);
    }

    @Description("Авторизация с пробелами перед логином и паролем. Тест-кейс №11")
    @Test
    //должен упасть, это баг, не должна проходить авторизация с пробелами перед логином и паролем
    public void authorizationWithSpacesBeforeLoginAndPassword() {
        authorizationPage.title();
        authorizationPage.authorizationWithSpaceBeforeData();
        authorizationPage.checkToastMessageText("Something went wrong. Try again later.", decorView);
    }

    @Description("Авторизация с невалидными данными. Тест-кейс №12")
    @Test
    public void authorizationWithInvalidData() {
        authorizationPage.title();
        authorizationPage.authorizationWithInvalidData();
        authorizationPage.checkToastMessageText("Something went wrong. Try again later.", decorView);
    }
}


