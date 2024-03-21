import java.util.*;

class Solution {
    public int solution(int[] sales, int[][] links) {
        // 노드를 생성한다.
        Node[] nodes = new Node[sales.length];
        for (int i = 0; i < nodes.length; i++) {
            nodes[i] = new Node(i, sales[i]);
        }
        
        // 노드에 자식 노드를 추가한다.
        for (int[] link : links) {
            int parentId = link[0] - 1;
            int childId = link[1] - 1;
            
            nodes[parentId].childIds.add(childId);
        }
        
        // 최소 비용을 구한다.
        int[][] costs = new int[nodes.length][2];
        treeDp(nodes, costs, 0);
        
        return Math.min(costs[0][0], costs[0][1]);
    }
    
    private void treeDp(Node[] nodes, int[][] costs, int id) {
        Node node = nodes[id];
        costs[id][0] = 0;
        costs[id][1] = node.sale;
        
        if (node.childIds.isEmpty()) {
            return;
        }
        
        int extraCost = Integer.MAX_VALUE;
        for (int childId : node.childIds) {
            treeDp(nodes, costs, childId);
                
            /*
            부모 노드가 참석하지 않을 경우, 자식 노드 중 1개가 참석해야 한다.
            추가되는 금액이 가장 작은 자식 노드를 참석시킨다.
            [0]이 참석했을 경우 -> [1]로 변경
            [1]이 참석했다면 참석 안해도 되므로 추가 요금 0
            */
            if (costs[childId][0] < costs[childId][1]) {
                costs[id][0] += costs[childId][0];
                costs[id][1] += costs[childId][0];
                
                // 자식 노드 중 추가되는 요금이 가장 작은 노드를 찾음
                extraCost = Math.min(extraCost, costs[childId][1] - costs[childId][0]);
            } else { // 워크숍에 참석한 자식 노드를 추가한 경우
                costs[id][0] += costs[childId][1];
                costs[id][1] += costs[childId][1];
                
                // 자식 노드가 이미 참석했으므로 추가 요금 = 0
                extraCost = 0;
            }
        }
        
        costs[id][0] += extraCost;        
    }
    
    private static class Node {
        final int id;
        final int sale;
        final List<Integer> childIds = new ArrayList<>();
        
        public Node(int id, int sale) {
            this.id = id;
            this.sale = sale;
        }
    }
}