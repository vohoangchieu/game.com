/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.com;

import game.com.entity.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author chieuvh
 */
public class DataCache {

    public static List<CategoryEntity> getCategoryEntityList() {
        try {
            return DataAccess.getCategoryEntityList();
        } catch (SQLException ex) {
            return new ArrayList();
        }
    }
    public static HashMap<Integer,CategoryEntity> getCategoryEntityMap() {
        List<CategoryEntity> categoryEntityList = getCategoryEntityList();
        HashMap<Integer, CategoryEntity> result = new HashMap();
        for (CategoryEntity categoryEntity : categoryEntityList) {
            result.put(categoryEntity.id, categoryEntity);
        }
        return result;
    }

    public static List<GameEntity> getActiveGameEntityList() {
        return DataAccess.getActiveGameEntityList();
    }

    public static HashMap<Integer, GameEntity> getActiveGameEntityMap() {
        List<GameEntity> gameEntityList = getActiveGameEntityList();
        HashMap<Integer, GameEntity> result = new HashMap();
        for (GameEntity gameEntity : gameEntityList) {
            result.put(gameEntity.id, gameEntity);
        }
        return result;
    }
}
