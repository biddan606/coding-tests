import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;
import java.util.stream.Collectors;

class Solution {

    public String solution(int n, int t, int m, String[] timetable) {
        Deque<Integer> shuttles = generateShuttles(n, t);
        Queue<Integer> crew = generateCrew(timetable);

        Integer lastShuttle = null;
        int passengers = 0;
        Integer lastCrew = null;
        while (!shuttles.isEmpty()) {
            lastShuttle = shuttles.removeFirst();
            passengers = 0;

            while (!crew.isEmpty()
                    && passengers < m
                    && lastShuttle >= crew.peek()) {
                lastCrew = crew.poll();
                passengers++;
            }
        }

        if (passengers == m && lastCrew != null) {
            return toResult(Math.max(lastCrew - 1, 0));
        }
        return toResult(lastShuttle);
    }

    private Deque<Integer> generateShuttles(int n, int t) {
        Deque<Integer> shuttles = new ArrayDeque<>();
        int currentShuttle = 9 * 60;
        
        for (int i = 0; i < n; i++) {
            shuttles.addLast(currentShuttle);
            currentShuttle += t;
        }
        return shuttles;
    }

    private Queue<Integer> generateCrew(String[] timetable) {
        return Arrays.stream(timetable)
                .map(this::toMinutes)
                .sorted()
                .collect(Collectors.toCollection(LinkedList::new));
    }


    private int toMinutes(String time) {
        String[] tokens = time.split(":");
        int hours = Integer.parseInt(tokens[0]);
        int minutes = Integer.parseInt(tokens[1]);
        return hours * 60 + minutes;
    }

    private String toResult(int totalMinutes) {
        int hours = totalMinutes / 60;
        int minutes = totalMinutes % 60;
        return String.format("%02d:%02d", hours, minutes);
    }
}
