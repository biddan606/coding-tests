class Solution {

    public int beautifulSubsets(int[] nums, int k) {
        Arrays.sort(nums);
        return dfs(nums, 0, k, new HashSet<>());
    }

    private int dfs(int[] nums, int index, int diff, Set<Integer> subset) {
        if (index == nums.length) {
            return subset.isEmpty() ? 0 : 1;
        }

        int count = 0;
        count += dfs(nums, index + 1, diff, subset);
        if (!subset.contains(nums[index] - diff)) {
            subset.add(nums[index]);
            count += dfs(nums, index + 1, diff, subset);
            subset.remove(nums[index]);
        }

        return count;
    }
}
