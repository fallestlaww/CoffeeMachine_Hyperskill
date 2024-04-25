package machine;

import static machine.CoffeeMachine.scanner;

public class CoffeeMachineWork {
    private static int remainingWater = 0;
    private static int remainingMilk = 0;
    private static int remainingCoffeeBeans = 0;
    private static int availableCups = 0;
    private static int earnedMoney = 0;

    public static void run() {
        while (true) {
            System.out.println("Write action (buy, fill, clear, take, remaining, exit): ");
            switch (scanner.nextLine().toLowerCase()) {
                case "buy":
                    buyCoffee();
                    break;
                case "fill":
                    fillCoffeeMachine();
                    break;
                case "clear":
                    clearCoffeeMachine();
                    break;
                case "take":
                    takeMoney();
                    break;
                case "remaining":
                    statsOfCoffeeMachine();
                    break;
                case "exit":
                    exit();
                    return;
                default:
                    System.out.println("Invalid input. Try again.");
            }
        }
    }

    public static void buyCoffee() {
        try {
            System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu: ");
            String choice = scanner.nextLine().toLowerCase();
            switch (choice) {
                case "1": {
                    makeCoffee(250, 0, 16, 4);
                    break;
                }
                case "2": {
                    makeCoffee(350, 75, 20, 7);
                    break;
                }
                case "3": {
                    makeCoffee(200, 100, 12, 6);
                    break;
                }
                case "back":
                    return;
                default:
                    System.out.println("Invalid input. Try again.");
            }
        } catch (NumberFormatException e) {
            System.out.println("Entered value is non-integer. Try again");
        }
    }

    private static void makeCoffee(int water, int milk, int coffeeBeans, int cost) {
        if (remainingWater < water) {
            System.out.println("Sorry, not enough water!");
        } else if (remainingMilk < milk) {
            System.out.println("Sorry, not enough milk!");
        } else if (remainingCoffeeBeans < coffeeBeans) {
            System.out.println("Sorry, not enough coffee beans!");
        } else if (availableCups <= 0) {
            System.out.println("Sorry, not enough disposable cups!");
        } else {
            System.out.println("I have enough resources, making you a coffee!");
            remainingWater -= water;
            remainingMilk -= milk;
            remainingCoffeeBeans -= coffeeBeans;
            earnedMoney += cost;
            availableCups--;
        }
    }

    public static void fillCoffeeMachine() {
        System.out.println("You choose to fill coffee machine with some ingredients");
        do {
            System.out.println("Enter the compartment which you want to fill (water, milk, beans, cups or enter back to return to main menu): ");
            String input = scanner.nextLine().toLowerCase();
            switch (input) {
                case "water":
                    System.out.println("Water compartment has been filled");
                    remainingWater += Input.inputWaterAmount();
                    break;
                case "milk":
                    System.out.println("Milk compartment has been filled");
                    remainingMilk += Input.inputMilkAmount();
                    break;
                case "beans":
                    System.out.println("Coffee beans compartment has been filled");
                    remainingCoffeeBeans += Input.inputCoffeeBeansAmount();
                    break;
                case "cups":
                    System.out.println("Cups compartment has been filled");
                    availableCups += Input.inputDisposableCupsAmount();
                    break;
                case "back":
                    return;
                default:
                    System.out.println("Invalid input. Try again");
            }
            System.out.println("Do you want to fill something more? (yes/no)");
        } while (scanner.nextLine().equalsIgnoreCase("yes"));
    }

    public static void clearCoffeeMachine () {
        System.out.println("You choose to clean your coffee machine!\nEnter YES if you want to do it or enter NO to back to the main menu:");
        String userInput = scanner.nextLine().toLowerCase();

        if (userInput.equals("yes")) {
            do {
                System.out.println("Enter which compartment you want to clean(water, milk, beans and cups): ");
                String compartment = scanner.nextLine().toLowerCase();
                switch (compartment) {
                    case "water":
                        System.out.println("Water compartment has been cleaned");
                        remainingWater = 0;
                        break;
                    case "milk":
                        System.out.println("Milk compartment has been cleaned");
                        remainingMilk = 0;
                        break;
                    case "beans":
                        System.out.println("Coffee beans compartment has been cleaned");
                        remainingCoffeeBeans = 0;
                        break;
                    case "cups":
                        System.out.println("Cups compartment has been cleaned");
                        availableCups = 0;
                        break;
                    default:
                        System.out.println("Invalid input. Try again");
                        continue;
                }
                System.out.println("Do you want to clear something else? (yes/no)");
            } while (scanner.nextLine().equalsIgnoreCase("yes"));
        } else if (userInput.equals("no")) {
            System.out.println("Returning to main menu!");
            return;
        } else {
            System.out.println("Invalid input. Try again");
            return;
        }
    }

    public static void takeMoney () {
        System.out.println("I gave you $" + earnedMoney);
        earnedMoney = 0;
    }

    public static void statsOfCoffeeMachine () {
        System.out.printf("The coffee machine has:\n" +
                "%d ml of water\n" +
                "%d ml of milk\n" +
                "%d g of coffee beans\n" +
                "%d disposable cups\n" +
                "$%d of money\n", remainingWater, remainingMilk, remainingCoffeeBeans, availableCups, earnedMoney);
    }


    public static void exit() {
        System.out.println("Thank you for using our coffee machine! Good luck!");
        System.exit(0);
    }
}
