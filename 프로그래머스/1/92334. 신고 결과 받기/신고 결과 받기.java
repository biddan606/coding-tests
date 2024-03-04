import java.util.*;
import java.util.stream.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        Map<String, Set<String>> reports = new HashMap<>();
        for (String id : id_list) {
            reports.put(id, new HashSet<>());
        }
        
        Map<String, Integer> reported = new HashMap<>();
        
        // 신고당한 횟수를 누적시킨다.
        for (String r : report) {
            String[] tokens = r.split(" ");
            String reporter = tokens[0];
            String target = tokens[1];
            
            Set<String> targets = reports.get(reporter);
            if (targets.contains(target)) {
                continue;
            }
            
            targets.add(target);
            reported.put(target, reported.getOrDefault(target, 0) + 1);
        }
        
        // 밴할 유저들을 얻는다.
        Set<String> banned = reported.keySet().stream()
            .filter(key -> reported.get(key) >= k)
            .collect(Collectors.toSet());
        
        // 밴한 유저가 몇명인지 센다
        return Arrays.stream(id_list)
            .mapToInt(id -> (int) reports.get(id).stream()
                     .filter(banned::contains)
                     .count())
            .toArray();
            
    }
}