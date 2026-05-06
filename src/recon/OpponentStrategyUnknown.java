package recon;

import game.GameState;
import strategies.CardEqualTrophyStrategy;
import strategies.Strategy;
import java.util.List;

public class OpponentStrategyUnknown implements ReconRule {
    @Override
    public boolean matches(List<GameState.Turn> history) {
        return true;
    }

    @Override
    public Strategy getCounterStrategy() {
        return new CardEqualTrophyStrategy();
    }
}
