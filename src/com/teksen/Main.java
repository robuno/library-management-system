package com.teksen;
import javax.swing.*;
/**
 * <h1> Main Program! </h1>
 *
 * This main class, driver runs program with login page
 *
 * @author Humeyra Dogus - Unat Teksen
 * @version 1.0
 * @since 2021-12-25
 *
 */
public class Main {
    /**
     * Main Function
     * It has run function to start program.
     * @param args
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            /**
             * Run Function
             * This function has loginPage object to run program
             * with Login Screen.
             */
            @Override
            public void run() {
                LoginPage newLoginPage = new LoginPage();
                newLoginPage.setVisible(true);
            }
        });
    }
}
