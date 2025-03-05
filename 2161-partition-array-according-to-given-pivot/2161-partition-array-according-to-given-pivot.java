class Solution {
    public int[] pivotArray(int[] nums, int pivot) {
        List<Integer> lessThan = new ArrayList<>();
        List<Integer> equal = new ArrayList<>();
        List<Integer> greatThan = new ArrayList<>();

        for (int num : nums) {
            if (num < pivot) {
                lessThan.add(num);
            } else if (num > pivot) {
                greatThan.add(num);
            } else { // num == pivot
                equal.add(num);
            }
        }

        return Stream.of(lessThan, equal, greatThan)
            .flatMap(List::stream)
            .mapToInt(Integer::intValue)
            .toArray();
    }
}
