import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

class Solution {

    /*
    각 그래프의 특징
    - 도넛 모양 그래프: 모든 노드가 incoming edge과 outgoing edge이 1개
    - 막대 모양 그래프: 시작 노드는 incoming edge 1개, 끝 노드는 outgoing edge 1개, 나머지는 incoming, outgoing node 1개
    - 8자 모양 그래프: 중간 노드를 제외한 모든 노드들은 incoming, outgoing node 1개씩 중간 노드는 incoming, outgoing 2개씩
     */
    public int[] solution(int[][] edges) {
        // [0]: incoming(들어오는), [1]: outgoing(나가는)
        Map<Integer, int[]> connected  = new HashMap<>();
        for (int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];

            connected.putIfAbsent(from, new int[]{0, 0});
            connected.putIfAbsent(to, new int[]{0, 0});

            connected.get(from)[1]++;
            connected.get(to)[0]++;
        }

        /*
        [0]: 생성 정점 번호
        [1]: 도넛 모양 그래프 개수
        [2]: 막대 모양 그래프 개수
        [3]: 8자 모양 그래프 개수
         */
        int[] result = new int[4];
        for (Entry<Integer, int[]> entry : connected.entrySet()) {
            int key = entry.getKey();
            int[] value = entry.getValue();

            // 생성 정점 번호
            if (value[0] == 0 && value[1] >= 2) {
                result[0] = key;
            } else if (value[1] == 0) { // 막대 모양 그래프의 마지막 노드는 outgoing edge가 없다
                result[2]++;
            } else if (value[0] >= 2 && value[1] == 2) { // 8자 모양 그래프의 중간 노드
                result[3]++;
            }
        }
        result[1] = connected.get(result[0])[1] - result[2] - result[3];

        return result;
    }
}
