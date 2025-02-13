import java.util.InputMismatchException;
import java.util.Scanner;

public class BankLoanSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Input loan amount with validation
        double loanAmount = getValidDoubleInput(scanner, "Enter the loan amount: ", 0);

        // Ask if the loan is insured
        String insuredChoice = getValidYesNoInput(scanner, "Is the loan insured? (yes/no): ");

        int duration = 0;
        double insurancePayment = 0;

        // Handle insured vs uninsured loans
        if (insuredChoice.equals("yes")) {
            System.out.println("Choose duration for insured loan:");
            System.out.println("1. 12 months");
            System.out.println("2. 24 months");
            System.out.println("3. 36 months");
            int choice = getValidChoice(scanner, "Enter your choice (1/2/3): ", 1, 3);

            switch (choice) {
                case 1:
                    duration = 12;
                    break;
                case 2:
                    duration = 24;
                    break;
                case 3:
                    duration = 36;
                    break;
            }

            // Add insurance payment
            insurancePayment = 250;
        } else {
            System.out.println("Choose duration for uninsured loan:");
            System.out.println("1. 6 months");
            System.out.println("2. 12 months");
            System.out.println("3. 18 months");
            int choice = getValidChoice(scanner, "Enter your choice (1/2/3): ", 1, 3);

            switch (choice) {
                case 1:
                    duration = 6;
                    break;
                case 2:
                    duration = 12;
                    break;
                case 3:
                    duration = 18;
                    break;
            }
        }

        // Calculate total repayment amount (loan + insurance + interest)
        double interestRate = 0.05; // 5% interest rate
        double totalInterest = loanAmount * interestRate * (duration / 12.0);
        double totalRepayment = loanAmount + totalInterest + insurancePayment;

        // Display loan details
        System.out.println("\nLoan Details:");
        System.out.println("Loan Amount: " + loanAmount + " lira");
        System.out.println("Duration: " + duration + " months");
        System.out.println("Insurance Payment: " + insurancePayment + " lira");
        System.out.println("Total Repayment Amount: " + totalRepayment + " lira");

        scanner.close();
    }

    // Helper method to validate double input (e.g., loan amount)
    private static double getValidDoubleInput(Scanner scanner, String prompt, double minValue) {
        while (true) {
            try {
                System.out.print(prompt);
                double input = scanner.nextDouble();
                if (input >= minValue) {
                    return input; // Valid input, return it
                } else {
                    System.out.println("Input must be greater than or equal to " + minValue + ". Please try again.");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next(); // Clear the invalid input from the scanner
            }
        }
    }

    // Helper method to validate yes/no input
    private static String getValidYesNoInput(Scanner scanner, String prompt) {
        while (true) {
            System.out.print(prompt);
            String input = scanner.next().toLowerCase();
            if (input.equals("yes") || input.equals("no")) {
                return input; // Valid input, return it
            } else {
                System.out.println("Invalid input. Please enter 'yes' or 'no'.");
            }
        }
    }

    // Helper method to validate choice input (e.g., duration options)
    private static int getValidChoice(Scanner scanner, String prompt, int min, int max) {
        while (true) {
            try {
                System.out.print(prompt);
                int input = scanner.nextInt();
                if (input >= min && input <= max) {
                    return input; // Valid choice, return it
                } else {
                    System.out.println("Invalid choice. Please enter a number between " + min + " and " + max + ".");
                }
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid number.");
                scanner.next(); // Clear the invalid input from the scanner
            }
        }
    }
}