import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        while (true) {
            st = new StringTokenizer(br.readLine());
            String s = st.nextToken();
            if (s.equals("*")) break;
            char[] arr = s.toCharArray();
            int up = s.length() - 1;
            boolean f = false;
            for (int i = 1; i <= up; i++) {
                HashSet<String> set = new HashSet<>();
                boolean flag = false;
                for (int j = 0; j < s.length() - i; j++) {
                    String tmp = Character.toString(arr[j]) + Character.toString(arr[j + i]);
                    if (set.contains(tmp)) {
                        flag = true;
                        break;
                    }
                    set.add(tmp);
                }

                if (flag) {
                    f = true;
                    sb.append(s).append(" is NOT surprising.").append("\n");
                    break;
                }
            }

            if (!f) {
                sb.append(s).append(" is surprising.").append("\n");
            }
        }

        System.out.println(sb);


    }

    private static int stoi(String v) {
        return Integer.parseInt(v);
    }
}