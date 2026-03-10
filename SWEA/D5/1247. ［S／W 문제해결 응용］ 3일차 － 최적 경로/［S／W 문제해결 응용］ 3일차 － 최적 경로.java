import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/*
# 문제
office에서 시작해서 다른 Customer Point를 훑은 뒤, 모든 customer들을 훑었다면, 마지막 customer와 home으로 돌아와야 한다.
이 경로의 최던 거리 값을 출력해야 한다.

# 접근
DFS로 모든 경로를 훑어도 충분하다.
단 항상 시작은 office여야 하고, 마지막은 home이어야 한다.

# 구현
1. home에서 DFS를 시작한다.
2. DFS시에는 모든 customer를 훑어야 한다. 순서는 상관없다.
3. 모든 customer를 훑었다면, 마지막 customer와 home을 비교한다.
4. 비교한 값이 최단거리라면 갱신한다.
5. 완전탐색을 마친 뒤, 최단 거리를 출력한다.
 */
class Solution {

    static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    static int customerCount;
    static Point office;
    static Point home;
    static Point[] customers;
    static boolean[] visited;
    static int minDistance;

    public static void main(String[] args) throws Exception {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int testCaseCount = Integer.parseInt(reader.readLine());

        for (int testCase = 1; testCase <= testCaseCount; testCase++) {
            customerCount = Integer.parseInt(reader.readLine());

            StringTokenizer tokenizer = new StringTokenizer(reader.readLine());

            office = new Point(
                    Integer.parseInt(tokenizer.nextToken()),
                    Integer.parseInt(tokenizer.nextToken())
            );

            home = new Point(
                    Integer.parseInt(tokenizer.nextToken()),
                    Integer.parseInt(tokenizer.nextToken())
            );

            customers = new Point[customerCount];
            for (int i = 0; i < customerCount; i++) {
                customers[i] = new Point(
                        Integer.parseInt(tokenizer.nextToken()),
                        Integer.parseInt(tokenizer.nextToken())
                );
            }

            visited = new boolean[customerCount];
            minDistance = Integer.MAX_VALUE;

            dfs(office, 0, 0);

            System.out.println("#" + testCase + " " + minDistance);
        }
    }

    static void dfs(Point currentPoint, int visitedCount, int totalDistance) {
        if (totalDistance >= minDistance) {
            return;
        }

        if (visitedCount == customerCount) {
            totalDistance += getDistance(currentPoint, home);
            minDistance = Math.min(minDistance, totalDistance);
            return;
        }

        for (int i = 0; i < customerCount; i++) {
            if (visited[i]) {
                continue;
            }

            visited[i] = true;
            dfs(
                    customers[i],
                    visitedCount + 1,
                    totalDistance + getDistance(currentPoint, customers[i])
            );
            visited[i] = false;
        }
    }

    static int getDistance(Point a, Point b) {
        return Math.abs(a.x - b.x) + Math.abs(a.y - b.y);
    }
}
