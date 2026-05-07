package strategies;

import game.GameConstants;
import java.util.List;

public class TrophyPlusNStrategy implements Strategy {
    private final int n;

    public TrophyPlusNStrategy(int n) {
        this.n = n;
    }

    @Override
    public int chooseCard(List<Integer> myCards, int currentTrophy) {
        return ((currentTrophy + n - 1) % GameConstants.MAX_CARD) + 1;
    }
}
