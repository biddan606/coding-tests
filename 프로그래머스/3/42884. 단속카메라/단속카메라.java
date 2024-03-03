import java.util.*;

class Solution {
    public int solution(int[][] routes) {
        int[][] sortedRoutes = Arrays.stream(routes)
            .map(r -> Arrays.copyOf(r, r.length))
            .toArray(int[][]::new);
        
        Arrays.sort(sortedRoutes, (a, b) -> a[1] - b[1]);
            
        int count = 1;
        int lastCamera = sortedRoutes[0][1];
        
        for (int i = 1; i < sortedRoutes.length; i++) {
            /*
            다음에 오는 끝점은 이전 끝점보다 항상 크다.
            끝점을 기준으로 정렬했기 때문에
            그러므로 시작점이 이전에 설치한 카메라보다 큰지만 확인해보면 된다.
            */
            if (sortedRoutes[i][0] <= lastCamera) {
                continue;
            }
            
            count++;
            lastCamera = sortedRoutes[i][1];
        }
        
        return count;
    }
}
