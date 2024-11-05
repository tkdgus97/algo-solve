
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static class Class{
        int start;
        int end;
        public Class(int start, int end) {
            this.start = start;
            this.end = end;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());
        int n = Integer.parseInt(st.nextToken());
        List<Class> classes = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int no = stoi(st.nextToken());
            int s = stoi(st.nextToken());
            int e = stoi(st.nextToken());
            classes.add(new Class(s,e));
        }

        classes.sort((o1, o2) -> {
            if (o1.start == o2.start) {
                return o1.end - o2.end;
            }
            return o1.start - o2.start;
        });

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for (Class c : classes) {
            if (pq.isEmpty()) {
                pq.add(c.end);
                continue;
            }
            if (c.start < pq.peek()) {
                pq.add(c.end);
            } else {
                pq.poll();
                pq.add(c.end);
            }
        }

        System.out.println(pq.size());
    }

    private static int stoi(String v) {
        return Integer.parseInt(v);
    }

}
