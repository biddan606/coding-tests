import java.util.ArrayList;
import java.util.List;

class Solution {

    private int result;

    public int solution(int n, int[] weak, int[] dist) {
        result = Integer.MAX_VALUE;

        int[][] weakStraights = getWeakStraights(weak, n);

        repair(dist, new boolean[dist.length], new ArrayList<>(), weakStraights);

        if (result <= dist.length) {
            return result;
        }
        return -1;
    }

    private int[][] getWeakStraights(int[] weak, int wallLength) {
        int[][] weakStraights = new int[weak.length][weak.length];

        for (int i = 0; i < weak.length; i++) {
            for (int j = i; j < i + weak.length; j++) {
                weakStraights[i][j - i] = weak[j % weak.length] + (j / weak.length) * wallLength;
            }
        }
        return weakStraights;
    }

    private void repair(int[] distances, boolean[] usedDistances, List<Integer> createdDistance,
            int[][] weakStraights) {
        if (createdDistance.size() == distances.length) {
            for (int[] weakStraight : weakStraights) {
                repair(weakStraight, createdDistance);
            }
        }

        for (int i = 0; i < distances.length; i++) {
            if (usedDistances[i]) {
                continue;
            }

            usedDistances[i] = true;
            createdDistance.add(distances[i]);

            repair(distances, usedDistances, createdDistance, weakStraights);

            createdDistance.remove(createdDistance.size() - 1);
            usedDistances[i] = false;
        }
    }

    private void repair(int[] weakStraight, List<Integer> distance) {
        int weakIndex = 0;
        int distanceIndex = 0;

        while (weakIndex < weakStraight.length && distanceIndex < distance.size()) {
            int nextWeakIndex = weakIndex + 1;
            int startWeakPoint = weakStraight[weakIndex];

            while (nextWeakIndex < weakStraight.length) {
                if (startWeakPoint + distance.get(distanceIndex) < weakStraight[nextWeakIndex]) {
                    break;
                }

                nextWeakIndex++;
            }

            weakIndex = nextWeakIndex;
            distanceIndex++;
        }

        if (weakIndex == weakStraight.length) {
            result = Math.min(distanceIndex, result);
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();

        int n = 12;
        int[] week = {1, 5, 6, 10};
        int[] dist = {1, 2, 3, 4};

        int result = solution.solution(n, week, dist);

        System.out.println(result);
    }
}
