import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());

        int n = stoi(st.nextToken());
        String[] strs = new String[n];

        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            strs[i] = st.nextToken();
        }
        int max = 0;
        String r1 = "", r2 = "";
        for (int i = 0; i < n - 1; i++) {
            String s1 = strs[i];
            for (int j = i + 1; j < n; j++) {
                String s2 = strs[j];
                int cnt = 0;
                for (int k = 0; k < Math.min(s1.length(), s2.length()); k++) {
                    if (s1.charAt(k) != s2.charAt(k)) break;
                    cnt++;
                }
                if (max < cnt) {
                    r1 = s1;
                    r2 = s2;
                    max = cnt;
                }
            }
        }
        System.out.println(r1);
        System.out.println(r2);
    }

    private static int stoi(String v) {
        return Integer.parseInt(v);
    }

}