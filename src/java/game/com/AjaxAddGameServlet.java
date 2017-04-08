/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.com;

import game.com.entity.AjaxResponseEntity;
//import game.com.entity.TuyenCapEntity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import game.com.entity.GameEntity;
import java.io.IOException;
import java.util.HashSet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author chieuvh
 */
public class AjaxAddGameServlet extends BaseServlet {

    private static final Logger logger = Logger.getLogger(AjaxAddGameServlet.class);

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AjaxResponseEntity responseObject = new AjaxResponseEntity();
        try {
            handle(request, responseObject);
            outContent(responseObject.toJsonString(), response);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        } finally {

        }

    }

    private void handle(HttpServletRequest request, AjaxResponseEntity responseObject) throws Exception {
        GsonBuilder gsonBuilder = new GsonBuilder().setDateFormat(AppConfig.dateFormat);
        Gson gson = gsonBuilder.create();
        String id = Util.getParameter(request, "id");
        if (StringUtils.isEmpty(id)) {
            responseObject.returnMessage = "input id";
            return;
        }
        String url = Util.getParameter(request, "url");
        if (StringUtils.isEmpty(url)) {
            responseObject.returnMessage = "input url";
            return;
        }
        String name = Util.getParameter(request, "name");
        if (StringUtils.isEmpty(name)) {
            responseObject.returnMessage = "input name";
            return;
        }
        String name_vn = Util.getParameter(request, "name_vn");
        if (StringUtils.isEmpty(name_vn)) {
            responseObject.returnMessage = "input name_vn";
            return;
        }
        String short_desc = Util.getParameter(request, "short_desc");
        if (StringUtils.isEmpty(short_desc)) {
            responseObject.returnMessage = "input short_desc";
            return;
        }
        String order_weight = Util.getParameter(request, "order_weight");
        if (StringUtils.isEmpty(order_weight)) {
            responseObject.returnMessage = "input order_weight";
            return;
        }
        String link_youtube = Util.getParameter(request, "link_youtube");
        if (StringUtils.isEmpty(link_youtube)) {
            responseObject.returnMessage = "input link_youtube";
            return;
        }
        String is_promote = Util.getParameter(request, "is_promote");
        if (StringUtils.isEmpty(is_promote)) {
            responseObject.returnMessage = "input is_promote";
            return;
        }
        String is_fearture = Util.getParameter(request, "is_fearture");
        if (StringUtils.isEmpty(is_fearture)) {
            responseObject.returnMessage = "input is_fearture";
            return;
        }
        String is_active = Util.getParameter(request, "is_active");
        if (StringUtils.isEmpty(is_active)) {
            responseObject.returnMessage = "input is_promote";
            return;
        }
        String category = Util.getParameter(request, "category");
        if (StringUtils.isEmpty(category)) {
            responseObject.returnMessage = "input category";
            return;
        }
        String long_desc = Util.getParameter(request, "long_desc");
        if (StringUtils.isEmpty(long_desc)) {
            responseObject.returnMessage = "input long_desc";
            return;
        }

        GameEntity gameEntity = new GameEntity();
        if (StringUtils.isBlank(id)) {
            gameEntity.id = 0;
        } else {
            gameEntity.id = Integer.parseInt(id);
        }
        gameEntity.url = url;
        gameEntity.name = name;
        gameEntity.name_vn = name_vn;
        gameEntity.short_desc = short_desc;
        gameEntity.order_weight = Integer.parseInt(order_weight);
        gameEntity.is_promote = Integer.parseInt(is_promote) == 1;
        gameEntity.link_youtube = link_youtube;
        gameEntity.is_fearture = Integer.parseInt(is_fearture) == 1;
        gameEntity.is_active = Integer.parseInt(is_active) == 1;
        gameEntity.long_desc = long_desc;
        gameEntity.category_set = getCategorySet(category);
        if (gameEntity.id <= 0) {
            int idret = DataAccess.insertGame(gameEntity);
            if (idret > 0) {
                gameEntity.id = idret;
                responseObject.data = String.valueOf(id);
                responseObject.returnCode = 1;
                responseObject.returnMessage = "success";
            } else {
                responseObject.returnMessage = "system error";
            }
        } else {
            int idret = DataAccess.updateGame(gameEntity);
            if (idret > 0) {
                responseObject.data = String.valueOf(idret);
                responseObject.returnCode = 1;
                responseObject.returnMessage = "success";
            } else {
                responseObject.returnMessage = "system error";
            }
        }
    }

    private HashSet<Integer> getCategorySet(String category) {
        HashSet<Integer> result = new HashSet();
        for (String id : category.split(",")) {
            result.add(Integer.parseInt(id));
        }
        return result;
    }
}
