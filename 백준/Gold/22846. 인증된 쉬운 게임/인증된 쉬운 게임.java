import java.io.*;


public class Main {
    public static int K;
    public static boolean [] dp;


    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

        K = Integer.parseInt(br.readLine());

        dp = new boolean[K+1];

        for (int x = K; x >= 1; x--) {
            // 시간 절약을 위해 y*y<=x 인 범위만큼만 약수 확인
            for (int y = 1; y * y <= x; y++) {

                if (x % y == 0) {
                   
                    if (x + y <= K && !dp[x + y]) {
                        dp[x]=true;
                        break;
                    }
                    // y*y<=x인 경우만큼만 구했기에 x/y도 추가적으로 구해줘야 함.
                    if (x + x / y <= K && !dp[x + x / y]) {
                        dp[x]=true;
                        break;
                    }
                }

            }
        }


        if (dp[1]) {
            bw.write("Kali");
        } else {
            bw.write("Ringo");
        }




        bw.flush();

    }

}