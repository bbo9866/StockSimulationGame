package model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Quiz {

	private String question; // 문제 내용
	private String[] options; // 보기 4개
	private int answer; // 정답 번호 (1번 ~ 4번)
	
	
	public Quiz(String question, String[] options, int answer) {
		this.question=question;
		this.options=options;
		this.answer = answer;
	}
	
	public String getQuestion() {
		return question;
	}
	
	public String[] getOptions() {
		return options;
	}
	
	public int getAnswer() {
		return answer;
	}
	
	
}
