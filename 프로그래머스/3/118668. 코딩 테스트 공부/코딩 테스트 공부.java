import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.function.ToIntFunction;

class Solution {

    public int solution(int initialAlp, int initialCop, int[][] rawProblems) {
        adjustProblemLevel(rawProblems, initialAlp, initialCop);

        List<Problem> problems = convertToProblems(rawProblems);
        addBasicProblems(problems);

        return calculateMinTime(problems);
    }

    private void adjustProblemLevel(int[][] problems, int alpToAdjust, int copToAdjust) {
        for (int i = 0; i < problems.length; i++) {
            problems[i][0] = Math.max(0, problems[i][0] - alpToAdjust);
            problems[i][1] = Math.max(0, problems[i][1] - copToAdjust);
        }
    }

    private List<Problem> convertToProblems(int[][] rawProblems) {
        List<Problem> problems = new ArrayList<>();

        for (int[] p : rawProblems) {
            problems.add(new Problem(p[0], p[1], p[2], p[3], p[4]));
        }

        return problems;
    }

    private void addBasicProblems(List<Problem> problems) {
        problems.add(new Problem(0, 0, 1, 0, 1));
        problems.add(new Problem(0, 0, 0, 1, 1));
    }

    private int calculateMinTime(List<Problem> problems) {
        int targetAlp = getMax(problems, p -> p.alp);
        int targetCop = getMax(problems, p -> p.cop);

        int[][] minTimes = initializeDp(targetAlp, targetCop);

        Queue<State> queue = new ArrayDeque<>();
        queue.offer(new State(0, 0, 0));

        while (!queue.isEmpty()) {
            State current = queue.poll();

            for (Problem problem : problems) {
                if (!current.canSolve(problem)) {
                    continue;
                }

                State next = current.solve(problem);
                // 최대값을 넘지 못함
                int nextAlp = Math.min(next.alp, targetAlp);
                int nextCop = Math.min(next.cop, targetCop);

                if (next.time < minTimes[nextAlp][nextCop]) {
                    minTimes[nextAlp][nextCop] = next.time;
                    queue.offer(new State(nextAlp, nextCop, next.time));
                }
            }
        }

        return minTimes[targetAlp][targetCop];
    }

    private int getMax(List<Problem> problems, ToIntFunction<Problem> getter) {
        return problems.stream()
                .mapToInt(getter)
                .max()
                .orElse(0);
    }

    private int[][] initializeDp(int targetAlp, int targetCop) {
        int[][] dp = new int[targetAlp + 1][targetCop + 1];

        for (int[] row : dp) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        dp[0][0] = 0;

        return dp;
    }

    private static class Problem {

        final int alp;
        final int cop;
        final int alpRwd;
        final int copRwd;
        final int cost;

        public Problem(int alp, int cop, int alpRwd, int copRwd, int cost) {
            this.alp = alp;
            this.cop = cop;
            this.alpRwd = alpRwd;
            this.copRwd = copRwd;
            this.cost = cost;
        }
    }

    private static class State {

        final int alp;
        final int cop;
        final int time;

        State(int alp, int cop, int time) {
            this.alp = alp;
            this.cop = cop;
            this.time = time;
        }

        public boolean canSolve(Problem problem) {
            return problem.alp <= alp && problem.cop <= cop;
        }

        public State solve(Problem problem) {
            return new State(
                    this.alp + problem.alpRwd,
                    this.cop + problem.copRwd,
                    this.time + problem.cost
            );
        }
    }
}
