import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

class Solution {
    public int solution(int coin, int[] cards) {
        Queue<Integer> cardQueue = new LinkedList<>();
        for (int card : cards) {
            cardQueue.offer(card);
        }

        Set<Integer> handCards = generateHandCards(cardQueue);
        int targetNumber = cards.length + 1;
        int rounds = 1;
        Set<Integer> drawableCards = new HashSet<>();
        int availableCoins = coin;

        while (!cardQueue.isEmpty()) {
            drawableCards.addAll(drawCards(cardQueue));
            if (!playRound(handCards, targetNumber)) {
                availableCoins -= pickCards(handCards, drawableCards, availableCoins, targetNumber);
                if (!playRound(handCards, targetNumber)) {
                    break;
                }
            }

            rounds++;
        }

        return rounds;
    }

    private Set<Integer> generateHandCards(Queue<Integer> initialQueue) {
        int size = initialQueue.size() / 3;
        Set<Integer> handCards = new HashSet<>();

        for (int i = 0; i < size; i++) {
            handCards.add(initialQueue.poll());
        }
        return handCards;
    }

    private boolean playRound(Set<Integer> handCards, int targetNumber) {
        for (Integer card : handCards) {
            if (handCards.contains(targetNumber - card)) {
                handCards.remove(card);
                handCards.remove(targetNumber - card);
                return true;
            }
        }

        return false;
    }

    private Set<Integer> drawCards(Queue<Integer> cardQueue) {
        Set<Integer> drawnCards = new HashSet<>();

        for (int i = 0; i < 2 && !cardQueue.isEmpty(); i++) {
            drawnCards.add(cardQueue.poll());
        }
        return drawnCards;
    }

    private int pickCards(Set<Integer> handCards, Set<Integer> drawableCards, int coinLimit, int targetNumber) {
        for (int card : drawableCards) {
            if (1 <= coinLimit && handCards.contains(targetNumber - card)) {
                drawableCards.remove(card);
                handCards.add(card);
                return 1;
            }
        }

        for (int card : drawableCards) {
            if (2 <= coinLimit && drawableCards.contains(targetNumber - card)) {
                drawableCards.remove(card);
                drawableCards.remove(targetNumber - card);
                handCards.add(card);
                handCards.add(targetNumber - card);
                return 2;
            }
        }

        return 0;
    }

    public static void main(String[] args) {
        int coin = 4;
        int[] cards = {3, 6, 7, 2, 1, 10, 5, 9, 8, 12, 11, 4};
        Solution solution = new Solution();

        int result = solution.solution(coin, cards);
        System.out.println(result);
    }
}
