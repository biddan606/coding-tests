import java.util.*;
import java.util.stream.*;

class Solution {
    public String[] solution(String[] orders, int[] course) {
        // 주문의 메뉴를 찾기 쉬운 형태인 Set<String>으로 변환한다.
        List<Set<String>> orderList = Arrays.stream(orders)
                .map(str -> Arrays.stream(str.split(""))
                                .collect(Collectors.toSet()))
                        .collect(Collectors.toList());

        // 입력 받은 course 길이의 메뉴 후보만 받도록 초기 설정을 한다.
        Map<Integer, List<Course>> courses = new HashMap<>();
        for (int length : course) {
            List<Course> list = new ArrayList<>();

            list.add(new Course("", 0));

            courses.put(length, list);
        }
        // 메뉴 후보를 받는다.
        // course 길이 중 가장 많이 주문된 메뉴만 받는다.(중복 가능)
        getCourses('A', new HashSet<>(), orderList, courses);

        // 2번 이상 주문한 메뉴 조합만을 반환한다.
        return courses.values().stream()
                .filter(list -> list.get(0).occurrences >= 2)
                .flatMap(List::stream)
                .map(c -> c.name)
                .sorted()
                .toArray(String[]::new);
    }

    private void getCourses(char nextMenu, Set<String> selectedMenus,
            List<Set<String>> orderList,
            Map<Integer, List<Course>> courses) {
        // 선택한 메뉴의 주문 횟수를 찾는다.
        int occurrences = (int) orderList.stream()
                .filter(order -> order.containsAll(selectedMenus))
                .count();
        
        // 이미 2보다 적게 불렸다면 다음으로 진행하더라도 적으므로 추가로 진행하지 않는다.
        if (occurrences < 2) {
            return;
        }

        // course 길이에 해당하는 길이라면 변경을 시도한다.
        int size = selectedMenus.size();
        if (courses.containsKey(size)) {
            List<Course> courseList = courses.get(size);
            Course course = new Course(selectedMenus.stream()
                    .sorted()
                    .collect(Collectors.joining("")),
                    occurrences);

            // 원래의 주문 횟수보다 많이 불렸다면, 변경한다.
            Course original = courseList.get(0);
            if (original.occurrences < occurrences) {
                courseList.clear();
                courseList.add(course);
            } else if (original.occurrences == occurrences) { // 중복
                courseList.add(course);
            }
        }
        
        // 길이가 10까지만 가능하므로 추가로 진행하지 않는다.
        if (size >= 10) {
            return;
        }

        // A-Z 오름차순으로 중복되지 않는 문자를 넣으며 메뉴를 구성한다.
        for (char menuChar = nextMenu; menuChar <= 'Z'; menuChar++) {
            String menu = String.valueOf(menuChar);

            selectedMenus.add(menu);

            getCourses((char) (menuChar + 1), selectedMenus, orderList, courses);

            selectedMenus.remove(menu);
        }
    }

    private static class Course {
        public final String name;
        public final int occurrences;

        public Course(String name, int occurrences) {
            this.name = name;
            this.occurrences = occurrences;
        }
    }
}
