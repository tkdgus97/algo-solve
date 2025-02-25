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
        long x = stoi(st.nextToken());
        long y = stoi(st.nextToken());
        long origin = (y * 100) / x;
        if (origin >= 99) {
            System.out.println(-1);
            return;
        }
        long l = 0;
        long r = x;

        while (l <= r) {
            long mid = (l + r) / 2;
            long rate = ((y + mid) * 100) / (x + mid);
            if (rate > origin) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }
        System.out.println(l);
    }

    private static int stoi(String v) {
        return Integer.parseInt(v);
    }
}