/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.com;

import game.com.entity.CategoryEntity;
import game.com.entity.GameEntity;
import hapax.Template;
import hapax.TemplateDataDictionary;
import hapax.TemplateDictionary;
import java.io.IOException;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author chieuvh
 */
public class GameServlet extends BaseServlet {

    private static final Logger logger = Logger.getLogger(GameServlet.class);

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
            String[] part = parseUriRequest(request);
            Integer id = Integer.parseInt(part[2]);
            GameEntity gameEntity = DataCache.getActiveGameEntityMap().get(id);
            TemplateDataDictionary dic = TemplateDictionary.create();
            dic.setVariable("name", gameEntity.name);
            dic.setVariable("short_desc", gameEntity.short_desc);
            dic.setVariable("long_desc", gameEntity.long_desc);
            dic.setVariable("thumb", gameEntity.getThumbUrl());
            dic.setVariable("url", gameEntity.getUrl());
            dic.setVariable("nesurl", gameEntity.getNesUrl());
            dic.setVariable("link_youtube", gameEntity.link_youtube);
            HashMap<Integer, CategoryEntity> categoryEntityMap = DataCache.getCategoryEntityMap();
            for (Integer categoryId : gameEntity.category_set) {
                CategoryEntity categoryEntity = categoryEntityMap.get(categoryId);
                TemplateDataDictionary categoryDic = dic.addSection("GAME_CATEGORY");
                categoryDic.setVariable("url", categoryEntity.getUrl());
                categoryDic.setVariable("name", categoryEntity.name);
                for (GameEntity gameEntity1 : DataCache.getActiveGameEntityList()) {
                    if (!gameEntity1.category_set.contains(categoryId)) {
                        continue;
                    }
                    if (gameEntity.id == gameEntity1.id) {
                        continue;
                    }
                    TemplateDataDictionary relativeGameDic = dic.addSection("RELATIVE_GAME");
                    relativeGameDic.setVariable("url", gameEntity1.getUrl());
                    relativeGameDic.setVariable("name", gameEntity1.name);
                    relativeGameDic.setVariable("thumb", gameEntity1.getThumbUrl());
                }
            }

            Template template = getCTemplate("game");
            showBaseSection(dic);
            renderCategoryHeader(dic, DataCache.getCategoryEntityList());
//            renderGameList(dic, DataCache.getActiveGameEntityList());
            String data = template.renderToString(dic);
            outContent(data, response);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        } finally {

        }

    }

}
