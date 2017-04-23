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
import hapax.TemplateException;
import hapax.TemplateLoader;
import hapax.TemplateResourceLoader;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author chieuvh
 */
public abstract class BaseServlet extends HttpServlet {

    private static final Logger logger = Logger.getLogger(BaseServlet.class);

    protected Template getCTemplate(String tpl) throws TemplateException {
        TemplateLoader templateLoader = TemplateResourceLoader.create("game/com/view/");
        Template template = templateLoader.getTemplate(tpl);
        return template;
    }

    protected void outContent(String content, HttpServletResponse response) {

        PrintWriter out = null;
        try {
            response.setContentType("text/html;charset=UTF-8");
            response.setCharacterEncoding("UTF-8");
            out = response.getWriter();
            out.print(content);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        } finally {
            if (out != null) {
                out.close();
            }
        }
    }

    protected String[] parseUriRequest(HttpServletRequest req) {

        String uripath = URLDecoder.decode(req.getRequestURI());
        return uripath.split("/");

    }

    protected void showAdminBaseSection(TemplateDataDictionary dic) {
        dic.showSection("top-head");
//        dic.showSection("best-sell-left");
//        dic.showSection("company");
//        dic.showSection("footer");
        dic.showSection("header");
//        dic.showSection("left-nav");
//        dic.showSection("side-ads");
//        dic.showSection("top-banner");
        dic.showSection("top-nav");
        int countCategoryInTopNav = 0;
        for (CategoryEntity categoryEntity : AppConfig.categoryList) {
            TemplateDataDictionary nav = dic.addSection("TOP_NAV_CATEGORY");
            nav.setVariable("CATEGORY_URL", categoryEntity.url);
            nav.setVariable("CATEGORY_NAME", categoryEntity.name);
            countCategoryInTopNav++;
            if (countCategoryInTopNav == AppConfig.maxCategoryInTopNav) {
                break;
            }
        }
        if (countCategoryInTopNav < AppConfig.categoryList.size()) {
            TemplateDataDictionary topNavOther = dic.addSection("TOP_NAV_CATEGORY_OTHER");
            for (int i = countCategoryInTopNav; i < AppConfig.categoryList.size(); i++) {
                TemplateDataDictionary nav = topNavOther.addSection("TOP_NAV_CATEGORY");
                CategoryEntity categoryEntity = AppConfig.categoryList.get(i);
                nav.setVariable("CATEGORY_URL", categoryEntity.url);
                nav.setVariable("CATEGORY_NAME", categoryEntity.name);
            }
        }
    }

    protected String getThumbUrl(String gameID) {
        return "/resource/thumb/" + gameID + ".png";
    }

    protected String getNesFileUrl(String gameID) {
        return "/resource/nes/" + gameID + ".zip";
    }

    protected List<String> getGalleryImage(String gameID) {
        List<String> gallery = new ArrayList();
        File folder = new File(AppConfig.OPENSHIFT_DATA_DIR + "/gallery/" + gameID);
        if (!folder.exists()) {
            return gallery;
        }
        if (folder.listFiles() == null) {
            return gallery;
        }
        for (File image : folder.listFiles()) {
            if (image.isDirectory()) {
                continue;
            }
            gallery.add(image.getName().substring(0, image.getName().lastIndexOf(".")));
        }
        return gallery;
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    protected void renderCategoryHeader(TemplateDataDictionary dic,List<CategoryEntity> catagoryEntityList) {
        for (CategoryEntity categoryEntity : catagoryEntityList) {
            TemplateDataDictionary category = dic.addSection("CATEGORY");
            category.setVariable("url", categoryEntity.getUrl());
            category.setVariable("name", categoryEntity.name);
        }
    }
    protected void renderGameList(TemplateDataDictionary dic,List<GameEntity> gameEntityList) {
        dic.showSection("game_unit");
        for (GameEntity gameEntity : gameEntityList) {
            TemplateDataDictionary category = dic.addSection("GAME");
            category.setVariable("url", gameEntity.getUrl());
            category.setVariable("name", gameEntity.name);
//            category.setVariable("thumb", "/resource/thumb/1.png");
            category.setVariable("thumb", gameEntity.getThumbUrl());
            category.setVariable("short_desc", gameEntity.short_desc);
        }
    }
    protected void showBaseSection(TemplateDataDictionary dic) {
        dic.showSection("header");
        dic.showSection("footer");
        dic.showSection("scroll_top");
        
    }
}
