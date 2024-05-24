import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        // 첫 줄 입력 처리: 집의 수와 공유기의 수
        int[] firstLine = Arrays.stream(reader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int houseCount = firstLine[0];
        int routerCount = firstLine[1];

        // 집의 위치 입력 처리
        int[] houseLocations = new int[houseCount];
        for (int i = 0; i < houseCount; i++) {
            houseLocations[i] = Integer.parseInt(reader.readLine());
        }

        // 집의 위치 정렬
        Arrays.sort(houseLocations);

        // 이진 탐색을 위한 초기값 설정
        int low = 1;
        int high = houseLocations[houseCount - 1] - houseLocations[0] + 1;

        // 이진 탐색을 통해 최대 최소 거리 찾기
        while (low < high) {
            int mid = (low + high) / 2;

            // 현재 간격(mid)으로 공유기 설치가 가능한지 확인
            if (install(houseLocations, mid) >= routerCount) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        // 결과 출력: 최대 가능한 최소 거리
        System.out.println(low - 1);
    }

    // 주어진 간격으로 공유기 설치가 가능한 개수 계산
    private static int install(int[] locations, int distance) {
        int count = 1;
        int lastInstalledLocation = locations[0];

        for (int i = 1; i < locations.length; i++) {
            if (locations[i] - lastInstalledLocation >= distance) {
                lastInstalledLocation = locations[i];
                count++;
            }
        }

        return count;
    }
}
