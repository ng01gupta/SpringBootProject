package com.example.rewardpoints;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/rewards")
public class RewardController {

    @Autowired
    private RewardCalculatorService rewardCalculatorService;

    @PostMapping("/calculate")
    public Map<String, Map<java.time.Month, Integer>> calculateRewards(@RequestBody List<Transaction> transactions) {
        return rewardCalculatorService.calculateRewards(transactions);
    }
}