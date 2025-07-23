/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bnhh.services.exam;

import com.bnhh.pojo.Exam;
import com.bnhh.pojo.Question;
import com.bnhh.utils.JbdcConnector;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import javax.xml.transform.Result;

/**
 *
 * @author HP
 */
public abstract class ExamStrategy {

    public abstract List<Question> getQuestions() throws SQLException;

    public void saveExam(List<Question> questions) throws SQLException {
        Connection conn = JbdcConnector.getInstance().connect();
        conn.setAutoCommit(false);
        Exam e = new Exam(questions);

        String sql = "INSERT INTO exam(title, created_date) VALUE(?,?)";
        PreparedStatement stm = conn.prepareCall(sql);
        stm.setString(1, e.getTitle());
        stm.setString(2, e.getCreatedDate().toString());

        if (stm.executeUpdate() > 0) {
            int exId = -1;
            ResultSet r = stm.getGeneratedKeys();
            if (r.next()) {
                exId = r.getInt(1);
            }

            sql = "INSERT INTO exam_question(exam_id, question_id) VALUES(?,?)";
            stm = conn.prepareCall(sql);
            for (var q : questions) {
                stm.setInt(1, exId);
                stm.setInt(2, q.getId());
                stm.executeUpdate();
            }
            conn.commit();;
        } else {
            conn.rollback();
        }
    }
}
