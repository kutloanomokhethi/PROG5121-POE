
import java.util.Scanner;

public class Main {
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

        scanner.close();
    }
}
