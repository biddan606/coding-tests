import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Main {

    /**
     * @param args
     * @throws IOException
     * @ 멀티탭에 전기용품을 꽂아 사용할 수 있다.
     * @ 멀티탭 구멍의 수가 전기용품보다 적을 때, 전기용품 교체를 최소화해야 한다.
     */
    public static void main(String[] args) throws IOException {
        // 멀티탭 구멍의 개수를 입력받는다.
        // 전기용품의 사용순서를 입력받는다.
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] firstToken = Arrays.stream(reader.readLine().split(" "))
                .mapToInt(Integer::valueOf)
                .toArray();
        int socketCount = firstToken[0];
        int totalUsages = firstToken[1];

        int[] usageSequence = Arrays.stream(reader.readLine().split(" "))
                .mapToInt(Integer::valueOf)
                .toArray();

        Set<Integer> pluggedDevices = new HashSet<>();
        int unplugCount = 0;

        for (int i = 0; i < totalUsages; i++) {
            // 이미 사용중이라면 아무것도 하지 않는다.
            int currentDevice = usageSequence[i];
            if (pluggedDevices.contains(currentDevice)) {
                continue;
            }

            // 멀티탭 구멍이 비어있다면, 전기용품을 꽂는다.
            // 구멍이 비어있지않다면, 다음에 사용하지 않는 전기용품을 뽑는다.
            if (pluggedDevices.size() == socketCount) {
                int deviceToUnplug = selectDeviceToUnplug(pluggedDevices, usageSequence, i + 1);

                pluggedDevices.remove(deviceToUnplug);
                unplugCount++;
            }

            pluggedDevices.add(usageSequence[i]);
        }

        System.out.println(unplugCount);
    }

    private static int selectDeviceToUnplug(Set<Integer> pluggedDevices, int[] usageSequence, int startIndex) {
        // 뽑을 전기용품은 끝까지 확인해보거나, 1개가 남을 때까지 한다.
        // 아무거나 뽑아도 된다면, 아무거나 뽑는다.
        Set<Integer> remainingDevices = new HashSet<>(pluggedDevices);

        for (int i = startIndex; i < usageSequence.length; i++) {
            if (remainingDevices.size() == 1) {
                break;
            }

            remainingDevices.remove(usageSequence[i]);
        }

        return remainingDevices.stream().findFirst().orElseThrow(RuntimeException::new);
    }
}
