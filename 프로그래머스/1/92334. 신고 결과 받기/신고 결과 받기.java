import java.util.*;

class Solution {
    public int[] solution(String[] id_list, String[] report, int k) {
        Map<String, Integer> reported = new HashMap<>();
        for (String id : id_list) {
            reported.put(id, 0);
        }
        
        Map<String, Set<String>> reportMap = new HashMap<>();
        for (String id : id_list) {
            reportMap.put(id, new HashSet<>());
        }
        
        // 신고당한 횟수를 누적시킨다.
        for (String line : report) {
            String[] splited = line.split(" ");
            String from = splited[0];
            String to = splited[1];
            
            if (reportMap.get(from).contains(to)) {
                continue;
            }
            
            reportMap.get(from).add(to);
            reported.put(to, reported.get(to) + 1);
        }
        
        // 유저를 정지시킨다.
        Set<String> stopped = new HashSet<>();
        for (Map.Entry<String,Integer> entry : reported.entrySet()) {
            if (entry.getValue() < k) {
                continue;
            }
            
            stopped.add(entry.getKey());
        }
        
        // 정지시킨 유저의 개수를 구한다.
        int[] notificationCounts = new int[id_list.length];
        for (int i = 0; i < id_list.length; i++) {
            String id = id_list[i];
            
            for (String reportedUser : reportMap.get(id)) {
                if (stopped.contains(reportedUser)) {
                    notificationCounts[i]++;
                }
            }
        }
        
        return notificationCounts;
    }
}