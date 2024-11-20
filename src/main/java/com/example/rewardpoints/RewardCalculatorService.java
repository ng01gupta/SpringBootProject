package com.example.rewardpoints;

import org.springframework.stereotype.Service;

import java.time.Month;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RewardCalculatorService {

    public Map<String, Map<Month, Integer>> calculateRewards(List<Transaction> transactions) {
        Map<String, Map<Month, Integer>> customerRewards = new HashMap<>();

        for (Transaction transaction : transactions) {
            String customerId = transaction.getCustomerId();
            Month month = transaction.getDate().getMonth();
            int points = calculatePoints(transaction.getAmount());

            customerRewards
                    .computeIfAbsent(customerId, k -> new HashMap<>())
                    .merge(month, points, Integer::sum);
        }

        return customerRewards;
    }

    private int calculatePoints(double amount) {
        int points = 0;
        if (amount > 100) {
            points += (amount - 100) * 2;
            amount = 100;
        }
        if (amount > 50) {
            points += (amount - 50);
        }
        return points;
    }
}
