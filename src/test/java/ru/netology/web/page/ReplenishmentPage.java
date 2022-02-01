package ru.netology.web.page;

import com.codeborne.selenide.SelenideElement;
import ru.netology.web.data.DataHelper;

import static com.codeborne.selenide.Condition.exactText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;

public class ReplenishmentPage {
    private SelenideElement heading = $("h1");
    private SelenideElement amountField = $("[data-test-id=amount] input");
    private SelenideElement fromField = $("[data-test-id=from] input");
    private SelenideElement topUpButton = $("[data-test-id=action-transfer]");

    public ReplenishmentPage() {
        heading.shouldBe(visible).shouldHave(exactText("Пополнение карты"));
    }

    public void topUpTheBalanceFrom(DataHelper.CardInfo fromCard, int amount) {
        amountField.setValue(String.valueOf(amount));
        fromField.setValue(fromCard.getNumber());
        topUpButton.click();
    }
}
