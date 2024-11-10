import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = stoi(st.nextToken());
        int m = stoi(st.nextToken());

        st = new StringTokenizer(br.readLine());
        String w = st.nextToken();
        st = new StringTokenizer(br.readLine());
        String s = st.nextToken();

        HashMap<Character, Integer> wMap = new HashMap<>();
        HashMap<Character, Integer> slideMap = new HashMap<>();

        for (int i = 0; i < n; i++) {
            wMap.put(w.charAt(i), wMap.getOrDefault(w.charAt(i), 0) + 1);
        }

        int l = 0;
        int cnt = 0;
        int result = 0;
        for (int r = 0; r < m; r++) {
            if (r >= n) {
                if (wMap.containsKey(s.charAt(l)) && slideMap.get(s.charAt(l)) <= wMap.get(s.charAt(l))) {
                    cnt--;
                }
                slideMap.put(s.charAt(l), slideMap.get(s.charAt(l)) - 1);
                l++;
            }
            slideMap.put(s.charAt(r), slideMap.getOrDefault(s.charAt(r), 0) + 1);
            if (wMap.containsKey(s.charAt(r)) && slideMap.get(s.charAt(r)) <= wMap.get(s.charAt(r))) {
                cnt++;
            }

            if (cnt == n) result++;
        }
        System.out.println(result);
    }

    private static int stoi(String v) {
        return Integer.parseInt(v);
    }

}
