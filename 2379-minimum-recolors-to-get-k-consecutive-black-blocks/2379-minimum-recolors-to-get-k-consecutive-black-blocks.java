class Solution {
    /*
    # 문제 이해
    blocks가 주어진다 이 blocks는 화이트와 블랙 2개의 상태가 있다
    k개만큼 연속된 블랙 blocks 되려면 최소 몇 번 white -> black으로 변환되어야 하는 지 구해야 한다

    # 접근
    - blocks의 최대 개수는 100이므로 시간복잡도:O(N^3)까지 가능하다
    - i번째 블록부터 k번째 blcok까지 블랙이 되려면 필요한 개수를 구하면 될거 같다
    
    - 시간복잡도:O(N*K)가 아닌 시간복잡도:O(N)만으로 풀어보고 싶다

    주어진 것: 블록들의 색상, 연속되어야 하는 길이
    필요한 것: 현재 블록이 k를 만족하기 위한 재색칠 개수
    
    - k번째 전부터 현재까지 white의 개수를 누적하면 될거 같다

    # 구현 스텝
    1. k번째 전부터 현재까지 white의 개수를 초기화한다(초기값)
    2. 초기화 이후 inddex부터 blocks를 순회하며 
        최소 재색칠 횟수를 갱신한다
    3. 최소 재색칠 횟수를 반환한다
    */
    public int minimumRecolors(String blocks, int k) {
        int whiteCountSinceK = 0;
        for (int i = 0; i < k; i++) {
            if (blocks.charAt(i) == 'W') {
                whiteCountSinceK++;
            }
        }

        int minRecolorCount = whiteCountSinceK;
        for (int i = k; i < blocks.length(); i++) {
            if (blocks.charAt(i - k) == 'W') {
                whiteCountSinceK--;
            }
            if (blocks.charAt(i) == 'W') {
                whiteCountSinceK++;
            }

            minRecolorCount = Math.min(minRecolorCount, whiteCountSinceK);
        }

        return minRecolorCount;
    }
}
