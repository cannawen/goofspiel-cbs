package strategies;

import java.util.List;

public class CardEqualTrophyStrategy implements Strategy {
    @Override
    public int chooseCard(List<Integer> myCards, int currentTrophy) {
        if (myCards.contains(currentTrophy)) {
            return currentTrophy;
        }
        return myCards.get(0);
    }
}
