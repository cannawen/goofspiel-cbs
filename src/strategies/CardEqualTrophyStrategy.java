package strategies;

import java.util.List;

public class CardEqualTrophyStrategy implements Strategy {
    @Override
    public int chooseCard(List<Integer> myCards, int currentTrophy) {
        return currentTrophy;
    }
}
