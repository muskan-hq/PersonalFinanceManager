package com.personalfinancemanager.service;
import java.util.ArrayList;
import com.personalfinancemanager.model.Transaction;
import com.personalfinancemanager.model.TransactionType;


public class FinanceService {
    private ArrayList<Transaction> transactions = new ArrayList<>();
    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }
    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }
    public double getTotalIncome() {
        double total = 0;
        for (Transaction transaction : transactions) {
            if (transaction.getType() == TransactionType.INCOME) {
                total += transaction.getAmount();
            }
        }
        return total;
    }
    public double getTotalExpense() {
        double total = 0;

        for (Transaction transaction : transactions) {
            if (transaction.getType() == TransactionType.EXPENSE) {
                total += transaction.getAmount();
            }
        }

        return total;
    }
    public double getBalance() {
        return getTotalIncome() - getTotalExpense();
    }
    public boolean deleteTransaction(int id) {

        for (int i = 0; i < transactions.size(); i++) {

            if (transactions.get(i).getId() == id) {
                transactions.remove(i);
                return true;
            }
        }

        return false;
    }
    public boolean updateTransaction(
            int id,
            double newAmount,
            String newDescription,
            TransactionType newType) {

        for (Transaction transaction : transactions) {

            if (transaction.getId() == id) {
                transaction.setAmount(newAmount);
                transaction.setDescription(newDescription);
                transaction.setType(newType);

                return true;
            }
        }

        return false;
    }
}

