import java.util.*;
import java.io.*;
public class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int n = stoi(st.nextToken());
        
        st = new StringTokenizer(br.readLine());
        int[] restrants = new int[n];

        for(int i = 0; i < n; i++) {
            restrants[i] = stoi(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        int ldr = stoi(st.nextToken());
        int mbr = stoi(st.nextToken());

        long result = 0;
        for(int i = 0; i < n; i++) {
            restrants[i] -= ldr;
            int tmpResult = 1;
            if(restrants[i] > 0) {
                tmpResult += (restrants[i] / mbr);
                if(restrants[i] % mbr != 0) tmpResult += 1;
            }
            result += tmpResult;
        }

        System.out.println(result);
    }

    private static int stoi(String s) {
        return Integer.parseInt(s);
    }
}