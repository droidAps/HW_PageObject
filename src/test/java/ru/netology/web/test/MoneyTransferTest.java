package ru.netology.web.test;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.web.data.DataHelper;
import ru.netology.web.page.LoginPage;

import static com.codeborne.selenide.Selenide.*;
import static org.junit.jupiter.api.Assertions.*;

public class MoneyTransferTest {

    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }

    @AfterEach
    void returnInitialCondition() {
        open("http://localhost:9999");
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);
        CleanAfterTest.returnInitialCardsBalance();
    }

    @Test
    void shouldTransferMoneyBetweenOwnCards() {
        // подготовка данных
        int transferAmount = 2561;

        // авторизация
        var loginPage = new LoginPage();
        var authInfo = DataHelper.getAuthInfo();
        var verificationPage = loginPage.validLogin(authInfo);
        var verificationCode = DataHelper.getVerificationCodeFor(authInfo);
        var dashboardPage = verificationPage.validVerify(verificationCode);

        // перевод средств
        var firstCardInfo = DataHelper.getFirstCardInfo();
        var secondCardInfo = DataHelper.getSecondCardInfo();
        var replenishmentPage = dashboardPage.clickCardButton(secondCardInfo);
        replenishmentPage.topUpTheBalanceFrom(firstCardInfo, transferAmount);

        // проверка баланса карт
        int expectedFirstCard = dashboardPage.getCardBalance(firstCardInfo);
        int expectedSecondCard = dashboardPage.getCardBalance(secondCardInfo);
        int[] expected = {expectedFirstCard, expectedSecondCard};

        int actualFirstCard = DataHelper.getFirstCardInfo().getInitialBalance() - transferAmount;
        int actualSecondCard = DataHelper.getSecondCardInfo().getInitialBalance() + transferAmount;
        int[] actual = {actualFirstCard, actualSecondCard};

        assertArrayEquals(expected, actual);
    }
}
