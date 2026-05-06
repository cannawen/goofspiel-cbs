package game;

import java.util.Arrays;
import java.util.List;
import strategies.Strategy;
import strategies.CardEqualTrophyStrategy;
import recon.ReconRule;
import recon.OpponentMatchesTrophy;

public class Game {
    private static final List<ReconRule> RECON_RULES = Arrays.asList(
        new OpponentMatchesTrophy()
    );

    public static int play(GameState state) {
        Strategy strategy = selectCounterStrategy(state);
        return strategy.chooseCard(state.myCards, state.currentTrophy);
    }

    static Strategy selectCounterStrategy(GameState state) {
        for (ReconRule rule : RECON_RULES) {
            if (rule.matches(state.history)) {
                return rule.getCounterStrategy();
            }
        }
        return new CardEqualTrophyStrategy();
    }
}
