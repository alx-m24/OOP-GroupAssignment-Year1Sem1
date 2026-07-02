package User.Controller;

import User.Entity.UserEntity;
import User.Service.UserService;
import User.View.UserView;

import java.util.Scanner;

public class UserController {
    final private UserService userService;
    final private UserView userView;
    final private Scanner scanner;

    public UserController(UserService userService, Scanner scanner) {
        this.userService = userService;
        this.scanner = scanner;
        this.userView = new UserView();
    }

    public UserEntity loginOrRegister() {
        userView.showLoginOrRegisterMenu();
        String choice = scanner.nextLine();
        switch (choice) {
            case "1": return login();
            case "2": return register();
            case "0": return null;
            default:
                userView.showInvalidChoice();
                return loginOrRegister();
        }
    }

    public UserEntity login() {
        userView.showLoginPrompt();
        String userName = scanner.nextLine();
        userView.showPasswordPrompt();
        String password = scanner.nextLine();

        if (!userService.userNameExists(userName)) {
            userView.showLoginError("Username");
            return login();
        }

        UserEntity user = userService.login(userName, password);
        if (user == null) {
            userView.showLoginError("Password");
            return login(); // retry
        }

        userView.showLoginSuccess(user);
        return user;
    }

    public UserEntity register() {
        userView.showRegisterPrompt();
        String userName = scanner.nextLine();
        userView.showRegisterPasswordPrompt();
        String password = scanner.nextLine();

        UserEntity user = userService.register(userName, password);
        if (user == null) {
            userView.showRegisterError(); // e.g. username already taken
            return register(); // retry
        }

        userView.showRegisterSuccess(user);
        return user;
    }
}
