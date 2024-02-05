import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        PriorityQueue<Task> sortedByCreatedAt = 
            new PriorityQueue<>((a, b) -> a.createdAt - b.createdAt);
        for (int i = 0; i < jobs.length; i++) {
            sortedByCreatedAt.add(new Task(jobs[i][0], jobs[i][1]));
        }
        
        
        PriorityQueue<Task> minHeap = 
            new PriorityQueue<>((a, b) -> a.workingTime - b.workingTime);
        int accumulated = 0;
        int time = 0;
        
        while (!sortedByCreatedAt.isEmpty() || !minHeap.isEmpty()) {
            // 최소힙에 진행할 수 있는 작업들을 등록한다.
            while (!sortedByCreatedAt.isEmpty() && 
                   sortedByCreatedAt.element().createdAt <= time) {
                Task adding = sortedByCreatedAt.remove();
                minHeap.add(adding);
            }
            
            if (minHeap.isEmpty()) {
                time = sortedByCreatedAt.element().createdAt;
                continue;
            }
            
            Task task = minHeap.remove();
            time += task.workingTime;
            accumulated += time - task.createdAt;
        }
        
        return accumulated / jobs.length;
    }
    
    private static class Task {
        int createdAt;
        int workingTime;
        
        public Task(int createdAt, int workingTime) {
            this.createdAt = createdAt;
            this.workingTime = workingTime;
        }
    }
}