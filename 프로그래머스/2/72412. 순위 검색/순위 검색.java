import java.util.*;
import java.util.function.*;

class Solution {
    public int[] solution(String[] info, String[] query) {
        // 조건별 점수들을 생성한다.
        Map<String, List<Integer>> scoresMap = buildScoresMap(info);
        
        // map의 values들을 오름차순 정렬한다.
        for (List<Integer> list : scoresMap.values()) {
            Collections.sort(list);
        }

        // 변환된 query들로 result를 얻는다.
        int[] result = new int[query.length];
        for (int i = 0; i < result.length; i++) {
            String[] tokens = query[i].split(" (and)?");
            String key = String.join("", Arrays.copyOf(tokens, tokens.length - 1));
            int score = Integer.parseInt(tokens[tokens.length - 1]);
            
            result[i] = count(scoresMap, key, score);
        }
        return result;
    }
    
    private Map<String, List<Integer>> buildScoresMap(String[] info) {
        Map<String, List<Integer>> scoresMap = new HashMap<>();
        
        for (String applicant : info) {
            String[] tokens = applicant.split(" ");
            int score = Integer.parseInt(tokens[tokens.length - 1]);
            
            forEachKey(0, "", tokens, key -> {
                scoresMap.computeIfAbsent(key, k -> new ArrayList<>())
                    .add(score);
            });
        }
        
        return scoresMap;
    }
    
    private void forEachKey(int index, String prefix, String[] tokens, Consumer<String> action) {
        if (index == tokens.length - 1) {
            action.accept(prefix);
            return;
        }
        
        forEachKey(index + 1, prefix + tokens[index], tokens, action);
        forEachKey(index + 1, prefix + "-", tokens, action);
    }
    
    private int count(Map<String, List<Integer>> scoresMap, String key, int score) {
        if (!scoresMap.containsKey(key)) {
            return 0;
        }
        List<Integer> scores = scoresMap.get(key);
        
        return scores.size() - binarySearch(scores, score);
    }
    
    private int binarySearch(List<Integer> scores, int score) {
        int start = 0;
        int end = scores.size() - 1;
        
        while (end > start) {
            int mid = (start + end) / 2;
            
            if (scores.get(mid) >= score) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        
        if (scores.get(start) < score) {
            return scores.size();
        }
        return start;
    }
}

/*
:: info ::
"java backend junior pizza 150",
"python frontend senior chicken 210",
"python frontend senior chicken 150",
"cpp backend senior pizza 260",
"java backend junior chicken 80",
"python backend senior chicken 50"

- - - - : 50, 80, 150, 150, 210, 260
java - - - : 80, 150
python - - - : 50, 150, 210
cpp - - - : 260
...

:: query ::
"java and backend and junior and pizza 100",
"python and frontend and senior and chicken 200",
"cpp and - and senior and pizza 250",
"- and backend and senior and - 150",
"- and - and - and chicken 100"
*/