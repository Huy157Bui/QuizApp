/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bnhh.utils.theme;

import com.bnhh.quizapp.App;
import javafx.scene.Scene;

/**
 *
 * @author admin
 */
public class ThemeManager {
    private static ThemeFactory themeFactory = new DefaultFactory());
    public static void setThemeFactory(ThemeFactory aThemeFactory){
        ThemeFactory ThemeFactory = aThemeFactory;
    }
    public static void applyTheme(Scene scene){
        scene.getRoot().getStylesheets().clear();
        scene.getRoot().getStylesheets().add(themeFactory.getStyleSheet());
                
    }
}
