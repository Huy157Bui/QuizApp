/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bnhh.services.exam;

import com.bnhh.pojo.Question;
import com.bnhh.services.questions.BaseQuestionServices;
import com.bnhh.services.questions.LimitQuestionServicesDecorator;
import com.bnhh.utils.Configs;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * @author HP
 */
public class SpecificExamStrategy extends ExamStrategy{
    private int num;

    public SpecificExamStrategy(int num) {
        this.num = num;
    }
    
    @Override
    public List<Question> getQuestions() throws SQLException {
        BaseQuestionServices s = new LimitQuestionServicesDecorator(Configs.questionServices, this.num);
        return s.list();
    }
}
