import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLOutput;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();

        st = new StringTokenizer(br.readLine());
        int n = stoi(st.nextToken());

        int[] booms = new int[n];
        for (int i = 0; i < n; i++) {
            st = new StringTokenizer(br.readLine());
            int v = stoi(st.nextToken());
            booms[i] = v;
        }

        if (n == 1) {
            System.out.println(1);
            return;
        }

        if (booms[0] >= booms[1]) {
            System.out.println(1);
        }

        for (int i = 1; i < n - 1; i++) {
            if (booms[i] >= booms[i + 1] && booms[i] >= booms[i - 1]) {
                System.out.println(i + 1);
            }
        }

        if (booms[n-1] >= booms[n - 2]) {
            System.out.println(n);
        }
    }


    private static int stoi(String v) {
        return Integer.parseInt(v);
    }
}