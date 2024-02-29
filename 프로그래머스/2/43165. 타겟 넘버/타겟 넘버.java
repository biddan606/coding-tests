import java.util.*;

class Solution {
    
    public int solution(int[] numbers, int target) {
        Deque<State> deque = new ArrayDeque<>();
        deque.addLast(new State(0, 0));
        int count = 0;
        
        while (!deque.isEmpty()) {
            State current = deque.removeFirst();
            
            if (current.index == numbers.length) {
                if (current.acc == target) {
                    count++;
                }
                continue;
            }
            
            deque.addLast(new State(current.index + 1, current.acc + numbers[current.index]));
            deque.addLast(new State(current.index + 1, current.acc - numbers[current.index]));
        }
        
        return count;
    }

    private static class State {
        final int index;
        final int acc;
        
        State(int index, int acc) {
            this.index = index;
            this.acc = acc;
        }
    }
}