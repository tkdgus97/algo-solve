import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
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
            int k = stoi(st.nextToken());
            //최소값
            PriorityQueue<Integer> pq1 = new PriorityQueue<>();
            //최대값
            PriorityQueue<Integer> pq2 = new PriorityQueue<>((o1, o2) -> Integer.compare(o2,o1));
            HashMap<Integer, Integer> map = new HashMap<>();
            int size = 0;
            for (int j = 0; j < k; j++) {
                st = new StringTokenizer(br.readLine());
                String oper = st.nextToken();
                int v = stoi(st.nextToken());

                if (oper.equals("I")) {
                    pq1.add(v);
                    pq2.add(v);
                    map.put(v, map.getOrDefault(v, 0) + 1);
                    size++;
                } else {
                    if (size > 0) {
                        if (v == 1) {
                            poll(pq2, map);
                        } else {
                            poll(pq1, map);
                        }
                        size--;
                    }
                }
            }
            String result = "EMPTY";
            if (size > 0) {
                int min = 0;
                int max = 0;
                while (!pq1.isEmpty()) {
                    int v = pq1.poll();
                    if (map.get(v) > 0) {
                        min = v;
//                        map.put(v, map.get(v) - 1);
                        break;
                    }
                }
                while (!pq2.isEmpty()) {
                    int v = pq2.poll();
                    if (map.get(v) > 0) {
                        max = v;
                        map.put(v, map.get(v) - 1);
                        break;
                    }
                }
                result = max + " " + min;
            }
            sb.append(result).append("\n");
        }
        System.out.print(sb);
    }

    private static void poll(PriorityQueue<Integer> pq, HashMap<Integer, Integer> map) {
        while (!pq.isEmpty()) {
            int v = pq.poll();
            if (map.get(v) > 0) {
                map.put(v, map.get(v) - 1);
                break;
            }
        }
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}
