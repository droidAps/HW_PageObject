package ru.netology.web.test;

import ru.netology.web.data.DataHelper;
import ru.netology.web.page.DashboardPage;

public class CleanAfterTest {
    private CleanAfterTest() {
    }

    public static void returnInitialCardsBalance() {
        var dashboardPage = new DashboardPage();
        var firstCardBalance = dashboardPage.getCardBalance(DataHelper.getFirstCardInfo());
        var secondCardBalance = dashboardPage.getCardBalance(DataHelper.getSecondCardInfo());
        if (firstCardBalance == secondCardBalance) {
            return;
        } else if (firstCardBalance > secondCardBalance) {
            int diff = (firstCardBalance - secondCardBalance) / 2;
            dashboardPage.topUpTheBalance(DataHelper.getFirstCardInfo(), DataHelper.getSecondCardInfo(), diff);
        } else {
            int diff = (secondCardBalance - firstCardBalance) / 2;
            dashboardPage.topUpTheBalance(DataHelper.getSecondCardInfo(), DataHelper.getFirstCardInfo(), diff);
        }
    }
}
