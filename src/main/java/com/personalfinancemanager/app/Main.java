package com.personalfinancemanager.app;

import com.personalfinancemanager.model.Transaction;
import com.personalfinancemanager.model.TransactionType;
import com.personalfinancemanager.service.FinanceService;
import com.personalfinancemanager.util.DBConnection;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        try {
            DBConnection.getConnection();
            System.out.println("Database connected successfully!");
        } catch (Exception e) {
            System.out.println("Database connection failed!");
            e.printStackTrace();
        }

        Scanner scanner = new Scanner(System.in);

        FinanceService service = new FinanceService();
        boolean running = true;

        while (running) {

            System.out.println("\n===== Personal Finance Manager =====");
            System.out.println("1. Add Transaction");
            System.out.println("2. View Transactions");
            System.out.println("3. Total Income");
            System.out.println("4. Total Expense");
            System.out.println("5. Balance");
            System.out.println("6. Delete Transaction");
            System.out.println("7. Update Transaction");
            System.out.println("8. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    System.out.print("Enter transaction ID: ");

                    while (!scanner.hasNextInt()) {
                        System.out.println("Please enter a valid number.");
                        scanner.next();
                    }

                    int id = scanner.nextInt();

                    System.out.print("Enter amount: ");

                    while (!scanner.hasNextDouble()) {
                        System.out.println("Please enter a valid amount.");
                        scanner.next();
                    }

                    double amount = scanner.nextDouble();
                    scanner.nextLine();

                    System.out.print("Enter description: ");
                    String description = scanner.nextLine();



                    System.out.print("Enter type (INCOME/EXPENSE): ");
                    String typeInput = scanner.nextLine();

                    while (!typeInput.equalsIgnoreCase("INCOME")
                            && !typeInput.equalsIgnoreCase("EXPENSE")) {

                        System.out.println("Please enter INCOME or EXPENSE.");
                        System.out.print("Enter type (INCOME/EXPENSE): ");
                        typeInput = scanner.nextLine();
                    }

                    TransactionType type =
                            TransactionType.valueOf(typeInput.toUpperCase());

                    Transaction transaction = new Transaction(
                            id,
                            amount,
                            description,
                            type
                    );

                    service.addTransaction(transaction);

                    System.out.println("Transaction added!");
                    System.out.println(transaction);
                    break;

                case 2:
                    System.out.println("\n===== All Transactions =====");

                    for (Transaction t : service.getTransactions()) {
                        System.out.println(t);
                    }

                    break;

                case 3:
                    System.out.println("Total Income: " + service.getTotalIncome());
                    break;

                case 4:
                    System.out.println("Total Expense: " + service.getTotalExpense());
                    break;

                case 5:
                    System.out.println("Balance: " + service.getBalance());
                    break;

                case 6:
                    System.out.print("Enter transaction ID to delete: ");

                    while (!scanner.hasNextInt()) {
                        System.out.println("Please enter a valid transaction ID.");
                        scanner.next();
                    }

                    int deleteId = scanner.nextInt();

                    boolean deleted = service.deleteTransaction(deleteId);

                    if (deleted) {
                        System.out.println("Transaction deleted successfully!");
                    } else {
                        System.out.println("Transaction not found!");
                    }

                    break;

                case 7:
                    System.out.print("Enter transaction ID to update: ");

                    while (!scanner.hasNextInt()) {
                        System.out.println("Please enter a valid transaction ID.");
                        scanner.next();
                    }

                    int updateId = scanner.nextInt();

                    System.out.print("Enter new amount: ");

                    while (!scanner.hasNextDouble()) {
                        System.out.println("Please enter a valid amount.");
                        scanner.next();
                    }

                    double newAmount = scanner.nextDouble();

                    scanner.nextLine();

                    System.out.print("Enter new description: ");
                    String newDescription = scanner.nextLine();

                    System.out.print("Enter new type (INCOME/EXPENSE): ");
                    String newTypeInput = scanner.nextLine();

                    while (!newTypeInput.equalsIgnoreCase("INCOME")
                            && !newTypeInput.equalsIgnoreCase("EXPENSE")) {

                        System.out.println("Please enter INCOME or EXPENSE.");
                        System.out.print("Enter new type (INCOME/EXPENSE): ");
                        newTypeInput = scanner.nextLine();
                    }

                    TransactionType newType =
                            TransactionType.valueOf(newTypeInput.toUpperCase());



                    boolean updated = service.updateTransaction(
                            updateId,
                            newAmount,
                            newDescription,
                            newType
                    );

                    if (updated) {
                        System.out.println("Transaction updated successfully!");
                    } else {
                        System.out.println("Transaction not found!");
                    }

                    break;

                case 8:
                    running = false;
                    System.out.println("Thank you for using Personal Finance Manager!");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }
        }

        scanner.close();
    }
}
