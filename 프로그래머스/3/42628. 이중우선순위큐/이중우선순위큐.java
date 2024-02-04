import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        DoublyPriorityQueue queue = new DoublyPriorityQueue();
        
        for (String operation : operations) {
            char command = operation.charAt(0);
            
            switch (command) {
                    case 'I' -> {
                        int value = Integer.parseInt(operation.substring(2));
                        queue.add(value);
                    }
                    case 'D' -> {
                        if (operation.charAt(2) == '1') {
                            queue.removeMax();
                        } else if (operation.charAt(2) == '-') {
                            queue.removeMin();
                        }
                    }
            }
        }
        
        return new int[]{queue.max(), queue.min()};
    }
    
    private static class DoublyPriorityQueue {
        private int size = 0;
        private final PriorityQueue<Integer> maxHeap = 
            new PriorityQueue<>((a, b) -> b - a);
        private final PriorityQueue<Integer> minHeap = 
            new PriorityQueue<>();
        
        public void add(int value) {
            maxHeap.add(value);
            minHeap.add(value);
            size++;
        }
        
        public void removeMax() {
            if (size == 0) {
                return;
            }
            
            maxHeap.remove();
            if (--size == 0) {
                maxHeap.clear();
                minHeap.clear();
            }
        }
        
        public void removeMin() {
            if (size == 0) {
                return;
            }
            
            minHeap.remove();
            if (--size == 0) {
                maxHeap.clear();
                minHeap.clear();
            }
        }
        
        public int max() {
            if (size == 0) {
                return 0;
            }
            return maxHeap.element();
        }
        
        public int min() {
            if (size == 0) {
                return 0;
            }
            return minHeap.element();
        }
    }
}
