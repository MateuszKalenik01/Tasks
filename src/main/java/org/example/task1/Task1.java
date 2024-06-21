package org.example.task1;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.*;

public class Task1 {

    public static void main(String[] args) {
        InputStream inputStream = Task1.class.getClassLoader().getResourceAsStream("bank_records.json");

        if (inputStream == null) {
            System.err.println("File not found!");
            return;
        }
        Map<Integer, User> users = new HashMap<>();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(inputStream);

            for (JsonNode recordNode : rootNode) {
                for (JsonNode transactionNode : recordNode) {
                    int userId = transactionNode.get("userId").asInt();
                    Map<String, Object> transactionData = objectMapper.convertValue(transactionNode, Map.class);

                    Transaction transaction = new Transaction(userId, transactionData);

                    users.putIfAbsent(userId, new User(userId));

                    users.get(userId).addTransaction();
                }
            }

            for (Map.Entry<Integer, User> entry : users.entrySet()) {
                System.out.println("User ID: " + entry.getKey() + ", Total Transactions: " + entry.getValue().getTransactionCount());
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
