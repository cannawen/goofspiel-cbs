import java.util.List;
import strategies.Strategy;
import strategies.CardPlusOneStrategy;
import strategies.CardEqualTrophyStrategy;

public class Game {
    public static int play(GameState state) {
        Strategy strategy = selectCounterStrategy(state);
        return strategy.chooseCard(state.myCards, state.currentTrophy);
    }

    static boolean opponentMatchesTrophy(List<GameState.Turn> history) {
        if (history.isEmpty()) return false;
        int matches = 0;
        for (GameState.Turn turn : history) {
            if (turn.opponent == turn.trophy) matches++;
        }
        return matches > history.size() / 2;
    }

    static Strategy selectCounterStrategy(GameState state) {
        if (opponentMatchesTrophy(state.history)) {
            return new CardPlusOneStrategy();
        }
        return new CardEqualTrophyStrategy();
    }
}
