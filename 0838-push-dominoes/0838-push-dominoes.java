class Solution {
    /*
    # 문제 이해
    - 도미노 게임 진행 
        오른쪽으로 넘어지면 다음 턴에 오른쪽 도미노 영향
        왼쪽으로 넘어지면 다음 턴에 왼쪽 도미노 영향
    - 넘어지고 있거나 이미 넘어진 도미노는 영향을 받지 않음

    # 풀이 접근
    - 지금까지 넘어진 도미노의 무게는 신경 쓰지 않아도 됨
    - n = 10^5이므로 n^2 풀이로는 불가능
    - 가장 원초적인 해결책으로 queue에 쓰러진 도미노들을 담고 다음턴에 해당 도미노 쓰러트리기
    - 종료지점이 필요하다. 종료지점으로는 큐가 비었을 때로 하자
        count를 세면 1턴이 더 빨리 끝날 수도 있을 듯, 다만 굳이스럽다

    # 구현 스텝
    1. input에 쓰러질 도미노들을 queue에 넣는다
    2. queue가 빌 때까지 순회한다. 큐에서 원소 한개를 추출한다
    3. 현재 index에 값이 할당되어 있다면, 무시한다
        할당 안되어 있다면, 현재 index를 원소 방향으로 변경한다. 
        다음 index로 넘어갈 수 있게 다음 Index 원소를 만들어 큐에 삽입한다
    4. Indexes를 string으로 변환하여 반환한다

    --- 

    같은 턴에 왼쪽, 오른쪽으로 동시에 움직인다면 중앙이 되어야 한다
    -> queue를 돌 때, step 필드를 두어 동시에 움직이는지 체크한다
        동시에 움직일 경우 중앙 고정
    */
    public String pushDominoes(String dominoes) {
        Queue<Element> queue = new ArrayDeque<>();
        for (int i = 0; i < dominoes.length(); i++) {
            if (dominoes.charAt(i) == '.') {
                continue;
            }

            queue.offer(new Element(i, dominoes.charAt(i)));
        }

        Domino[] result = new Domino[dominoes.length()];
        int step = 1;
        while (!queue.isEmpty()) {
            int size = queue.size();

            for (int i = 0; i < size; i++) {
                Element e = queue.poll();
                if (e.index < 0 || e.index >= result.length) {
                    continue;
                }

                Domino current = result[e.index];
                if (current != null) {
                    if (current.createdAt == step && current.direct != e.direct) {
                        current.direct = '.';
                    }

                    continue;
                }
            
                result[e.index] = new Domino(e.direct, step);
                
                int nextIndex = -1;
                if (e.direct == 'L') {
                    nextIndex = e.index - 1;
                } else if(e.direct == 'R') {
                    nextIndex = e.index + 1;
                }
                queue.offer(new Element(nextIndex, e.direct));
            }
            
            step++;
        }

        for (int i = 0; i < result.length; i++) {
            if (result[i] == null) {
                result[i] = new Domino('.', step);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (Domino d : result) {
            sb.append(d.direct);
        }
        return sb.toString();
    }

    private static class Element {
        final int index;
        final char direct;

        public Element(int index, char direct) {
            this.index = index;
            this.direct = direct;
        }
    }

    private static class Domino {
        char direct;
        final int createdAt;

        public Domino(char direct, int createdAt) {
            this.direct = direct;
            this.createdAt = createdAt;
        }
    }
}
