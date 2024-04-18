import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    private static List<Integer>[] trees;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        String s = st.nextToken();
        int[] alpha = new int[26];
        int rp = 0;
        int diff = 0;
        int result = 0;
        for (int i = 0; i < s.length(); i++) {
            while (rp < s.length()) {
                alpha[s.charAt(rp) - 'a']++;
                if (alpha[s.charAt(rp) - 'a'] == 1) {
                    diff += 1;
                }
                rp++;
                if (diff > n) {
                    rp--;
                    alpha[s.charAt(rp) - 'a']--;
                    if (alpha[s.charAt(rp) - 'a'] == 0) {
                        diff--;
                    }
                    break;
                }
            }
            alpha[s.charAt(i) - 'a']--;
            if (alpha[s.charAt(i) - 'a'] == 0) diff--;
            result = Math.max(result, rp - i);
        }

        System.out.println(result);
    }


}
