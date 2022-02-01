package ru.netology.web.page;

import com.codeborne.selenide.*;
import lombok.val;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;

public class DashboardPage {
    private SelenideElement heading = $("[data-test-id=dashboard]");
    private ElementsCollection cards = $$(".list__item");
    private SelenideElement amountField = $("[data-test-id=amount] input");
    private SelenideElement fromField = $("[data-test-id=from] input");
    private SelenideElement topUpButton = $("[data-test-id=action-transfer]");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    public DashboardPage() {
        heading.shouldBe(visible);
    }

    public String returnTheLast4Digits(DataHelper.CardInfo cardInfo) {
        String text = cardInfo.getNumber();
        val start = text.length() - 4;
        val finish = text.length();
        val value = text.substring(start, finish);
        return value;
    }

    public ReplenishmentPage clickCardButton(DataHelper.CardInfo onCard) {
        String number = returnTheLast4Digits(onCard);
        cards.find(text(number)).$("button").click();
        return new ReplenishmentPage();
    }

    public int getCardBalance(DataHelper.CardInfo cardInfo) {
        String number = returnTheLast4Digits(cardInfo);
        String text = cards.find(text(number)).text();
        return extractBalance(text);
    }

    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }
}