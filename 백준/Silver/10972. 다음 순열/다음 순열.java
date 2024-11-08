import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.stream.Collectors;

public class Main {

    public static void main(String[] args) throws IOException {
        // 입력
        // 1. 순열의 개수
        // 2. 순열
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        int size = Integer.parseInt(reader.readLine());
        int[] permutation = Arrays.stream(reader.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();
        reader.close();

        int targetIndex = size - 2;
        while (targetIndex >= 0 && permutation[targetIndex] >= permutation[targetIndex + 1]) {
            targetIndex--;
        }
        if (targetIndex < 0) {
            System.out.println(-1);
            return;
        }

        int swapIndex = size - 1;
        while (permutation[swapIndex] <= permutation[targetIndex]) {
            swapIndex--;
        }

        // swap
        swap(permutation, swapIndex, targetIndex);

        // reverse
        for (int i = 1; targetIndex + i < size - i; i++) {
            swap(permutation, targetIndex + i, size - i);
        }

        // 다음 순열 출력
        BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
        for (int j : permutation) {
            writer.append(String.valueOf(j))
                    .append(" ");
        }
        writer.flush();
        writer.close();
    }

    private static void swap(int[] permutation, int swapIndex, int targetIndex) {
        int temp = permutation[swapIndex];
        permutation[swapIndex] = permutation[targetIndex];
        permutation[targetIndex] = temp;
    }
}

// 순열과 다음 순열 패턴
/*
1, 2, 3, 4
1, 2, 4, 3 -> 1, // 3, 2, 4
1, 3, 2, 4 -> 1, 3, // 4, 2
1, 3, 4, 2 -> 1, // 4, 2, 3
1, 4, 2, 3 -> 1, 4, // 3, 2
1, 4, 3, 2
2, 1, 3, 4
2, 1, 4, 3
2, 3, 1, 4
2, 3, 4, 1
2, 4, 1, 3
 */

/*
4, 3, 2, 1
-1
 */
