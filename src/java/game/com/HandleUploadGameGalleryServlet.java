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
public class HandleUploadGameGalleryServlet extends BaseServlet {

    private static final Logger logger = Logger.getLogger(HandleUploadGameGalleryServlet.class);

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
        boolean isMultipart;
        String filePath;
        int maxFileSize = 4 * 1024 * 1024;
        int maxMemSize = 4 * 1024 * 1024;
        File file;

        DiskFileItemFactory factory = new DiskFileItemFactory();
        // maximum size that will be stored in memory
        factory.setSizeThreshold(maxMemSize);
        // Location to save data that is larger than maxMemSize.
        factory.setRepository(new File("/tmp"));

        // Create a new file upload handler
        ServletFileUpload upload = new ServletFileUpload(factory);
        // maximum file size to be uploaded.
        upload.setSizeMax(maxFileSize);
        Map<String, List<FileItem>> postData = upload.parseParameterMap(request);
        String id = postData.get("id").get(0).getString();
        if (StringUtils.isBlank(id)) {
            logger.info("id= " + id);
        }
        File folder = new File(AppConfig.OPENSHIFT_DATA_DIR + "/gallery/" + id);
        if (!folder.exists()) {
            folder.mkdir();
        }
        try {
            // Parse the request to get file items.
            List<FileItem> fileItems = postData.get("imagegallery");
            // Process the uploaded file items
            SimpleDateFormat dateFormat = new SimpleDateFormat("MMddhhmmSSS");
            for (FileItem fi : fileItems) {
                if (!fi.isFormField()) {
                    // Get the uploaded file parameters
//                    String fieldName = fi.getFieldName();
//                    String fileName = fi.getName();
//                    String contentType = fi.getContentType();
//                    boolean isInMemory = fi.isInMemory();
//                    long sizeInBytes = fi.getSize();
                    // Write the file
                    String filename = dateFormat.format(new Date());
                    file = new File(AppConfig.OPENSHIFT_DATA_DIR + "/gallery/" + id + "/" + filename + ".png");
                    fi.write(file);
                } else {
                    logger.info("isFormField " + fi.getFieldName());
                }
            }
            List<String> gallery = getGalleryImage(id);
            
            responseObject.data = new Gson().toJson(gallery);
            responseObject.returnCode = 1;
            responseObject.returnMessage = "success";

        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

}
