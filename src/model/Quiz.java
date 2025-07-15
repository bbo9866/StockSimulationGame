// logic/QuizManager.java
// ---------------------
package model;

public class Quiz {
	private String question;
    private String[] options;
    private int answer;

    public Quiz(String question, String[] options, int answer) {
        this.question = question;
        this.options = options;
        this.answer = answer;
    }

    public String getQuestion() { return question; }
    public String[] getOptions() { return options; }
    public int getAnswer() { return answer; }
}