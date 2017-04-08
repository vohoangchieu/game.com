/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.com;

import com.google.gson.Gson;
import game.com.entity.AjaxResponseEntity;
import java.io.File;
//import game.com.entity.TuyenCapEntity;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.filechooser.FileNameExtensionFilter;
import org.apache.log4j.Logger;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author chieuvh
 */
public class AjaxDeleteGameGalleryImageServlet extends BaseServlet {

    private static final Logger logger = Logger.getLogger(AjaxDeleteGameGalleryImageServlet.class);

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

        String id = request.getParameter("id");
        if (StringUtils.isBlank(id)) {
            logger.info("id= " + id);
        }
        String imagename = request.getParameter("imagename");
        if (StringUtils.isBlank(imagename)) {
            logger.info("imagename= " + imagename);
        }
        File file = new File(AppConfig.OPENSHIFT_DATA_DIR + "/gallery/" + id + "/" + imagename + ".png");
        if (file.exists()) {
            file.delete();
            responseObject.returnCode = 1;
            responseObject.returnMessage = "success";
        } else {
            responseObject.returnCode = 0;
            responseObject.returnMessage = "file not exist";
        }

    }

}
