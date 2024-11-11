class Solution {
    public boolean primeSubOperation(int[] nums) {
        TreeSet<Integer> primes = new TreeSet<>();
        
        // 소수 구하기
        for (int n = 2; n < 1000; n++) {
            boolean divided = false;
            
            int sqrtN = (int)Math.sqrt(n);

            // n의 제곱근 이하의 소수들 가져오기
            for (Integer prime : primes.headSet(sqrtN + 1)) {
                if (n % prime == 0) {
                    divided = true;
                    break;
                }
            }
    
            if (!divided) {
                primes.add(n);
            }
        }

        // 역순 검사
        /*
        nums[i - 1] >= nums[i]를 만족할 경우,  
        num[i - 1] < nums[i]를 만족할 수 있게 nums[i - 1] -= prime
        */
        for (int i = nums.length - 1; i - 1 >= 0; i--) {
            if (nums[i - 1] >= nums[i]) {
                Integer higher = primes.higher(nums[i - 1] - nums[i]);
                if (higher == null || higher >= nums[i - 1]) {
                    return false;
                }

                nums[i - 1] -= higher;
            }
        }
        return true;
    }
}
