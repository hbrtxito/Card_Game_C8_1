package com.HebertCarbonel;

import java.util.Scanner;

/**
 * Created by hbrtxito on 11/7/2015.
 */
public class ChangeofSuits {
    // this method will return ONE character
    protected static char promptForChar(String promt) {
        Scanner scanner = new Scanner(System.in);
        char c = ' ';
        boolean valid = false;
        while (!valid) {
            System.out.println(promt);
            String s = scanner.nextLine();
            //Validating that the user only enter one character
            if (s.length() == 1) {
                c = s.charAt(0);
                valid = true;

            }
            // or will prompt again the message
            else {
                c = promptForChar(promt);
            }
        }
        return c;
    }
}
