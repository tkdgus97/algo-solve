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

    private static boolean areSimilar(String word, String word1) {
        Map<Character, Character> map = new HashMap<>();
        boolean[] check = new boolean[26];

        for (int i = 0; i < word.length(); i++) {
            if (map.containsKey(word.charAt(i))) {
                if (map.get(word.charAt(i)) != word1.charAt(i)) return false;
            } else {
                if (check[word1.charAt(i) - 'a']) return false;
                map.put(word.charAt(i), word1.charAt(i));
                check[word1.charAt(i) - 'a'] = true;
            }
        }

        return true;
    }


    private static int stoi(String v) {
        return Integer.parseInt(v);
    }
}