import java.math.BigInteger;

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


/**
# 문제 이해
숫자가 listNode 형태로 주어짐
342 = (2) -> (4) -> (3)

두 수가 listNode로 주어지고, 두 수의 합을 listNode 형태로 반환해야 함

# 간단한 해결방법

listNode 형태 -> int로 변환하여 두 수의 합을 구한 후,
listNode로 반환해도 됨

수의 길이가 총 100자까지 될 수 있어 int, long으로 안됨
BigInteger로 풀 수 있음

# 분해

# 코드 번역


 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        BigInteger bigInt1 = toBigInt(l1);
        BigInteger bigInt2 = toBigInt(l2);

        BigInteger sum = bigInt1.add(bigInt2);

        return toLinkedList(sum.toString());
    }

    private static BigInteger toBigInt(ListNode node) {
        StringBuilder sb = new StringBuilder();

        while (node != null) {
            sb.append(node.val);
            node = node.next;
        }

        sb.reverse();
        return new BigInteger(sb.toString());
    }

    private static ListNode toLinkedList(String digits) {
        ListNode dummy = new ListNode(0);
        ListNode current = dummy;
        
        for (int i = digits.length() - 1; i >= 0; i--) {
            current.next = new ListNode(digits.charAt(i) - '0');
            
            current = current.next;
        }
        return dummy.next;
    }
}
