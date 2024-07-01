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

        int n = Integer.parseInt(st.nextToken());

        ArrayDeque<Integer> deque = new ArrayDeque<>();

        long result = 0;
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int now = Integer.parseInt(st.nextToken());
            max = Math.max(max, now);
            if (deque.isEmpty()) {
                deque.add(now);
            }
            else {
                int peek = deque.peekLast();
                if (now > peek) {
                    deque.poll();
                    deque.add(now);
                    result += (now - peek);
                } else if (now < peek) {
                    deque.poll();
                    deque.add(now);
                }
            }
        }
        
        result += (max - deque.poll());
        System.out.println(result);
    }


}
