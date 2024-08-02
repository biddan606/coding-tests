import java.util.HashMap;
import java.util.Map;

class Solution {
    /**
     * 자카드 유사도를 구해야 한다.
     * 자카드 유사도 = (A, B의 교집합) / (A, B의 합집합)
     * A, B 모두 공집합이면, (A, B의 합집합) = 0이 되어 나눗셈이 정의되지 않으므로 1로 친다.
     * 각 집합에는 중복값이 들어 있을 수 있다.
     * 중복값이 V라 했을 때, 교집합 = min(A에서의 V 원소 개수, B에서의 V 원소 개수), 합집합 = max(A에서의 V 원소 개수, B에서의 V 원소 개수)이다.
     */
    public int solution(String str1, String str2) {
        // 각 집합을 만든다. key: 문자열, value: 문자열의 개수
        Map<String, Integer> a = createMap(str1);
        Map<String, Integer> b = createMap(str2);

        // A를 기준으로 교집합을 구한다.
        Map<String, Integer> intersection = createInterSection(a, b);
        // 합집합을 구한다.
        Map<String, Integer> union = createUnion(a, b);

        // 자카드 유사도를 구한 뒤, 값을 반환한다.
        // 반환값: (int) (유사도 * 65536)
        double similarity = getSimilarity(intersection, union);
        return (int) (similarity * 65536);
    }

    private Map<String, Integer> createMap(String str) {
        String lowerCase = str.toLowerCase();
        Map<String, Integer> result = new HashMap<>();

        for (int i = 0; i + 1 < lowerCase.length(); i++) {
            char first = lowerCase.charAt(i);
            char second = lowerCase.charAt(i + 1);

            if (Character.isLowerCase(first) && Character.isLowerCase(second)) {
                String key = "" + first + second;
                result.put(key, result.getOrDefault(key, 0) + 1);
            }
        }

        return result;
    }

    private Map<String, Integer> createInterSection(Map<String, Integer> a, Map<String, Integer> b) {
        Map<String, Integer> result = new HashMap<>();

        for (String key : a.keySet()) {
            if (b.containsKey(key)) {
                result.put(key, Math.min(a.get(key), b.get(key)));
            }
        }

        return result;
    }

    private Map<String, Integer> createUnion(Map<String, Integer> a, Map<String, Integer> b) {
        Map<String, Integer> result = new HashMap<>(a);

        b.forEach((key, value) -> result.merge(key, value, Math::max));

        return result;
    }

    private double getSimilarity(Map<String, Integer> intersection, Map<String, Integer> union) {
        if (union.isEmpty()) {
            return 1.0;
        }

        int intersectionValuesSum = intersection.values()
                .stream().mapToInt(Integer::intValue)
                .sum();
        int unionValuesSum = union.values()
                .stream().mapToInt(Integer::intValue)
                .sum();
        
        return intersectionValuesSum / (double) unionValuesSum;
    }
}
