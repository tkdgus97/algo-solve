import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

class Solution {
    public int[] solution(int[][] edges) {
        int[] answer = new int[4];

        HashMap<Integer, Integer> in = new HashMap<>();
        HashMap<Integer, Integer> out = new HashMap<>();

        for (int[] edge : edges) {
            out.put(edge[0], out.getOrDefault(edge[0], 0) + 1);
            in.put(edge[1], in.getOrDefault(edge[1], 0) + 1);
        }

        for (Integer i : out.keySet()) {
            if (out.get(i) > 1) {
                if (!in.containsKey(i)) answer[0] = i;

                if (in.containsKey(i) && in.get(i) > 1) {
                    answer[3] += 1;
                }
            }
        }
        for (Integer i : in.keySet()) {
            if (!out.containsKey(i)) answer[2] += 1;
        }

        answer[1] = out.get(answer[0]) - answer[2] - answer[3];
        return answer;
    }
}