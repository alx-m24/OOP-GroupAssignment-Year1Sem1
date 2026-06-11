import UI.InputHelper;
import UI.Menu;
import HouseHold.HouseHold;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner   = new Scanner(System.in);
        InputHelper input = new InputHelper(scanner);
        Menu menu         = new Menu(new HouseHold(), input);

        int choice;
        do {
            menu.show();
            choice = input.getInt("Enter choice: ");
            menu.handleChoice(choice);
        } while (choice != 9);

        scanner.close();
    }
}