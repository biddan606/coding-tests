class Solution {
    public int[] shortestDistanceAfterQueries(int n, int[][] queries) {
        // 초기 경로를 생성한다
        Map<Integer, Set<Integer>> routes = new HashMap<>();
        for (int i = 0; i + 1 < n; i++) {
            routes.computeIfAbsent(i, k -> new HashSet<>())
                .add(i + 1);
        }

        int[] result = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int[] query = queries[i];

            // 경로를 추가한다
            routes.get(query[0])
                .add(query[1]);

            // 최단 경로를 구한다
            Queue<Element> queue = new ArrayDeque<>();
            queue.offer(new Element(0, 0));
            
            int[] distance = new int[n];

            while (!queue.isEmpty()) {
                Element e = queue.poll();

                for (int to : routes.getOrDefault(e.from, Collections.emptySet())) {
                    if (distance[to] != 0) {
                        continue;
                    }

                    distance[to] = e.time + 1;
                    queue.offer(new Element(to, e.time + 1));
                }
            }

            result[i] = distance[n - 1];
        }
        return result;
    }

    private static class Element {
        final int from;
        final int time;

        public Element(int from, int time) {
            this.from = from;
            this.time = time;
        }
    }
}
