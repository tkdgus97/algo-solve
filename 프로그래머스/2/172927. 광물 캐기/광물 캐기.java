import java.util.*;
class Solution {
    static class Mine{
        int d;
        int i;
        int s;
        
        Mine(int d, int i, int s) {
            this.d = d;
            this.i = i;
            this.s = s;
        }
    }
    public int solution(int[] picks, String[] minerals) {
        int answer = 0;
        
        int[][] pirodo = {{1,1,1}, {5,1,1}, {25,5,1}};
        
        int totalPick = 0;
        
        List<Mine> list = new ArrayList<>();
        for(int p : picks) totalPick += p;
        
        for(int i = 0; i < minerals.length; i += 5) {
            if(totalPick == 0) break;
            int d = 0;
            int iron = 0;
            int s = 0;
            
            for(int j = i; j < i + 5; j++) {
                if(j == minerals.length) break;
                
                int v = convertNum(minerals[j]);
                
                d += pirodo[0][v];
                iron += pirodo[1][v];
                s += pirodo[2][v];
            }
            list.add(new Mine(d,iron,s));
            totalPick--;
        }
        
        Collections.sort(list, (o1,o2) -> {
            return o2.s - o1.s;
        });
        
        for(Mine m : list) {
            
            if(picks[0] > 0) {
                answer += m.d;
                picks[0]--;
                continue;
            }
            if(picks[1] > 0) {
                answer += m.i;
                picks[1]--;
                continue;
            }
            if(picks[2] > 0) {
                answer += m.s;
                picks[2]--; 
                continue;
            }
            
        }
        
        return answer;
    }
    
    private static int convertNum(String mine) {
        if(mine.equals("stone")) return 2;
        else if(mine.equals("iron")) return 1;
        else return 0;
    }
}