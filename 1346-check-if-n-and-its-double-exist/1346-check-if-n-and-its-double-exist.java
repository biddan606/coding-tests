class Solution {
    public boolean checkIfExist(int[] arr) {
        Set<Integer> set = new HashSet<>();

        for (int number : arr) {
            if (set.contains(number * 2)
                || (number % 2 == 0 && set.contains(number / 2))) {
                return true;
            }
            set.add(number);
        }
        return false;
    }
}
