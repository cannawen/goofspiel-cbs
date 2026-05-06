import java.util.List;

public class Game {
    public static int play(GameState state) {
        int delta = theirStrategy(state.history);
        return chooseClosestAboveIfPossible(state.myCards, state.currentTrophy + delta % 13);
    }

    static int chooseClosestAboveIfPossible(List<Integer> options, int target) {
        int[] arr = new int[options.size()];
        for (int i = 0; i < options.size(); i++) {
            arr[i] = options.get(i);
        }
        return findClosest(arr, target);
    }

    public static int findClosest(int[] arr, int target) {
        int closest = Integer.MIN_VALUE;
        int smallestAbove = Integer.MAX_VALUE;
        boolean foundAbove = false;

        for (int num : arr) {
            if (num >= target && num < smallestAbove) {
                smallestAbove = num;
                foundAbove = true;
            }
            if (num < target && Math.abs(num - target) < Math.abs(closest - target)) {
                closest = num;
            }
        }
        return foundAbove ? smallestAbove : closest;
    }

    static int theirStrategy(List<GameState.Turn> history) {
        if (history.size() < 2) {
            return 1;
        }
        int[] deltas = new int[history.size()];
        for (int i = 0; i < history.size(); i++) {
            deltas[i] = history.get(i).opponent - history.get(i).trophy;
        }
        return majorityElement(deltas);
    }

    public static int majorityElement(int[] nums) {
        int candidate = nums[0];
        int count = 0;

        for (int num : nums) {
            if (count == 0) candidate = num;
            count += (num == candidate) ? 1 : -1;
        }

        int frequency = 0;
        for (int num : nums) {
            if (num == candidate) frequency++;
        }

        return frequency > nums.length / 2 ? candidate : 0;
    }
}
