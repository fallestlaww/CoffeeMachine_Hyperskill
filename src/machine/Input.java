package machine;

import static machine.CoffeeMachine.scanner;

public class Input {
    public static int inputWaterAmount() {
        while (true) {
            try {
                System.out.println("Enter amount of water: ");
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("This value is non-integer, input only integer values");
            }
        }
    }

    public static int inputMilkAmount() {
        while (true) {
            try {
                System.out.println("Enter amount of milk: ");
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("This value is non-integer, input only integer values");
            }
        }
    }
    public static int inputCoffeeBeansAmount() {
        while (true) {
            try {
                System.out.println("Enter amount of coffee beans: ");
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("This value is non-integer, input only integer values");
            }
        }
    }

    public static int inputDisposableCupsAmount() {
        while (true) {
            try {
                System.out.println("Enter amount of disposable cups: ");
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("This value is non-integer, input only integer values");
            }
        }
    }
}
