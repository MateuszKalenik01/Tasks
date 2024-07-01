package org.example.task1;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.*;

public class Task1 {

    public static void main(String[] args) {
        String jsonString = "[{\"e64667a9-c9bd-4cf2-bd3f-a1e357df99c7\": {\"userId\": 1, \"data1\": 1000, \"data2\": 500, \"data3\": false}}, " +
                "{\"9fbaece9-4f5d-4f2e-a8ba-71721198b3ad\": {\"userId\": 1, \"data1\": 1000, \"data3\": false}}, " +
                "{\"610adf77-8391-472a-8b7f-8de5e17c1093\": {\"userId\": 2, \"data3\": false}}, " +
                "{\"6dc07f0f-dce4-478c-940a-b5e91528e7c5\": {\"userId\": 3, \"data1\": 1000, \"data3\": false}}]";

        Map<Integer, User> users = new HashMap<>();

        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode rootNode = objectMapper.readTree(jsonString);

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