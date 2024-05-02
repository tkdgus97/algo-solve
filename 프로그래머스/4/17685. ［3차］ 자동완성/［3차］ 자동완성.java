import java.util.HashMap;

class Solution {
    private static class N {
        HashMap<Character, N> child;
        int count;

        public N() {
            child = new HashMap<>();
        }
    }

    private static N root = new N();

    public int solution(String[] words) {
        int answer = 0;
        for (String word : words) {
            add(word);
        }

        for (String word : words) {
            answer += count(word);
        }
        return answer;
    }


    private static void add(String s) {
        N now = root;

        for (int i = 0; i < s.length(); i++) {
            now.child.putIfAbsent(s.charAt(i), new N());
            now = now.child.get(s.charAt(i));
            now.count++;
        }

    }

    private static int count(String s) {
        N now = root;
        int depth = 0;

        for (int i = 0; i < s.length(); i++) {
            depth++;
            now = now.child.get(s.charAt(i));
            if (now.count == 1) {
                return depth;
            }
        }
        return depth;
    }
}