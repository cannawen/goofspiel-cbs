package recon;

import game.GameConstants;
import game.GameState;
import strategies.Strategy;
import strategies.TrophyPlusNStrategy;
import java.util.List;

// Detects whether the opponent plays high cards or low cards in response to high-value trophies.
public class OpponentHighTrophyPattern implements ReconRule {
    private static final int MEDIAN = GameConstants.MEDIAN;
    private static final int MIN_HIGH_TROPHY_TURNS = 3;

    private boolean highForHigh = true;

    @Override
    public boolean matches(List<GameState.Turn> history) {
        int highForHighCount = 0;
        int lowForHighCount = 0;

        for (GameState.Turn turn : history) {
            if (turn.trophy > MEDIAN) {
                if (turn.opponent > MEDIAN) {
                    highForHighCount++;
                } else {
                    lowForHighCount++;
                }
            }
        }

        int totalHighTrophyTurns = highForHighCount + lowForHighCount;
        if (totalHighTrophyTurns < MIN_HIGH_TROPHY_TURNS) return false;

        int majorityThreshold = totalHighTrophyTurns / 2;
        if (highForHighCount > majorityThreshold) {
            highForHigh = true;
            return true;
        }
        if (lowForHighCount > majorityThreshold) {
            highForHigh = false;
            return true;
        }
        return false;
    }

    @Override
    public Strategy getCounterStrategy() {
        // High for high: opponent bids high on valuable trophies, so outbid by 1
        // Low for high: opponent bids low on valuable trophies, so beat their low bid cheaply
        return highForHigh ? new TrophyPlusNStrategy(1) : new TrophyPlusNStrategy(-5);
    }
}
