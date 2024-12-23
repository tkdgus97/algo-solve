import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static String origin;
    private static int result = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        origin = st.nextToken();

        st = new StringTokenizer(br.readLine());
        String start = st.nextToken();

        rec(start.length() - 1, start);
        System.out.println(result);
    }

    private static void rec(int n, String s) {
        if (n + 1 == origin.length()) {
            if (s.equals(origin)) {
                result = 1;
            }
            return;
        }

        if (s.charAt(n) == 'A') {
            s = s.substring(0, n);
            rec(n - 1, s);
        } else if (s.charAt(n) == 'B') {
            StringBuilder tmp = new StringBuilder(s.substring(0, n));
            rec(n - 1, tmp.reverse().toString());
        }
    }

    private static int stoi(String v) {
        return Integer.parseInt(v);
    }

}

