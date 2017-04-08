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
public class AddGameServlet extends BaseServlet {

    private static final Logger logger = Logger.getLogger(AddGameServlet.class);

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
            int gameID = getGameID(request);
            TemplateDataDictionary dic = TemplateDictionary.create();
            GameEntity gameEntity = null;
            if (gameID > 0) {
                gameEntity = DataAccess.getGame(gameID);
            }
            if (gameEntity == null) {
                dic.setVariable("TITLE", "Add game");
                dic.setVariable("id", "0");
                dic.setVariable("url", "url0");
                dic.setVariable("name", "name0");
                dic.setVariable("name_vn", "namevn0");
                dic.setVariable("short_desc", "short_dest0");
                dic.setVariable("long_desc", "long_desc0");
                dic.setVariable("order_weight", "0");
                dic.setVariable("is_promote", "");
                dic.setVariable("is_fearture", "");
                dic.setVariable("link_youtube", "link_youtube0");
                dic.setVariable("is_active", "");
            } else {
                dic.setVariable("TITLE", "Update game");
                dic.showSection("UPDATE");
                dic.setVariable("id", String.valueOf(gameEntity.id));
                dic.setVariable("url", gameEntity.url);
                dic.setVariable("name", gameEntity.name);
                dic.setVariable("name_vn", gameEntity.name_vn);
                dic.setVariable("short_desc", gameEntity.short_desc);
                dic.setVariable("long_desc", gameEntity.long_desc);
                dic.setVariable("order_weight", String.valueOf(gameEntity.order_weight));
                dic.setVariable("is_promote_checked", gameEntity.is_promote ? "checked" : "");
                dic.setVariable("is_fearture_checked", gameEntity.is_fearture ? "checked" : "");
                dic.setVariable("link_youtube", gameEntity.link_youtube);
                dic.setVariable("is_active_checked", gameEntity.is_active ? "checked" : "");
                dic.setVariable("thumb", getThumbUrl(String.valueOf(gameID)));
                dic.setVariable("nes", getNesFileUrl(String.valueOf(gameID)));
                List<String> gallery = getGalleryImage(String.valueOf(gameEntity.id));
                for (String image : gallery) {
                    TemplateDataDictionary nav = dic.addSection("GALLERY");
                    String imgageurl = "/resource/gallery/" + gameEntity.id + "/" + image + ".png?" + System.currentTimeMillis();
                    nav.setVariable("imageurl", imgageurl);
                    nav.setVariable("imagename", image);
                }
            }

            showBaseSection(dic);
            for (CategoryEntity categoryEntity : AppConfig.categoryList) {
                TemplateDataDictionary nav = dic.addSection("CATEGORY");
                nav.setVariable("CATEGORY_ID", String.valueOf(categoryEntity.id));
                nav.setVariable("CATEGORY_NAME", categoryEntity.name);
                if (gameEntity != null && gameEntity.category_set.contains(categoryEntity.id)) {
                    nav.setVariable("checked", "checked");
                }
            }
            Template template = getCTemplate("add-game");
            String data = template.renderToString(dic);
            outContent(data, response);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        } finally {

        }

    }

    private int getGameID(HttpServletRequest request) {
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
