import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int n = stoi(br.readLine());

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            for (int j = 0; j < n; j++) {
                int v = stoi(st.nextToken());
                if (pq.size() < n) pq.add(v);
                else {
                    if (pq.peek() < v) {
                        pq.poll();
                        pq.add(v);
                    }
                }
            }
        }
//
//        for (int i = 0; i < n - 1; i++) {
//            pq.poll();
//        }

        System.out.println(pq.poll());
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}
