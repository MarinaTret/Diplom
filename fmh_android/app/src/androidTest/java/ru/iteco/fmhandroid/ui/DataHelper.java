package ru.iteco.fmhandroid.ui;

import static androidx.test.espresso.matcher.ViewMatchers.isRoot;
import static androidx.test.espresso.matcher.ViewMatchers.withId;

import android.view.View;

import androidx.test.espresso.PerformException;
import androidx.test.espresso.UiController;
import androidx.test.espresso.ViewAction;
import androidx.test.espresso.util.HumanReadables;
import androidx.test.espresso.util.TreeIterables;

import org.hamcrest.Matcher;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.concurrent.TimeoutException;

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

    public static String currentDate() {
        return LocalDate.now().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static String dateInPast() {
        return LocalDate.now().minusDays(1).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static String dateInFuture() {
        return LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

}



