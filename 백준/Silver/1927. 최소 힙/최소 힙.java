import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        st = new StringTokenizer(br.readLine());

        int n = stoi(st.nextToken());
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int oper = stoi(st.nextToken());
            if (pq.isEmpty() && oper == 0) {
                sb.append(0).append("\n");
            } else if (!pq.isEmpty() && oper == 0) {
                sb.append(pq.poll()).append("\n");
            } else {
                pq.add(oper);
            }
        }
        System.out.println(sb);
    }

    private static int stoi(String v) {
        return Integer.parseInt(v);
    }

}
