class Solution {
    public double mincostToHireWorkers(int[] quality, int[] wage, int k) {
        int size = quality.length;
        
        Worker[] workers = new Worker[size];
        for (int i = 0; i < size; i++) {
            workers[i] = new Worker(quality[i], wage[i]);
        }
        
        /**
         * 가장 낮은 금액으로 만들어지는 k명의 그룹을 형성한다.
         * wage이상의 금액을 받아야만 그룹에 참여할 수 있다.
         * 그룹에 참여할 때 할당되는 금액은 workers[i].ratio * quality 값이다.
         */
        // 가장 낮은 금액으로 k명을 채워야 하므로 quality가 낮은 순으로 정렬한다.
        Arrays.sort(workers, (w1, w2) -> w1.quality - w2.quality);
        
        double min = Double.MAX_VALUE;
        for (int i = 0; i < size; i++) {
            double standardRatio = workers[i].ratio;
            double current = workers[i].wage;
            int count = 1;

            for (int j = 0; j < size && count < k; j++) {
                // workers[i] 값이 중복되어 추가되지 않도록 한다.
                if (i == j) {
                    continue;
                }
                // 기준 비율보다 높은 비율이라면, 그룹에 들어올 수 없다.(기준 비율로 계산된 임금이 최저 임금보다 낮기 떄문에)
                if (standardRatio < workers[j].ratio) {
                    continue;
                }

                current += standardRatio * workers[j].quality;
                count++;
            }

            // k명 미만의 그룹이 형성되었다면 미달이다.
            if (count < k) {
                continue;
            }
            
            min = Math.min(min, current);
        }

        return min;
    }

    private static class Worker {
        final int quality;
        final int wage;
        final double ratio;

        public Worker(int quality, int wage) {
            this.quality = quality;
            this.wage = wage;
            this.ratio = wage / (double) quality;
        }
    }
}
