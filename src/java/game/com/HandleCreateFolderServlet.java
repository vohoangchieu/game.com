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

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

import org.apache.commons.io.FileUtils;

/**
 *
 * @author chieuvh
 */
public class HandleCreateFolderServlet extends BaseServlet {

    private static final Logger logger = Logger.getLogger(HandleCreateFolderServlet.class);

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
        String foldername = request.getParameter("foldername");
        if (foldername == null) {
            responseObject.returnCode = 0;
            responseObject.returnMessage = "invalid path";
            return;
        }
        try {

            File file = new File(path + "/" + foldername);
            if (file.exists()) {
                responseObject.returnCode = 0;
                responseObject.returnMessage = file.getAbsolutePath() + " exist";
                return;
            }
            file.mkdir();
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
