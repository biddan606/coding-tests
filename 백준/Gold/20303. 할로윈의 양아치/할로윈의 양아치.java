import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int[] counts = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        int childCount = counts[0];
        int friendshipCount = counts[1];
        int limit = counts[2];

        int[] children = Arrays.stream(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[] parents = new int[children.length];
        for (int i = 0; i < parents.length; i++) {
            parents[i] = i;
        }
        for (int i = 0; i < friendshipCount; i++) {
            int[] token = Arrays.stream(br.readLine().split(" "))
                    .mapToInt(Integer::parseInt)
                    .toArray();
            int child1 = token[0] - 1;
            int child2 = token[1] - 1;

            merge(parents, child1, child2);
        }

        Map<Integer, Parent> candyWithFriendship = new HashMap<>();
        for (int i = 0; i < children.length; i++) {
            int candy = children[i];
            int key = find(parents, i);

            candyWithFriendship.putIfAbsent(key, new Parent());
            Parent parent = candyWithFriendship.get(key);

            parent.candy += candy;
            parent.childCount++;
        }

        int[] dp = new int[limit];
        for (Parent parent : candyWithFriendship.values()) {
            int count = parent.childCount;
            int candy = parent.candy;

            for (int n = dp.length - 1; n >= count; n--) {
                dp[n] = Math.max(dp[n], dp[n - count] + candy);
            }
        }

        System.out.println(dp[dp.length - 1]);
    }

    private static void merge(int[] parents, int child1, int child2) {
        int parent1 = find(parents, child1);
        int parent2 = find(parents, child2);

        if (parent1 != parent2) {
            parents[parent1] = parent2;
        }
    }

    private static int find(int[] parents, int i) {
        if (parents[i] != i) {
            parents[i] = find(parents, parents[i]);
        }
        return parents[i];
    }

    private static class Parent {

        int childCount = 0;
        int candy = 0;
    }
}
