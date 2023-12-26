class Solution {

    public int solution(int n) {
        String base3 = Integer.toString(n, 3);

        String reversedBase3 = new StringBuilder(base3)
                .reverse().toString();

        return Integer.parseInt(reversedBase3, 3);
    }
}
