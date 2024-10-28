import java.util.*;

class Solution {
    public int solution(int n, int[][] edges) {
        // 각 노드들을 연결한다.
        int nodeSize = n + 1;
        Map<Integer, List<Integer>> linked = new HashMap<>();

        for (int[] edge : edges) {
            linked.computeIfAbsent(edge[0], k -> new ArrayList<>())
                    .add(edge[1]);
            linked.computeIfAbsent(edge[1], k -> new ArrayList<>())
                    .add(edge[0]);
        }

        // 1번 노드 -> 다른 노드들을 방문한다.
        // 방문한 노드는 재방문하지 않는다.
        // 방문할 때마다 depth를 증가시킨다. 아무것도 방문하지 않았을 때는 1이다.
        Queue<Element> queue = new LinkedList<>();
        queue.offer(new Element(1, 1));
        int lastDepth = 0;
        int lastDepthNodeCount = 0;
        boolean[] visited = new boolean[nodeSize];

        while (!queue.isEmpty()) {
            Element current = queue.poll();
            if (visited[current.node]) {
                continue;
            }

            visited[current.node] = true;
            if (lastDepth == current.depth) {
                lastDepthNodeCount++;
            } else {
                lastDepth = current.depth;
                lastDepthNodeCount = 1;
            }

            for (int linkedNode : linked.getOrDefault(current.node, Collections.emptyList())) {
                queue.offer(new Element(linkedNode, current.depth + 1));
            }
        }

        // 마지막 depth를 반환한다.
        return lastDepthNodeCount;
    }

    private static class Element {
        final int node;
        final int depth;

        public Element(int node, int depth) {
            this.node = node;
            this.depth = depth;
        }
    }

    public static void main(String[] args) {

    }
}
