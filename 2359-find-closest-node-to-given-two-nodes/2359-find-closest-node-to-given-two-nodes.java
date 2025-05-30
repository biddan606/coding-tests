class Solution {
    /*
    # 문제 이해
    방향 그래프가 주어진다
    각 노드는 다른 노드로 가는 1개 이하의 간선이 존재한다
    node1, node2 두 노드에서 모두 갈 수 있고,
    두 노드에서 다른 노드의 거리의 최대값이 최소가 되는 값을 반환해야 한다
    답이 여러 개라면 작은 Index 반환
    
    *주의: 간선끼리는 순환이 될 수 있다

    # 풀이 접근
    n은 O(10^5)이므로 O(n^2) 풀이는 안된다

    node1이 진행할 수 없을 때까지 node1으로부터 다른 node까지의 거리들을 구함
    node2이 진행할 수 없을 때까지 node2으로부터 다른 node까지의 거리들을 구함

    구한 2개의 거리들을 비교하며 최대값이 최소가 되는 노드를 찾음

    # 구현 스텝
    1. node1에서 다른 Node들을 방문하며 거리를 구함
    2. node2에서 다른 Node들을 방문하며 거리를 구함
    3. node1으로부터 다른 노드 거리와 node2로부터 다른 노드 거리를 비교하며
        최소값을 찾음
        - node1 노드 거리: 5, node2 노드 거리:10 이라면 이 노드의 거리는 10(최대값)
    4. 최소값을 가진 노드 index를 반환함
    */
    public int closestMeetingNode(int[] edges, int node1, int node2) {
        int size = edges.length;

        int currentNode1 = node1;

        int[] node1Distances = new int[size];
        Arrays.fill(node1Distances, -1);
        
        int currentNode1distance = 0;
        
        while (currentNode1 != -1 && node1Distances[currentNode1] == -1) {
            node1Distances[currentNode1] = currentNode1distance;

            currentNode1 = edges[currentNode1];
            currentNode1distance++;
        }

        int currentNode2 = node2;

        int[] node2Distances = new int[size];
        Arrays.fill(node2Distances, -1);
        
        int currentNode2distance = 0;
        
        while (currentNode2 != -1 && node2Distances[currentNode2] == -1) {
            node2Distances[currentNode2] = currentNode2distance;

            currentNode2 = edges[currentNode2];
            currentNode2distance++;
        }

        int result = -1;
        int minDistance = 100_000;

        for (int i = 0; i < size; i++) {
            if (node1Distances[i] == -1 || node2Distances[i] == -1) {
                continue;
            }
            
            int currentDistance = Math.max(node1Distances[i], node2Distances[i]);

            if (currentDistance < minDistance) {
                result = i;
                minDistance = currentDistance;
            }
        }

        return result;
    }
}
