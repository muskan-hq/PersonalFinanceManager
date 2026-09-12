
package com.personalfinancemanager.model;

public class Transaction {

    private int id;
    private double amount;
    private String description;
    private TransactionType type;

    public Transaction(int id, double amount, String description, TransactionType type) {
        this.id = id;
        this.amount = amount;
        this.description = description;
        this.type = type;
    }
    public int getId() {
        return id;
    }
    public double getAmount() {
        return amount;
    }
    public String getDescription() {
        return description;
    }
    public TransactionType getType() {
        return type;
    }
    public void setAmount(double amount) {
        this.amount = amount;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setType(TransactionType type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", amount=" + amount +
                ", description='" + description + '\'' +
                ", type=" + type +
                '}';
    }


}