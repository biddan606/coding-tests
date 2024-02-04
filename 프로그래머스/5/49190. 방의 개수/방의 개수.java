import java.util.*;

class Solution {
    private static final int[] dx = {0, 1, 1, 1, 0, -1, -1, -1};
    private static final int[] dy = {-1, -1, 0, 1, 1, 1, 0, -1};
    
    public int solution(int[] arrows) {
        int count = 0;
        
        Map<String, Vertex> vertices = new HashMap<>();
        Vertex v = new Vertex(0, 0);
        vertices.put(v.id, v);
        
        for (int arrow : arrows) {
            /*
            Z 일 때 대각선으로 이동하면 2개의 방이 만들어집니다.
            1개의 arrow가 2칸씩 이동하는 걸로 인지하여 1칸 이동시 1개의 방만 만들어질 수 있게 합니다.
            */
            for (int i = 0; i < 2; i++) {
                int x = v.x + dx[arrow];
                int y = v.y + dy[arrow];
                String id = Vertex.id(x, y);
                
                if (!vertices.containsKey(id)) {
                    vertices.put(id, new Vertex(x, y));
                } else if (!v.connectedVertices.contains(id)) {
                    count++;
                }
                
                Vertex u = vertices.get(id);
                
                u.connectedVertices.add(v.id);
                v.connectedVertices.add(u.id);
                v = u;
            }
        }
        
        return count;
    }
    
    private static class Vertex {
        public final int x;
        public final int y;
        public final String id;
        public final Set<String> connectedVertices = new HashSet<>();
        
        public Vertex(int x, int y) {
            this.x = x;
            this.y = y;
            this.id = id(x, y);
        }
        
        public static String id(int x, int y) {
            return x + ", " + y;
        }
    }
}