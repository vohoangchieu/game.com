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
import game.com.entity.PostEntity;
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
public class AjaxAddPostServlet extends BaseServlet {

    private static final Logger logger = Logger.getLogger(AjaxAddPostServlet.class);

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
        String title = Util.getParameter(request, "title");
        if (StringUtils.isEmpty(title)) {
            responseObject.returnMessage = "input title";
            return;
        }
        String keyword = Util.getParameter(request, "keyword");
        if (StringUtils.isEmpty(keyword)) {
            responseObject.returnMessage = "input keyword";
            return;
        }
        String description = Util.getParameter(request, "description");
        if (StringUtils.isEmpty(description)) {
            responseObject.returnMessage = "input description";
            return;
        }
        String content = Util.getParameter(request, "content");
        if (StringUtils.isEmpty(content)) {
            responseObject.returnMessage = "input content";
            return;
        }
        String order_weight = Util.getParameter(request, "order_weight");
        if (StringUtils.isEmpty(order_weight)) {
            responseObject.returnMessage = "input order_weight";
            return;
        }

        String is_active = Util.getParameter(request, "is_active");
        if (StringUtils.isEmpty(is_active)) {
            responseObject.returnMessage = "input is_promote";
            return;
        }

        PostEntity postEntity = new PostEntity();
        if (StringUtils.isBlank(id)) {
            postEntity.id = 0;
        } else {
            postEntity.id = Integer.parseInt(id);
        }
        postEntity.url = url;
        postEntity.title = title;
        postEntity.keyword = keyword;
        postEntity.description = description;
        postEntity.content = content;
        postEntity.order_weight = Integer.parseInt(order_weight);
        postEntity.is_active = Integer.parseInt(is_active) == 1;

        if (postEntity.id <= 0) {
            int idret = DataAccess.insertPost(postEntity);
            if (idret > 0) {
                postEntity.id = idret;
                responseObject.data = String.valueOf(idret);
                responseObject.returnCode = 1;
                responseObject.returnMessage = "insert success";
            } else {
                responseObject.returnMessage = "system error";
            }
        } else {
            int idret = DataAccess.updatePost(postEntity);
            if (idret > 0) {
                responseObject.data = String.valueOf(idret);
                responseObject.returnCode = 1;
                responseObject.returnMessage = "update success";
            } else {
                responseObject.returnMessage = "system error";
            }
        }
    }

  
}
