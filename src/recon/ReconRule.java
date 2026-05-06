package recon;

import game.GameState;
import strategies.Strategy;
import java.util.List;

public interface ReconRule {
    boolean matches(List<GameState.Turn> history);
    Strategy getCounterStrategy();
}
