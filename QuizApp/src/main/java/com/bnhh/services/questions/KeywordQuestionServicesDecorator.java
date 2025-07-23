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
public class KeywordQuestionServicesDecorator extends QuestionDecorator {

    private String kw;

    public KeywordQuestionServicesDecorator(BaseQuestionServices decorator, String kw) {
        super(decorator);
        this.kw = kw;
    }

    @Override
    public String getSQL(List<Object> params) {
        String sql = this.decorator.getSQL(params) + " AND content like concat('%', ? ,'%') ";
        params.add(kw);

        return sql;
    }
}
