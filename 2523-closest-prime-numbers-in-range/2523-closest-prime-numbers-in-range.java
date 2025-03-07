class Solution {
    /*
    # 문제 이해
    left와 right 사이에 있는 가장 가까운 소수 2개를 반환해야 한다(left <= x <= right)
    - 소수 2개이상 존재하는 경우에만 반환한다 없다면 [-1, -1]을 반환한다
    - 소수쌍들 중 가장 가까운 쌍을 반환한다(right - left == minimum) 

    # 접근
    - 최대 범위는 10^6이 될 수 있다 시간복잡도 O(NlogN)까지 가능하다
    - iterate하며 매번 소수를 검사하면 시간복잡도:N*sqrt(N)가 발생한다
        최대 10^6*10^3 = 10^9
        다른 방법을 모색해야 한다
    - 에라토스테네스의 체를 사용하면 된다 
        에라토스테네스의 체는 자기 자신의 배수만을 제거하므로 소수 제거시 logN밖에 안 든다
    
    # 구현 스텝
    1. 에라토스테네스의 체 알고리즘을 이용하여 소수 제외한 수를 체크한다
    2. 범위(left <= x <= right) 안의 소수들을 추출한다
    3. 소수들 중 가장 가까운 쌍을 찾는다
    4. 찾은 소수쌍(가장 가까운 소수쌍)을 반환한다
    */
    public int[] closestPrimes(int left, int right) {
        // 에라토스테네스의 체 알고리즘을 이용하여 소수 제외한 수를 체크
        boolean[] isComposite = new boolean[right + 1];
        isComposite[1] = true; // left 최소값이 1이므로

        for (int num = 2; num * num <= right; num++) {
            if (isComposite[num]) {
                continue;
            }

            for (int composite = num * 2; composite <= right; composite += num) {
                isComposite[composite] = true;
            }
        }

        // 범위 안의 소수들을 추출
        List<Integer> primesInRange = new ArrayList<>();
        
        for (int num = left; num <= right; num++) {
            if (!isComposite[num]) {
                primesInRange.add(num);
            }
        }

        // 가장 가까운 소수쌍을 찾는다
        int[] result = {-1, -1};
        int minDiff = right - left + 1;

        for (int i = 0; i + 1 < primesInRange.size(); i++) {
            int currentMinPrime = primesInRange.get(i);
            int currentMaxPrime = primesInRange.get(i + 1);
            int currentDiff =  currentMaxPrime - currentMinPrime;
            
            if (result[0] == -1 || minDiff > currentDiff) {
                result = new int[]{currentMinPrime, currentMaxPrime};
                minDiff = currentDiff;
            }
        }

        return result;
    }
}
