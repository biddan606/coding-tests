class Solution {
    public int[] kthSmallestPrimeFraction(int[] arr, int k) {
        PriorityQueue<Fraction> maxHeap = new PriorityQueue<>((f1, f2) -> Double.compare(f2.value, f1.value));

        for (int i = 0; i < arr.length; i++) {
            for (int j = i + 1; j < arr.length; j++) {
                Fraction current = new Fraction(arr[i], arr[j]);
                maxHeap.offer(current);
            }

            while (maxHeap.size() > k) {
                maxHeap.poll();   
            }
        }

        Fraction result = maxHeap.poll();
        return new int[]{result.numerator, result.denominator};
    }

    private static class Fraction {
        final int numerator;
        final int denominator;
        final double value;

        public Fraction(int numerator, int denominator) {
            this.numerator = numerator;
            this.denominator = denominator;
            this.value = numerator / (double) denominator;
        }
    }
}
