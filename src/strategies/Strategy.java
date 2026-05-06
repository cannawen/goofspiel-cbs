package strategies;

import java.util.List;

public interface Strategy {
    int chooseCard(List<Integer> myCards, int currentTrophy);
}
