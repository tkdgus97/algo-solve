class Solution {
    public int solution(String[] board) {
        int answer = 1;
        
        int oCnt = 0;
        int xCnt = 0;
        
        
        for(int r = 0; r < 3; r++) {
            for(int c = 0; c < 3; c++) {
                if(board[r].charAt(c) == 'O') oCnt++;
                if(board[r].charAt(c) == 'X') xCnt++;
            }
        }
        
        if(oCnt < xCnt || Math.abs(xCnt - oCnt) >= 2 || (win('O', board) && xCnt >= oCnt) || (win('X', board) && xCnt < oCnt)) return 0;
        
        return answer;
    }
    
    private static boolean win(char t, String[] board) {
        for(int r = 0; r < 3; r++) {
            if(board[r].charAt(0) == t && board[r].charAt(1) == t && board[r].charAt(2) == t) return true;
            if(board[0].charAt(r) == t && board[1].charAt(r) == t && board[2].charAt(r) == t) return true;
            
        }
        
        if(board[0].charAt(0) == t && board[1].charAt(1) == t && board[2].charAt(2) == t) return true;
        if(board[0].charAt(2) == t && board[1].charAt(1) == t && board[2].charAt(0) == t) return true;
        return false;
        
    }
}