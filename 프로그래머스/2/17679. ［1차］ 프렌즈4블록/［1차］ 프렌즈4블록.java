import java.util.*;

class Solution {
    private static final int[][] ADJACENT_POINTS = {
        {0, 1},
        {1, 0},
        {1, 1}
    };
    
    public int solution(int m, int n, String[] board) {
        // 블록 보드 생성, 기존 보드판 row 반전, row-col 반전
        List<List<Block>> blockBoard = new ArrayList<>();
        int rows = n;
        int cols = m;
        
        for (int r = 0; r < rows; r++) {
            List<Block> newRow = new ArrayList<>();
            
            for (int c = 0; c < cols; c++) {
                newRow.add(new Block(board[cols - c - 1].charAt(r)));
            }
            
            blockBoard.add(newRow);
        }
        
        // 삭제가 안될 때까지 반복
        boolean blocksRemoved = true;
        int removedBlocks = 0;
        while (blocksRemoved) {
            blocksRemoved = false;
            
            // 삭제할 블록 체크
            for (int r = 0; r < blockBoard.size(); r++) {
                for (int c = 0; c < blockBoard.get(r).size(); c++) {
                    markBlocksForRemoval(blockBoard, r, c);
                }
            }
            
            // 삭제할 블록 제외하여 새 블록보드 생성 후 교체
            List<List<Block>> newBlockBoard = new ArrayList<>();
            for (int r = 0; r < blockBoard.size(); r++) {
                List<Block> newRow = new ArrayList<>();
                
                for (int c = 0; c < blockBoard.get(r).size(); c++) {
                    if (blockBoard.get(r).get(c).removeMark) {
                        blocksRemoved = true;
                        removedBlocks++;
                        continue;
                    }
                    
                    newRow.add(new Block(blockBoard.get(r).get(c).value));
                }
                
                newBlockBoard.add(newRow);
            }
            
            blockBoard = newBlockBoard;
        }
                           
        return removedBlocks;
    }
    
    private void markBlocksForRemoval(List<List<Block>> blockBoard, int row, int col) {
        int checkValue = blockBoard.get(row).get(col).value;
        
        for (int[] p : ADJACENT_POINTS) {
            int adjacentRow = row + p[0];
            int adjacentCol = col + p[1];
            
            if (blockBoard.size() <= adjacentRow
                || blockBoard.get(adjacentRow).size() <= adjacentCol
                || checkValue != blockBoard.get(adjacentRow).get(adjacentCol).value) {
                return;
            }
        }
        
        blockBoard.get(row).get(col).removeMark = true;
        for (int[] p : ADJACENT_POINTS) {
            int adjacentRow = row + p[0];
            int adjacentCol = col + p[1];
            
            blockBoard.get(adjacentRow).get(adjacentCol).removeMark = true;
        }
    }
    
    private class Block {
        final char value;
        boolean removeMark;
        
        public Block(char value) {
            this.value = value;
        }
    }
}
