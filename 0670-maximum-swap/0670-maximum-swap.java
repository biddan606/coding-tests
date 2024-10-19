import java.util.PriorityQueue;

class Solution {
    public int maximumSwap(int num) {
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> {
            if (a[0] == b[0]) {
                return a[1] - b[1];
            }
            return b[0] - a[0];
        });

        String number = String.valueOf(num);
        for (int i = 0; i < number.length(); i++) {
            pq.offer(new int[]{number.charAt(i) - '0', i});
        }

        int expectedIndex = 0;
        int swapIndex1 = 0;
        int swapIndex2 = 0;
        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            if (current[1] != expectedIndex) {
                swapIndex1 = expectedIndex;
                swapIndex2 = current[1];
                break;
            }
            expectedIndex++;
        }

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < number.length(); i++) {
            int index = i;
            if (index == swapIndex1) {
                index = swapIndex2;
            } else if (index == swapIndex2) {
                index = swapIndex1;
            }

            sb.append(number.charAt(index));
        }

        return Integer.parseInt(sb.toString());
    }

    public static void main(String[] args) {
        int result = new Solution().maximumSwap(2736);

        System.out.println(result);
    }
}
