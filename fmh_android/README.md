## Запуск автотестов:

1. Клонировать данный репозиторий при помощи команды git clone
2. Установить приложение Android Studio
3. Открыть проект fmh_android в Android Studio
4. Запустить эмулятор Android с API 29
5. В терминале ввести команду ./gradlew connectedAndroidTest

## Подготовка отчета:
1. Скачать Allure
2. Результаты тестов найти в Device Explorer -> data -> data -> /data/data/ru.iteco.fmhandroid -> files -> build -> allure-results
8. Сохранить результаты в корневой папке проекта
9. Запустить в терминале команду allure serve и просмотреть отчет в браузере