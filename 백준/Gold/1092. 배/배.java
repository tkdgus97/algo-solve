import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = Integer.parseInt(st.nextToken());
        st = new StringTokenizer(br.readLine());

        List<Integer> crains = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            crains.add(Integer.parseInt(st.nextToken()));
        }
        st = new StringTokenizer(br.readLine());
        int m = Integer.parseInt(st.nextToken());

        List<Integer> boxes = new ArrayList<>();
        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < m; i++) {
            boxes.add(Integer.parseInt(st.nextToken()));
        }

        Collections.sort(crains, Collections.reverseOrder());
        Collections.sort(boxes, Collections.reverseOrder());
        if (crains.get(0) < boxes.get(0)) {
            System.out.println(-1);
            return;
        }
        int time = 0;
        while (!boxes.isEmpty()) {
            int craIdx = 0;
            int boxIdx = 0;
            while (craIdx < n) {
                if (boxIdx == boxes.size()) break;
                if (crains.get(craIdx) >= boxes.get(boxIdx)) {
                    boxes.remove(boxIdx);
                    craIdx++;
                } else {
                    boxIdx++;
                }
            }
            time++;
        }

        System.out.println(time);
    }
}
