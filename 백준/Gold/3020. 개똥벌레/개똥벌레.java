import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int H = Integer.parseInt(st.nextToken());

        List<Integer> up = new ArrayList<>();
        List<Integer> down = new ArrayList<>();
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int l = Integer.parseInt(st.nextToken());
            if (i % 2 == 0) down.add(l);
            else up.add(l);
        }

        Collections.sort(up);
        Collections.sort(down);

        int min = Integer.MAX_VALUE;
        int count = 0;
        for (int i = 1; i <= H; i++) {
            int c = bnSearch(i, down) + bnSearch(H - i + 1, up);
            if (min == c) {
                count += 1;
            }

            if (min > c) {
                min = c;
                count = 1;
            }

        }
        System.out.println(min + " " + count);
    }

    private static int bnSearch(int h, List<Integer> len) {
        int lt = 0;
        int rt = len.size();

        while (lt < rt) {
            int mid = (lt + rt) / 2;

            if (len.get(mid) >= h) {
                rt = mid;
            } else {
                lt = mid + 1;
            }
        }
        return len.size() - rt;
    }
}
