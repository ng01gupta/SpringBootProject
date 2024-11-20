
package com.example.rewardpoints;

import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RewardCalculatorServiceTest {

	private final RewardCalculatorService service = new RewardCalculatorService();

	@Test
	public void testCalculateRewards() {
		List<Transaction> transactions = List.of(
				new Transaction() {{
					setCustomerId("C001");
					setAmount(120);
					setDate(LocalDate.of(2024, 1, 10));
				}},
				new Transaction() {{
					setCustomerId("C001");
					setAmount(75);
					setDate(LocalDate.of(2024, 1, 20));
				}},
				new Transaction() {{
					setCustomerId("C002");
					setAmount(150);
					setDate(LocalDate.of(2024, 2, 15));
				}},
				new Transaction() {{
					setCustomerId("C002");
					setAmount(60);
					setDate(LocalDate.of(2024, 2, 25));
				}},
				new Transaction() {{
					setCustomerId("C003");
					setAmount(200);
					setDate(LocalDate.of(2024, 3, 05));
				}},
				new Transaction() {{
					setCustomerId("C003");
					setAmount(85);
					setDate(LocalDate.of(2024, 3, 12));
				}}
		);

		Map<String, Map<java.time.Month, Integer>> rewards = service.calculateRewards(transactions);
		assertEquals(115, rewards.get("C001").get(java.time.Month.JANUARY));
		assertEquals(160, rewards.get("C002").get(java.time.Month.FEBRUARY));
		assertEquals(285, rewards.get("C003").get(java.time.Month.MARCH));
	}
}