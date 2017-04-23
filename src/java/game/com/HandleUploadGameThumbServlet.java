/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.com;

import game.com.entity.AjaxResponseEntity;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
//import game.com.entity.TuyenCapEntity;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.imageio.ImageIO;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang.StringUtils;

/**
 *
 * @author chieuvh
 */
public class HandleUploadGameThumbServlet extends BaseServlet {

    private static final Logger logger = Logger.getLogger(HandleUploadGameThumbServlet.class);

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

        try {
            // Parse the request to get file items.
            List<FileItem> fileItems = postData.get("image");
            // Process the uploaded file items

            for (FileItem fi : fileItems) {
                if (!fi.isFormField()) {
                    // Get the uploaded file parameters
                    String fieldName = fi.getFieldName();
                    String fileName = fi.getName();
                    String contentType = fi.getContentType();
                    boolean isInMemory = fi.isInMemory();
                    long sizeInBytes = fi.getSize();
                    // Write the file
                    file = new File(AppConfig.OPENSHIFT_DATA_DIR + "/thumb/" + id + ".png");
//                    Image img = ImageIO.read(fi.getInputStream());
//                    BufferedImage tempPNG = resizeImage(img, 256, 240);
//                    ImageIO.write(tempPNG, "png", file);
                    fi.write(file);
                    responseObject.data = getThumbUrl(id);
                    responseObject.returnCode = 1;
                    responseObject.returnMessage = "success";
                    break;
                } else {
                    logger.info("isFormField " + fi.getFieldName());
                }
            }

        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

    public static BufferedImage resizeImage(final Image image, int width, int height) {
        final BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        final Graphics2D graphics2D = bufferedImage.createGraphics();
        graphics2D.setComposite(AlphaComposite.Src);
        //below three lines are for RenderingHints for better image quality at cost of higher processing time
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        graphics2D.drawImage(image, 0, 0, width, height, null);
        graphics2D.dispose();
        return bufferedImage;
    }
}
