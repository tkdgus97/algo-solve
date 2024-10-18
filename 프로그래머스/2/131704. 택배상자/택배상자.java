import java.util.*;
class Solution {
    public int solution(int[] order) {
        int answer = 0;
        int n = order.length;
        
        Stack<Integer> stack = new Stack<>();
        
        int idx = 0;
        for(int i = 1; i <= n; i++) {
            if(order[idx] == i) {
                idx++;
                while(!stack.isEmpty() && order[idx] == stack.peek()) {
                    idx++;
                    stack.pop();
                }
            } else {
                stack.push(i);
            }
        }
        
        return idx;
    }
}