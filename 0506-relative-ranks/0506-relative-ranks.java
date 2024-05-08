class Solution {
    public String[] findRelativeRanks(int[] score) {
        // 인덱스와 점수를 매핑한다.
        Athlete[] athletes = new Athlete[score.length];
        for (int i = 0; i < score.length; i++) {
            athletes[i] = new Athlete(i, score[i]);
        }

        // 점수 내림차순으로 정렬한다.
        Arrays.sort(athletes, (a1, a2) -> a2.score - a1.score);

        // 정렬한 선수를 기준으로 순위표를 작성한다.
        // index -> rank로 치환하는데, index는 0부터 시작하므로 rank = index + 1 해준다.
        int[] ranks = new int[athletes.length];
        for (int r = 0; r < ranks.length; r++) {
            ranks[athletes[r].index] = r + 1;
        }

        // 순위표에서 1 -> Gold Medal, 2 -> Silver Medal, 3 -> Bronze Medal로 치환한다.
        String[] result = new String[ranks.length];
        for (int i = 0; i < result.length; i++) {
            String rank = String.valueOf(ranks[i]);
            switch(rank) {
                case "1":
                    result[i] = "Gold Medal";
                    break;
                case "2":
                    result[i] = "Silver Medal";
                    break;
                case "3":
                    result[i] = "Bronze Medal";
                    break;
                default:
                    result[i] = rank;
                    break;
            }
        }

        return result;
    }

    private static class Athlete {
        final int index;
        final int score;

        public Athlete(int index, int score) {
            this.index = index;
            this.score = score;
        }
    }
}
