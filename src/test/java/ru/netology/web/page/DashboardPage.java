package ru.netology.web.page;

import com.codeborne.selenide.*;
import lombok.val;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;

public class DashboardPage {
    private SelenideElement heading = $("[data-test-id=dashboard]");
    private SelenideElement firstCardButton = $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0'] button");
    private SelenideElement secondCardButton = $("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d'] button");
    private SelenideElement amountField = $("[data-test-id=amount] input");
    private SelenideElement fromField = $("[data-test-id=from] input");
    private SelenideElement topUpButton = $("[data-test-id=action-transfer]");
    private SelenideElement firstCardBalance = $("[data-test-id='92df3f1c-a033-48e6-8390-206f6b1f56c0']");
    private SelenideElement secondCardBalance = $("[data-test-id='0f3f5c2a-249e-4c3d-8287-09f7a039391d']");
    private final String balanceStart = "баланс: ";
    private final String balanceFinish = " р.";

    public DashboardPage() {
        heading.shouldBe(visible);
    }

    public void topUpTheBalance(DataHelper.CardInfo fromCard, DataHelper.CardInfo onCard, int amount) {
        if(onCard.getId() == 1) {
            firstCardButton.click();
        } else {
            secondCardButton.click();
        }
        amountField.setValue(String.valueOf(amount));
        fromField.setValue(fromCard.getNumber());
        topUpButton.click();
    }

    public int getCardBalance(DataHelper.CardInfo cardInfo) {
        String text = " ";
        if(cardInfo.getId() == 1) {
            text = firstCardBalance.text();
        } else {
            text = secondCardBalance.text();
        }
        return extractBalance(text);
    }

    private int extractBalance(String text) {
        val start = text.indexOf(balanceStart);
        val finish = text.indexOf(balanceFinish);
        val value = text.substring(start + balanceStart.length(), finish);
        return Integer.parseInt(value);
    }


}