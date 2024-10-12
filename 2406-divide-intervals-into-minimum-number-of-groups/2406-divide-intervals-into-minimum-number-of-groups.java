class Solution {
    public int minGroups(int[][] intervals) {
        // left 기준으로 정렬한다.
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        TreeMap<Integer, Integer> groupsByEndPoint = new TreeMap<>();

        for (int[] interval : intervals) {
            int left = interval[0];
            int right = interval[1];

            // TreeMap의 key 중에 left보다 작은 가장 큰 값이 없다면, 새로운 그룹을 만든다.
            Integer lessThanEndPointKey = groupsByEndPoint.floorKey(left - 1);
            if (lessThanEndPointKey == null) {
                groupsByEndPoint.put(right, groupsByEndPoint.getOrDefault(right , 0) + 1);
            } else { // TreeMap의 key 중에 left보다 작은 값이 있다면, 해당 키를 갱신한다.
                int endPointKeyCount = groupsByEndPoint.get(lessThanEndPointKey);
                
                // endPointKey에 해당 그룹 1개를 제거하고, 새 그룹을 만든다.
                if (endPointKeyCount == 1) {
                    groupsByEndPoint.remove(lessThanEndPointKey);
                } else {
                    groupsByEndPoint.put(lessThanEndPointKey, endPointKeyCount - 1);
                }

                groupsByEndPoint.put(right, groupsByEndPoint.getOrDefault(right , 0) + 1);
            }

        }

        int result = 0;
        for (int v : groupsByEndPoint.values()) {
            result += v;
        }
        return result;
    }
}