/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.com;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.logging.Level;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;

/**
 *
 * @author chieuvh
 */
public class StaticResourceServlet extends BaseServlet {

    private static final Logger logger = Logger.getLogger(IndexServlet.class);

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) {
        try {
            String[] part = parseUriRequest(request);
            if (part.length < 4) {
                return;
            }
            if (request.getRequestURI().contains("..") || request.getRequestURI().contains("./")) {
                return;
            }
            String OPENSHIFT_DATA_DIR = System.getenv("OPENSHIFT_DATA_DIR");
            if (OPENSHIFT_DATA_DIR == null) {
                OPENSHIFT_DATA_DIR = "/zserver/data/openshift";
            }
            logger.info("OPENSHIFT_DATA_DIR " + OPENSHIFT_DATA_DIR);
            File file = new File(OPENSHIFT_DATA_DIR,  part[2]+ "/" + part[3]);
            if (file.isDirectory()) {
                return;
            }
            if (!file.exists()) {
                return;
            }
            switch (part[2]) {
                case "nes":
                    response.setHeader("Content-disposition", "attachment; filename=\"" + file.getName() + "\"");
                    break;
                case "thumb":
                case "gallery":
                    response.setHeader("Content-Disposition", "inline; filename=\"" + file.getName() + "\"");
                    break;
                default:
                    return;
            }

            response.setHeader("Content-Type", getServletContext().getMimeType(file.getName()));
            response.setHeader("Content-Length", String.valueOf(file.length()));
//            response.setHeader("Content-disposition", "attachment; filename=\"" + file.getName() + "\"");
//            resp.setHeader("Content-Disposition", "inline; filename=\"" + file.getName() + "\"");
            Files.copy(file.toPath(), response.getOutputStream());
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
    }
}
