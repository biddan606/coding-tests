class Solution {
    public int[] twoSum(int[] nums, int target) {
        Map<Integer, Integer> indexesByValue = new HashMap<>();

        for (int i = 0; i < nums.length; i++) {
            int currentValue = nums[i];

            Integer otherIndex = indexesByValue.get(target - currentValue);
            if (otherIndex != null) {
                return new int[]{i, otherIndex};
            }

            if (!indexesByValue.containsKey(currentValue)) {
                indexesByValue.put(currentValue, i);    
            }
        }
        
        return null;
    }
}
