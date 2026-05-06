package strategies;

import java.util.List;

public class CardPlusOneStrategy implements Strategy {
    @Override
    public int chooseCard(List<Integer> myCards, int currentTrophy) {
        if (currentTrophy == 13) {
            return myCards.stream().min(Integer::compareTo).get();
        }
        int target = currentTrophy + 1;
        if (myCards.contains(target)) {
            return target;
        }
        return myCards.get(0);
    }
}
