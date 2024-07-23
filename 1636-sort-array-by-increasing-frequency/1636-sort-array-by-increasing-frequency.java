class Solution {
    public int[] frequencySort(int[] nums) {
        // 숫자의 빈도를 계산한다.
        Map<Integer, Integer> numberFrequency = new HashMap<>();
        for (int num : nums) {
            numberFrequency.put(num, numberFrequency.getOrDefault(num, 0) + 1);
        }

        // 숫자의 빈도수 기준으로 오름차 순 정렬, 빈도수가 같다면 숫자 크기 기준 내림차순 정렬
        int[] sortedNums = numberFrequency.entrySet().stream()
            .sorted((e1, e2) -> {
                int frequencyCompareResult = e1.getValue().compareTo(e2.getValue());

                if (frequencyCompareResult == 0) {
                    return e2.getKey().compareTo(e1.getKey());
                }
                return frequencyCompareResult;
            })
            .mapToInt(Map.Entry::getKey)
            .toArray();

        // 정렬된 숫자를 기준으로 숫자를 생성한다.
        // 숫자는 해당 숫자의 빈도수만큼 생성된다.
        List<Integer> result = new ArrayList<>();
        for (int num : sortedNums) {
            int repeat = numberFrequency.get(num);
            
            IntStream.range(0, repeat)
                .forEach(c -> result.add(num));
        }

        // 결과를 반환한다.
        return result.stream()
            .mapToInt(Integer::intValue)
            .toArray();
    }
}
