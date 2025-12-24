class Solution {
    public int minimumBoxes(int[] apple, int[] capacity) {
        int totalApples = 0;

        for (int a : apple) {
            totalApples += a;
        }   

        int[] capacityDesc = Arrays.stream(capacity)
            .boxed()
            .sorted(Comparator.reverseOrder())
            .mapToInt(Integer::intValue)
            .toArray();

        int remaingApples = totalApples;
        int usedBoxes = 0;

        for (int c : capacityDesc) {
            remaingApples -= c;
            usedBoxes++;

            if (remaingApples <= 0) {
                break;
            }
        }

        return usedBoxes;
    }
}
