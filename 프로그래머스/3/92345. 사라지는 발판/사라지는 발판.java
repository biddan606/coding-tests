class Solution {
    
    private static final int ABLE = 1;
    private static final int UNABLE = 0;
    
    private static final Point[] DIRECTS = {
        new Point(-1, 0),
        new Point(1, 0),
        new Point(0, -1),
        new Point(0, 1)
    };
    
    public int solution(int[][] board, int[] aloc, int[] bloc) {
        // 게임을 실행한다.
        GameResult result = game(board, new Point(aloc[1], aloc[0]), new Point(bloc[1], bloc[0]));
        return result.turns;
    }
    
    private GameResult game(int[][] board, Point player, Point opponent) {
        /*
        플레이어가 패배한 경우 반환합니다.
        - 4방향 중 1곳도 이동할 수 없다면 패배입니다.
        - 플레이어가 서있는 곳에 발판이 사라진 경우 패배입니다.
        */
        if (canNotMove(board, player) || board[player.y][player.x] == UNABLE) {
            return new GameResult(false, 0);
        }
        board[player.y][player.x] = UNABLE;
        
        
        boolean win = false;
        int maxTurnsToLose = Integer.MIN_VALUE;
        int minTurnsToWin = Integer.MAX_VALUE;
        
        /*
        1. 플레이어는 상하좌우 4방향으로 이동한다.
            - 보드 범위 안이고, 발판이 있는 곳만 방문할 수 있습니다.
        2. 다음 턴으로 진행합니다. 플레이어와 상대를 바꿉니다.(다음에 플레이할 유저는 상대이기 때문에)
        3. 반환 값을 받아 갱신합니다.
            - win == true 인 경우, 현재 플레이어가 진 경우입니다. max값을 갱신합니다.
            - win == false 인 경우, 현재 플레이어가 이긴 경우입니다. min값을 갱신합니다.
        */
        for (Point direct : DIRECTS) {
            Point next = new Point(player.x + direct.x, player.y + direct.y);
            
            if (!isWithinRange(board, next) || board[next.y][next.x] == UNABLE) {
                continue;
            }
            
            GameResult currentResult = game(board, opponent, next);
            if (currentResult.win) {
                maxTurnsToLose = Math.max(currentResult.turns + 1, maxTurnsToLose);
            } else {
                win = true;
                minTurnsToWin = Math.min(currentResult.turns + 1, minTurnsToWin);
            }
        }
        board[player.y][player.x] = ABLE;
        
        
        /*
        - win == true 인 경우, 현재 플레이어가 이겼으므로 min 값을 반환합니다.
        - win == false 인 경우, 현재 플레이어가 한 번도 이긴 적 없는 경우입니다. 이 경우 max값을 반환합니다.
        */
        if (win) {
            return new GameResult(true, minTurnsToWin);
        }
        return new GameResult(false, maxTurnsToLose);
    }
    
    private boolean canNotMove(int[][] board, Point player) {
        for (Point direct : DIRECTS) {
            Point next = new Point(player.x + direct.x, player.y + direct.y);
            
            if (isWithinRange(board, next) && board[next.y][next.x] == ABLE) {
                return false;
            }
        }
        
        return true;
    }
    
    private boolean isWithinRange(int[][] board, Point point) {
        if (point.y < 0 || board.length <= point.y) {
            return false;
        }
        return 0 <= point.x && point.x < board[point.y].length;
    }
    
    private static class Point {
        final int x;
        final int y;
        
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
    
    private static class GameResult {
        final boolean win;
        final int turns;
        
        public GameResult(boolean win, int turns) {
            this.win = win;
            this.turns = turns;
        }
    }
}