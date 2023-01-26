package se.nackademin;

import java.io.Serializable;
import java.util.Scanner;

public class Helper implements Serializable {

    public Helper() {

    }
    
    public static final void CLEAR() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public String takeInput(String text) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(text);
        String input = scanner.next();
        return input;
    }
}
