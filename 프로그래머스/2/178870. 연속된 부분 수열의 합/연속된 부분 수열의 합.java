class Solution {
    public int[] solution(int[] sequence, int k) {
        int[] answer = {};
        
        int left = 0, right =0;
        int l = 0;
        int len = 1000000;
        int sum = 0;
        for(int r = 0; r < sequence.length; r++) {
            sum += sequence[r];
            while(sum > k) {
                sum -= sequence[l];
                l++;
            }
            if(sum == k) {
                if(len > (r - l)) {
                    left = l;
                    right = r;
                    len = (r-l);
                }
            }
        }
        return new int[] {left,right};
    }
}