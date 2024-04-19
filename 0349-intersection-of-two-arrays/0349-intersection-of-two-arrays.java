class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        Set<Integer> nums1Set = new HashSet<>();
        for (int n : nums1) {
            nums1Set.add(n);
        }

        Set<Integer> nums2Set = new HashSet<>();
        for (int n : nums2) {
            nums2Set.add(n);
        }

        List<Integer> intersection = new ArrayList<>();
        for (int e : nums1Set) {
            if (nums2Set.contains(e)) {
                intersection.add(e);
            }
        }

        return intersection.stream()
            .mapToInt(Integer::intValue)
            .toArray();
    }
}