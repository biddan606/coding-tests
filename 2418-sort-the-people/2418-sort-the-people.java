class Solution {
    public String[] sortPeople(String[] names, int[] heights) {
        int size = names.length;

        // 이름과 키를 조합하여 사람들을 만든다.
        Person[] people = new Person[size];
        for (int i = 0; i < size; i++) {
            people[i] = new Person(names[i], heights[i]);
        }

        // 사람들을 키 순으로 정렬한다.
        Arrays.sort(people, Comparator.comparingInt(Person::getHeight).reversed());

        // 키 순으로 정렬된 사람들의 이름을 추출한다.
        String[] namesOrderedByHeight = new String[size];
        for (int i = 0; i < size; i++) {
            namesOrderedByHeight[i] = people[i].name;
        }

        // 이름을 반환한다.
        return namesOrderedByHeight;
    }

    private class Person {
        private String name;
        private int height;

        public Person(String name, int height) {
            this.name = name;
            this.height = height;
        }

        public int getHeight() {
            return height;
        }

        public String getName() {
            return name;
        }
    }
}
