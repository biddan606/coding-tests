import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

class Solution {

    public int solution(String[] userIds, String[] bannedIds) {
        String[][] bannedGroup = Arrays.stream(bannedIds)
                .map(bannedId -> bannedId.replace("*", "."))
                .map(bannedId ->
                        Arrays.stream(userIds)
                                .filter(userId -> userId.matches(bannedId))
                                .toArray(String[]::new)
                ).toArray(String[][]::new);

        Set<Set<String>> matchedResult = new HashSet<>();

        countCases(bannedGroup, 0, new HashSet<>(), matchedResult);

        return matchedResult.size();
    }

    private void countCases(String[][] bannedGroup, int bannedGroupIndex, Set<String> banned,
            Set<Set<String>> matchedResult) {
        if (bannedGroup.length == bannedGroupIndex) {
            matchedResult.add(new HashSet<>(banned));
            return;
        }

        for (String banningId : bannedGroup[bannedGroupIndex]) {
            if (banned.contains(banningId)) {
                continue;
            }

            banned.add(banningId);

            countCases(bannedGroup, bannedGroupIndex + 1, banned, matchedResult);

            banned.remove(banningId);
        }
    }

    public static void main(String[] args) {
        Solution solution = new Solution();
        String[] userIds = {"frodo", "fradi", "crodo", "abc123", "frodoc"};
        String[] bannedIds = {"fr*d*", "abc1**"};

        System.out.println(solution.solution(userIds, bannedIds));
    }
}
