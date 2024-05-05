class Solution {
    public int solution(int n, int k) {
        return n * 12_000 + (k - (n / 10)) * 2_000;
    }
}