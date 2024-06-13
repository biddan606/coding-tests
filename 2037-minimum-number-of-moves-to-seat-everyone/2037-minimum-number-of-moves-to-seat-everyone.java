class Solution {
    public int minMovesToSeat(int[] seats, int[] students) {
        Arrays.sort(seats);
        Arrays.sort(students);

        int size = seats.length;
        int moveTimes = 0;
        for (int i = 0; i < size; i++) {
            moveTimes += Math.abs(seats[i] - students[i]);
        }

        return moveTimes;
    }
}
