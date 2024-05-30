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
        int m = Integer.parseInt(st.nextToken());

        int[][] cards = new int[n][m];
        int[] diff = new int[n];
        int maxDiff = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int tmpDiff = 0;
            for (int j = 0; j < m; j++) {
                cards[i][j] = Integer.parseInt(st.nextToken());
                if (cards[i][j] != 0) tmpDiff++;
            }

            diff[i] = tmpDiff;
            maxDiff = Math.max(maxDiff, tmpDiff);
        }

        int minMove = Integer.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            if (diff[i] < maxDiff) continue;
            boolean[] cardNumber = new boolean[m];
            int move = 0;
            for (int j = 0; j < n; j++) {
                if (i == j) continue;
                if (diff[j] > 1) {
                    move++;
                    continue;
                }
                for (int k = 0; k < m; k++) {
                    if (cards[j][k] != 0) {
                        if (!cardNumber[k]) {
                            cardNumber[k] = true;
                        } else {
                            move++;
                        }
                        break;
                    }
                }
            }

            minMove = Math.min(minMove, move);
        }
        System.out.println(minMove);
    }

}
