import java.util.*;
class Solution {
    public int solution(int[] topping) {
        int answer = 0;
        Set<Integer> f = new HashSet<>();
        HashMap<Integer, Integer> second = new HashMap<>();
        for(int t : topping) {
            second.put(t, second.getOrDefault(t, 0) + 1);
        }
        
        for(int t : topping) {
            f.add(t);
            second.put(t, second.get(t) - 1);
            if(second.get(t) == 0) second.remove(t);
            if(f.size() == second.size()) answer++;
        }
        return answer;
    }
}