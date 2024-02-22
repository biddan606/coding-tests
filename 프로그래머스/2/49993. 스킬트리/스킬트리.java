class Solution {

    public int solution(String skill, String[] skill_trees) {
        // 스킬트리가 가능한지 검사한다.
        int availableCount = 0;
        for (String skillTree : skill_trees) {
            if (isAvailable(skill, skillTree)) {
                availableCount++;
            }
        }

        // 가능한 스킬트리 개수를 반환한다.
        return availableCount;
    }

    private boolean isAvailable(String skill, String skillTree) {
        // 스킬트리 순서대로 스킬을 배울 수 있는지 검사한다.
        String onlySkillCharacters = skillTree.replaceAll("[^" + skill + "]", "");
        return skill.startsWith(onlySkillCharacters);
    }
}
