import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int n, k;
    private static boolean[] visit = new boolean[26];
    private static String[] words;
    private static int result = 0;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        n = Integer.parseInt(st.nextToken());
        k = Integer.parseInt(st.nextToken());

        words = new String[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String s = st.nextToken();
            s = s.replace("anta", "");
            s = s.replace("tica", "");
            words[i] = s;
        }

        if (k == 26) {
            result = n;
        } else if (k >= 5) {
            visit['a' - 'a'] = true;
            visit['n' - 'a']  = true;
            visit['t' - 'a']  = true;
            visit['i' - 'a']  = true;
            visit['c' - 'a']  = true;
            rec(0, 0);
        } else result = 0;
        System.out.println(result);

    }

    private static void rec(int l, int now) {
        if (l == k - 5) {
            int cnt = 0;
            for (String word : words) {
                boolean f = true;
                for (int i = 0; i < word.length(); i++) {
                    if (!visit[word.charAt(i) - 'a']) {
                        f = false;
                        break;
                    }
                }
                if (f) cnt++;
            }
            result = Math.max(cnt, result);
            return;
        }

        for (int i = now; i < 26; i++) {
            if (!visit[i]) {
                visit[i] = true;
                rec(l + 1, i);
                visit[i] = false;
            }
        }

    }
}
