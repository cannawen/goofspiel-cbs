package recon;

import game.GameState;
import strategies.Strategy;
import strategies.TrophyPlusNStrategy;
import java.util.List;

public class OpponentStrategyUnknown implements ReconRule {
    @Override
    public boolean matches(List<GameState.Turn> history) {
        return true;
    }

    @Override
    public Strategy getCounterStrategy() {
        return new TrophyPlusNStrategy(0);
    }
}
