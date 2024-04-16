import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;


public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int n = Integer.parseInt(st.nextToken());

        int[] p = new int[n];
        int[] s = new int[n];
        int[] cards = new int[n];
        int[] tmpCard = new int[n];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            p[i] = Integer.parseInt(st.nextToken());
            cards[i] = i % 3;
        }
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            s[i] = Integer.parseInt(st.nextToken());
        }
        int time = 0;

        while (true) {
            int eqCnt = 0;
            int brCnt = 0;
            for (int i = 0; i < n; i++) {
                if (cards[i] == p[i]) eqCnt++;
                if (cards[i] == i % 3) brCnt++;
            }

            if (eqCnt == n) {
                break;
            }
            if (brCnt == n && time > 0) {
                time = -1;
                break;
            }

            tmpCard = new int[n];
            for (int i = 0; i < n; i++) {
                tmpCard[i] = cards[s[i]];
            }

            cards = tmpCard;
            time++;
        }
        System.out.println(time);
    }


}
