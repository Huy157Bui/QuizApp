/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bnhh.services;

import com.bnhh.pojo.Category;
import com.bnhh.pojo.Question;
import java.sql.Connection;
import com.bnhh.utils.JbdcConnector;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class QuestionServices {

    public void addQuestion(Question q) throws SQLException {
        Connection conn = JbdcConnector.getInstance().connect();

        String sql = "INSERT INTO question(content, hint, image, category_id, level_id) VALUE(?, ?, ?, ?, ?)";
        PreparedStatement stm = conn.prepareCall(sql);
        stm.setString(1, q.getContent());
        stm.setString(2, q.getHint());
        stm.setString(3, q.getImage());
        stm.setInt(4, q.getCategory().getId());
        stm.setInt(5, q.getLevel().getId());

        if (stm.executeUpdate() > 0) {
            int questionId = -1;
            ResultSet r = stm.getGeneratedKeys();
            if (r.next()) {
                questionId = r.getInt(1);
            }
            sql = "INSERT INTO choice (content, question_id) VALUES(? ?)";

            for (var c : q.getChoices()) {
                stm = conn.prepareCall(sql);
                stm.setString(1, c.getContent());
                stm.setBoolean(2, c.isCorrect());
                stm.setInt(3, questionId);

                stm.executeUpdate();
            }

            conn.commit();
        } else {
            conn.rollback();
        }

    }

    public List<Question> getQuestion() throws SQLException {
        Connection conn = JbdcConnector.getInstance().connect();

        Statement stm = conn.createStatement();
        ResultSet rs = stm.executeQuery("SELECT * FROM question");

        List<Question> questions = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String content = rs.getString("content");
            Question q = new Question.Builder(id, content).build();

            questions.add(q);
        }
        return questions;

    }
    public List<Question> getQuestions(String kw) throws SQLException {
        Connection conn = JbdcConnector.getInstance().connect();

        PreparedStatement stm = conn.prepareCall("SELECT * FROM question WHERE content like concat('%', ? ,'%') ");
        stm.setString(1, kw);
        ResultSet rs = stm.executeQuery();

        List<Question> questions = new ArrayList<>();
        while (rs.next()) {
            int id = rs.getInt("id");
            String content = rs.getString("content");
            Question q = new Question.Builder(id, content).build();

            questions.add(q);
        }
        return questions;

    }
}
