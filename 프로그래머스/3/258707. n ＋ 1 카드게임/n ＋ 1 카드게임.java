import java.util.ArrayList;
import java.util.List;
class Solution {
    public int solution(int coin, int[] cards) {
        int answer = game(cards.length, coin, cards);;

        game(cards.length, coin, cards);

        return answer;
    }

    private static int game(int n, int coin, int[] cards) {
        int result = 1;

        int cardIdx = n / 3;
        int target = n + 1;
        boolean[] isGetCard = new boolean[n + 1];
        boolean[] trashCard = new boolean[n + 1];
        List<Integer> nowCards = new ArrayList<>();

        for (int i = 0; i < cardIdx; i++) {
            nowCards.add(cards[i]);
            isGetCard[cards[i]] = true;
        }

        while (cardIdx < n) {

            trashCard[cards[cardIdx++]] = trashCard[cards[cardIdx++]] = true;

            boolean isGo = false;
            for (Integer nowCard : nowCards) {
                if (isGetCard[nowCard] && isGetCard[target - nowCard] && !isGo) {
                    isGetCard[nowCard] = isGetCard[target - nowCard] = false;
                    isGo = true;
                }
            }

            if (!isGo) {
                if (coin >= 1) {
                    for (Integer nowCard : nowCards) {
                        if (isGetCard[nowCard] && trashCard[target - nowCard] && !isGo) {
                            coin -= 1;
                            trashCard[target - nowCard] = false;
                            isGetCard[nowCard] = false;
                            isGo = true;
                        }
                    }
                }
            }

            if (!isGo) {
                if (coin >= 2) {
                    for (int i = 1; i <= n; i++) {
                        if (trashCard[i] && trashCard[target - i] && !isGo) {
                            isGo = true;
                            trashCard[i] = trashCard[target - i] = false;
                            coin -= 2;
                        }
                    }
                }
            }

            if (isGo) {
                result++;
                continue;
            }

            break;
        }
        return result;
    }
}