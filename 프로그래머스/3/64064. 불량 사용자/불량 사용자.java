import java.util.Arrays;
import java.util.HashSet;

class Solution {
    private static HashSet<String> set = new HashSet<>();
    private static boolean[] visit;

    public int solution(String[] user_id, String[] banned_id) {
        int answer = 0;
        visit = new boolean[user_id.length];
        func(0, new String[banned_id.length], user_id, banned_id);
        return set.size();
    }

    private static void func(int L, String[] list, String[] user_id, String[] banned_id) {
        if (L == banned_id.length) {
            String[] tmp = Arrays.copyOf(list, list.length);

            for (int i = 0; i < banned_id.length; i++) {
                if (tmp[i].length() != banned_id[i].length()) return;
                for (int j = 0; j < banned_id[i].length(); j++) {
                    if (banned_id[i].charAt(j) == '*') continue;
                    if (tmp[i].charAt(j) != banned_id[i].charAt(j)) {
                        return;
                    }
                }
            }

            StringBuilder sb = new StringBuilder();
            Arrays.sort(tmp);

            for (int i = 0; i < tmp.length; i++) {
                sb.append(tmp[i]);
            }
            set.add(sb.toString());
            return;
        }

        for (int i = 0; i < user_id.length; i++) {
            if (!visit[i]) {
                visit[i] = true;
                list[L] = user_id[i];
                func(L + 1, list, user_id, banned_id);
                visit[i] = false;
            }
        }
    }

}