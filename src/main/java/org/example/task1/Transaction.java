package org.example.task1;

import java.util.Map;

class Transaction {
    private int userId;
    private Map<String, Object> data;

    public Transaction(int userId, Map<String, Object> data) {
        this.userId = userId;
        this.data = data;
    }

    public int getUserId() {
        return userId;
    }

    public Map<String, Object> getData() {
        return data;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "userId=" + userId +
                ", data=" + data +
                '}';
    }
}