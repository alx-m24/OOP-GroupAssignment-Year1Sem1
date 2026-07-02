package User.View;

import User.Entity.UserEntity;

public class UserView {
    public void showLoginOrRegisterMenu() {
        System.out.println("=== Welcome ===");
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("0. Exit");
        System.out.print("Choice: ");
    }

    public void showLoginPrompt() {
        System.out.print("Username: ");
    }

    // called after username, since we read line by line
    public void showPasswordPrompt() {
        System.out.print("Password: ");
    }

    public void showLoginError(String invalidField) {
        System.out.printf("Invalid %s. Please try again.\n", invalidField);
    }

    public void showLoginSuccess(UserEntity user) {
        System.out.println("Welcome back, " + user.getUserName() + "!");
    }

    public void showRegisterPrompt() {
        System.out.println("=== Register ===");
        System.out.print("Choose a username: ");
    }

    public void showRegisterPasswordPrompt() {
        System.out.print("Choose a password: ");
    }

    public void showRegisterError() {
        System.out.println("Username already taken. Please try another.");
    }

    public void showRegisterSuccess(UserEntity user) {
        System.out.println("Account created! Welcome, " + user.getUserName() + ".");
    }

    public void showInvalidChoice() {
        System.out.println("Invalid choice. Please enter 0, 1 or 2.");
    }
}