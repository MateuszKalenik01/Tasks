package org.example.task1;

import java.io.*;
import java.util.*;

public class Task1 {

    public static void main(String[] args) {
        String filePath = "/bank_records.json";

        try {
            List<Map<String, Map<String, Object>>> transactions = readTransactionsFromFile(filePath);
            Map<Integer, Map<String, Integer>> result = calculateTotalAmounts(transactions);

            result.forEach((userId, data) ->
                    System.out.println("UserId: " + userId + ", Data: " + data));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static List<Map<String, Map<String, Object>>> readTransactionsFromFile(String filePath) throws IOException {
        InputStream inputStream = Task1.class.getResourceAsStream(filePath);
        if (inputStream == null) {
            throw new FileNotFoundException("File not found: " + filePath);
        }

        StringBuilder jsonContent = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                jsonContent.append(line);
            }
        }

        return parseJsonArray(jsonContent.toString());
    }

    public static List<Map<String, Map<String, Object>>> parseJsonArray(String jsonString) {
        jsonString = jsonString.trim();
        if (!jsonString.startsWith("[") || !jsonString.endsWith("]")) {
            throw new IllegalArgumentException("Invalid JSON array format");
        }

        jsonString = jsonString.substring(1, jsonString.length() - 1).trim();
        List<Map<String, Map<String, Object>>> list = new ArrayList<>();

        int start = 0;
        boolean inString = false;
        int braceCount = 0;
        for (int i = 0; i < jsonString.length(); i++) {
            char c = jsonString.charAt(i);
            if (c == '"') {
                inString = !inString;
            } else if (!inString) {
                if (c == '{') {
                    if (braceCount == 0) {
                        start = i;
                    }
                    braceCount++;
                } else if (c == '}') {
                    braceCount--;
                    if (braceCount == 0) {
                        String jsonObjectStr = jsonString.substring(start, i + 1);
                        list.add(parseJsonObject(jsonObjectStr));
                    }
                }
            }
        }

        return list;
    }

    public static Map<String, Map<String, Object>> parseJsonObject(String jsonString) {
        jsonString = jsonString.trim();
        if (!jsonString.startsWith("{") || !jsonString.endsWith("}")) {
            throw new IllegalArgumentException("Invalid JSON object format");
        }

        jsonString = jsonString.substring(1, jsonString.length() - 1).trim();
        Map<String, Map<String, Object>> map = new HashMap<>();
        String[] parts = jsonString.split(":", 2);
        String key = parts[0].trim().replaceAll("\"", "");
        Map<String, Object> value = parseInnerJsonObject(parts[1].trim());

        map.put(key, value);
        return map;
    }

    public static Map<String, Object> parseInnerJsonObject(String jsonString) {
        jsonString = jsonString.trim();
        if (!jsonString.startsWith("{") || !jsonString.endsWith("}")) {
            throw new IllegalArgumentException("Invalid JSON object format");
        }

        jsonString = jsonString.substring(1, jsonString.length() - 1).trim();
        Map<String, Object> map = new HashMap<>();

        boolean inString = false;
        int braceCount = 0;
        int start = 0;
        for (int i = 0; i < jsonString.length(); i++) {
            char c = jsonString.charAt(i);
            if (c == '"') {
                inString = !inString;
            } else if (!inString) {
                if (c == '{' || c == '[') {
                    braceCount++;
                } else if (c == '}' || c == ']') {
                    braceCount--;
                } else if (c == ',' && braceCount == 0) {
                    String[] kv = jsonString.substring(start, i).split(":", 2);
                    String key = kv[0].trim().replaceAll("\"", "");
                    Object value = parseJsonValue(kv[1].trim());
                    map.put(key, value);
                    start = i + 1;
                }
            }
        }

        if (start < jsonString.length()) {
            String[] kv = jsonString.substring(start).split(":", 2);
            String key = kv[0].trim().replaceAll("\"", "");
            Object value = parseJsonValue(kv[1].trim());
            map.put(key, value);
        }

        return map;
    }

    public static Object parseJsonValue(String value) {
        if (value.startsWith("\"") && value.endsWith("\"")) {
            return value.substring(1, value.length() - 1);
        } else if (value.equals("true") || value.equals("false")) {
            return Boolean.parseBoolean(value);
        } else if (value.contains(".")) {
            return Double.parseDouble(value);
        } else {
            return Integer.parseInt(value);
        }
    }

    public static Map<Integer, Map<String, Integer>> calculateTotalAmounts(List<Map<String, Map<String, Object>>> transactions) {
        Map<Integer, Map<String, Integer>> totalAmounts = new HashMap<>();

        for (Map<String, Map<String, Object>> transaction : transactions) {
            for (String key : transaction.keySet()) {
                Map<String, Object> record = transaction.get(key);
                int userId = (int) record.get("userId");
                Map<String, Integer> userTotals = totalAmounts.getOrDefault(userId, new HashMap<>());
                userTotals.put("data1", userTotals.getOrDefault("data1", 0) + (int) record.getOrDefault("data1", 0));
                userTotals.put("data2", userTotals.getOrDefault("data2", 0) + (int) record.getOrDefault("data2", 0));
                totalAmounts.put(userId, userTotals);
            }
        }

        for (Map<String, Map<String, Object>> transaction : transactions) {
            for (String key : transaction.keySet()) {
                Map<String, Object> record = transaction.get(key);
                int userId = (int) record.get("userId");
                if (!totalAmounts.containsKey(userId)) {
                    totalAmounts.put(userId, new HashMap<>());
                }
            }
        }

        return totalAmounts;
    }
}
