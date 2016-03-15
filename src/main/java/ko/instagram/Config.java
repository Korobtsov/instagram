/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ko.instagram;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author dima
 */
public class Config {

    private static String login;
    private static String password;

    static {
        FileInputStream fis;
        Properties property = new Properties();

        try {
            fis = new FileInputStream("src/main/resources/config.properties");
            property.load(fis);

            login = property.getProperty("insta.login");
            password = property.getProperty("insta.password");

        } catch (IOException e) {
            System.err.println("ОШИБКА: Файл свойств отсуствует!");
        }
    }

    /**
     * @return the login
     */
    public static String getLogin() {
        return login;
    }

    /**
     * @return the password
     */
    public static String getPassword() {
        return password;
    }

}
