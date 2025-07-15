package controller;

import logic.QuizManager;
import logic.Market;
import logic.Portfolio;
import view.View;
import model.Stock;

import java.util.Scanner;

public class MainController {

    private QuizManager quizManager;
    private Market market;
    private Portfolio portfolio;
    private View view;

    private double initialCash;
    private double goalPercent;
    private int maxTurns;

    public MainController(double initialCash, double goalPercent, int maxTurns) {
        this.initialCash = initialCash;
        this.goalPercent = goalPercent;
        this.maxTurns = maxTurns;

        this.quizManager = new QuizManager();
        this.market = new Market();
        this.portfolio = new Portfolio(initialCash);
        this.view = new View();
    }

    // 전체 게임 실행 흐름
    public void start() {
        view.showWelcomeMessage();
        runQuiz();
        runInvestment();
        calculateAndShowFinalResult(portfolio.getPlayerName());
    }

    // 퀴즈 진행 로직
    private void runQuiz() {
        view.showQuizIntro();
        int score = quizManager.startQuiz();
        double bonus = score * 10000; // 예: 문제당 1만 원 보너스
        portfolio.addCash(bonus);
        view.showQuizBonus(bonus);
    }

    // 투자 게임 실행
    private void runInvestment() {
        for (int turn = 1; turn <= maxTurns; turn++) {
            view.showTurnHeader(turn);
            market.fluctuatePrices();
            view.showStockList(market.getStocks());

            int action = view.getActionInput(); // 1=매수, 2=매도, 0=건너뛰기

            switch (action) {
                case 1 -> buyStock();
                case 2 -> sellStock();
                case 0 -> view.showSkipTurnMessage();
                default -> view.showInvalidAction();
            }
        }
    }

    // 매수 처리
    private void buyStock() {
        int stockIndex = view.getStockSelectionInput(market.getStocks());
        if (!market.isValidIndex(stockIndex)) {
            view.showInvalidStockIndex();
            return;
        }

        Stock selected = market.getStocks()[stockIndex];
        int quantity = view.getQuantityInput("매수");

        boolean result = portfolio.buy(selected, quantity);
        if (result) {
            view.showBuySuccess(selected, quantity);
        } else {
            view.showBuyFail();
        }
    }

    // 매도 처리
    private void sellStock() {
        if (portfolio.getOwnedStocks().isEmpty()) {
            view.showNoStocksToSell();
            return;
        }

        view.showOwnedStocks(portfolio.getOwnedStocks());
        String stockName = view.getStockNameInput("매도");
        int quantity = view.getQuantityInput("매도");

        boolean result = portfolio.sell(stockName, quantity, market);
        if (result) {
            view.showSellSuccess(stockName, quantity);
        } else {
            view.showSellFail();
        }
    }

    // 결과 출력
    @SuppressWarnings("unused")
	private void calculateAndShowFinalResult(String playerName) {
        double finalAsset = portfolio.getTotalAssetValue(market);
        double profitRate = (finalAsset - initialCash) / initialCash * 100;

        view.showFinalResult(playerName, finalAsset, profitRate, goalPercent);
    }
}
