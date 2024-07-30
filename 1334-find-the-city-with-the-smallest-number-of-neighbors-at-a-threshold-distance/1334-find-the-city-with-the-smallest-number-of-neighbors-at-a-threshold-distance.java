class Solution {
    /**
     * 도시 -> 토시로 이동하는 최단 거리가 distanceThreshold 이하인 도시 쌍을 찾고,
     * 도시 쌍을 가장 적게 가지고 있는 도시 넘버를 반환한다.
     * 가장 적은 도시가 여러 개 존재하는 경우, 도서 넘버가 가장 큰 값을 반환한다.
     */
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {
        int[][] distances = new int[n][n];
        for (int i = 0; i < n; i++) {
            Arrays.fill(distances[i], Integer.MAX_VALUE / 2);
            distances[i][i] = 0;
        }

        for (int[] e : edges) {
            int u = e[0];
            int v = e[1];
            int weight = e[2];

            distances[u][v] = weight;
            distances[v][u] = weight;
        }

        for (int m = 0; m < n; m++) {
            for (int s = 0; s < n; s++) {
                for (int e = 0; e < n; e++) {
                    distances[s][e] = Math.min(
                        distances[s][e], distances[s][m] + distances[m][e]);
                }
            }
        }

        int[] neighbors = new int[n];
        int minNeighbors = Integer.MAX_VALUE;
        int result = -1;

        for (int city = 0; city < n; city++) {
            for (int d : distances[city]) {
                if (d <= distanceThreshold) {
                    neighbors[city]++;
                }
            }

            if (neighbors[city] <= minNeighbors) {
                minNeighbors = neighbors[city];
                result = city;
            }
        }

        return result;
    }
}
