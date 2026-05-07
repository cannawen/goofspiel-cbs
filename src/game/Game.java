package game;

import java.util.Arrays;
import java.util.List;
import strategies.Strategy;
import strategies.ClosestCardStrategy;
import recon.ReconRule;
import recon.OpponentMatchesTrophy;
import recon.OpponentPlaysTrophyPlusN;
import recon.OpponentHighTrophyPattern;
import recon.OpponentStrategyUnknown;

public class Game {
    private static final List<ReconRule> RECON_RULES = Arrays.asList(
        new OpponentMatchesTrophy(),
        new OpponentPlaysTrophyPlusN(),
        new OpponentHighTrophyPattern()
    );

    public static int play(GameState state) {
        Strategy strategy = new ClosestCardStrategy(selectCounterStrategy(state));
        return strategy.chooseCard(state.myCards, state.currentTrophy);
    }

    static Strategy selectCounterStrategy(GameState state) {
        for (ReconRule rule : RECON_RULES) {
            if (rule.matches(state.history)) {
                return rule.getCounterStrategy();
            }
        }

        return new OpponentStrategyUnknown().getCounterStrategy();
    }
}
