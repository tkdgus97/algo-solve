import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

public class Main {
    private static int result = 0;
    private static String a, b;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();
        a = st.nextToken();

        st = new StringTokenizer(br.readLine());
        b = st.nextToken();

        dfs(b);

        System.out.println(result);
    }

    private static void dfs(String word) {
        int len = word.length();
        if (len == a.length()) {
            if (word.equals(a)) {
                result = 1;
            }
            return;
        }
        if (word.endsWith("A")) {
            dfs(word.substring(0, len - 1));
        }
        if (word.startsWith("B")) {
            dfs(new StringBuilder(word.substring(1)).reverse().toString());
        }
    }

}
