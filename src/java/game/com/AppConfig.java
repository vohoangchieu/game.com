/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.com;

import game.com.entity.CategoryEntity;
import java.util.List;

/**
 *
 * @author chieuvh
 */
public class AppConfig {

    public static String staticVersion = "";
    public static String webTitle = "";
    public static String contextPath = "";
    public static String databaseUrl = "";
    public static String databaseUser = "";
    public static String databasePassword = "";
    public static String dateFormat = "";
    public static int maxCategoryInTopNav = 5;
    
    public static List<CategoryEntity> categoryList;
}
