import java.util.*;

class Solution {
    /*
    # 문제 이해
    1초(1_000밀리초)간 처리하는 요청의 최대 개수를 반환해야 한다
    
    # 풀이 접근
    lines는 최대 2_000
    
    0.001 <= T <= 3.000
    -> T를 1_000자릿수로 통일하면 깔끔
    
    9월15일 데이터이므로 시간만 분석
    -> 1번째 띄어쓰기 이후부터 분석하면 됨
    -> 시간을 밀리초로 변환하면 깔끔
    
    문자열은 응답 완료시간, 처리시간이 표시되어 있음
    -> 시작 시간과 완료 시간을 구할 수 있음
    
    응답 시간 오름차순으로 되어 있으므로 재정렬이 필요함
    -> 시작 시간 오름차순 필요
    -> 시작 시간을 기준으로 이전 값들은 큐에서 제거
    -> 현재 값을 포함한 뒤, 크기 구함, 현재 처리값
    
    # 구현 스텝
    1. 입력값을 (요청 시간, 응답 시간)으로 변환
        - 요청 시간은 응답 시간 - 처리 시간 + 1
    2. 요청 시간 기준으로 정렬
    3. 요청값을 큐에 넣으며, 최대 처리값 갱신
        - 현재 요청시간보다 -1000밀리초 이상 값들 제거한 뒤, 처리값 갱신
    4. 최대 값을 반환한다
    */
    public int solution(String[] lines) {
        int size = lines.length;
        Request[] requests = new Request[size];
        
        for (int i = 0; i < size; i++) {
            String time = lines[i].substring(lines[i].indexOf(" ") + 1);
            requests[i] = convertRequest(time);
        }
        
        Arrays.sort(requests, (r1, r2) -> {
           return r1.startTimeMs - r2.startTimeMs;
        });
        
        int maxRequestsPerSecond = 0;
        PriorityQueue<Request> pq = new PriorityQueue<>((r1, r2) -> r1.endTimeMs - r2.endTimeMs);
        
        for (Request req : requests) {
            while (!pq.isEmpty() && pq.peek().endTimeMs + 1_000 <= req.startTimeMs) {
                pq.poll();
            }
            
            pq.offer(req);
            maxRequestsPerSecond = Math.max(maxRequestsPerSecond, pq.size());
        }
        
        return maxRequestsPerSecond;
    }
    
    private static Request convertRequest(String time) {
        String[] splited = time.split(" ");
        
        String endTime = splited[0];
        int endTimeMs = 0;
        
        endTimeMs += Integer.parseInt(endTime.substring(0, 2)) * 60 * 60 * 1000;
        endTimeMs += Integer.parseInt(endTime.substring(3, 5)) * 60 * 1000;
        endTimeMs += Integer.parseInt(endTime.substring(6, 8)) * 1000;
        endTimeMs += Integer.parseInt(endTime.substring(9));
        
        String processingTime = splited[1];
        int processingTimeMs = 0;
        
        int dotIndex = processingTime.indexOf(".");
        if (dotIndex == -1) {
            dotIndex = processingTime.length() - 1;
        }
        String processingTimeSecond = processingTime.substring(0, dotIndex);
        if (processingTimeSecond.length() > 0) {
            processingTimeMs += Integer.parseInt(processingTimeSecond) * 1000;
        }
        if (dotIndex != processingTime.length() - 1) {
            processingTimeMs += Integer.parseInt(processingTime.substring(dotIndex + 1, processingTime.indexOf("s")));
        }
            
        return new Request(endTimeMs - processingTimeMs + 1, endTimeMs);
    }
    
    private static class Request {
        final int startTimeMs;
        final int endTimeMs;
        
        public Request(int startTimeMs, int endTimeMs) {
            this.startTimeMs = startTimeMs;
            this.endTimeMs = endTimeMs;
        }
    }
}
