class Solution {
    public int solution(String name) {
        int[] target = name.chars()
            .map(c -> c - 'A')
            .toArray();
        
        int changes = 0;
        for (int i = 0; i < target.length; i++) {
            int up = target[i];
            int down = 'Z' - 'A' + 1 - target[i];
            
            changes += Math.min(up, down);
        }
        
        // cursors의 기본값은 정방향으로 설정
        int cursors = target.length - 1;
        for (int i = 0; i < target.length; i++) {
            int next = i + 1;
            while (next < target.length && target[next] == 0) {
                next++;
            }
            
            int forwardAndBack = i * 2 + target.length - next;
            int backAndForward = i + (target.length - next) * 2;
            
            cursors = Math.min(cursors, forwardAndBack);
            cursors = Math.min(cursors, backAndForward);
        }
        
        return cursors + changes;
    }
}