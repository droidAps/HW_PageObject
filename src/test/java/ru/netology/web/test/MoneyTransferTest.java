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
        dashboardPage.topUpTheBalance(firstCardInfo, secondCardInfo, transferAmount);

        // проверка баланса карт
        int expected = dashboardPage.getCardBalance(secondCardInfo);
        int actual = dashboardPage.getCardBalance(firstCardInfo) + transferAmount * 2;
        assertEquals(expected, actual);
    }
}
