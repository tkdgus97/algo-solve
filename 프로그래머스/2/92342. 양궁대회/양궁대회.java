class Solution {
    private static int[] ryon = new int[11];
    private static int[] result = new int[11];
    private static int max = Integer.MIN_VALUE;

    public int[] solution(int n, int[] info) {
        rec(0, n, info,0);

        if (max == -1) {
            result = new int[1];
            result[0] = -1;
        }

        return result;
    }

    private static void rec(int L, int n, int[] info, int start) {
        if (L == n) {
            int diff = score(info);

            if (max < diff) {
                max = diff;
                result = ryon.clone();
            } else if(max== diff){
int o=0;
                int p= 0;
for(int i = 0; i<=10; i++){
    o += ((10-i)* result[i]);
    p += ((10-i)* ryon[i]);
}
                if(p < o){
result = ryon.clone();
}
                
}

            return;
        }
        int i;
        for (i = start; i <= 10; i++) {
            if (ryon[i] > info[i]) continue;
            ryon[i]++;
            rec(L + 1, n, info,i);
            ryon[i]--;
        }
    }

    private static int score(int[] info) {
        int pSum = 0;
        int rSum = 0;
        int i;
        for (i = 0; i < 11; i++) {
            if (ryon[i] == 0 && info[i] == 0) continue;
            if (ryon[i] > info[i]) {
                rSum += (10 - i);
            } else {
                pSum += (10 - i);
            }
        }

        if (pSum >= rSum) {
            return -1;
        }
        return (rSum - pSum);
    }
}