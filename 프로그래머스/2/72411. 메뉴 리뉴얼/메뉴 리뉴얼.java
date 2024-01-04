import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

class Solution {

    public String[] solution(String[] orders, int[] course) {
        // orders를 정렬된 set으로 변환한다.
        Set<String>[] orderSet = new Set[orders.length];
        for (int i = 0; i < orderSet.length; i++) {
            orderSet[i] = new TreeSet<>(List.of(orders[i].split("")));
        }

        Set<String> menuCandidates = new HashSet<>();

        // course 크기의 orders로 만든 코스요리를 만든다.
        for (int size : course) {
            List<Set<String>> courseMenus = new ArrayList<>();
            generateCourseMenus(orderSet, size, courseMenus);

            // 만든 코스요리가 몇번 주문 되었는지 계산한다.
            Map<Integer, List<String>> courseMap = new HashMap<>();
            for (Set<String> courseMenu : courseMenus) {
                CourseCount courseCount = getCourseCount(orderSet, courseMenu);
                courseMap.computeIfAbsent(courseCount.count, k -> new ArrayList<>())
                        .add(courseCount.name);
            }

            // 해당 course count에서 가장 많이 주문된 요리를 메뉴 후보에 추가한다. (최소 2 이상이어야 한다)
            List<String> addingCourses = courseMap.entrySet().stream()
                    .filter(e -> e.getKey() >= 2)
                    .max(Entry.comparingByKey())
                    .map(Entry::getValue)
                    .orElse(new ArrayList<>());

            menuCandidates.addAll(addingCourses);
        }

        // 메뉴 후보를 반환한다.
        return menuCandidates.stream()
                .sorted()
                .toArray(String[]::new);
    }

    private void generateCourseMenus(Set<String>[] orderSet, int size, List<Set<String>> courseMenus) {
        for (Set<String> order : orderSet) {
            ArrayList<String> list = new ArrayList<>(order);
            generateCourseMenus(list, 0, new TreeSet<>(), size, courseMenus);
        }
    }

    private void generateCourseMenus(List<String> order, int orderIndex,
            Set<String> courseMenu, int size, List<Set<String>> courseMenus) {
        if (courseMenu.size() == size) {
            courseMenus.add(new TreeSet<>(courseMenu));
            return;
        }

        for (int i = orderIndex; i < order.size(); i++) {
            courseMenu.add(order.get(i));

            generateCourseMenus(order, i + 1, courseMenu, size, courseMenus);

            courseMenu.remove(order.get(i));
        }
    }


    private CourseCount getCourseCount(Set<String>[] orderSet, Set<String> courseMenu) {
        int count = (int) Arrays.stream(orderSet)
                .filter(order -> order.containsAll(courseMenu))
                .count();

        return new CourseCount(count, String.join("", courseMenu));
    }

    private static class CourseCount {

        int count;
        String name;

        public CourseCount(int count, String name) {
            this.count = count;
            this.name = name;
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] orders = {"ABCFG", "AC", "CDE", "ACDE", "BCFG", "ACDEH"};
        int[] course = {2, 3, 4};

        String[] result = solution.solution(orders, course);
        System.out.println(Arrays.toString(result));
    }
}