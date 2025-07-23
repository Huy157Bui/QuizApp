/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.bnhh.services;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author HP
 */

// không cần lặp lại bước lấy dữ liệu

public class FlyweightFactory {
    private static Map<String, List> cacheData = new HashMap<>();
    public static <E> List<E> getData(BaseServices s,  String key) throws SQLException{
        if(cacheData.containsKey(key) == true)
            return cacheData.get(key);
        else{
            List results = s.list();
            cacheData.put(key, results);
            return results;
        }
    }
}
