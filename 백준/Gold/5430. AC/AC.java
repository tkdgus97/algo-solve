import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        StringBuilder sb = new StringBuilder();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int TC = stoi(st.nextToken());

        for (int i = 0; i < TC; i++) {
            st = new StringTokenizer(br.readLine());

            String p = st.nextToken();
            int n = stoi(br.readLine());

            String s = br.readLine();
            String[] arr = s.substring(1, s.length() - 1).split(",");
            int[] num = new int[n];

            for (int j = 0; j < n; j++) {
                num[j] = stoi(arr[j]);
            }

            sb.append(sol(p, num, n));
        }
        System.out.print(sb);
    }

    private static String sol(String p, int[] num, int n) {
        int l = 0;
        int r = n;
        boolean reverse = false;
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < p.length(); j++) {
            if (p.charAt(j) == 'R') {
                reverse = !reverse;
            } else {
                if (!reverse) {
                    l++;
                } else {
                    r--;
                }
                if (l > r) {
                    return sb.append("error").append("\n").toString();
                }
            }
        }

        sb.append("[");
        if (!reverse) {
            for (int j = l; j <= r - 1; j++) {
                sb.append(num[j]);
                if (j != r - 1) sb.append(",");
            }
        } else {
            for (int j = r - 1; j >= l; j--) {
                sb.append(num[j]);
                if (j != l) sb.append(",");
            }
        }
        sb.append("]").append("\n");
        return sb.toString();
    }


    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}
