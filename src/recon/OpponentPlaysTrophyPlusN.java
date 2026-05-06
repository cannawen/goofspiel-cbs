package recon;

import game.GameState;
import strategies.CardTrophyPlusNStrategy;
import strategies.Strategy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OpponentPlaysTrophyPlusN implements ReconRule {
    private int detectedOffset = 1;

    @Override
    public boolean matches(List<GameState.Turn> history) {
        if (history.isEmpty()) return false;
        Map<Integer, Integer> offsetCounts = new HashMap<>();
        for (GameState.Turn turn : history) {
            int offset = turn.opponent - turn.trophy;
            if (offset > 0) {
                offsetCounts.merge(offset, 1, Integer::sum);
            }
        }
        for (Map.Entry<Integer, Integer> entry : offsetCounts.entrySet()) {
            if (entry.getValue() > history.size() / 2) {
                detectedOffset = entry.getKey();
                return true;
            }
        }
        return false;
    }

    @Override
    public Strategy getCounterStrategy() {
        return new CardTrophyPlusNStrategy(detectedOffset + 1);
    }
}
