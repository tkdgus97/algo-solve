import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.lang.reflect.Array;
import java.util.*;

//문제 풀이용
public class Main {
    private static class HomeWork implements Comparable<HomeWork>{
        int d;
        int t;
        public HomeWork(int d, int t) {
            super();
            this.d = d;
            this.t = t;
        }
        @Override
        public int compareTo(HomeWork o) {
            // TODO Auto-generated method stub
            return o.t - this.t;
        }

    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());

        PriorityQueue<HomeWork> pq = new PriorityQueue<>();

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int d = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());
            pq.add(new HomeWork(d, t));
        }

        int startD = pq.peek().t;

        while (!pq.isEmpty()) {
            HomeWork h = pq.poll();

            if (h.t <= startD) {
                startD = h.t - h.d;
            } else {
                startD -= h.d;
            }
        }

        System.out.println(startD);
    }
}
