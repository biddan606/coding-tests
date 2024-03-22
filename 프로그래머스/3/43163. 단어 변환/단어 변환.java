import java.util.*;

class Solution {
    /*
    1. words를 순회한다.
    2. currentWord와 nextWord의 글자 차이를 반환 받는다.
    3. 차이가 1개이고 방문한 적 없다면, 방문 처리하고 큐에 쌓는다.
        - 1번 방문한 노드는 다시 방문할 필요 없다. 이전에도 방문이 가능했기에 더 깊은 곳에서 방문할 필요가 없다.
    4. 다시 1번부터 반복한다. 만약 뽑은 값이 타겟이라면 현재 뎁스를 반환한다.
        - 큐에 쌓을 때 다음 뎁스는 맨 뒤로 이동한다. 그러므로 현재 뽑은 큐의 뎁스가 항상 가장 작은 뎁스이다.
    */
    public int solution(String begin, String target, String[] words) {
        boolean[] visitedWords = new boolean[words.length];
        
        Queue<Element> queue = new LinkedList<>();
        queue.offer(new Element(begin, 0));
        
        while (!queue.isEmpty()) {
            Element element = queue.poll();
            if (Objects.equals(element.str, target)) {
                return element.depth;
            }
            
            for (int i = 0; i < words.length; i++) {
                if (visitedWords[i]) {
                    continue;
                }
                String nextStr = words[i];
            
                int difference = 0;
                for (int p = 0; p < element.str.length(); p++) {
                    char baseCh = element.str.charAt(p);
                    char compCh = nextStr.charAt(p);
                    if (baseCh != compCh) {
                        difference++;
                    }
                }
                if (difference != 1) {
                    continue;
                }
            
                visitedWords[i] = true;
                queue.offer(new Element(nextStr, element.depth + 1));
            }
        }
        
        return 0;
    }
    
    private static class Element {
        final String str;
        final int depth;
        
        public Element(String str, int depth) {
            this.str = str;
            this.depth = depth;
        }
    }
}