import java.util.*;
class Solution {
    private static int max = Integer.MIN_VALUE;
    private static HashMap<String, Integer> map;
    private static int[] orderCnt = new int[26];

    public String[] solution(String[] orders, int[] course) {
        int idx = 0;
        for (String order : orders) {
            for (int i = 0; i < order.length(); i++) {
                orderCnt[order.charAt(i) - 'A']++;
            }
        }
        List<String> result = new ArrayList<>();
        for (int n : course) {
            map = new HashMap<>();
            max = Integer.MIN_VALUE;
            for (int i = 0; i < orders.length; i++) {
               char[] arr = orders[i].toCharArray();
                Arrays.sort(arr);
                rec(0, 0, n, arr, "");
            }
             if (max < 2) {
                continue;
            }
            for (String s : map.keySet()) {
                if (map.get(s) == max) result.add(s);
            }
        }
        Collections.sort(result, (o1, o2) -> {
            return o1.compareTo(o2);
        });

        String[] answer = new String[result.size()];

        for (String s : result) {
            answer[idx++] = s;
        }
        
        return answer;
    }

    private static void rec(int start, int L, int n, char[] order, String combi) {
        if (L == n) {
            map.put(combi, map.getOrDefault(combi, 0) + 1);

            max = Math.max(max, map.get(combi));
            return;
        }

        for (int i = start; i < order.length; i++) {
            if (orderCnt[order[i] - 'A'] < 2) continue;
            rec(i + 1, L + 1, n, order, combi + order[i]);
        }
    }
}