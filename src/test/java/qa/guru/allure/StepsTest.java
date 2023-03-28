package qa.guru.allure;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.logevents.SelenideLogger;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.Test;

import static com.codeborne.selenide.Selectors.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;
import static io.qameta.allure.Allure.step;
import static org.openqa.selenium.By.linkText;

public class StepsTest {

    private static final String REPOSITORY = "eroshenkoam/allure-example";
    private static final int ISSUE = 80;
    @Test
    public void testLambdaStep() { // Использование лямбда-степов, для разметки теста в аллюр
        SelenideLogger.addListener("allure", new AllureSelenide());

        step("Открытие главной страницы", () -> {
            open("https://github.com");
        });
        step("Ищем репозиторий " + REPOSITORY, () -> {
            $(".header-search-input").click();
            $(".header-search-input").sendKeys(REPOSITORY);
            $(".header-search-input").submit();
        });
        step("Кликаем по ссылке репозитория " + REPOSITORY, () -> {
            $(linkText(REPOSITORY)).click();
        });
        step("Открываем таб issues", () -> {
            $("#issues-tab").click();
        });
        step("Проверяем содержание issues", () -> {
            $(withText("#" + ISSUE)).should(Condition.exist);
        });
    }
        @Test
        public void testAnnotatedStep() {
            WebSteps steps = new WebSteps();

            steps.openMainPage();
            steps.searchForRepository(REPOSITORY);
            steps.clickOnRepositoryLink(REPOSITORY);
            steps.openIssuesTab();
            steps.shouldIssueWithNumber(ISSUE);

        }
    }

