class Solution {
    /*
    연속된 subarray를 떼어내어 increasing된 배열이 되려 한다.
    이 때, 뗴어내야 할 연속된 subarray의 길이를 반환한다.

    예) [1,2,3,10,4,2,3,5] -> expected: 3, [1, 2, 3], [10, 4, 2], [3, 5]
    index 0부터 increasing된 배열 [1, 2, 3, 10]
    index n - 1부터 decreasing된 배열 [5, 3, 2]
    중간 배열 [4]

    [1, 2, 3, 10], [4], [2, 3, 5] -> [1, 2, 3], [10, 4, 2], [3, 5]
    left: 가장 왼쪽 배열의 첫번째 인덱스(0), right: 가장 오른쪽 배열의 첫번째 인덱스(5)
    left -> 가장 왼쪽 배열의 끝까지 순회하한다.
    이때, [right] 값보다 [left]이 작거나 같다면 left++
    [left] 값이 크다면 right++
    right가 size가 될 때까지 반복한다.

    [left:가장 오른쪽 배열의 첫번째 인덱스)까지는 제외해야 되는 배열이 된다.
    */
    public int findLengthOfShortestSubarray(int[] arr) {
        int size = arr.length;

        int firstEndIndex = 0;
        while (firstEndIndex + 1 < size && arr[firstEndIndex] <= arr[firstEndIndex + 1]) {
            firstEndIndex++;
        }

        int lastStartIndex = size - 1;
        while (lastStartIndex > 0 && arr[lastStartIndex] >= arr[lastStartIndex - 1]) {
            lastStartIndex--;
        }
        if (lastStartIndex == 0) {
            return 0;
        }

        int left = 0;
        int right = lastStartIndex;
        int result = Math.min(size - firstEndIndex - 1, lastStartIndex); // 중간 배열이 아닌 첫번째 배열 또는 마지막 배열을 제거해야 할 경우
        while (left <= firstEndIndex && right < size) {
            if (arr[left] <= arr[right]) {
                left++;
                result = Math.min(result, right - left); // left 이동시에만 갱신, left 이동시 result는 점점 작아짐
            } else { // arr[left] > arr[right]
                right++;
            }
        }

        return result; 
    }
}
