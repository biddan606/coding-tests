class Solution {
    public double maxAverageRatio(int[][] classes, int extraStudents) {
        PriorityQueue<Element> pq = new PriorityQueue<>((a, b) -> {
            double aExpectedValue = (a.pass + 1) / (double)(a.total + 1) - a.pass / (double)a.total;
            double bExpectedValue = (b.pass + 1) / (double)(b.total + 1) - b.pass / (double)b.total;
            if (aExpectedValue > bExpectedValue) {
                return -1;
            }
            return 1;
        });
        for (int[] c : classes) {
            pq.offer(new Element(c[0], c[1]));
        }

        for (int i = 0; i < extraStudents; i++) {
            Element e = pq.poll();

            Element newE = new Element(e.pass + 1, e.total + 1);
            pq.offer(newE);
        }

        double result = 0d;
        while (!pq.isEmpty()) {
            Element e = pq.poll();

            System.out.printf("pass: %d, total: %d%n", e.pass, e.total);
            result += e.pass / (double) e.total;
        }

        return result / classes.length;
    }

    private static class Element {
        final int pass;
        final int total;

        public Element(int pass, int total) {
            this.pass = pass;
            this.total = total;
        }
    }
}
