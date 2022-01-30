package ru.netology.web.data;

import lombok.Value;
import ru.netology.web.page.DashboardPage;

public class DataHelper {
    private DataHelper() {
    }

    @Value
    public static class AuthInfo {
        private String login;
        private String password;
    }

    public static AuthInfo getAuthInfo() {
        return new AuthInfo("vasya", "qwerty123");
    }

    @Value
    public static class VerificationCode {
        private String code;
    }

    public static VerificationCode getVerificationCodeFor(AuthInfo authInfo) {
        return new VerificationCode("12345");
    }

    @Value
    public static class CardInfo {
        private int id;
        private String number;
        private int initialBalance;
    }

    public static CardInfo getFirstCardInfo() {
        return new CardInfo(1, "5559 0000 0000 0001", 10000);
    }

    public static CardInfo getSecondCardInfo() {
        return new CardInfo(2, "5559 0000 0000 0002", 10000);
    }
}