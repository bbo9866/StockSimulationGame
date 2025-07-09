package model;


import java.util.*;

public class QuizManager {
		
	private List<Quiz> quiz = new ArrayList<>();
	private int correctCount = 0;
	
	public void loadQuiz() {
		quiz.clear(); //list 초기화
		quiz.clear(); //list 초기화
		quiz.add(new Quiz("주식을 매수한다는 뜻은?", new String[]{"주식을 판다", "주식을 산다", "현금을 인출한다", "이자를 받는다"}, 2));
		quiz.add(new Quiz("PER(주가 수익비율)은 무엇을 나타내나요?", new String[]{"부채비율", "기업의 순이익", "주가가 이익의 몇 배인지", "배당금 비율"}, 3));
		quiz.add(new Quiz("주식시장에서 ‘호가’란 무엇을 의미하나요?", new String[]{"거래 수수료", "주식을 보유한 기간", "사고파는 가격", "투자자 등급"}, 3));
		quiz.add(new Quiz("장기투자의 장점으로 올바른 것은?", new String[]{"단타보다 수수료가 많이 든다", "가격 예측이 어려워진다", "복리 효과를 기대할 수 있다", "하루에 수익을 내야 한다"}, 3));
		quiz.add(new Quiz("분산투자가 필요한 이유는?", new String[]{"한 종목에 집중해서 더 큰 수익을 내기 위해", "여러 종목에 나누어 위험을 줄이기 위해", "수수료를 높이기 위해", "종목을 많이 보유하면 부자가 되기 위해"}, 2));


		}
	// 퀴즈들을 불러오기 위한 메서드
	public List<Quiz> getQuizList() {
		return quiz;
		
	}
	
	// 맞춘 개수를 증가 하기 위한 메서드
	public void increaseCorrectCount() {
		correctCount++;
	
	}
	// 맞춘 개수를 가져오기 위한 메서드
	public int getCorrectCount() {
		return correctCount;
	
	}
	// 100점 만점을 기준으로 총 5문제, 한 문제당 20점씩 해서 원점수*20
	// 점수를 가져오기 위한 메서드.
	public int getScore() {
		return correctCount * 20;
	
	}
		
		
	}

