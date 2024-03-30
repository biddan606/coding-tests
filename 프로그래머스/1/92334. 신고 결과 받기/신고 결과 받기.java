import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] reports, int k) {
        // 유저 닉네임과 아이디 번호를 매핑시킨다.
        Map<String, Integer> intIds = new HashMap<>();
        for (int i = 0; i < id_list.length; i++) {
            intIds.put(id_list[i], i);
        }
        
        // 신고한다. 신고는 번호를 기준으로 한다.
        Map<Integer, Set<Integer>> reportersMap = new HashMap<>();
        for (int i = 0; i < id_list.length; i++) {
            reportersMap.put(i, new HashSet<>());
        }
        
        for (String report : reports) {
            String[] tokens = report.split(" ");
            String reporterNickname = tokens[0];
            String targetNickname = tokens[1];
            
            int reporterId = intIds.get(reporterNickname);
            int targetId = intIds.get(targetNickname);
            
            Set<Integer> reporters = reportersMap.get(targetId);
            reporters.add(reporterId);
        }
        
        // 결과를 도출하여 반환한다.
        int[] result = new int[id_list.length];
        for (Set<Integer> reporters : reportersMap.values()) {
            if (reporters.size() < k) {
                continue;
            }
            
            for (int reporter : reporters) {
                result[reporter]++;
            }
        }
        
        return result;
    }
}
