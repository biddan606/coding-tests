class Solution {
    public int[] sortJumbled(int[] mapping, int[] nums) {
        // mapping으로 숫자를 매핑한다.
        List<MappedNumber> mappedNumbers = new ArrayList<>();
        for (int n : nums) {
            int changed = change(mapping, n);
            mappedNumbers.add(new MappedNumber(n, changed));
        }

        // 매핑된 숫자를 정렬한다.
        mappedNumbers.sort((a, b) -> a.changed - b.changed);

        // 정렬된 리스트에서 오리지널 숫자를 추출하여 반환한다.
        return mappedNumbers.stream()
            .mapToInt(m -> m.original)
            .toArray();
    }

    private int change(int[] mapping, int number) {
        // 문자열로 변환한다.
        String numberToStr = String.valueOf(number);

        // 문자열을 순회하며, mapping 값으로 변경한다.
        int changed = 0;
        for (char c : numberToStr.toCharArray()) {
            changed *= 10;
            changed += mapping[c - '0'];
        }

        return changed;
    }

    private class MappedNumber {
        final int original;
        final int changed;

        MappedNumber(int original, int changed) {
            this.original = original;
            this.changed = changed;
        }
    }
}
