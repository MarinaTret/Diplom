package ru.iteco.fmhandroid.ui;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.matcher.ViewMatchers.isAssignableFrom;
import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import android.os.SystemClock;
import android.view.View;
import android.widget.TextView;

import androidx.test.espresso.PerformException;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.RootMatchers;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.espresso.util.HumanReadables;
import androidx.test.espresso.util.TreeIterables;

import org.hamcrest.Matcher;
import org.hamcrest.Matchers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.concurrent.TimeoutException;

import io.qameta.allure.kotlin.Allure;
import ru.iteco.fmhandroid.dto.User;

public class DataHelper {


    /**
     * Perform action of waiting for a specific view id to be displayed.
     *
     * @param viewId The id of the view to wait for.
     * @param millis The timeout of until when to wait for.
     */
    public static ViewAction waitDisplayed(final int viewId, final long millis) {
        return new ViewAction() {

            @Override
            public Matcher<View> getConstraints() {
                return isRoot();
            }

            @Override
            public String getDescription() {
                return "wait for a specific view with id <" + viewId + "> has been displayed during " + millis + " millis.";
            }

            @Override
            public void perform(UiController uiController, View view) {
                performWaitDisplayed(uiController, view, viewId, millis, getDescription());
            }
        };
    }


    public static boolean isViewVisible(View view) {
        return view.isShown();
    }

    private static void performWaitDisplayed(UiController uiController, View view, int viewId, long millis, String description) {
        uiController.loopMainThreadUntilIdle();
        long startTime = System.currentTimeMillis();
        long endTime = startTime + millis;

        do {
            for (View child : TreeIterables.breadthFirstViewTraversal(view)) {
                if (child.getId() == viewId && isViewVisible(child)) {
                    return;
                }
            }

            uiController.loopMainThreadForAtLeast(50);
        }
        while (System.currentTimeMillis() < endTime);

        throw new PerformException.Builder()
                .withActionDescription(description)
                .withViewDescription(HumanReadables.describe(view))
                .withCause(new TimeoutException())
                .build();
    }

    public static class User {
        private final String login;
        private final String password;

        public User(String login, String password) {
            this.login = login;
            this.password = password;
        }

        public String getLogin() {
            return login;
        }

        public String getPassword() {
            return password;
        }
    }

    public User getValidUser() {
        return new User("login2", "password2");
    }

    public User getNotValidUser() {
        return new User("qwerty", "qwerty");
    }

    public User getSpacesInsteadOfUser() {
        return new User("  ", "  ");
    }

    public User getSpacesBeforeLoginAndPasswordUser() {
        return new User(" login2", " password2");
    }

    public void waitForErrorMassage(String text) {

        Allure.step("Проверить текст ошибки");

        final int timeout = 5000;
        final int interval = 100;

        long startTime = System.currentTimeMillis();
        boolean isElementFound = false;

        while (!isElementFound && (System.currentTimeMillis() - startTime < timeout)) {
            try {
                onView(ViewMatchers.withText(text))
                        .inRoot(RootMatchers.withDecorView(Matchers.instanceOf(View.class)))
                        .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));
                isElementFound = true;
            } catch (Exception e) {

            }
            SystemClock.sleep(interval);
        }

        if (!isElementFound) {
            throw new RuntimeException("Element not found within the allotted time");
        }
    }

    public static final String errorMasssageSomethingWentWrong = "Something went wrong. Try again later.";
    public static final String errorMasssageLoginAndPasswordCannotBeEmpty = "Login and password cannot be empty";
    public static final String errorMasssageFillEmptyFields = "Fill empty fields";
}













