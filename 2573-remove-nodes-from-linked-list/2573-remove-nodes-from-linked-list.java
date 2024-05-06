/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    
    /**
     * 다음 노드가 작은 값만 있는 노드들을 복사합니다.
     * 복사된 ListNode는 내림차순으로 이루어져 있습니다.
     */ 
    public ListNode removeNodes(ListNode head) {
        ListNode current = head;
        Deque<Integer> stack = new ArrayDeque<>();
        while (current != null) {
            // 이전 노드가 작다면 제거합니다.
            while (!stack.isEmpty() && stack.peek() < current.val) {
                stack.pop();
            }
            stack.push(current.val);

            current = current.next;
        }

        if (stack.isEmpty()) {
            return null;
        }
        ListNode newTail = new ListNode(stack.pop());
        
        ListNode next = newTail;
        while(!stack.isEmpty()) {
            ListNode newNode = new ListNode(stack.pop(), next);
            next = newNode;
        }

        return next;
    }
}
