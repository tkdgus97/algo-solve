import java.util.*;
class Solution {
    public int solution(int n, int w, int num) {
        int answer = 0;
        
        int[][] boxes = new int[(n / w) + 1][w];
        
        int line = 0;
        for(int i = 0; i < (n / w) + 1; i++) {
            Arrays.fill(boxes[i], -1);
        }
        for(int i = 0; i < n; i++) {
            if((i / w) > line) {
                line++;
            }
            if(line % 2 == 0) {
                boxes[line][i % w] = i;
            } else {
                boxes[line][w - (i % w) - 1] = i;
            }
        }
        
        int r = (num - 1) / w;
        int c = r % 2 == 0 ? (num - 1) % w : w - ((num - 1) % w) - 1;
        
        for(int i = r + 1; i < (n / w) + 1; i++) {
            if(boxes[i][c] != -1) {
                answer++;
            }
        }
        
        
        return answer + 1;
    }
}