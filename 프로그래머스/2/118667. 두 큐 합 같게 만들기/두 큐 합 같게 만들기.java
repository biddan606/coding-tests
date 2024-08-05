import java.util.Arrays;
import java.util.LinkedList;
import java.util.Queue;

class Solution {
    public int solution(int[] array1, int[] array2) {
        long queue1Sum = sum(array1);
        long queue2Sum = sum(array2);

        int totalElementCount = array1.length + array2.length;
        Queue<Integer> queue1 = convertToQueue(array1);
        Queue<Integer> queue2 = convertToQueue(array2);

        for (int i = 0; i < totalElementCount * 2; i++) {
            if (queue1Sum == queue2Sum) {
                return i;
            }

            if (queue1Sum > queue2Sum) {
                int e = queue1.poll();
                queue2.add(e);
                queue1Sum -= e;
                queue2Sum += e;
            } else { // queue1Sum < queue2Sum
                int e = queue2.poll();
                queue1.add(e);
                queue1Sum += e;
                queue2Sum -= e;
            }
        }

        return -1;
    }

    private long sum(int[] queue1) {
        return Arrays.stream(queue1)
                .sum();
    }

    private Queue<Integer> convertToQueue(int[] array) {
        Queue<Integer> queue = new LinkedList<>();

        for (int i = 0; i < array.length; i++) {
            queue.add(array[i]);
        }

        return queue;
    }
}
