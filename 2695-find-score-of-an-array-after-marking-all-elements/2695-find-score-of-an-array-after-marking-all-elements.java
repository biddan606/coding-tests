class Solution {
    public long findScore(int[] nums) {
        // 큐 생성
        PriorityQueue<Element> pq = new PriorityQueue<>((a, b) -> {
            if (a.value == b.value) {
                return a.index - b.index;
            }
            return a.value - b.value;
        });

        // 큐에 값 삽입
        for (int i = 0; i < nums.length; i++) {
            pq.offer(new Element(i, nums[i]));
        }

        // 큐에서 작은 값을 차례대로 추출
        // 해당 값의 인덱스가 노마크라면, 양옆 마킹 후 score값에 추가
        // 마크되어 있다면, 넘어감
        long score = 0;
        boolean[] marked = new boolean[nums.length];

        while (!pq.isEmpty()) {
            Element e = pq.poll();
            if (marked[e.index]) {
                continue;
            }

            score += e.value;

            marked[e.index] = true;
            if (e.index - 1 >= 0) {
                marked[e.index - 1] = true;
            }
            if (e.index + 1 < nums.length) {
                marked[e.index + 1] = true;
            }
        }

        return score;
    }

    private static class Element {
        final int index;
        final int value;

        public Element(int index, int value) {
            this.index = index;
            this.value = value;
        }
    }
}
