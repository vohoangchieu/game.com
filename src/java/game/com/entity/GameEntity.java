/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.com.entity;

import java.util.HashSet;

/**
 *
 * @author chieuvh
 */
public class GameEntity extends BaseEntity{
   public int id;
   public String url;
   public String name;
   public String name_vn;
   public String short_desc;
   public String long_desc;
   public int order_weight;
   public boolean is_promote;
   public boolean is_fearture;
   public String link_youtube;
   public boolean is_active;
   public HashSet<Integer> category_set;
}
