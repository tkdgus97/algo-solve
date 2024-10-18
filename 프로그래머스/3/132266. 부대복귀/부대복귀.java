import java.util.*;
class Solution {
    private static List<Integer>[] adj;
    
    public int[] solution(int n, int[][] roads, int[] sources, int destination) {
        int[] answer = new int[sources.length];
        
        adj = new ArrayList[n + 1];
        for(int i = 1; i <= n; i++) {
            adj[i] = new ArrayList<>();
        }
        
        for(int[] r : roads) {
            adj[r[0]].add(r[1]);
            adj[r[1]].add(r[0]);
        }
        
        int[] dis = new int[n + 1];
        Arrays.fill(dis, Integer.MAX_VALUE);
        
        dis[destination] = 0;
        
        PriorityQueue<int[]> pq = new PriorityQueue<>((o1, o2) -> {
            return o1[1] - o2[1];
        });
        pq.add(new int[] {destination, 0});

        while(!pq.isEmpty()) {
            int[] now = pq.poll();
            for(int next : adj[now[0]]) {
                if(dis[next] > now[1] + 1){
                    dis[next] = now[1] + 1;
                    pq.add(new int[] {next, now[1] + 1});
                }
            }
        }
        
        for(int i = 0; i < sources.length; i++) {
            answer[i] = (dis[sources[i]] == Integer.MAX_VALUE ? -1 : dis[sources[i]]);
        }
        
        return answer;
    }
}