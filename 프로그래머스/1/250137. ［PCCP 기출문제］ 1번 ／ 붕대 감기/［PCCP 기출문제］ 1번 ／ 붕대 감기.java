class Solution {
    public int solution(int[] bandage, int health, int[][] attacks) {
        int last = attacks[attacks.length - 1][0];
        int cnt = 0;
        int attackIdx = 0;
        int nowHp = health;
        for(int t = 1; t <= last; t++) {
            if(attacks[attackIdx][0] == t) {
                nowHp -= attacks[attackIdx][1];
                if(nowHp <= 0) {
                    nowHp = -1;
                    break;
                }
                cnt = 0;
                attackIdx++;
                continue;
            } 
            nowHp += bandage[1];
            cnt++;
            
            if(cnt == bandage[0]) {
                cnt = 0;
                nowHp += bandage[2];
            }
            
            if(nowHp > health) nowHp = health;
        }
        return nowHp;
    }
}