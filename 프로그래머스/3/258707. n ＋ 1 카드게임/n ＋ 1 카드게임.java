import java.util.ArrayList;
import java.util.HashSet;
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

        int targetCnt = 0;
        for (int i = 0; i < cardIdx; i++) {
            isGetCard[cards[i]] = true;
        }
        for (int i = 0; i < cardIdx; i++) {
            if (isGetCard[target - cards[i]] && isGetCard[cards[i]]) {
                isGetCard[target - cards[i]] = false;
                isGetCard[cards[i]] = false;
                targetCnt++;
            }
        }

        HashSet<Integer> trashC = new HashSet<>();

        while (cardIdx < n) {
            int card1 = cards[cardIdx++], card2 = cards[cardIdx++];

            trashC.add(card1);
            trashC.add(card2);
            trashCard[card1] = trashCard[card2] = true;

            boolean isGo = false;
            if (targetCnt > 0) {
                targetCnt -= 1;
                isGo = true;
            }

            if (!isGo) {
                if (coin >= 1) {
                    for (Integer i : trashC) {
                        if (isGetCard[target - i]) {
                            isGetCard[i] = false;
                            isGo = true;
                            coin -= 1;
                            trashC.remove(i);
                            break;
                        }
                    }
                }
            }

            if (!isGo) {
                if (coin >= 2) {
                    for (Integer i : trashC) {
                        if (trashCard[target - i]) {
                            trashCard[target - i] = trashCard[i] = false;
                            isGo = true;
                            coin -= 2;
                            trashC.remove(i);
                            trashC.remove(target - i);
                            break;
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
