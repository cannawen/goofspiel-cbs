package recon;

import game.GameState;
import strategies.Strategy;
import strategies.TrophyPlusNStrategy;
import java.util.List;

public class OpponentMatchesTrophy implements ReconRule {
    @Override
    public boolean matches(List<GameState.Turn> history) {
        if (history.isEmpty()) return false;
        int matches = 0;
        for (GameState.Turn turn : history) {
            if (turn.opponent == turn.trophy) matches++;
        }
        return matches > history.size() / 2;
    }

    @Override
    public Strategy getCounterStrategy() {
        return new TrophyPlusNStrategy(1);
    }
}
