class Solution {
    private String pattern;
    private int[] result;
    private TreeSet<Integer> numbers;

    public String smallestNumber(String pattern) {
        this.pattern = pattern;
        result = new int[pattern.length() + 1];
        numbers = new TreeSet<>();
        
        numbers.addAll(List.of(1, 2, 3, 4, 5, 6, 7, 8, 9));

        for (int n = 1; n <= 9; n++) {
            numbers.remove(n);
            result[0] = n;

            if (backtracking(1)) {
                break;
            }

            numbers.add(n);
        }

        return Arrays.stream(result)
            .mapToObj(String::valueOf)
            .collect(Collectors.joining(""));
    }

    public boolean backtracking(int index) {
        // index == pattern.size()일 경우, 일치를 비교한다
        if (index == pattern.length() + 1) {
            return true;
        }

        // 'I'라면 result[index - 1]보다 큰 값만 넣는다
        // 'D'라면 result[index - 1]보다 작은 값만 넣는다
        SortedSet<Integer> candidates = (pattern.charAt(index - 1) == 'I') 
            ? numbers.tailSet(result[index - 1] + 1) 
            : numbers.headSet(result[index - 1]);

        for (int num : new ArrayList<>(candidates)) {
            numbers.remove(num);
            result[index] = num;

            if (backtracking(index + 1)) {
                return true;
            }

            numbers.add(num);
        }

        return false;
    }
}
