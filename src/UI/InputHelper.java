package UI;

import java.util.Scanner;

public class InputHelper {
    private final Scanner scanner;

    public InputHelper(Scanner scanner) {
        this.scanner = scanner;
    }

    public String getText(String message) {
        System.out.print(message);
        return scanner.nextLine();
    }

    public int getInt(String message) {
        System.out.print(message);
        while (!scanner.hasNextInt()) {
            System.out.println("Please enter a valid whole number.");
            scanner.next();
            System.out.print(message);
        }
        return scanner.nextInt();
    }

    public double getDouble(String message) {
        System.out.print(message);
        while (!scanner.hasNextDouble()) {
            System.out.println("Please enter a valid number.");
            scanner.next();
            System.out.print(message);
        }
        return scanner.nextDouble();
    }
}