import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = stoi(st.nextToken());
        int m = stoi(st.nextToken());


        int packMin = Integer.MAX_VALUE;
        int oneMin = Integer.MAX_VALUE;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());
            int p = stoi(st.nextToken());
            int o = stoi(st.nextToken());

            packMin = Math.min(packMin, p);
            oneMin = Math.min(oneMin, o);
        }

        int result = 0;
        if (oneMin*6 < packMin) {
            result = oneMin*n;
        } else {
            int packPrice = packMin*(n / 6);
            int onePrice = Math.min(oneMin * (n % 6), packMin);
            result = packPrice + onePrice;
        }

        System.out.println(result);
    }

    private static int stoi(String v) {
        return Integer.parseInt(v);
    }

}
