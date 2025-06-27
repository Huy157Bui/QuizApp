/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bnhh.utils.theme;

import com.bnhh.quizapp.App;

/**
 *
 * @author admin
 */
public class DarkFactory implements ThemeFactory{

    @Override
    public String getStyleSheet() {
        return App.class.getResource("Dark.css").toExternalForm();
    }

}
