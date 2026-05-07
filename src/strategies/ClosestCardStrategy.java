package strategies;

import java.util.List;

public class ClosestCardStrategy implements Strategy {
    private final Strategy inner;

    public ClosestCardStrategy(Strategy inner) {
        this.inner = inner;
    }

    @Override
    public int chooseCard(List<Integer> myCards, int currentTrophy) {
        int target = inner.chooseCard(myCards, currentTrophy);
        if (myCards.contains(target)) {
            return target;
        }
        boolean preferHigher = currentTrophy > 7;
        return myCards.stream()
            .min((a, b) -> {
                int distA = Math.abs(a - target);
                int distB = Math.abs(b - target);
                if (distA != distB) return Integer.compare(distA, distB);
                return preferHigher ? Integer.compare(b, a) : Integer.compare(a, b);
            })
            .get();
    }
}
