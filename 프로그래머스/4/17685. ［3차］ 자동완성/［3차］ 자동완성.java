class Solution {
    /*
    문제
    단어를 구분할 수 있는 최소 문자의 개수를 구해야 한다
    
    접근1
    - 단어들이 겹치지 않게 저장해야 한다
    - 겹치지 않을 경우에는 그대로 저장하고, 겹쳐지는 경우에 분할하는 방법
        - 예, ["go", "gone", "guild"]
        go - go를 그대로 저장한다
        gone - go와 겹치므로 겹치는 지점에서 분할한다 go, gon
        guild - 그대로 저장한다
    
    예제1
    - 문자열의 길이 1_000_000에 근접하는 100_000개의 문자열이 있다
    - 이 문자열들은 마지막 글자만 다르고 모두 비슷하다
    
    1. 달라지는 글자를 찾아야 하므로 1_000_000번 연산하여 비교한다
    2. 총 100_000개를 할 것이다
    1_000_000 * 100_000 = 100_000_000_000
    
    **시간 초과 발생**

    문제 해석 오류
    - N개의 문자들의 총합이 L이므로 시간 초과 발생 X
    - 트라이로 풀면 될 듯
    
    예제2
    - 문자열의 길이가 100인 100_000개의 문자열 존재한다
    - 이 문자열들은 마지막 글자만 다르고 모두 비슷하다
    
    100_000개를 전부 순회해도 1_000_000번의 연산 발생
    모든 글자를 노드로 분리해도 될 듯
    
    접근2
    트라이 생성
    1. 단어를 분리하여 글자마다 노드를 생성하여 연결한다
    2. 노드가 있는 경우에는 count를 증가시킨다
    
    입력횟수 계산
    1. 단어를 분리한 글자로 노드를 탐색한다
    2. count가 1이 되거나 마지막 글자일 경우까지 확인한다
    
    회고
    문제 잘 읽자
    */
    public int solution(String[] words) {
        int wordCount = words.length;
        // charArray를 두번 사용하므로 미리 생성
        char[][] charArrays = new char[wordCount][];
        
        for (int i = 0; i < wordCount; i++) {
            charArrays[i] = words[i].toCharArray();
        }
            
        Node trie = new Node();
        
        for (char[] charArray : charArrays) {
            Node current = trie;
            
            for (char c : charArray) {
                // 소문자로만 구성됨
                if (current.children[c - 'a'] == null) {
                    current.children[c - 'a'] = new Node();
                }
                current.children[c - 'a'].count++;
                
                current = current.children[c - 'a'];
            }
        }
        
        int totalSearchCount = 0;
        
        for (char[] charArray : charArrays) {
            Node current = trie;
            
            for (char c : charArray) {
                if (current.count == 1) {
                    break;
                }
                
                current = current.children[c - 'a'];
                totalSearchCount++;
            }
        }
        
        return totalSearchCount;
    }
    
    private class Node {
        Node[] children;
        int count;
        
        public Node() {
            children = new Node['z' - 'a' + 1];
        }
    }
}

