import java.util.ArrayList;
import java.util.List;

class MyCalendarTwo {

    List<int[]> bookings = new ArrayList<>();
    List<int[]> doubleBookings = new ArrayList<>();

    public MyCalendarTwo() {

    }

    public boolean book(int start, int end) {
        int[] current = {start, end};

        // 더블 부킹에서 겹친 영역이 존재한다면, triple 부킹
        for (int[] doubleBooking : doubleBookings) {
            if (isOverraping(doubleBooking, current)) {
                return false;
            }
        }

        // 겹치는 영역을 추가한다.
        for (int[] booking : bookings) {
            if (isOverraping(booking, current)) {
                doubleBookings.add(new int[]{
                        Math.max(booking[0], current[0]),
                        Math.min(booking[1], current[1])});
            }
        }

        bookings.add(current);
        return true;
    }

    private boolean isOverraping(int[] booking1, int[] booking2) {
        return Math.max(booking1[0], booking2[0]) < Math.min(booking1[1], booking2[1]);
    }

    public static void main(String[] args) {
        MyCalendarTwo myCalendarTwo = new MyCalendarTwo();
        int[][] books = {
                {28, 46}, {9, 21}, {21, 39}, {37, 48}, {38, 50}, {22, 39}, {45, 50}, {1, 12}, {40, 50}, {31, 44}
        };

        List<Boolean> results = new ArrayList<>();
        for (int[] book : books) {
            results.add(myCalendarTwo.book(book[0], book[1]));
        }

        System.out.println(results);
    }
}
