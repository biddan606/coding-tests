import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.Deque;
import java.util.List;
import java.util.StringTokenizer;

/*
 * # 문제 설명
 * N명의 학생(1..N) 키는 모두 다르며, M개의 (a<b) 비교 결과가 주어진다.
 * 이 정보만으로 "자신의 키 순위(작은 쪽부터 몇 번째인지)"를 확정할 수 있는 학생 수를 구한다.
 *
 * # 문제 단순화
 * a<b 관계를 간선 a->b 로 보면 DAG가 된다.
 * 어떤 학생 i의 순위가 확정되려면, 자신보다 확실히 작은 사람 수 + 확실히 큰 사람 수 = N-1 이어야 한다.
 * 즉, i의 조상(자신보다 작은 쪽) 수 + i의 후손(자신보다 큰 쪽) 수 + 1(자신) = N 이면 순위가 결정된다.
 * => i에서 도달 가능한 정점과 i로 도달 가능한 정점의 합집합 크기가 N 이면 된다.
 *
 * # 아이디어
 * 1) Kahn 알고리즘으로 위상정렬.
 * 2) 위상 역순으로 desc[u] = {u} U desc[v]  (u->v 자식들) 누적, u의 후손 집합.
 * 3) 위상 정순으로 anc[u]  = {u} U anc[p]   (p->u 부모들)  누적, u의 조상 집합.
 * 4) 각 i에 대해 |desc[i]| + |anc[i]| - 1 == N 인지 검사.
 * 집합 연산은 BitSet OR 로 O(N/64) 에 처리, 전체 O(N*(N+M)/64).
 *
 * # 구현
 * - fwd: a<b 방향 그래프(작은->큰), rev: 역방향 그래프.
 * - 위상정렬 순서 order[] 를 기반으로 두 방향의 도달 가능 집합을 BitSet 으로 누적.
 * - 마지막에 cardinality 합이 N 인 정점 개수를 세어 출력.
 */
public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= T; tc++) {
            int N = Integer.parseInt(br.readLine().trim());
            int M = Integer.parseInt(br.readLine().trim());

            List<List<Integer>> fwd = new ArrayList<>(); // u -> v (u < v in height)
            List<List<Integer>> rev = new ArrayList<>();
            int[] indeg = new int[N + 1];
            for (int i = 0; i <= N; i++) {
                fwd.add(new ArrayList<>());
                rev.add(new ArrayList<>());
            }

            for (int i = 0; i < M; i++) {
                StringTokenizer st = new StringTokenizer(br.readLine());
                int a = Integer.parseInt(st.nextToken());
                int b = Integer.parseInt(st.nextToken());
                fwd.get(a).add(b);
                rev.get(b).add(a);
                indeg[b]++;
            }

            // Kahn's topological sort
            int[] order = new int[N];
            int head = 0, tail = 0;
            Deque<Integer> q = new ArrayDeque<>();
            for (int i = 1; i <= N; i++)
                if (indeg[i] == 0)
                    q.add(i);
            while (!q.isEmpty()) {
                int u = q.poll();
                order[tail++] = u;
                for (int v : fwd.get(u)) {
                    if (--indeg[v] == 0)
                        q.add(v);
                }
            }

            BitSet[] desc = new BitSet[N + 1]; // 후손(자기 포함)
            BitSet[] anc = new BitSet[N + 1];
            for (int i = 0; i <= N; i++) {
                desc[i] = new BitSet(N + 1);
                anc[i] = new BitSet(N + 1);
            }

            // 역순: 후손 집합 누적
            for (int idx = N - 1; idx >= 0; idx--) {
                int u = order[idx];
                desc[u].set(u);
                for (int v : fwd.get(u))
                    desc[u].or(desc[v]);
            }
            // 정순: 조상 집합 누적
            for (int idx = 0; idx < N; idx++) {
                int u = order[idx];
                anc[u].set(u);
                for (int v : rev.get(u))
                    anc[u].or(anc[v]);
            }

            int answer = 0;
            for (int i = 1; i <= N; i++) {
                // 자기 자신이 양쪽에 들어있으므로 -1
                int reachable = desc[i].cardinality() + anc[i].cardinality() - 1;
                if (reachable == N)
                    answer++; // 자기 포함 전체
            }

            sb.append('#').append(tc).append(' ').append(answer).append('\n');
        }
        System.out.print(sb);
    }
}