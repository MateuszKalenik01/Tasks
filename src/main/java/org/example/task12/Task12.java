package org.example.task12;

import java.util.HashMap;
import java.util.Map;

public class Task12 {
    private int adDeliveryPennies;
    private int paymentPennies;

    public Task12() {
        this.adDeliveryPennies = 0;
        this.paymentPennies = 0;
    }

    public void ingestTransaction(Map<String, Integer> transaction) {
        this.adDeliveryPennies += transaction.getOrDefault("adDeliveryPennies", 0);
        this.paymentPennies += transaction.getOrDefault("paymentPennies", 0);
    }

    @Override
    public String toString() {
        return "BillingStatus{" +
                "adDeliveryPennies=" + adDeliveryPennies +
                ", paymentPennies=" + paymentPennies +
                '}';
    }

    public static void main(String[] args) {
        String[] monetaryColumns = {"adDeliveryPennies", "paymentPennies"};
        Map<String, Map<String, Integer>> transactions = new HashMap<>();
        transactions.put("ff8bc1c2-8d45-11e9-bc42-526af7764f64", Map.of("userId", 1, "adDeliveryPennies", 1000, "transactionTimestamp", 1500000001));
        transactions.put("ff8bc2e4-8d45-11e9-bc42-526af7764f64", Map.of("userId", 1, "adDeliveryPennies", 1000, "transactionTimestamp", 1500000002));
        transactions.put("ff8bc4ec-8d45-11e9-bc42-526af7764f64", Map.of("userId", 1, "paymentPennies", 500, "transactionTimestamp", 1500000003));
        transactions.put("fv24z4ec-8d45-11e9-bc42-526af7764f64", Map.of("userId", 1, "adDeliveryPennies", 1000, "paymentPennies", 500, "transactionTimestamp", 1500000004));

        Map<Integer, Task12> userBillingStatuses = new HashMap<>();

        for (Map<String, Integer> transaction : transactions.values()) {
            int userId = transaction.get("userId");
            userBillingStatuses.putIfAbsent(userId, new Task12());
            userBillingStatuses.get(userId).ingestTransaction(transaction);
        }

        System.out.println(userBillingStatuses);
    }
}
