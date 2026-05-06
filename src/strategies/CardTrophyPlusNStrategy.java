package strategies;

import java.util.List;

public class CardTrophyPlusNStrategy implements Strategy {
    private final int n;

    public CardTrophyPlusNStrategy(int n) {
        this.n = n;
    }

    @Override
    public int chooseCard(List<Integer> myCards, int currentTrophy) {
        return currentTrophy + n;
    }
}
