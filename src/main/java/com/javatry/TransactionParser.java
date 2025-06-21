package com.javatry;

import java.util.*;

public class TransactionParser {
    public static List<String> normalizeDescriptions(String rawInput) {
        String[] parts = rawInput.split(",");
        List<String> cleaned = new ArrayList<>();
        for (String p : parts) {
            String trimmed = p.trim().replaceAll("\\s+", " ").toLowerCase();
            if (trimmed.contains("at") && trimmed.matches(".*\\d{2}:\\d{2}.*")) {
                cleaned.add(trimmed);
            } else {
                throw new IllegalArgumentException("Invalid transaction format: " + trimmed);
            }
        }
        return cleaned;
    }
}
