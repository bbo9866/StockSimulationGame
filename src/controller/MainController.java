package controller;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import model.Player;
import model.Quiz;
import model.QuizDAO;
import model.Stock;
import model.StockDAO;
import view.GameView;

public class MainController {

	private GameView view = new GameView();
	private List<Quiz> quizList = new ArrayList<>();
	private Map<String, Stock> stockMap = new LinkedHashMap<>();
	private Map<String, List<Double>> turnRates = new LinkedHashMap<>();
	private Player player;

	public void run() {
		try {
			initQuiz();
			initStocks();
		} catch (Exception e) {
			e.printStackTrace();
		}

		String name = view.inputName();

		player = new Player(name);
		view.welcome(name);

		// 퀴즈 전 시세 안내
		view.showStockIntro();
		view.showStocks(stockMap);

		// 메뉴 반복
		boolean proceedToQuiz = true;
		while (proceedToQuiz) {
			String choice = view.showPreQuizMenu();

			try {
				int number = Integer.parseInt(choice);
				
				switch (number) {
					case 1 -> {
						String newName = view.inputStockName();
						int newPrice = view.inputStockPrice();
						Stock stock = new Stock(newName, newPrice);
	
						try {
							if (StockDAO.existsByName(newName)) {
								System.out.println("이미 존재하는 종목입니다. 다른 이름을 입력해주세요.");
								break; // 중복이므로 추가하지 않고 종료
							}
	
							StockDAO.insertStock(stock);
							System.out.println("종목 추가 완료!");
	
							if (!turnRates.containsKey(newName)) {
								turnRates.put(newName, new ArrayList<>());
							}
	
							stockMap = StockDAO.getAllStocks();
							view.showStocks(stockMap);
	
						} catch (Exception e) {
							System.out.println("중복된 종목입니다. 다시 입력하세요.");
							continue;
						}
					}
					case 2 -> proceedToQuiz = false;
					default -> {
						System.out.println("올바른 번호를 입력해주세요.");
						continue;
					}
				}
			} catch (Exception e) {
				System.out.println("올바른 번호를 입력해주세요.");
				continue;
			}

		}

		int quizScore = view.showQuiz(quizList);
		view.showInvestmentIntro();

		for (int turn = 1; turn <= 5; turn++) {
			System.out.println("\n--- TURN " + turn + " ---");
			boolean endTurn = true;

			while (endTurn) {
				view.showStockTurnHistory(stockMap, turnRates);
				int menu = view.showMenu();

				switch (menu) {
				case 1 -> {
					int code = view.inputCode("매수");
					int qty = view.inputQuantity();
					Stock stock = getStockByCode(code);
					int cost = stock.getPrice() * qty;

					if (player.deductCash(cost)) {
						player.buy(stock.getName(), qty);
						System.out.println("####################");
						System.out.println("→ " + stock.getName() + " " + qty + "주 매수 완료 (" + cost + "원 차감)");

					} else
						System.out.println("→ 잔액 부족");
				}

				case 2 -> {
					int code = view.inputCode("매도");
					int qty = view.inputQuantity();
					Stock stock = getStockByCode(code);

					if (player.sell(stock.getName(), qty)) {
						int income = stock.getPrice() * qty;
						player.addCash(income);
						System.out.println("####################");
						System.out.println("→ " + stock.getName() + " " + qty + "주 매도 완료 (" + income + "원 입금)");

					} else
						System.out.println("→ 보유 수량 부족");
				}

				case 3 -> {
					System.out.println("####################");
					System.out.printf("\n[현재 잔고] %,d원\n", player.getCash());

				}
				case 4 -> endTurn = false;

				}
			}
			// 턴 종료 후 가격 업데이트 및 변화율 기록
			for (Stock stock : stockMap.values()) {
				stock.updatePrice(); // 내부에서 가격 & 변화율 갱신
				turnRates.get(stock.getName()).add(stock.getRate()); // 외부에서 변화율 저장

			}
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
			for (String name : stockMap.keySet()) {
				turnRates.put(name, new ArrayList<>()); // 종목별 턴별 변화율 초기화
			}
		} catch (Exception e) {
			System.out.println("주식 데이터를 불러오는 중 오류 발생: " + e.getMessage());
		}
	}

	private Stock getStockByCode(int code) {
		List<Stock> list = new ArrayList<>(stockMap.values());
		return list.get(code - 1);
	}

}