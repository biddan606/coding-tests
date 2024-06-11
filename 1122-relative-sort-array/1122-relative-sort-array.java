class Solution {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        Map<Integer, Integer> weightMap = new HashMap<>();
        for (int i = 0; i < arr2.length; i++) {
            weightMap.put(arr2[i], i);
        }

        return Arrays.stream(arr1)
                     .boxed()
                     .sorted((a, b) -> compareByWeight(a, b, weightMap))
                     .mapToInt(Integer::intValue)
                     .toArray();
    }

    private int compareByWeight(Integer a, Integer b, Map<Integer, Integer> weightMap) {
        int aWeight = weightMap.getOrDefault(a, (a + 1) * 1001);
        int bWeight = weightMap.getOrDefault(b, (b + 1) * 1001);
        return Integer.compare(aWeight, bWeight);
    }
}
