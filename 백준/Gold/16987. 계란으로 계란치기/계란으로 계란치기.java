import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class Main {

    private static int maxValue = 0;

    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int eggCount = Integer.parseInt(br.readLine());
        Egg[] eggs = new Egg[eggCount];
        for (int i = 0; i < eggs.length; i++) {
            int[] token = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int durability = token[0];
            int weight = token[1];

            eggs[i] = new Egg(durability, weight);
        }

        Set<Integer> broken = new HashSet<>();
        dfs(eggs, 0, broken);

        System.out.println(maxValue);
    }

    private static void dfs(Egg[] eggs, int left, Set<Integer> broken) {
        if (left >= eggs.length) {
            maxValue = Math.max(maxValue, broken.size());
            return;
        }

        // left가 이미 깨진 경우 또는 다른 계란과 칠 수 없는 경우, for문을 돌 수 없으므로 다음으로 넘어갑니다.
        if (broken.contains(left)
                || broken.size() >= eggs.length - 1) {
            dfs(eggs, left + 1, broken);
            return;
        }

        // 다른 계란 치는 경우
        for (int i = 0; i < eggs.length; i++) {
            // 자기 자신이나 이미 깨진 계란을 칠 수 없습니다.
            if (left == i
                    || broken.contains(i)) {
                continue;
            }

            eggs[i].durability -= eggs[left].weight;
            if (eggs[i].durability <= 0) {
                broken.add(i);
            }

            eggs[left].durability -= eggs[i].weight;
            if (eggs[left].durability <= 0) {
                broken.add(left);
            }

            dfs(eggs, left + 1, broken);

            eggs[left].durability += eggs[i].weight;
            broken.remove(left);

            eggs[i].durability += eggs[left].weight;
            broken.remove(i);
        }
    }

    private static class Egg {

        int durability;
        final int weight;

        public Egg(int durability, int weight) {
            this.durability = durability;
            this.weight = weight;
        }
    }
}
