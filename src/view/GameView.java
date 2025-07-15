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
        System.out.print("투자자 이름을 입력해주세요: ");
        return sc.nextLine();
    }
    
    public void welcome(String name) {
        System.out.println("\n" + name + "님, 환영합니다! 당신은 주식 투자 경험이 있나요?");
        System.out.println("경험이 있다면 당신의 실력을 판별할 기회, 없다면 주식을 입문하기 위한 지식을 쌓아봐요!");
        System.out.println("시작합니다!");
        System.out.println("======================================================");
    }
    
    public String showPreQuizMenu() {
        System.out.println("\n[퀴즈 시작 전 메뉴]");
        System.out.println("1. 주식 종목 추가");
        System.out.println("2. 퀴즈 시작");
        System.out.print("선택: ");

        return sc.nextLine();
    }
    
    public String inputStockName() {
        System.out.print("➕ 추가할 종목명: ");
        sc.nextLine(); // 개행 문자 처리
        return sc.nextLine();
    }

    public int inputStockPrice() {
        System.out.print("💰 초기 주가: ");
        return sc.nextInt();
    }
    
    public void showStockIntro() {
    	
        System.out.println("================================================================");
        System.out.println("[📊 현재 주식 시세 안내]");
        
    }
    
    //추가    
    public int showQuiz(List<Quiz> quizList) {
    	
        int score = 0;
        System.out.println("[첫번째 종목: 퀴즈]");
        System.out.println("총 5문제의 주식 관련 퀴즈를 풀어보세요!\n");
        
        int size = quizList.size();
        Quiz q = null;
        
        for (int i = 0; i < size; i++) {
            q = quizList.get(i);
            System.out.println((i + 1) + ") " + q.getQuestion());
            
            String[] opts = q.getOptions();
            for (int j = 0; j < opts.length; j++) {
                System.out.println("\t" + (j + 1) + ". " + opts[j]);
            }
            
            System.out.print("정답을 입력해주세요> ");
            int input = sc.nextInt();
            if (input == q.getAnswer()) {
            	score++;
            }
            
            System.out.println("→ 정답: " + q.getAnswer() + "\n");
        }
        
        return score * 10;
        
    }
    
    public void showInvestmentIntro() {
        System.out.println("======================================================");
        System.out.println("\n[두번째 종목: 모의투자]");
        System.out.println("주가 변동을 체험해보며 직접 자본을 관리해보세요!");
        System.out.println("- 초기자본: 1,000,000원");
        System.out.println("- 목표 수익률: 10%");
        System.out.println("- 도전 턴 수: 5");
        System.out.println("======================================================");
    }
    
    public int showMenu() {
        System.out.println("\n- 메뉴를 선택하세요:");
        System.out.println("1. 매수\n2. 매도\n3. 보유 자산 확인\n4. 다음 턴 진행");
        return sc.nextInt();
    }
    
    public int inputCode(String action) {
        System.out.print("\n" + action + "할 종목코드를 입력하세요: ");
        return sc.nextInt();
    }
    
    public int inputQuantity() {
        System.out.print("수량을 입력하세요: ");
        return sc.nextInt();
    }
    
    public void showStocks(Map<String, Stock> stocks) {
        System.out.println("\n[종목 리스트]");
        System.out.printf("%-5s %-12s %-12s\n", "코드", "종목명", "가격(원)");
        System.out.println("------------------------------------------------");
        int code = 1;
        for (Stock s : stocks.values()) {
            String name = padRight(s.getName(), 12);
            String price = String.format("%,12d", s.getPrice());
            System.out.printf("%-5d %s %s\n", code++, name, price);
        }
    }
    
    public void showHoldings(Player p, Map<String, Stock> stocks) {
    	
        System.out.println("\n[보유 종목]");
        int total = 0;
        System.out.printf("%-10s %-10s %-10s\n", "종목명", "보유수량", "평가금액");
        
        for (String name : p.getHoldings().keySet()) {
            int qty = p.getHoldings().get(name);
            int price = stocks.get(name).getPrice();
            int value = qty * price;
            total += value;
            System.out.printf("%-10s %-10d %,10d원\n", name, qty, value);
        }
        
        System.out.println("-----------------------------------------");
        System.out.printf("총 평가 금액: %,d원\n", total);
        
    }
    
    public void showResult(int eval, int quizScore) {
    	
        System.out.println("\n[최종 결과]");
        int profit = eval - 1_000_000;
        double percent = (profit / 1_000_000.0) * 100;
        int investScore = (percent >= 15) ? 100 : (percent >= 10 ? 80 : 50);
        int finalScore = (int) (quizScore * 0.6 + investScore * 0.4);
        
        System.out.printf("총 평가금액: %,d원\n", eval);
        System.out.printf("수익률: %.1f%%\n", percent);
        System.out.printf("[퀴즈 점수] %d점\n", quizScore);
        System.out.printf("[모의투자 점수] %d점\n", investScore);
        System.out.printf("→ 종합점수: %d점\n", finalScore);
        String level = finalScore >= 90 ? "Legend"
                : finalScore >= 75 ? "고수"
                : finalScore >= 60 ? "중급"
                : finalScore >= 40 ? "초보"
                : "입문자";
        System.out.println("등급: " + level);
        
    }
    
    public void showStockTurnHistory(Map<String, Stock> stocks, Map<String, List<Double>> turnRates) {
    	
    	System.out.println("\n턴별 종목 성과");

        for (Map.Entry<String, Stock> entry : stocks.entrySet()) {
            String name = entry.getKey();
            Stock stock = entry.getValue();
            List<Double> rates = turnRates.get(name);

            String leftPart = String.format("%-15s (%8s원)", name, String.format("%,d", stock.getPrice()));

            StringBuilder ratePart = new StringBuilder();
            
            if (rates != null) {
                for (int i = 0; i < 4; i++) {
                    String rateStr = (i < rates.size()) ? String.format("%+5.1f%%", rates.get(i)) : "  ---  ";
                    ratePart.append(String.format("  T%d: %7s", i + 1, rateStr));  // 앞에 세로선 대신 공백 두칸 띄움
                }
            } else {
                for (int i = 0; i < 4; i++) {
                    ratePart.append(String.format("  T%d: %7s", i + 1, "  ---  "));
                }
            }

            System.out.println(leftPart + ratePart.toString());
        }
    }

    private int getDisplayWidth(String text) {
        int width = 0;
        for (char c : text.toCharArray()) {
            width += (Character.UnicodeBlock.of(c) == Character.UnicodeBlock.HANGUL_SYLLABLES) ? 2 : 1;
        }
        return width;
    }
    
    private String padRight(String text, int targetWidth) {
        int pad = targetWidth - getDisplayWidth(text);
        StringBuilder sb = new StringBuilder(text);
        for (int i = 0; i < pad; i++) sb.append(' ');
        return sb.toString();
    }
    
    public Stock inputNewStock() {
		sc.nextLine();
		System.out.println("새 주식 이름 입력:");
		String name = sc.nextLine();
		
	    System.out.print("시작 가격 입력: ");
	    int price = sc.nextInt();
	    
	    System.out.print("등락률 입력 (예: 5.5): ");
	    return new Stock(name, price);
	}
    
  
}