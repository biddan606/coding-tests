import java.util.*;

class Solution {
    /*
    # 문제 이해
    알파벳을 2개씩 제거했을 때, 모두 제거되는 문자열인지 여부를 반환해야 한다
    
    # 접근1
    - 문자열의 최대 길이는 1_000_000(백만)
    - 2개씩 제거했을 때 시간복잡도:O(N * logN)이 든다
    
    # 구현 스텝1
    1. 큐에 문자열의 문자들을 순서대로 담는다
    2. 문자열 개수만큼 순회한다
    3. 2 문자가 일치하면 제거한다
    4. 2,3번 과정을 제거가 불가능할 때까지 반복한다
    5. 큐가 비어있으면 1, 그렇지 않다면 0을 반환한다
    
    # 시간 초과
    시간복잡도O(NlogN)이 아니다
    1쌍씩 빠질 경우 O(N^2)가 발생한다
    
    # 접근2
    접근1 방식은 현재 문자열들을 쌍을 모두 제거하는 방식이다
    그러다보니 최악의 경우 시간복잡도가 O(N^2)까지 될 수 있다
    
    접근2 방식은
    문자를 애니팡처럼 한 쌍씩 제거하는 방식이다
    "baabaa" -> "bbaa" -> "aa" -> ""
    이렇게 되면 쌍을 제거할 때마다 반복되는 iteration이 사라진다
    
    # 구현스텝2
    1. 문자열을 순회한다
    2. 스택의 가장 최상단과 문자가 일치한다면 스택을 제거한다, 
        일치하지 않는다면 문자를 스택에 추가한다
    3. 스택이 비어있다면 1, 아니라면 0을 반환한다
    */
    public int solution(String s) {
        Deque<Character> stack = new ArrayDeque<>();
        for (char ch : s.toCharArray()) {
            if (!stack.isEmpty() && stack.peek() == ch) {
                stack.pop();
            } else {
                stack.push(ch);
            }
        }
        
        return stack.isEmpty() ? 1 : 0;
    }
}
