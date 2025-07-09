// logic/QuizManager.java
// ---------------------
package logic;

import java.util.Scanner;

public class QuizManager {
    public int startQuiz() {
        Scanner sc = new Scanner(System.in);
        int score = 0;

        System.out.println("총 5문제의 퀴즈를 맞춰보세요!\n");

        System.out.println("1) 주식을 매수한다는 뜻은?");
        System.out.println("1. 주식을 판다\n2. 주식을 산다\n3. 현금을 인출한다\n4. 이자를 받는다");
        if (sc.nextInt() == 2) score++;
        System.out.println("⸻\n");

        System.out.println("2) PER(주가수익비율)은 무엇을 나타내나요?");
        System.out.println("1. 부채비율\n2. 기업의 순이익\n3. 주가가 이익의 몇 배인지\n4. 배당금 비율");
        if (sc.nextInt() == 3) score++;
        System.out.println("⸻\n");

        System.out.println("3) 주식시장에서 ‘호가’란 무엇을 의미하나요?");
        System.out.println("1. 거래 수수료\n2. 주식을 보유한 기간\n3. 사고파는 가격\n4. 투자자 등급");
        if (sc.nextInt() == 3) score++;
        System.out.println("⸻\n");

        System.out.println("4) 장기투자의 장점으로 올바른 것은?");
        System.out.println("1. 단타보다 수수료가 많이 든다\n2. 가격 예측이 어려워진다\n3. 복리 효과를 기대할 수 있다\n4. 하루에 수익을 내야 한다");
        if (sc.nextInt() == 3) score++;
        System.out.println("⸻\n");

        System.out.println("5) 분산투자가 필요한 이유는?");
        System.out.println("1. 한 종목에 집중해서 더 큰 수익을 내기 위해\n2. 여러 종목에 나누어 위험을 줄이기 위해\n3. 수수료를 높이기 위해\n4. 종목을 많이 보유하면 부자가 되기 위해");
        if (sc.nextInt() == 2) score++;
        System.out.println("⸻\n");

        return score;
    }
}