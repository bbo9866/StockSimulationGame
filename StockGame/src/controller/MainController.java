package controller;

import view.GameView;
import model.Player;
import model.Quiz;
import model.QuizDAO;
import model.Stock;
import model.StockDAO;

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
        try {
            quizList = QuizDAO.getRandomQuizzes(5);
        } catch (Exception e) {
            System.out.println("퀴즈 데이터를 불러오는 중 오류 발생: " + e.getMessage());
        }
    }

    private void initStocks() {
        try {
            stockMap = StockDAO.getAllStocks();
        } catch (Exception e) {
            System.out.println("주식 데이터를 불러오는 중 오류 발생: " + e.getMessage());
        }
    }

    private Stock getStockByCode(int code) {
        List<Stock> list = new ArrayList<>(stockMap.values());
        return list.get(code - 1);
    }
}
