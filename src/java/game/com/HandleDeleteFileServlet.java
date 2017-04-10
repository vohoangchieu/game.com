/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.com;

import game.com.entity.AjaxResponseEntity;
import java.io.File;
//import game.com.entity.TuyenCapEntity;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.io.FileUtils;

/**
 *
 * @author chieuvh
 */
public class HandleDeleteFileServlet extends BaseServlet {

    private static final Logger logger = Logger.getLogger(HandleDeleteFileServlet.class);

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

        String path = getPath(request);
        if (path == null) {
            responseObject.returnCode = 0;
            responseObject.returnMessage = "invalid request";
            return;
        }
        File folder = new File(path);
        if (!folder.exists()) {
            responseObject.returnCode = 0;
            responseObject.returnMessage = "path not exist";
            return;
        }
        if (folder.getAbsolutePath().startsWith(AppConfig.OPENSHIFT_DATA_DIR) == false) {
            responseObject.returnCode = 0;
            responseObject.returnMessage = "invalid path";
            return;
        }
        try {

            File file = new File(path);
            if (file.isDirectory()) {
                if (file.listFiles() != null && file.listFiles().length > 0) {
                    responseObject.returnCode = 0;
                    responseObject.returnMessage = "folder is not empty";
                    return;
                } else {
                    FileUtils.deleteDirectory(file);
                    logger.info("delete folder " + file.getAbsolutePath());
                }
            } else {
                file.delete();
                logger.info("delete file " + file.getAbsolutePath());
            }

            responseObject.returnCode = 1;
            responseObject.returnMessage = "success";

        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

    private String getPath(HttpServletRequest request) {
        if (request.getParameter("path") == null) {
            return null;
        }
        return request.getParameter("path").trim();

    }
}
