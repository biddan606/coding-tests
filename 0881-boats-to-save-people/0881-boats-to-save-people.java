class Solution {
    public int numRescueBoats(int[] people, int limit) {
        Arrays.sort(people);
        System.out.println(Arrays.toString(people));
        int boats = 0;
        int left = 0;
        int right = people.length - 1;
        
        while (left <= right) {
            int remainingWeight = limit - people[right];
            if (left < right && remainingWeight - people[left] >= 0) {
                left++;
            }

            right--;
            boats++;
        }

        return boats;
    }
}