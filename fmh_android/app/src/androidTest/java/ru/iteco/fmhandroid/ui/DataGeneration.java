package ru.iteco.fmhandroid.ui;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import com.github.javafaker.Faker;

public class DataGeneration {

    private final Faker faker = new Faker(new Locale("ru"));

    public String generateFakeTitle() {
        return faker.lorem().word() + " " + faker.lorem().word();
    }

    public String generateFakeEditTitle() {
        return faker.lorem().word();
    }

    public String generateFakeEditDescription() {
        return faker.lorem().word() + " "
                + faker.lorem().word() + " "
                + faker.lorem().word();
    }

    public String generateFakeDescription() {
        return faker.lorem().word() + " "
                + faker.lorem().word();
    }

    public static String getCurrentDate() {
        Date currentDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy", Locale.getDefault());
        return dateFormat.format(currentDate);
    }

    public static String getCurrentTime() {
        Date currentDate = new Date();
        DateFormat dateFormat = new SimpleDateFormat("HH:mm", Locale.getDefault());
        return dateFormat.format(currentDate);
    }

    public static String dateInPast() {
        return LocalDate.now().minusDays(1).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }

    public static String dateInFuture() {
        return LocalDate.now().plusDays(1).format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
    }


    public static class Rand {
        static final Random rand = new Random();

        public static String randomCategory() {
            String[] category = {
                    "Зарплата",
                    "Объявление",
                    "День рождения",
                    "Профсоюз",
                    "Массаж",
                    "Праздник",
                    "Нужна помощь",
                    "Благодарность",

            };
            return category[rand.nextInt(category.length)];
        }
    }
}




