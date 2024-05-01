import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {

    private static class P {
        Map<Integer, P> child;
        boolean isEnd;

        public P() {
            child = new HashMap<>();
        }
    }

    private static P root;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int t = Integer.parseInt(st.nextToken());

        while (t > 0) {
            root = new P();

            st = new StringTokenizer(br.readLine());

            int n = Integer.parseInt(st.nextToken());
            String result = "YES";
            int tmp = n;
            for (int i = 0; i < n; i++) {
                st = new StringTokenizer(br.readLine());
                String s = st.nextToken();

                if (!add(s)) {
                    result = "NO";
                }
            }

            t -= 1;

            sb.append(result).append("\n");
        }
        System.out.println(sb);
    }

    private static boolean add(String v) {
        P now = root;
        boolean flag = false;
        for (int i = 0; i < v.length(); i++) {
            if (now.child.getOrDefault(Character.getNumericValue(v.charAt(i)), null) == null) flag = true;
            now.child.putIfAbsent(Character.getNumericValue(v.charAt(i)), new P());
            now = now.child.get(Character.getNumericValue(v.charAt(i)));

            if (now.isEnd) return false;
        }

        now.isEnd = true;
        return flag;
    }

}
