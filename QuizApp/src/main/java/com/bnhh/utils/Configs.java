/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bnhh.utils;

import com.bnhh.services.CategoryServices;
import com.bnhh.services.LevelServices;
import com.bnhh.services.questions.QuestionServices;
import com.bnhh.services.questions.UpdateQuestionServices;
import com.bnhh.services.questions.BaseQuestionServices;

/**
 *
 * @author HP
 */
public class Configs {

    public static final LevelServices levelServices = new LevelServices();
    public static final CategoryServices cateService = new CategoryServices();
    public static BaseQuestionServices questionServices = new QuestionServices();
    public static final UpdateQuestionServices uQServices = new UpdateQuestionServices();

    public static final int NUM_OF_QUES = 10;
    public static final double[] RATES = {0.4, 0.4, 0.2};

}
