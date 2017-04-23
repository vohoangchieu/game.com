/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.com;

import game.com.entity.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author chieuvh
 */
public class DataCache {

    public static List<CategoryEntity> getCategoryEntityList(){
        try {
            return DataAccess.getCategoryEntityList();
        } catch (SQLException ex) {
            return new ArrayList();
        }
    }

    public static List<GameEntity> getActiveGameEntityList(){
        return DataAccess.getActiveGameEntityList();
    }
}
