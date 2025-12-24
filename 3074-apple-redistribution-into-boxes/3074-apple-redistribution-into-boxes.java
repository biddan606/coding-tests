class Solution {
    public int minimumBoxes(int[] apple, int[] capacity) {   
        int[] capacityDesc = Arrays.stream(capacity)
            .boxed()
            .sorted(Comparator.reverseOrder())
            .mapToInt(Integer::intValue)
            .toArray();

        int remaingApples = 0;

        for (int a : apple) {
            remaingApples += a;
        }

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
