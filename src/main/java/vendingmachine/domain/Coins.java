package vendingmachine.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import camp.nextstep.edu.missionutils.Randoms;

public class Coins {
	private HashMap<Coin, Integer> coins;

	public Coins() {
		this.coins = new HashMap<>();
	}

	public void addCoin(Coin coin, int count) {
		coins.put(coin, count);
	}

	public void makeCoins(int money) {
		for (Coin coin : Coin.values()) {
			int coinCount = makeRandomCoin(money / coin.getAmount());
			addCoin(coin, coinCount);
			money -= coin.getAmount() * coinCount;
		}
		if (money > 0) {
			convertRestTo10(money);
		}
	}

	private void convertRestTo10(int money) {
		coins.put(Coin.COIN_10, coins.get(Coin.COIN_10) + money / Coin.COIN_10.getAmount());
	}

	public int makeRandomCoin(int maxValue) {
		List<Integer> list = makeRandomCondition(maxValue);
		int count = Randoms.pickNumberInList(list);
		return count;
	}

	public List<Integer> makeRandomCondition(int maxValue) {
		List<Integer> numberList = new ArrayList<>();
		for (int i = 0; i <= maxValue; i++) {
			numberList.add(i);
		}
		return numberList;
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		for (Coin coin : coins.keySet()) {
			stringBuilder.append(coin).append(" - ").append(coin.getAmount()).append("개");
			stringBuilder.append("\n");
		}
		return stringBuilder.toString();
	}
}
