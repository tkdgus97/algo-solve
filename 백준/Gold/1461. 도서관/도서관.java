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
        int m = stoi(st.nextToken());

        PriorityQueue<Integer> p1 = new PriorityQueue<>((o1, o2) -> {
            return o2 - o1;
        });

        PriorityQueue<Integer> p2 = new PriorityQueue<>((o1, o2) -> {
            return o2 - o1;
        });
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            int v = stoi(st.nextToken());
            if (v > 0) {
                p1.add(v);
            } else {
                p2.add(Math.abs(v));
            }
        }

        int max = 0;
        if (p1.isEmpty()) {
            max = p2.peek();
        } else if (p2.isEmpty()) {
            max = p1.peek();
        } else {
            max = Math.max(p1.peek(), p2.peek());
        }
        int result = 0;
        while (!p1.isEmpty()) {
            int tmp = p1.poll();
            for (int i = 0; i < m - 1; i++) {
                p1.poll();
                if (p1.isEmpty()) break;
            }
            result += tmp * 2;
        }

        while (!p2.isEmpty()) {
            int tmp = p2.poll();
            for (int i = 0; i < m - 1; i++) {
                p2.poll();
                if (p2.isEmpty()) break;
            }
            result += tmp * 2;
        }

        result -= max;

        System.out.println(result);
    }

    private static int stoi(String v) {
        return Integer.parseInt(v);
    }
}