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
        int[] arr = new int[n];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < n; i++) {
            arr[i] = stoi(st.nextToken());
        }

        int[] upDp = new int[n];
        int[] downDp = new int[n];

        for (int i = 0; i < n; i++) {
            upDp[i] = 1;
            for (int j = 0; j < i; j++) {
                if (arr[j] < arr[i]) {
                    upDp[i] = Math.max(upDp[j] + 1, upDp[i]);
                }
            }
        }

        for (int i = n - 1; i >= 0; i--) {
            downDp[i] = 1;
            for (int j = n - 1; j > i; j--) {
                if (arr[i] > arr[j]) {
                    downDp[i] = Math.max(downDp[j] + 1, downDp[i]);
                }
            }
        }

        int result = 0;
        for (int i = 0; i < n; i++) {
            result = Math.max((upDp[i] + downDp[i]) - 1, result);
        }

        System.out.println(result);
    }

    private static int stoi(String v) {
        return Integer.parseInt(v);
    }

}