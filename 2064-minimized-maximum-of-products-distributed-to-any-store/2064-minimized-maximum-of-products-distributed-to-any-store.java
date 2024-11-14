class Solution {
    public int minimizedMaximum(int n, int[] quantities) {
        // n개의 소매점에 x개의 제품을 배포한다.
        // 제품이 남는다면, x를 올린다.
        // 제품이 부족하거나 충분하다면, x를 낮춘다
        int left = 1;
        int right = 100_000;
        
        while (left < right) {
            int mid = left + ((right - left) / 2);
            int partitions = 0;

            for (int quantity : quantities) {
                partitions += (quantity + mid - 1) / mid;
            }

            if (partitions > n) {
                left = mid + 1;
            } else { // partitions <= n
                right = mid;
            }
        }

        // x의 최대 지점을 반환한다.
        return left;
    }
}
