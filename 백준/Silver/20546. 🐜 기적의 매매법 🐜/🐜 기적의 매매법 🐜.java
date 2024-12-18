import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    private static final int MAX_DAY = 14;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        st = new StringTokenizer(br.readLine());

        int m = stoi(st.nextToken());
        st = new StringTokenizer(br.readLine());
        int[] prices = new int[MAX_DAY];
        for (int i = 0; i < MAX_DAY; i++) {
            prices[i] = stoi(st.nextToken());
        }
        int jun = 0;
        int junM = m;
        int sung = 0;
        int sungM = m;
        for (int i = 0; i < 14; i++) {
            if (prices[i] <= junM) {
                int cnt = junM / prices[i];
                jun += cnt;
                junM -= (prices[i] * cnt);
            }
        }
        int upF = 0;
        int dnF = 0;
        for (int i = 1; i < 14; i++) {
            if (prices[i - 1] < prices[i]) {
                dnF = 0;
                upF++;
            } else if (prices[i - 1] > prices[i]) {
                upF = 0;
                dnF++;
            } else {
                dnF = 0;
                upF = 0;
            }

            if (upF >= 3) {
                if (sung > 0) {
                    sungM += (sung * prices[i]);
                    sung = 0;
                }
            } else if (dnF >= 3) {
                if (sungM > prices[i]) {
                    int v = sungM / prices[i];
                    sung += v;
                    sungM -= (v * prices[i]);
                }
            }
        }
        sungM = (sung*prices[MAX_DAY - 1] + sungM);
        junM = (jun*prices[MAX_DAY - 1] + junM);

        System.out.println(sungM == junM ? "SAMESAME" : (sungM > junM ? "TIMING" : "BNP"));
    }

    private static int stoi(String v) {
        return Integer.parseInt(v);
    }

}
