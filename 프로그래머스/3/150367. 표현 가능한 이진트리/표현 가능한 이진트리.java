class Solution {
    
    public int[] solution(long[] numbers) {
        int[] answer = new int[numbers.length];
        for(int i = 0; i < numbers.length; i++) {
            String cur = Long.toBinaryString(numbers[i]);
            
            int d = 1;
            while((int) Math.pow(2, d) - 1 < cur.length()) {
                d++;
            }
            while((int) Math.pow(2, d) - 1 != cur.length()) {
                cur = "0" + cur;
            }
            
            boolean result = dfs(cur);
            
            answer[i] = result ? 1 : 0;
        }
        return answer;
    }
    
    private static boolean dfs(String s) {
        if(s.length() <= 1) return true;
        int root = s.length() / 2;
        String left = s.substring(0,root);
        String right = s.substring(root + 1, s.length());
        
        char lr = left.charAt(left.length() / 2);
        char rr = right.charAt(right.length() / 2);
        
        if(s.charAt(root) == '0' && (lr == '1' || rr == '1')) {
            return false;
        }
        
        boolean l = dfs(left);
        boolean r = dfs(right);
        
        return l && r;
    }
}