// view/View.java
// ---------------------
package view;

import model.Stock;
import java.util.Map;
import java.util.Scanner;

public class View {
    private Scanner sc = new Scanner(System.in);

    public void showWelcomeMessage() {
        System.out.println("==== 🎯 목표 수익률 주식 게임 ====");
    }

    public void showQuizIntro() {
        System.out.println("퀴즈를 시작합니다! 맞힐수록 보너스 자본 지급 💰");
    }

    public void showQuizBonus(double bonus) {
        System.out.println("퀴즈 보너스: " + (int) bonus + "원 지급!");
    }

    public void showTurnHeader(int turn) {
        System.out.println("\n==== 턴 " + turn + " ====");
    }

    public void showStockList(Stock[] stocks) {
        System.out.println("[종목 리스트]");
        for (int i = 0; i < stocks.length; i++) {
            String sign = stocks[i].getChangePercent() >= 0 ? "+" : "";
            System.out.printf("%d) %s %.0f (%s%.1f%%)\n",
                i + 1,
                stocks[i].getName(),
                stocks[i].getPrice(),
                sign,
                stocks[i].getChangePercent());
        }
    }


    public int getActionInput() {
        System.out.print("1: 매수, 2: 매도, 0: 건너뛰기 > ");
        return sc.nextInt();
    }

    public int getStockSelectionInput(Stock[] stocks) {
        System.out.print("매수할 종목 번호 입력: ");
        return sc.nextInt();
    }

    public int getQuantityInput(String action) {
        System.out.print(action + "할 수량 입력: ");
        return sc.nextInt();
    }

    public void showSkipTurnMessage() {
        System.out.println("턴을 건너뜁니다.");
    }

    public void showInvalidAction() {
        System.out.println("잘못된 입력입니다.");
    }

    public void showInvalidStockIndex() {
        System.out.println("존재하지 않는 종목입니다.");
    }

    public void showBuySuccess(Stock stock, int qty) {
        System.out.println("✅ 매수 완료: " + stock.getName() + " x" + qty);
    }

    public void showBuyFail() {
        System.out.println("❌ 자본 부족으로 매수 실패");
    }

    public void showSellSuccess(String name, int qty) {
        System.out.println("✅ 매도 완료: " + name + " x" + qty);
    }

    public void showSellFail() {
        System.out.println("❌ 매도 실패: 수량 부족 또는 종목 없음");
    }

    public void showNoStocksToSell() {
        System.out.println("보유한 종목이 없습니다.");
    }

    public void showOwnedStocks(Map<String, Integer> owned) {
        System.out.println("[보유 종목]");
        owned.forEach((name, qty) ->
            System.out.println("- " + name + " x" + qty)
        );
    }

    public String getStockNameInput(String action) {
        System.out.print(action + "할 종목명 입력: ");
        return sc.next();
    }

    public void showFinalResult(String playerName, double finalAsset, double profitRate, double goal) {
        System.out.println("\n▶ " + playerName + "님의 게임 결과");
        System.out.printf("→ 최종 자산 평가: %.0f원%n", finalAsset);
        System.out.printf("→ 수익률: %.2f%%%n", profitRate);
        if (profitRate >= goal) {
            System.out.println("🎉 목표 달성 성공! 축하합니다!");
        } else {
            System.out.println("😢 목표 달성 실패. 다시 도전해보세요.");
        }
    }
}
