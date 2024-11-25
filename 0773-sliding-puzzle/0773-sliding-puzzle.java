import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

class Solution {
    private static final String TARGET_BOARD_KEY = "123450";
    private static int[][] DIRECTIONS = {
            {0, 1},
            {0, -1},
            {-1, 0},
            {1, 0}
    };

    public int slidingPuzzle(int[][] board) {
        Queue<Element> queue = new ArrayDeque<>();
        Element start = new Element(board, getBlankLocation(board), 0);
        queue.offer(start);

        Set<String> visited = new HashSet<>();

        while(!queue.isEmpty()) {
            Element current = queue.poll();

            String boardKey = generateBoardKey(current);
            if (visited.contains(boardKey)) {
                continue;
            }
            visited.add(boardKey);

            if (matchTargetKey(boardKey)) {
                return current.depth;
            }

            int nextDepth = current.depth + 1;
            for (int[] direction : DIRECTIONS) {
                int[] nextBlankLocation = generateNextBlankLocation(board, direction, current);
                if (nextBlankLocation == null) {
                    continue;
                }

                int[][] nextBoard = generateNextBoard(current, nextBlankLocation);
                queue.offer(new Element(nextBoard, nextBlankLocation, nextDepth));
            }
        }

        return -1;
    }

    private static int[][] generateNextBoard(Element current, int[] nextBlankLocation) {
        int[][] nextBoard = Arrays.stream(current.board)
                .map(int[]::clone)
                .toArray(int[][]::new);
        nextBoard[current.blankLocation[0]][current.blankLocation[1]] = nextBoard[nextBlankLocation[0]][nextBlankLocation[1]];
        nextBoard[nextBlankLocation[0]][nextBlankLocation[1]] = 0;
        return nextBoard;
    }

    private static int[] generateNextBlankLocation(int[][] board, int[] direction, Element current) {
        int[] nextBlankLocation = {
                current.blankLocation[0] + direction[0],
                current.blankLocation[1] + direction[1]};
        if (!isWithinBounds(board, nextBlankLocation)) {
            return null;
        }
        return nextBlankLocation;
    }

    private static String generateBoardKey(Element current) {
        StringBuilder boardKeyBuilder = new StringBuilder();
        for (int r = 0; r < current.board.length; r++) {
            for (int c = 0; c < current.board[0].length; c++) {
                boardKeyBuilder.append(current.board[r][c]);
            }
        }

        String boardKey = boardKeyBuilder.toString();
        return boardKey;
    }

    private static int[] getBlankLocation(int[][] board) {
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[r].length; c++) {
                if (board[r][c] == 0) {
                    return new int[]{r, c};
                }
            }
        }

        throw new RuntimeException("빈 칸을 찾을 수 없습니다.");
    }

    private static boolean isWithinBounds(int[][] board, int[] location) {
        return (location[0] >= 0 && location[0] < board.length)
                && (location[1] >= 0 && location[1] < board[location[0]].length);
    }

    private static boolean matchTargetKey(String boardKey) {
        return TARGET_BOARD_KEY.equals(boardKey);
    }

    private static class Element {
        final int[][] board;
        final int[] blankLocation;
        final int depth;

        public Element(int[][] board, int[] blankLocation, int depth) {
            this.board = board;
            this.blankLocation = blankLocation;
            this.depth = depth;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        int[][] board = {{3,2,4},{1,5,0}};

        int result = solution.slidingPuzzle(board);
        System.out.println(result);
    }
}
