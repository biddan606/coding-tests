import java.io.*;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        
        // 첫 번째 줄 입력: N(숫자 개수), K(점프 한계)
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());
        
        // 두 번째 줄 입력: 외칠 수 없는 수 (금지된 수)
        // 빠른 조회를 위해 boolean 배열 사용
        boolean[] isForbidden = new boolean[N + 1];
        if (br.ready()) {
            String line = br.readLine();
            if (line != null && !line.isEmpty()) {
                st = new StringTokenizer(line);
                while (st.hasMoreTokens()) {
                    isForbidden[Integer.parseInt(st.nextToken())] = true;
                }
            }
        }
        
        // dp[i]: i에서 게임이 끝날 때까지의 턴 수
        int[] dp = new int[N + 1];
        // isWinning[i]: i 상태가 승리(true)인지 패배(false)인지
        boolean[] isWinning = new boolean[N + 1];
        
        // 슬라이딩 윈도우용 덱
        // winDeque: 승리 상태들의 인덱스 저장 (dp 값 내림차순 정렬 -> First가 최대값)
        Deque<Integer> winDeque = new ArrayDeque<>();
        // loseDeque: 패배 상태들의 인덱스 저장 (dp 값 오름차순 정렬 -> First가 최소값)
        Deque<Integer> loseDeque = new ArrayDeque<>();
        
        // 기저 사례: N에서는 더 이상 수를 부를 수 없으므로 패배, 턴 수 0
        // (단, N 자체가 금지된 수라면 덱에 넣지 않음)
        if (!isForbidden[N]) {
            dp[N] = 0;
            isWinning[N] = false;
            loseDeque.add(N);
        }

        // N-1부터 0까지 역순으로 채움
        for (int i = N - 1; i >= 0; i--) {
            if (isForbidden[i]) {
                continue; // 금지된 수는 도달할 수 없으므로 계산 생략
            }
            
            // 1. 윈도우 범위를 벗어난 인덱스 제거 (i + K 보다 먼 곳은 갈 수 없음)
            while (!winDeque.isEmpty() && winDeque.peekFirst() > i + K) winDeque.pollFirst();
            while (!loseDeque.isEmpty() && loseDeque.peekFirst() > i + K) loseDeque.pollFirst();
            
            // 2. 승패 결정 로직
            if (!loseDeque.isEmpty()) {
                // 갈 수 있는 곳 중 '상대방이 지는 곳'이 하나라도 있다면 -> 나는 이김 (WIN)
                // "빠르게 이길 수 있는" -> 상대가 지는 곳 중 턴 수가 가장 적은 곳 선택
                isWinning[i] = true;
                dp[i] = dp[loseDeque.peekFirst()] + 1;
                
                // 현재 상태(WIN)를 winDeque에 추가 (내림차순 유지)
                // 왜냐하면 이전 상태(i-1 등)에서 내가 WIN인 곳으로 오려면(지게 되려면) 턴을 길게 끌어야 하므로 Max가 필요
                while (!winDeque.isEmpty() && dp[winDeque.peekLast()] <= dp[i]) {
                    winDeque.pollLast();
                }
                winDeque.addLast(i);
                
            } else {
                // 갈 수 있는 곳이 모두 '상대방이 이기는 곳'이거나 갈 곳이 없음 -> 나는 짐 (LOSE)
                // "오래 끌 수 있는" -> 상대가 이기는 곳 중 턴 수가 가장 많은 곳 선택
                isWinning[i] = false;
                
                if (!winDeque.isEmpty()) {
                    dp[i] = dp[winDeque.peekFirst()] + 1;
                } else {
                    // 갈 곳이 아예 없는 경우
                    dp[i] = 0;
                }
                
                // 현재 상태(LOSE)를 loseDeque에 추가 (오름차순 유지)
                // 왜냐하면 이전 상태에서 내가 LOSE인 곳으로 오려면(이기려면) 턴을 짧게 가져가야 하므로 Min이 필요
                while (!loseDeque.isEmpty() && dp[loseDeque.peekLast()] >= dp[i]) {
                    loseDeque.pollLast();
                }
                loseDeque.addLast(i);
            }
        }
        
        // 0에서 시작했을 때의 총 턴 수 출력
        System.out.println(dp[0]);
    }
}