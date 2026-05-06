import org.json.JSONObject;

public class Main {
    static public void main(String[] Argv) {
        String input = new java.util.Scanner(System.in).useDelimiter("\\A").next();
        JSONObject obj = new JSONObject(input);
        System.out.println(main2(obj));
    }

    static String main2(JSONObject obj) {
        String pingValue = obj.optString("ping");

        if (pingValue != null && pingValue.length() > 0) {
            return "{\"pong\":\"" + pingValue + "\"}";
        }

        GameState state = new GameState(obj);
        return String.valueOf(Game.play(state));
    }
}
