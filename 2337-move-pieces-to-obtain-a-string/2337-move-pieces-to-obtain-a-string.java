class Solution {
    public boolean canChange(String start, String target) {
        // 'L'은 왼쪽으로 이동할 수 있다.
        // 'R'은 오른쪽으로 이동할 수 있다.
        // 다른 조각을 넘을 수 없다.

        // start와 target은 같은 순서로 조각들이 배치되어 있어야 한다.
        // 'L'일 때는 target보다 start가 크거가 같아야 한다.
        // 'R'일 때는 target보다 start가 작거나 같아야 한다.
        if (start.length() != target.length()) {
            return false;
        }

        Queue<Direction> startDirectionQueue = generateDirectionQueue(start);
        Queue<Direction> targetDirectionQueue = generateDirectionQueue(target);
        if (startDirectionQueue.size() != targetDirectionQueue.size()) {
            return false;
        }
        
        while (!targetDirectionQueue.isEmpty()) {
            Direction startDirection = startDirectionQueue.poll();
            Direction targetDirection = targetDirectionQueue.poll();

            if ((startDirection.ch != targetDirection.ch)
                || (targetDirection.ch == 'L' && targetDirection.index > startDirection.index)
                || (targetDirection.ch == 'R' && targetDirection.index < startDirection.index)) {
                return false;
            }
        }
        
        if (!startDirectionQueue.isEmpty()) {
            return false;
        }
        return true;
    }

    private static Queue<Direction> generateDirectionQueue(String str) {
        Queue<Direction> directionQueue = new ArrayDeque<>();

        for (int i = 0; i < str.length(); i++) {
            char ch = str.charAt(i);

            if (ch == 'L' || ch == 'R') {
                directionQueue.offer(new Direction(ch, i));
            }
        }
        return directionQueue;
    }

    private static class Direction {
        final char ch;
        final int index;

        public Direction(char ch, int index) {
            this.ch = ch;
            this.index = index;
        }
    }
}
