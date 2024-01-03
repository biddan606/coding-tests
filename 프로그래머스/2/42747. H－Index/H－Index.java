import java.util.Arrays;

class Solution {

    public int solution(int[] citations) {
        int[] sortedCitations = Arrays.stream(
                        Arrays.copyOf(citations, citations.length)
                )
                .sorted()
                .toArray();
        int maxH = 0;

        for (int i = 0; i < sortedCitations.length; i++) {
            int h = Math.min(sortedCitations.length - i, sortedCitations[i]);
//            h = Math.min(h, i);

            maxH = Math.max(maxH, h);
        }

        return maxH;
    }
}

/*
[3, 0, 6, 1, 5]

정렬 후:
[0, 1, 3, 5, 6]
 0  1  2  3  4
length: 5

---

i: 0
length - i = 5
[0]: 0
나머지: i

5개 이상이 0이상 인용되었다.
나머지는 0개 이하이다.

h = 0

---

i: 1
length - i = 4
[1]: 1
나머지: i

4개 이상이 1이상 인용되었다.
나머지는 1개 이하이다.

h = 1

---

i: 2
length - i = 3
[2]: 3
나머지: i

3개 이상이 3이상 인용되었다.
나머지는 2개 이하이다.

h = 3

---

i: 3
length - i = 2
[3]: 5
나머지: i

2개 이상이 5이상 인용되었다.
나머지는 3개 이하이다.

h = 2

---

i: 4
length - i = 1
[3]: 6
나머지: i

1개 이상이 6이상 인용되었다.
나머지는 4개 이하이다.

h = 1
 */
