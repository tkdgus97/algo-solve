import java.util.*;
class Solution {
    private static LinkedList<int[]>[] route;
    private static int ROBOT_COUNT = 0;
    
    public int solution(int[][] points, int[][] routes) {
        int answer = 0;
        ROBOT_COUNT = (routes.length + 1);
        route = new LinkedList[ROBOT_COUNT];
        for(int i = 1; i < ROBOT_COUNT; i++) {
            route[i] = new LinkedList<>();
        }
        
        getRoute(points, routes);
        answer = getCrash();
        return answer;
    }
    
    private static void getRoute(int[][] points, int[][] routes) {
        int idx = 1;
        for(int[] r : routes) {
            int[] s = points[r[0] - 1];
            int sx = s[0];
            int sy = s[1];
            route[idx].add(new int[] {sx, sy});
            for(int i = 1; i < r.length; i++) {
                int[] e = points[r[i] - 1];
                int ex = e[0];
                int ey = e[1];
                
                while(sx != ex) {
                    if(sx > ex) sx-=1;
                    else sx += 1;
                    route[idx].add(new int[] {sx, sy});
                }

                while(sy != ey) {
                    if(sy > ey) sy -= 1;
                    else sy += 1;
                    route[idx].add(new int[] {sx, sy});
                }
            }
            idx++;
        }
    }
    
    private static int getCrash() {
        int cnt = 0;
        int check = 0;
        while(check != ROBOT_COUNT - 1){
            int[][] visit = new int[101][101];
            check = 0;
            for(int j = 1; j < ROBOT_COUNT; j++) {
                if(route[j].isEmpty()) {
                    check++;
                    continue;
                }
                int[] r = route[j].poll();
                visit[r[0]][r[1]]++;
            }
            for(int i = 1; i<= 100; i++) {
                for(int j = 1; j <= 100; j++) {
                    if(visit[i][j] > 1) cnt++;
                }
            }
        }
        return cnt;
    }
}