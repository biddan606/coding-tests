import java.util.*;

class Solution {
    /*
    # 문제 이해
    (r1, c1) -> (r2, c2)로 가는 로봇들이 있다
    이 로봇들이 겹치는 영역의 개수를 반환해야 한다
    여러 로봇이 특정 초에 같은 영역이라면 1번 겹치는 것이고, 나뉘어서 겹치면 나뉜만큼 횟수로 친다
    즉, 특정 초에 겹친 영역당 1번으로 친다
    
    # 접근
    - 최적화로 가는 방법은 단순하다. row 좌표를 목표로 맞춘 뒤, column 좌표를 맞추면 된다
    - 좌표로 이동하며 겹친 영역의 개수만 체크하면 된다
    - 마지막 위치에서 중복 체크 후 종료된다
    
    - routes의 개수와 길이는 100이다 
        최적화로 가장 길게 가는 방법이 200이니 
        100개의 로봇이 100개의 좌표를 최적해서 간다하면 100^3이다(단순 구현)
    
    # 구현 스텝
    1. 각 로봇을 시작 위치(routes[i][0])에 배치시킨다
    2. 배치시킨 로봇들을 Queue에 넣는다
    3. 겹친 횟수(overlapCount)를 저장할 변수를 선언한다
    4. Queue의 모든 로봇이 없어질 때까지 순회한다
        5. 1번 순회할 때마다 Queue의 크기만큼 순회한다(모든 로봇들이 1번씩 움직이게)
        6. 순회 하기 전, 겹치는 걸 판별할 Map<String, Integer>를 선언한다
            value가 2가 될 때만 overlapCount를 증가시킨다(나머지는 중복이므로 X)
            7. 로봇은 현재 위치를 Map에 기록하고 value가 2가 되면 겹친 횟수를 증가시킨다
            8. 현재 위치가 target_point라면 next_target_point를 바라본다
            9-1. next_target_point가 없다면 Queue에 넣지 않는다
            9-2. next_target_point가 있다면 row를 맞춘다. row가 맞다면 col을 맞춘다
    10. 겹친 횟수를 반환한다
    */
    public int solution(int[][] points, int[][] routes) {
        // 로봇 큐에 넣기
        Queue<Robot> robots = new ArrayDeque<>();
        for (int[] route : routes) {
            // 로봇 생성
            int[] startPoint = points[route[0] - 1];
            
            Robot newRobot = new Robot(new int[]{startPoint[0], startPoint[1]});
            for (int i = 1; i < route.length; i++) { // 첫 번째 포인트(시작 위치)는 건너뜀
               int[] targetPoint = points[route[i] - 1];
                newRobot.targetPoints.addLast(new int[]{targetPoint[0], targetPoint[1]});
            }
            
            robots.offer(newRobot);
        }
        
        // 로봇을 타겟 위치로 전진하며 겹친 횟수 세기
        int overlapCount = 0;
        while (!robots.isEmpty()) {
            Map<String, Integer> locations = new HashMap<>();
            int size = robots.size();
            
            for (int i = 0; i < size; i++) {
                Robot robot = robots.poll();
                String location = robot.point[0] + " " + robot.point[1];
                
                
                int locationCount = locations.getOrDefault(location, 0);
                if (locationCount == 1) {
                    overlapCount++;
                }
                locations.put(location, locationCount + 1);
                
                int[] targetPoint = robot.targetPoints.getFirst();
                
                if (targetPoint[0] == robot.point[0] && targetPoint[1] == robot.point[1]) {
                    robot.targetPoints.removeFirst();
                    
                    if (robot.targetPoints.isEmpty()) {
                        continue;
                    }
                    targetPoint = robot.targetPoints.getFirst();
                }
                
                if (robot.point[0] != targetPoint[0]) {
                    int deltaRow = robot.point[0] < targetPoint[0] ? 1 : -1;
                    robot.point[0] += deltaRow;
                } else { // row가 같을 경우
                    int deltaCol = robot.point[1] < targetPoint[1] ? 1 : -1;
                    robot.point[1] += deltaCol;
                }
                
                robots.offer(robot);
            }
        }
        
        return overlapCount;
    }
    
    private static class Robot {
        final int[] point;
        final Deque<int[]> targetPoints = new ArrayDeque<>();
        
        private Robot(int[] point) {
            this.point = point;
        }
    }
}
