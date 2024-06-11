class Solution {
    public int[] relativeSortArray(int[] arr1, int[] arr2) {
        Map<Integer, Integer> weightMap = new HashMap<>();
        for (int i = 0; i < arr2.length; i++) {
            weightMap.put(arr2[i], i);
        }

        return Arrays.stream(arr1)
                    .boxed()
                    .sorted((a, b) -> {
                        int aWeight = weightMap.getOrDefault(a, (a + 1) * 1001);
                        int bWeight = weightMap.getOrDefault(b, (b + 1) * 1001);

                        return aWeight - bWeight;
                    })
                    .mapToInt(Integer::intValue)
                    .toArray();
    }
}
