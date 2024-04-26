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
        boolean validInput = false;

        do {
            try {
                System.out.println("What do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to the main menu: ");
                String choice = scanner.nextLine().toLowerCase();
                switch (choice) {
                    case "1":
                        makeCoffee(250, 0, 16, 4);
                        validInput = true;
                        break;
                    case "2":
                        makeCoffee(350, 75, 20, 7);
                        validInput = true;
                        break;
                    case "3":
                        makeCoffee(200, 100, 12, 6);
                        validInput = true;
                        break;
                    case "back":
                        return;
                    default:
                        System.out.println("Invalid input. Try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Entered value is non-integer. Try again");
            }
        } while (!validInput);
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

        boolean validInput = true;

        do {
            System.out.println("Enter the compartment which you want to fill (water, milk, beans, cups or enter 'back' to return to main menu): ");
            String input = scanner.nextLine().toLowerCase();

            switch (input) {
                case "water":
                    remainingWater += Input.inputWaterAmount();
                    System.out.println("Water compartment has been filled");
                    validInput = true;
                    break;
                case "milk":
                    remainingMilk += Input.inputMilkAmount();
                    System.out.println("Milk compartment has been filled");
                    validInput = true;
                    break;
                case "beans":
                    remainingCoffeeBeans += Input.inputCoffeeBeansAmount();
                    System.out.println("Coffee beans compartment has been filled");
                    validInput = true;
                    break;
                case "cups":
                    availableCups += Input.inputDisposableCupsAmount();
                    System.out.println("Cups compartment has been filled");
                    validInput = true;
                    break;
                case "back":
                    return;
                default:
                    System.out.println("Invalid input. Try again");
                    validInput = false;
            }

            if (validInput) {
                System.out.println("Do you want to fill something more? (yes/no)");
                validInput = scanner.nextLine().equalsIgnoreCase("yes");
            }
        } while (validInput);
    }




    public static void clearCoffeeMachine() {
        System.out.println("You choose to clean your coffee machine!\nEnter YES if you want to do it or enter NO to return to the main menu:");
        String userInput = scanner.nextLine().toLowerCase();

        if (userInput.equals("yes")) {
            boolean validInput = false;

            do {
                System.out.println("Enter which compartment you want to clean (water, milk, beans, or cups): ");
                String compartment = scanner.nextLine().toLowerCase();
                switch (compartment) {
                    case "water":
                        System.out.println("Water compartment has been cleaned");
                        remainingWater = 0;
                        validInput = true;
                        break;
                    case "milk":
                        System.out.println("Milk compartment has been cleaned");
                        remainingMilk = 0;
                        validInput = true;
                        break;
                    case "beans":
                        System.out.println("Coffee beans compartment has been cleaned");
                        remainingCoffeeBeans = 0;
                        validInput = true;
                        break;
                    case "cups":
                        System.out.println("Cups compartment has been cleaned");
                        availableCups = 0;
                        validInput = true;
                        break;
                    default:
                        System.out.println("Invalid input. Try again");
                        validInput = false;
                }

                if (validInput) {
                    System.out.println("Do you want to clear something else? (yes/no)");
                    validInput = scanner.nextLine().equalsIgnoreCase("yes");
                }
            } while (validInput);
        } else if (userInput.equals("no")) {
            System.out.println("Returning to the main menu!");
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
