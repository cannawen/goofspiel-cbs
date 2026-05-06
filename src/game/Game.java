package game;

import java.util.Arrays;
import java.util.List;
import strategies.Strategy;
import recon.ReconRule;
import recon.OpponentMatchesTrophy;
import recon.OpponentStrategyUnknown;

public class Game {
    private static final List<ReconRule> RECON_RULES = Arrays.asList(
        new OpponentMatchesTrophy()
    );

    public static int play(GameState state) {
        Strategy strategy = selectCounterStrategy(state);
        int card = strategy.chooseCard(state.myCards, state.currentTrophy);

        if (state.myCards.contains(card)) {
            return card;
        } else {
            return state.myCards.get(0);
        }
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
