import java.io.*;
import java.util.Arrays;

class Main {
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] firstLine = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int towerCount = firstLine[0];
        int cookieCount = firstLine[1];

        int[] towers = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        br.close();

        Arrays.sort(towers);

        // index 높이를 제거할 경우 필요한 쿠키의 수
        int maxHeight = towers[towers.length - 1];
        int minHeight = towers[0];
        int[] cookiesNeededForHeight = new int[maxHeight + 1];
        int towersIndex = towers.length - 1;

        // 높은 층부터 낮은 층으로 가면서 필요한 쿠키의 수를 누적시킨다.
        int accumulatedTowerCount = 0;
        for (int height = cookiesNeededForHeight.length - 1; height > minHeight; height--) {
            int towerCountAtHeight = 0;
            while (towersIndex >= 0 && towers[towersIndex] == height) {
                towerCountAtHeight++;
                towersIndex--;
            }

            accumulatedTowerCount += towerCountAtHeight;
            cookiesNeededForHeight[height] = accumulatedTowerCount;
        }

        // 높은 층부터 제일 작은 층까지 갈 경우 cookieCount를 몇번 반복해야 하는지 센다.
        int repeatCount = 0;
        int heigthIndex = cookiesNeededForHeight.length - 1;
        while (heigthIndex > minHeight) {
            int remainingCookies = cookieCount;
            while (heigthIndex > minHeight && remainingCookies >= cookiesNeededForHeight[heigthIndex]) {
                remainingCookies -= cookiesNeededForHeight[heigthIndex];
                heigthIndex--;
            }

            repeatCount++;
        }

        System.out.println(repeatCount);
    }
}
