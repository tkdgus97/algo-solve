import java.util.*;

class Solution {
    private class Node {
        String s;
        int depth;
        int idx;
        public Node(String word, int d, int i) {
            s = word;
            depth = d;
            idx = i;
        }
    }
    public int solution(String begin, String target, String[] words) {
        int answer = 0;
        boolean f = false;
        for(String t : words) {
            if(target.equals(t)) f = true;
        }
        
        if(!f) return answer;
        
        Queue<Node> q = new LinkedList();
        boolean[] visit = new boolean[words.length];
        List<Node>[] adj = new ArrayList[words.length];
        
        for(int i = 0; i < words.length; i++) {
            adj[i] = new ArrayList();
        }
        for(int i = 0; i < words.length; i++) {
            int diff = 0;
            for(int j = 0; j < words.length; j++) {
                diff = 0;
                for(int k = 0; k < words[j].length(); k++) {
                    if(words[i].charAt(k) != words[j].charAt(k)) {
                        diff++;
                    }
                }
                if(diff == 1) {
                    adj[i].add(new Node(words[j], 0, j));
                }
            }
            
            diff = 0;
            
            for(int j = 0; j < words[i].length(); j++) {
                if(words[i].charAt(j) != begin.charAt(j)) {
                    diff++;
                }
            }
            if(diff == 1) {
                visit[i] = true;
                q.add(new Node(words[i], 1, i));
            }
        }
        
        while(!q.isEmpty()) {
            Node now = q.poll();
            if(now.s.equals(target)) {
                answer = now.depth;
                break;
            }
            
            for(Node t : adj[now.idx]) {
                if(!visit[t.idx]) {
                    visit[t.idx] = true;
                    q.add(new Node(t.s, now.depth + 1, t.idx));
                }
            }
        }
    
        
        return answer;
    }
}