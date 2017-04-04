/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.com.entity;

import game.com.entity.BaseEntity;
import com.google.gson.Gson;

/**
 *
 * @author chieuvh
 */
public class AjaxResponseEntity extends BaseEntity{

    public int returnCode = 0;
    public String returnMessage = "";
    public String data="";

    
}
