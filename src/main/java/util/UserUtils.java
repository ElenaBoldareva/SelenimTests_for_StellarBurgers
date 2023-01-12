package util;

import org.apache.commons.lang3.RandomStringUtils;
import pojo.User;

public class UserUtils {

    public static User getRandomUser() {
        String email = getRandomEmail(16);
        String password = RandomStringUtils.randomAlphanumeric(12);
        String name = RandomStringUtils.randomAlphanumeric(7);
        User user = new User(email, password, name);
        return user;
    }

    public static String getRandomEmail(int length) {
        return RandomStringUtils.randomAlphabetic(length) + "@yandex.ru";
    }
}
