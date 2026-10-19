
import java.util.Scanner;

public class Main {

        private static java.util.List<Message> GlobalMessageHistory = new java.util.ArrayList<>();
        private static int totalMessageSent = 0;
        private static Scanner scanner = new Scanner(System.in);
        // ... your userName, password etc here
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String firstName = "";
        String lastName = "";
        String userName = "";
        String password = "";

        // ===== REGISTRATION =====
        System.out.println(" ===== REGISTRATION ===== ");

        boolean hasNumber = false;
        boolean hasSpace = false;

        // ===== FIRST NAME VALIDATION =====
        while (true) {
            System.out.println("Enter first name:");
            firstName = scanner.nextLine();

            hasSpace = false;
            hasNumber = false;

            for (int i = 0; i < firstName.length(); i++) {
                char c = firstName.charAt(i);

                if (c == ' ') {
                    hasSpace = true;
                }

                if (Character.isDigit(c)) {
                    hasNumber = true;
                }
            }

            if (hasSpace) {
                System.out.println("First name must not have space");
            } else if (hasNumber) {
                System.out.println("First name must not have number");
            } else if (firstName.isEmpty()) {
                System.out.println("First name cannot be empty");
            } else {
                break;
            }
        }

        // ===== LAST NAME VALIDATION =====
        while (true) {
            System.out.println("Enter last name:");
            lastName = scanner.nextLine();

            hasSpace = false;
            hasNumber = false;

            for (int i = 0; i < lastName.length(); i++) {
                char c = lastName.charAt(i);

                if (c == ' ') {
                    hasSpace = true;
                }

                if (Character.isDigit(c)) {
                    hasNumber = true;
                }
            }

            if (hasSpace) {
                System.out.println("Last name must not have space");
            } else if (hasNumber) {
                System.out.println("Last name must not have number");
            } else if (lastName.isEmpty()) {
                System.out.println("Last name cannot be empty");
            } else {
                break;
            }
        }

        // ===== USERNAME VALIDATION =====
        while (true) {
            System.out.println("Enter username: ");
            userName = scanner.nextLine();

            if (userName.length() > 10) {
                System.out.println("Username must be max 10 characters");
            } else if (userName.contains(" ")) {
                System.out.println("Username must not have space");
            } else if (userName.isEmpty()) {
                System.out.println("Username cannot be empty");
            } else {
                break;
            }
        }

        // ===== PASSWORD VALIDATION =====
        // Password validation - min 6, upper and lower
        while (true) {
            System.out.println("Enter password: ");
            password = scanner.nextLine();

            boolean hasUpper = false;
            boolean hasLower = false;

            for (int i = 0; i < password.length(); i++) {
                char c = password.charAt(i);

                if (Character.isUpperCase(c)) {
                    hasUpper = true;
                }

                if (Character.isLowerCase(c)) {
                    hasLower = true;
                }
            }

            if (password.length() < 6) {
                System.out.println("Password must be at least 6 characters");
            } else if (!hasUpper) {
                System.out.println("Password must contain an uppercase letter");
            } else if (!hasLower) {
                System.out.println("Password must contain a lowercase letter");
            } else {
                break;
            }
        }

        // ===== LOGIN =====
        System.out.println("\nlogin");

        while (true) {
            System.out.println("Enter username: ");
            String loginUser = scanner.nextLine();

            System.out.println("Enter password: ");
            String loginPass = scanner.nextLine();

            if (loginUser.equals(userName) && loginPass.equals(password)) {
                System.out.println("Welcome " + firstName + " " + lastName + "!");
                break;
            } else {
                System.out.println("Wrong username or password, try again");
            }
        }

        System.out.println("Welcome to QuichChat.");
        int selection = 0;

        while (selection != 3) {
            System.out.println("Send Messages");
            System.out.println("Show recently sent messages");
            System.out.println("Quit");
            System.out.println("Please select an option");

            if (scanner.hasNextInt()) {
                selection = scanner.nextInt();
                scanner.nextLine();

                switch (selection) {
                    case 1:
                        runSendMessage(scanner);
                        break;
                    case 2:
                        System.out.println("Coming soon");
                        break;
                    case 3:
                        System.out.println("Exiting application loop. Goodbye!");
                        break;
                    default:
                        System.out.println("Invalid selection option context bounds.");
                }
            } else {
                System.out.println("Invalid choice input. Please select via option digit numbers.");
                scanner.nextLine();
                continue;
            }


        }
        scanner.close();
    }

    private static void runSendMessage(Scanner scanner) {
        System.out.println("How many messages do you wish to enter? ");
        int totalBatchCount = 0;

        if (scanner.hasNextInt()) {
            totalBatchCount = scanner.nextInt();
            scanner.nextInt(); // consume enter
        } else {
            System.out.println("Invalid input count detected.");
            scanner.nextLine();
            return;
        }

        for (int i = 0; i < totalBatchCount; i++) {
            System.out.println("------Processing Message " + (i + 1) + " of " + totalBatchCount + "------");
            Message messageInstance = new Message();

            if (!messageInstance.checkMessageID()) {
                System.out.println("Critical Error occurred: Invalid ID structure encountered. ");
                continue;
            }

            while (true) {
                System.out.println("Enter Recipient Cell Number (e.g., +27729718441): ");
                String cellInput = scanner.nextLine();
                if (messageInstance.checkRecipientCell(cellInput)) {
                    messageInstance.setRecipientCell(cellInput);
                    break;
                } else {
                    System.out.println("Incorrect format configuration. Must contain internation code. ");

                }
            }

            while (true) {
                System.out.println("Enter message body content text: ");
                String textInput = scanner.nextLine();
                if (textInput.length() <= 250) {
                    System.out.println("Message sent");
                    break;
                } else {
                    System.out.println("Please enter a amessage of less than 250 characters. ");
                }
            }

            messageInstance.createMessageHash(totalMessageSent, messageInstance.getMessageText());

            System.out.println("Select structural handling path outcome routines");
            System.out.println("1 - Send Message");
            System.out.println("2 - Disregard Message");
            System.out.println("3 - Store Message");
            System.out.println("Action choice selection input: ");

            int handlingChoice = 0;
            if (scanner.hasNextInt()) {
                handlingChoice = scanner.nextInt();
                scanner.nextLine();
            } else {
                scanner.nextLine();
            }

            System.out.println(messageInstance.SentMessage(handlingChoice));

            if (handlingChoice == 2) {
                System.out.println("Message disregarded by manual input rule triggers. ");
                continue;
            }

            GlobalMessageHistory.add(messageInstance);
            totalMessageSent++;

            System.out.println(messageInstance.printMessages());

        }

        returnTotalMessages();
    }

    public static int returnTotalMessages() {
        System.out.println(">>> Cumulative program messages processed history: " + totalMessageSent + " <<< ");
        return totalMessageSent;
    }

}



























