import java.util.*;
import java.io.*;
public class Main {
    private static int[] c;
    private static int[][] p;
    private static int n;
    private static boolean[] visit;
    private static int result = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        StringBuilder sb = new StringBuilder();
        st = new StringTokenizer(br.readLine());
        
        n = stoi(st.nextToken());

        p = new int[n + 1][n + 1];
        c = new int[n / 2];
        visit = new boolean[n + 1];

        for(int i = 1; i <= n; i++) {
            st = new StringTokenizer(br.readLine());
            for(int j = 1; j <= n; j++) {
                p[i][j] = stoi(st.nextToken());
            }
        }

        rec(0,1);
        System.out.println(result);
    }

    private static void rec(int d, int s) {
        if(d == n / 2) {
            int s1 = 0;
            int s2 = 0;

            int[] tmp = new int[n / 2];
            int idx = 0;
            for(int i = 1; i <= n; i++) {
                if(!visit[i]) {
                    tmp[idx++] = i;
                }
            }
            
            for(int i = 0; i < (n / 2); i++) {
                for(int j = 0; j < (n / 2); j++) {
                    s1 += p[c[i]][c[j]];
                }
            }

            for(int i = 0; i < n / 2; i++) {
                for(int j = 0; j < n / 2; j++) {
                    s2 += p[tmp[i]][tmp[j]];
                }
            }
            // System.out.println(s1 + " " + s2);
            result = Math.min(result, Math.abs(s1 - s2));
            return;
        }

        for(int i = s; i <= n; i++) {
            if(!visit[i]) {
                visit[i] = true;
                c[d] = i;
                rec(d + 1, i + 1);
                visit[i] = false;
            }
        }

    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}