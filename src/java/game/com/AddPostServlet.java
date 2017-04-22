/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.com;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import game.com.entity.CategoryEntity;
import game.com.entity.GameEntity;
import game.com.entity.PostEntity;
import hapax.Template;
import hapax.TemplateDataDictionary;
import hapax.TemplateDictionary;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author chieuvh
 */
public class AddPostServlet extends BaseServlet {

    private static final Logger logger = Logger.getLogger(AddPostServlet.class);

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
        try {
            GsonBuilder gsonBuilder = new GsonBuilder().setDateFormat(AppConfig.dateFormat);
            int postID = getPostID(request);
            TemplateDataDictionary dic = TemplateDictionary.create();
            PostEntity postEntity = null;
            if (postID > 0) {
                postEntity = DataAccess.getPost(postID);
            }
            if (postEntity == null) {
                dic.setVariable("TITLE", "Add post");
                dic.setVariable("id", "0");
                dic.setVariable("url", "url0");
                dic.setVariable("title", "title0");
                dic.setVariable("keyword", "keyword0");
                dic.setVariable("description", "description0");
                dic.setVariable("content", "content");
                dic.setVariable("order_weight", "0");
                dic.setVariable("is_active", "");
            } else {
                dic.setVariable("TITLE", "Update post");
                dic.showSection("UPDATE");
                dic.setVariable("id", String.valueOf(postEntity.id));
                dic.setVariable("url", postEntity.url);
                dic.setVariable("title", postEntity.title);
                dic.setVariable("keyword", postEntity.keyword);
                dic.setVariable("description", postEntity.description);
                dic.setVariable("content", postEntity.content);
                dic.setVariable("order_weight", String.valueOf(postEntity.order_weight));
                dic.setVariable("is_active_checked", postEntity.is_active ? "checked" : "");
                
            }

            showAdminBaseSection(dic);
            
            Template template = getCTemplate("admin/add-post");
            String data = template.renderToString(dic);
            outContent(data, response);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        } finally {

        }

    }

    private int getPostID(HttpServletRequest request) {
        if (request.getParameter("id") == null) {
            return 0;
        }
        try {
            return Integer.parseInt(request.getParameter("id"));
        } catch (Exception ex) {
            return 0;
        }

    }

}
