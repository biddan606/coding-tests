class Solution {
    public int[] intersect(int[] nums1, int[] nums2) {
        Map<Integer, Integer> numberCountOfnums1 = new HashMap<>();
        for (int number : nums1) {
            numberCountOfnums1.put(number, numberCountOfnums1.getOrDefault(number, 0) + 1);
        }

        List<Integer> result = new ArrayList<>();
        for (int number : nums2) {
            int numberCount = numberCountOfnums1.getOrDefault(number, 0);

            if (numberCount > 0) {
                result.add(number);
                numberCountOfnums1.put(number, numberCount - 1);
            }
        }

        return result.stream()
            .mapToInt(Integer::intValue)
            .toArray();
    }
}
