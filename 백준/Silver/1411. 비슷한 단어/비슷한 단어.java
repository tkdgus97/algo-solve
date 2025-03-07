import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLOutput;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int n = stoi(st.nextToken());

        String[] words = new String[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            words[i] = st.nextToken();
        }
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if (areSimilar(words[i], words[j])) {
                    cnt++;
                }
            }
        }
        System.out.println(cnt);
    }

    private static boolean areSimilar(String word1, String word2) {
        Map<Character, Character> mapping = new HashMap<>();
        Set<Character> used = new HashSet<>();

        for (int k = 0; k < word1.length(); k++) {
            char c1 = word1.charAt(k);
            char c2 = word2.charAt(k);

            if (mapping.containsKey(c1)) {
                if (mapping.get(c1) != c2) {
                    return false; // 기존 매핑과 다르면 실패
                }
            } else {
                if (used.contains(c2)) {
                    return false; // 이미 다른 문자가 c2로 매핑됨
                }
                mapping.put(c1, c2);
                used.add(c2);
            }
        }
        return true;
    }

    private static int stoi(String v) {
        return Integer.parseInt(v);
    }
}