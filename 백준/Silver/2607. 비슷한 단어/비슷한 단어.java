import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static int MOD = 1000000000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        String start = st.nextToken();
        int[] origin = new int[26];
        int originLen = start.length();
        for (int i = 0; i < start.length(); i++) {
            origin[start.charAt(i) - 'A']++;
        }
        int result = 0;
        for (int i = 0; i < n - 1; i++) {
            st = new StringTokenizer(br.readLine());
            String s = st.nextToken();
            int[] inputChar = Arrays.copyOf(origin, 26);

            int cnt = 0;
            int sLen = s.length();
            for (int j = 0; j < s.length(); j++) {
                if (inputChar[s.charAt(j) - 'A'] > 0) {
                    cnt++;
                    inputChar[s.charAt(j) - 'A']--;
                }
            }
            if (sLen == originLen && (originLen == cnt || originLen - 1 == cnt)) {
                result++;
            } else if (sLen - 1 == originLen && originLen == cnt) {
                result++;
            } else if (sLen + 1 == originLen && originLen == cnt + 1) {
                result++;
            }

        }
        System.out.println(result);


    }

}
