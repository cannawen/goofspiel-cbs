package strategies;

import game.GameConstants;
import java.util.List;
import java.util.Random;

public class FuzzingStrategy implements Strategy {
    private static final double FUZZ_PROBABILITY = 0.20;
    private final Strategy inner;
    private final Random random;

    public FuzzingStrategy(Strategy inner) {
        this.inner = inner;
        this.random = new Random();
    }

    @Override
    public int chooseCard(List<Integer> myCards, int currentTrophy) {
        int target = inner.chooseCard(myCards, currentTrophy);
        if (random.nextDouble() >= FUZZ_PROBABILITY) {
            return target;
        }
        boolean lowTrophy = currentTrophy <= GameConstants.MEDIAN;
        int fuzzed = lowTrophy ? target - 1 : target + 1;
        return Math.max(GameConstants.MIN_CARD, Math.min(GameConstants.MAX_CARD, fuzzed));
    }
}
