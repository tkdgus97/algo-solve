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
        int[] v = new int[26];

        for(int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            String s = st.nextToken();
            for (int j = 0; j < s.length(); j++) {
                v[s.charAt(j) - 'A'] += (int)Math.pow(10, s.length() - j - 1);
            }
        }

        Arrays.sort(v);
        int num = 9;
        int result = 0;
        for (int i = 25; i >= 16 ; i--) {
            result += (v[i] * num--);
        }

        System.out.println(result);
    }

    private static int stoi(String v) {
        return Integer.parseInt(v);
    }
}