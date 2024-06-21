package org.example.task1;

class User {
    private int userId;
    private int transactionCount;

    public User(int userId) {
        this.userId = userId;
        this.transactionCount = 0;
    }

    public void addTransaction() {
        this.transactionCount++;
    }

    public int getTransactionCount() {
        return this.transactionCount;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", transactionCount=" + transactionCount +
                '}';
    }
}
