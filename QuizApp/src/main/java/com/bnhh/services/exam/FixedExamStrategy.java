/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bnhh.services.exam;

import com.bnhh.pojo.Question;
import com.bnhh.services.questions.BaseQuestionServices;
import com.bnhh.services.questions.LevelQuestionServicesDecorator;
import com.bnhh.services.questions.LimitQuestionServicesDecorator;
import com.bnhh.utils.Configs;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author HP
 */
public class FixedExamStrategy extends ExamStrategy {

    @Override
    public List<Question> getQuestions() throws SQLException {
        List<Question> questions = new ArrayList<>();
        for (int i =0; i<Configs.RATES.length; i++){
            BaseQuestionServices s = new LimitQuestionServicesDecorator(new LevelQuestionServicesDecorator(Configs.questionServices, i + 1), (int)(Configs.RATES[i] * Configs.NUM_OF_QUES));
            questions.addAll(s.list());
        }
        return questions;
    }

}
