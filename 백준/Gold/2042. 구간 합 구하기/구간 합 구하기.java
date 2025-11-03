import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        int numberCount = Integer.parseInt(st.nextToken());
        int totalChangeCount = Integer.parseInt(st.nextToken());
        int totalSumOperationCount = Integer.parseInt(st.nextToken());

        long[] numbers = new long[numberCount];

        for (int i = 0; i < numberCount; i++) {
            numbers[i] = Long.parseLong(br.readLine());
        }

        Sum totalSum = createSums(numbers);

        int currentChangeCount = 0;
        int currentSumOperationCount = 0;

        while (currentChangeCount < totalChangeCount || currentSumOperationCount < totalSumOperationCount) {
            String[] command = br.readLine().split(" ");

            int operation = Integer.parseInt(command[0]);
            int number1 = Integer.parseInt(command[1]);
            long number2 = Long.parseLong(command[2]);

            if (operation == 1) {
                totalSum.update(number1 - 1, number2);

                currentChangeCount++;
            } else {
                long result = totalSum.query(number1 - 1, (int) (number2 - 1));
                System.out.println(result);

                currentSumOperationCount++;
            }
        }
    }

    private static Sum createSums(long[] numbers) {
        long[] cumulativeSums = new long[numbers.length + 1];

        for (int i = 0; i < numbers.length; i++) {
            cumulativeSums[i + 1] = cumulativeSums[i] + numbers[i];
        }

        return buildSum(cumulativeSums, 0, numbers.length - 1);
    }

    private static Sum buildSum(long[] cumulativeSums, int startIndexInclusive, int endIndexInclusive) {
        Sum sum = new Sum(startIndexInclusive, endIndexInclusive,
                cumulativeSums[endIndexInclusive + 1] - cumulativeSums[startIndexInclusive]);

        if (startIndexInclusive == endIndexInclusive) {
            return sum;
        }

        int mid = startIndexInclusive + (endIndexInclusive - startIndexInclusive) / 2;

        sum.left = buildSum(cumulativeSums, startIndexInclusive, mid);
        sum.right = buildSum(cumulativeSums, mid + 1, endIndexInclusive);

        return sum;
    }

    private static class Sum {
        int startIndexInclusive;
        int endIndexInclusive;
        long value;
        Sum left;
        Sum right;

        public Sum(int startIndexInclusive, int endIndexInclusive, long value) {
            this.startIndexInclusive = startIndexInclusive;
            this.endIndexInclusive = endIndexInclusive;
            this.value = value;
        }

        public Long query(int targetStart, int targetEnd) {
            // 1. 현재 노드 범위 [start, end]가 찾는 범위 [targetStart, targetEnd]와 전혀 겹치지 않는 경우
            if (targetEnd < this.startIndexInclusive || this.endIndexInclusive < targetStart) {
                return 0L;
            }

            // 2. 현재 노드 범위가 찾는 범위에 완전히 포함되는 경우
            if (targetStart <= this.startIndexInclusive && this.endIndexInclusive <= targetEnd) {
                return this.value;
            }

            // 3. 그 외 (애매하게 걸쳐있는 경우), 왼쪽/오른쪽 자식에게 '원래 범위'를 그대로 물어본다.
            int mid = this.startIndexInclusive + (this.endIndexInclusive - this.startIndexInclusive) / 2;
            long leftSum = 0;
            long rightSum = 0;

            // 왼쪽 자식에게 물어보기
            if (this.left != null) {
                leftSum = this.left.query(targetStart, targetEnd);
            }
            // 오른쪽 자식에게 물어보기
            if (this.right != null) {
                rightSum = this.right.query(targetStart, targetEnd);
            }

            return leftSum + rightSum;
        }

        public long update(int index, long changeValue) {
            if (this.startIndexInclusive == index && this.endIndexInclusive == index) {
                long prevValue =  this.value;
                this.value = changeValue;

                return prevValue;
            }

            int mid = this.startIndexInclusive + (this.endIndexInclusive - this.startIndexInclusive) / 2;
            long prevValue;

            if (index <= mid) {
                prevValue = left.update(index, changeValue);
            } else {
                prevValue = right.update(index, changeValue);
            }

            this.value += changeValue - prevValue;
            return prevValue;
        }
    }
}
