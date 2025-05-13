class Solution {
    /*
    # 문제 이해
    제거할 수 edge를 반환해야 한다. 답이 여러 개일 경우 가장 마지막 edge
    제거할 수 있는 엣지는 이미 연결된 엣지이다

    # 풀이 접근
    union-find 알고리즘으로 풀이 가능
    부모가 같다면 연결된 노드이므로

    # 구현 스텝
    1. 각 노드의 부모를 자신으로 둔다
    2. 엣지들을 순회한다
        3. 엣지에 연결된 노드를 연결한다
            - 연결시 더 높은 부모 노드로 통합
        4. 이미 연결되어 있다면, 해당 엣지를 반환한다
    5. 이미 연결된 노드가 없다면 null return
    */
    public int[] findRedundantConnection(int[][] edges) {
        int[] parents = new int[edges.length + 1];
        for (int i = 1; i < parents.length; i++) {
            parents[i] = i;
        }

        for (int[] edge : edges) {
            int node1 = edge[0];
            int node2 = edge[1];

            if (parent(parents, node1) == parent(parents, node2)) {
                return edge;
            } else {
                link(parents, node1, node2);
            }
        }

        return null;
    }

    private static int parent(int[] parents, int index) {
        if (parents[index] != index) {
            parents[index] = parent(parents, parents[index]);
        }
        return parents[index];
    }

    private static void link(int[] parents, int index1, int index2) {
        int parentOfIndex1 = parent(parents, index1);
        int parentOfIndex2 = parent(parents, index2);
        
        parents[parentOfIndex1] = Math.max(parentOfIndex1, parentOfIndex2);
        parents[parentOfIndex2] = Math.max(parentOfIndex1, parentOfIndex2);
    }
}
