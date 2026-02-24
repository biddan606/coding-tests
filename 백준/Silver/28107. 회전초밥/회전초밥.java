import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.StringTokenizer;

/**
 * BOJ - 28107번: 회전초밥 (실버1) 
 * 
 * @author suinLee
 * 
 * [문제]
 * - N명의 손님과 M개의 초밥
 * - 초밥을 먼저 받은 손님이 먹으면, 뒤에 사람은 못먹는다.
 * - 만약 아무도 먹지 않으면 버려진다.
 * - N명의 손님은 각자 목고 싶은 초밥 주문 목록을 가지고 있고, 
 * - (1번부터 N번 손님의 순서대로, 각 종류의 초밥은 최대 한 번만) 
 * - M개의 초밥이 순서대로 주어진다. 
 * - 각 손님이 먹게 되는 초밥의 개수를 구하자.
 * 
 * [전략]
 * - N명의 주문 목록에 대한 인접 리스트 생성 -> 인덱스: 초밥 번호, 값: 손님 번호
 * - M개의 음식을 덱으로 받기
 * - poll할때마다 인접 리스트에 있는 손님 번호 중 가장 작은 번호 빼기
 * - answer에 손님 번호에 해당하는 값에 ++하기 
 * 
 */

public class Main {
	
	public static void main(String[] args) throws IOException{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		
		StringTokenizer st = new StringTokenizer(br.readLine());
		int N = Integer.parseInt(st.nextToken()); // 손님 수
		int M = Integer.parseInt(st.nextToken()); // 초밥의 수 
		
		Deque <Integer>[] orderLst = new ArrayDeque[200001]; // 초밥의 종류
		for (int i = 1; i < 200001; i++) {
			orderLst[i] = new ArrayDeque<>();
		}
		
		for (int order = 1; order <= N; order++) { // 1번부터 N번 손님까지의 주문 
			st = new StringTokenizer(br.readLine());
			int number = Integer.parseInt(st.nextToken());
			for (int num = 0; num < number; num++) {
				orderLst[Integer.parseInt(st.nextToken())].offer(order); // 메뉴 인덱스에 손님 번호 넣기 
			}
		}
		
		int [] answer = new int[N];
		
		st = new StringTokenizer(br.readLine());
		for (int m = 0; m < M; m++) { // M개의 메뉴가 공백으로 구분되어 순서대로 
			int menu = Integer.parseInt(st.nextToken());
			if (!orderLst[menu].isEmpty()) { // 해당 메뉴를 주문한 사람이 있는 경우
				int guest = orderLst[menu].poll(); // 가장 먼저 주문한 사람으로 poll 
				answer[guest-1]++; // 그 손님 주문 수 증 가 
			}
		}

		for (int ans : answer) {
			sb.append(ans).append(" ");
		}
		
		System.out.println(sb);

	}
}
