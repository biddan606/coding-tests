import java.util.*;

class Solution {
    public int solution(int[][] jobs) {
        Arrays.sort(jobs, (a, b) -> a[0] - b[0]);
        PriorityQueue<Task> minHeap = 
            new PriorityQueue<>((a, b) -> a.workingTime - b.workingTime);
        int accumulatedTime = 0;
        int currentTime = 0;
        int jobsIndex = 0;
        
        while (jobsIndex < jobs.length) {
            // 최소힙에 진행할 수 있는 작업들을 등록한다.
            jobsIndex = insert(minHeap, jobs, jobsIndex, currentTime);
            // 작업을 진행한다. 현재 진행할 수 있는 작업이 없다면 시간을 1초 증가시킨다.
            if (minHeap.isEmpty()) {
                currentTime = jobs[jobsIndex][0];
                continue;
            }
            Task task = minHeap.remove();
            currentTime += task.workingTime;
            accumulatedTime += currentTime - task.createdAt;
        }
        
        // 남은 힙 정리를 한다.
        while (!minHeap.isEmpty()) {
            Task task = minHeap.remove();
            currentTime += task.workingTime;
            accumulatedTime += currentTime - task.createdAt;
        }
        
        return accumulatedTime / jobs.length;
    }
    
    private int insert(PriorityQueue<Task> heap, 
                       int[][] tasks, int startIndex, int time) {
        int index = startIndex;
        while (index < tasks.length && tasks[index][0] <= time) {
            heap.add(new Task(tasks[index][0], tasks[index][1]));
            index++;
        }
        
        return index;
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