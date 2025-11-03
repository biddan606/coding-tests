import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());

        // DP 배열 초기화 (int[]는 기본적으로 0으로 초기화됨)
        int[] maxDp = new int[3];
        int[] minDp = new int[3];

        for (int i = 0; i < n; i++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int c0 = Integer.parseInt(st.nextToken());
            int c1 = Integer.parseInt(st.nextToken());
            int c2 = Integer.parseInt(st.nextToken());

            // maxDp를 업데이트하기 전, 이전 라인의 값을 임시 변수에 저장
            int prevMax0 = maxDp[0];
            int prevMax1 = maxDp[1];
            int prevMax2 = maxDp[2];

            // 현재 라인의 최댓값 계산
            maxDp[0] = Math.max(prevMax0, prevMax1) + c0;
            maxDp[1] = Math.max(Math.max(prevMax0, prevMax1), prevMax2) + c1;
            maxDp[2] = Math.max(prevMax1, prevMax2) + c2;
            
            // minDp를 업데이트하기 전, 이전 라인의 값을 임시 변수에 저장
            int prevMin0 = minDp[0];
            int prevMin1 = minDp[1];
            int prevMin2 = minDp[2];

            // 현재 라인의 최솟값 계산
            minDp[0] = Math.min(prevMin0, prevMin1) + c0;
            minDp[1] = Math.min(Math.min(prevMin0, prevMin1), prevMin2) + c1;
            minDp[2] = Math.min(prevMin1, prevMin2) + c2;
        }

        int maxResult = Math.max(maxDp[0], Math.max(maxDp[1], maxDp[2]));
        int minResult = Math.min(minDp[0], Math.min(minDp[1], minDp[2]));

        System.out.println(maxResult + " " + minResult);
    }
}