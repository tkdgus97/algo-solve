import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    private static class Water {
        int start;
        int end;

        Water(int s, int e) {
            this.start = s;
            this.end = e;
        }
    }
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = stoi(st.nextToken());
        int l = stoi(st.nextToken());

        int result = 0;
        List<Water> waters = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());

            int s = stoi(st.nextToken());
            int e = stoi(st.nextToken());

            waters.add(new Water(s,e));
        }

        Collections.sort(waters, (o1, o2) -> {
            return o1.start - o2.start;
        });

        int prevEnd = 0;
        for (Water water : waters) {
            if (prevEnd >= water.end) continue;
            if (prevEnd != 0 && prevEnd >= water.start) {
                water.start = prevEnd + 1;
            }
            int width = water.end - water.start;
            int tmpCnt = (width / l);
            if (width % l != 0) {
                tmpCnt += 1;
            }
            result += tmpCnt;
            prevEnd = water.start + (tmpCnt * l) - 1;
        }
        System.out.println(result);
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}
