package controller;

import view.GameView;
import model.Player;
import model.Portfolio;
import model.Quiz;
import model.Stock;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class MainController {

	private GameView view = new GameView();
    private List<Quiz> quizList = new ArrayList<>();
    private Map<String, Stock> stockMap = new LinkedHashMap<>();
    private Player player;

    public void run() {
        initQuiz();
        initStocks();

        String name = view.inputName();
        player = new Player(name);
        view.welcome(name);

        int quizScore = view.showQuiz(quizList);
        view.showInvestmentIntro();

        for (int turn = 1; turn <= 5; turn++) {
            System.out.println("\n--- TURN " + turn + " ---");
            boolean endTurn = false;
            while (!endTurn) {
                view.showStocks(stockMap);
                int menu = view.showMenu();
                switch (menu) {
                    case 1 -> {
                        int code = view.inputCode("매수");
                        int qty = view.inputQuantity();
                        Stock stock = getStockByCode(code);
                        int cost = stock.getPrice() * qty;
                        if (player.deductCash(cost)) {
                            player.buy(stock.getName(), qty);
                            System.out.println("→ " + stock.getName() + " " + qty + "주 매수 완료 (" + cost + "원 차감)");
                        } else System.out.println("→ 잔액 부족");
                    }
                    case 2 -> {
                        int code = view.inputCode("매도");
                        int qty = view.inputQuantity();
                        Stock stock = getStockByCode(code);
                        if (player.sell(stock.getName(), qty)) {
                            int income = stock.getPrice() * qty;
                            player.addCash(income);
                            System.out.println("→ " + stock.getName() + " " + qty + "주 매도 완료 (" + income + "원 입금)");
                        } else System.out.println("→ 보유 수량 부족");
                    }
                    case 3 -> {
                        System.out.printf("\n[현재 잔고] %,d원\n", player.getCash());
                        view.showHoldings(player, stockMap);
                    }
                    case 4 -> endTurn = true;
                }
            }
            stockMap.values().forEach(Stock::updatePrice);
        }
        int eval = player.evaluate(stockMap);
        view.showResult(eval, quizScore);
    }

    private void initQuiz() {
        quizList.add(new Quiz("주식을 매수한다는 뜻은?", new String[]{"주식을 판다", "주식을 산다", "현금을 인출한다", "이자를 받는다"}, 2));
        quizList.add(new Quiz("PER(주가수익비율)은 무엇을 나타내나요?", new String[]{"부채비율", "기업의 순이익", "주가가 이익의 몇 배인지", "배당금 비율"}, 3));
        quizList.add(new Quiz("주식시장에서 ‘호가’란 무엇을 의미하나요?", new String[]{"거래 수수료", "주식을 보유한 기간", "사고파는 가격", "투자자 등급"}, 3));
        quizList.add(new Quiz("장기투자의 장점으로 올바른 것은?", new String[]{"단타보다 수수료가 많이 든다", "가격 예측이 어려워진다", "복리 효과를 기대할 수 있다", "하루에 수익을 내야 한다"}, 3));
        quizList.add(new Quiz("분산투자가 필요한 이유는?", new String[]{"한 종목에 집중해서 더 큰 수익을 내기 위해", "여러 종목에 나누어 위험을 줄이기 위해", "수수료를 높이기 위해", "종목을 많이 보유하면 부자가 되기 위해"}, 2));
    }

    private void initStocks() {
        stockMap.put("삼성전자", new Stock("삼성전자", 65000, 1.2));
        stockMap.put("SK하이닉스", new Stock("SK하이닉스", 120000, -0.8));
        stockMap.put("현대차", new Stock("현대차", 180000, 0.3));
    }

    private Stock getStockByCode(int code) {
        List<Stock> list = new ArrayList<>(stockMap.values());
        return list.get(code - 1);
    }
}
