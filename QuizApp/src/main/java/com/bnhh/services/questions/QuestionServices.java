/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bnhh.services.questions;


import java.util.List;

/**
 *
 * @author HP
 */
public class QuestionServices extends BaseQuestionServices{
    @Override
    public String getSQL(List<Object> params) {
        return "SELECT * FROM question WHERE 1=1";
    }

//    public List<Question> getQuestion() throws SQLException {
//        Connection conn = JbdcConnector.getInstance().connect();
//
//        Statement stm = conn.createStatement();
//        ResultSet rs = stm.executeQuery("SELECT * FROM question");
//
//        List<Question> questions = new ArrayList<>();
//        while (rs.next()) {
//            int id = rs.getInt("id");
//            String content = rs.getString("content");
//            Question q = new Question.Builder(id, content).build();
//
//            questions.add(q);
//        }
//        return questions;
//
//    }
//    public List<Question> getQuestions(String kw) throws SQLException {
//        Connection conn = JbdcConnector.getInstance().connect();
//
//        PreparedStatement stm = conn.prepareCall("SELECT * FROM question WHERE content like concat('%', ? ,'%') ");
//        stm.setString(1, kw);
//        ResultSet rs = stm.executeQuery();
//
//        List<Question> questions = new ArrayList<>();
//        while (rs.next()) {
//            int id = rs.getInt("id");
//            String content = rs.getString("content");
//            Question q = new Question.Builder(id, content).build();
//
//            questions.add(q);
//        }
//        return questions;
//    }
//    public List<Question> getQuestions(int num) throws SQLException {
//        Connection conn = JbdcConnector.getInstance().connect();
//
//        PreparedStatement stm = conn.prepareCall("SELECT * FROM question ORDER BY rand() LIMIT ? ");
//        stm.setInt(1, num);
//        ResultSet rs = stm.executeQuery();
//
//        List<Question> questions = new ArrayList<>();
//        while (rs.next()) {
//            int id = rs.getInt("id");
//            String content = rs.getString("content");
//            Question q = new Question.Builder(id, content)
//                    .addAllChoice(this.getChoiceByQuestionId(id)).build();
//            
//            
//            questions.add(q);
//        }
//        return questions;
//    }
}
