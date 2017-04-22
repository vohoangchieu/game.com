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
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author chieuvh
 */
public class ManageFileServlet extends BaseServlet {

    private static final Logger logger = Logger.getLogger(ManageFileServlet.class);

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
            TemplateDataDictionary dic = TemplateDictionary.create();
            String path = getPath(request);
            dic.setVariable("path", path);
            List<String> folderList = getAllFolder(path);
            for (String string : folderList) {
                TemplateDataDictionary folderSection = dic.addSection("FOLDER");
//                folderSection.setVariable("path", string.substring(AppConfig.OPENSHIFT_DATA_DIR.length()));
                folderSection.setVariable("path", string);
                folderSection.setVariable("fullpath", string);
            }
            List<String> fileList = getAllFile(path);
            String imagePrefix = AppConfig.OPENSHIFT_DATA_DIR + "/image";
            for (String string : fileList) {
                TemplateDataDictionary folderSection = dic.addSection("FILE");
                folderSection.setVariable("path", string);
                if (string.startsWith(imagePrefix)) {
                    folderSection.showSection("img");
                    folderSection.setVariable("imgurl", string.replace(AppConfig.OPENSHIFT_DATA_DIR, "/resource"));
                }
            }
            showAdminBaseSection(dic);

            Template template = getCTemplate("admin/manage-file");
            String data = template.renderToString(dic);
            outContent(data, response);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        } finally {

        }

    }

    private String getPath(HttpServletRequest request) {
        if (request.getParameter("path") == null) {
            return AppConfig.OPENSHIFT_DATA_DIR;
        }
        return request.getParameter("path").trim();

    }

    private List<String> getAllFolder(String path) {
        List<String> gallery = new ArrayList();
        File folder = new File(path);
        if (StringUtils.isNotBlank(path) && folder.getParent() != null && folder.getParent().contains(AppConfig.OPENSHIFT_DATA_DIR)) {
            gallery.add(folder.getParentFile().getAbsolutePath());
        }
        if (!folder.exists()) {
            return gallery;
        } else {
            gallery.add(folder.getAbsolutePath());
        }

        if (folder.listFiles() == null) {
            return gallery;
        }
        for (File file : folder.listFiles()) {
            if (file.isDirectory()) {
                gallery.add(file.getAbsolutePath());
            }
        }
        return gallery;
    }

    private List<String> getAllFile(String path) {
        List<String> gallery = new ArrayList();
        File folder = new File(path);
        if (!folder.exists()) {
            return gallery;
        }
//        if (folder.getParent() != null) {
//            gallery.add(folder.getParent());
//        }
        if (folder.listFiles() == null) {
            return gallery;
        }
        for (File file : folder.listFiles()) {
            if (file.isDirectory()) {
                continue;
            }
            gallery.add(file.getAbsolutePath());
        }
        return gallery;
    }

}
