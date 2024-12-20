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

        int n = stoi(st.nextToken());
        int tae = stoi(st.nextToken());
        int p = stoi(st.nextToken());

        int[] scores = new int[n + 1];
        int[] rank = new int[n + 1];

        if (n == 0) {
            System.out.println(1);
            return;
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < n; i++) {
            scores[i] = stoi(st.nextToken());
        }

        Arrays.fill(rank, 1);
        scores[n] = tae;

        for (int i = 0; i < n + 1; i++) {
            for (int j = 0; j < n + 1; j++) {
                if (i == j) continue;
                if (scores[i] < scores[j]) rank[i]++;
            }
        }
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (rank[i] <= rank[n]) cnt++;
        }

        System.out.println(cnt < p ? rank[n] : -1);
    }

    private static int stoi(String v) {
        return Integer.parseInt(v);
    }

}

