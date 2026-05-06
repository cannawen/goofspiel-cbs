package game;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;

public class GameState {

    public static class Turn {
        public final int me;
        public final int opponent;
        public final int trophy;

        Turn(int me, int opponent, int trophy) {
            this.me = me;
            this.opponent = opponent;
            this.trophy = trophy;
        }
    }

    public final List<Integer> myCards;
    public final List<Integer> opponentCards;
    public final List<Integer> trophyCards;
    public final int currentTrophy;
    public final List<Turn> history;

    public GameState(JSONObject obj) {
        JSONObject playerCards = obj.getJSONObject("player-cards");
        this.myCards = toList(playerCards.getJSONArray("me"));
        this.opponentCards = toList(playerCards.getJSONArray("opponent"));
        this.trophyCards = toList(obj.getJSONArray("trophy-cards"));
        this.currentTrophy = obj.getInt("current-trophy");

        JSONArray historyArr = obj.getJSONArray("history");
        this.history = new ArrayList<>(historyArr.length());
        for (int i = 0; i < historyArr.length(); i++) {
            JSONObject turn = historyArr.getJSONObject(i);
            this.history.add(new Turn(
                turn.getInt("me"),
                turn.getInt("opponent"),
                turn.getInt("trophy")
            ));
        }
    }

    private static List<Integer> toList(JSONArray arr) {
        List<Integer> list = new ArrayList<>(arr.length());
        for (int i = 0; i < arr.length(); i++) {
            list.add(arr.getInt(i));
        }
        return list;
    }
}
