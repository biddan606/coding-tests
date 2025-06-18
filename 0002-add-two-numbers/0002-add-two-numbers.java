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


## 다른 풀이

BigInteger는 간단한 풀이지만 변환 과정이 많음
ListNode -> ListNode를 그대로 반환할 수 있음(BigO는 동일)

# 분해

같은 자릿수의 digit를 더하면 두자릿수가 나옴

-> 십의 자릿수는 다음으로 넘기고 일의 자릿수는 현재 결과

-> ListNode.next가 더 큰 값이므로 다음 결과를 재귀형태로 받으면 됨

두 수의 길이는 같지 않음

-> ListNode가 Null일 수도 있다는 것을 감안해야 함

# 코드 번역

1. 재귀를 통해 ListNode 값을 받음
2. 재귀 함수의 결과가 null이면 반환, 존재한다면 다음 재귀를 진행함
-> 결과적으로 ListNode가 이어지는 형태를 띔

 */
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return addTwoNumbersHelper(l1, l2, 0);
    }

    private static ListNode addTwoNumbersHelper(ListNode l1, ListNode l2, int carry) {
        if (l1 == null && l2 == null) {
            return carry == 0 ? null : new ListNode(carry);
        }

        int sum = carry;

        sum += l1 == null ? 0 : l1.val;
        sum += l2 == null ? 0 : l2.val;
        
        int nextCarry = sum / 10;
        ListNode newListNode = new ListNode(sum % 10);
        
        ListNode nextL1 = l1 == null ? null : l1.next;
        ListNode nextL2 = l2 == null ? null : l2.next;

        newListNode.next = addTwoNumbersHelper(nextL1, nextL2, nextCarry);

        return newListNode;
    }
}
