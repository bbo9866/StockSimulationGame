package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import util.DBUtil;

public class QuizDAO {

    public static List<Quiz> getRandomQuizzes(int count) throws Exception {
        List<Quiz> quizList = null;

        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement("SELECT * FROM (SELECT * FROM quiz ORDER BY DBMS_RANDOM.VALUE) WHERE ROWNUM <= ?");
            pstmt.setInt(1, count);
            rs = pstmt.executeQuery();
            
            quizList = new ArrayList<>();
            
            while (rs.next()) {
                String question = rs.getString("question");
                String[] options = new String[4];
                options[0] = rs.getString("option1");
                options[1] = rs.getString("option2");
                options[2] = rs.getString("option3");
                options[3] = rs.getString("option4");
                int answer = rs.getInt("correct_option");

                quizList.add(new Quiz(question, options, answer));
            }
        } finally {
            DBUtil.close(conn, pstmt, rs);
        }

        return quizList;
    }
}