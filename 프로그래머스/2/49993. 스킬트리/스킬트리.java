import java.util.*;

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
        // 스킬트리 큐를 만든다.
        Deque<Character> skillTreeQueue = new ArrayDeque<>();
        for (char c : skill.toCharArray()) {
            skillTreeQueue.addLast(c);
        }
        
        // 금지된 스킬들을 얻는다.
        Set<Character> pobbidenSkillSet = new HashSet<>();
        for (int i = 1; i < skill.length(); i++) {
            pobbidenSkillSet.add(skill.charAt(i));
        }
        
        // 스킬트리 순서대로 스킬을 배울 수 있는지 검사한다.
        for (char c : skillTree.toCharArray()) {
            // 배울 수 없는 스킬이라면 false를 반환한다.
            if (pobbidenSkillSet.contains(c)) {
                return false;
            }
            
            // 스킬 중 가장 앞에 있는 스킬이라면 다음 금지된 스킬을 열어준다.
            if (!skillTreeQueue.isEmpty() && skillTreeQueue.peek() == c) {
                skillTreeQueue.removeFirst();
                pobbidenSkillSet.remove(skillTreeQueue.peek());
            }
        }
        
        return true;
    }
}