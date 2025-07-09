// view/View.java
// ---------------------
package view;

import model.Player;
import model.Quiz;
import model.Stock;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class GameView {
	private Scanner sc = new Scanner(System.in);

    public String inputName() {
        System.out.println("이름을 입력해주세요:");
        return sc.nextLine();
    }

    public void welcome(String name) {
        System.out.println("\n" + name + "님, 환영합니다! 당신은 주식 투자 경험이 있나요?\n" +
                "경험이 있다면 당신의 실력을 판별할 기회, 없다면 주식을 입문하기 위한 지식을 쌓아봐요!\n"
                + "시작합니다!");
        System.out.println("================================================================");
    }

    public int showQuiz(List<Quiz> quizList) {
        int score = 0;
        System.out.println("[첫번째 종목: 퀴즈]\n총 5문제의 주식 관련 퀴즈를 풀어보세요! \n");

        for (int i = 0; i < quizList.size(); i++) {
            Quiz q = quizList.get(i);
            System.out.println((i + 1) + ") " + q.getQuestion());
            String[] opts = q.getOptions();
            for (int j = 0; j < opts.length; j++) {
                System.out.println("\t" + (j + 1) + ". " + opts[j]);
            }
            int input = sc.nextInt();
            if (input == q.getAnswer()) score++;
            System.out.println("→ 정답: " + q.getAnswer() + "\n");
        }
        return score * 10;
    }

    public void showInvestmentIntro() {
        System.out.println("================================================================");
        System.out.println("\n[두번쨰 종목: 모의투자]\n주가 변동을 체험해보며 직접 자본을 관리해보세요!\n " +
                "주어진 턴 안에 목표 수익에 달성 해봅시다!!\n-초기자본: 1,000,000원\n-목표 수익률: 10%\n-도전 턴 수: 5");
    }

    public int showMenu() {
        System.out.println("\n- 메뉴를 선택하세요:\n1. 매수\n2. 매도\n3. 보유 자산 확인\n4. 다음 턴 진행");
        return sc.nextInt();
    }

    public int inputCode(String action) {
        System.out.println("\n" + action + "할 종목코드를 입력하세요:");
        return sc.nextInt();
    }

    public int inputQuantity() {
        System.out.println("수량을 입력하세요:");
        return sc.nextInt();
    }

    public void showStocks(Map<String, Stock> stocks) {
        System.out.println("\n[종목 리스트]");
        int i = 1;
        for (Stock s : stocks.values()) {
            System.out.printf("%d. %s %d (%.1f%%)\n", i++, s.getName(), s.getPrice(), s.getRate());
        }
    }

    public void showHoldings(Player p, Map<String, Stock> stocks) {
        System.out.println("\n[보유 종목]");
        for (String name : p.getHoldings().keySet()) {
            int qty = p.getHoldings().get(name);
            int price = stocks.get(name).getPrice();
            System.out.printf("%s %d주 (%d원)\n", name, qty, price);
        }
    }

    public void showResult(int eval, int quizScore) {
        System.out.println("\n[최종 결과]");
        int profit = eval - 1000000;
        double percent = (profit / 1000000.0) * 100;
        int investScore = (percent >= 15) ? 100 : (percent >= 10 ? 80 : 50);
        int finalScore = (int)(quizScore * 0.6 + investScore * 0.4);

        System.out.printf("총 평가금액: %,d원\n수익률: %.1f%%\n", eval, percent);
        System.out.printf("[퀴즈 점수] %d점\n[모의투자 점수] %d점\n→ 종합점수: %d점\n", quizScore, investScore, finalScore);

        String level = finalScore >= 90 ? "Legend" : finalScore >= 75 ? "고수" : finalScore >= 60 ? "중급" : finalScore >= 40 ? "초보" : "입문자";
        System.out.println("등급: " + level);
    }
}
