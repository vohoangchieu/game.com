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

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author chieuvh
 */
public class HandleDownloadFolderServlet extends BaseServlet {

    private static final Logger logger = Logger.getLogger(HandleDownloadFolderServlet.class);
    private static int DEFAULT_BUFFER_SIZE = 2048;

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
            handle(request, response, responseObject);
            outContent(responseObject.toJsonString(), response);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        } finally {

        }

    }

    private void handle(HttpServletRequest request, HttpServletResponse response, AjaxResponseEntity responseObject) throws Exception {

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

            if (!file.isDirectory()) {
                response.setHeader("Content-Type", getServletContext().getMimeType(file.getName()));
                response.setHeader("Content-Length", String.valueOf(file.length()));
                response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\"");
                Files.copy(file.toPath(), response.getOutputStream());
            } else {
                response.setContentType("application/zip");
                response.setHeader("Content-Disposition", "attachment; filename=\"allfiles.zip\"");

                try (ZipOutputStream output = new ZipOutputStream(new BufferedOutputStream(response.getOutputStream(), DEFAULT_BUFFER_SIZE))) {
//                    for (File fileId : file.listFiles()) {
//
//                        InputStream input = null;
//                        byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
//                        try {
//                            input = new BufferedInputStream(new FileInputStream(fileId), DEFAULT_BUFFER_SIZE);
//                            output.putNextEntry(new ZipEntry(fileId.getName()));
//                            for (int length = 0; (length = input.read(buffer)) > 0;) {
//                                output.write(buffer, 0, length);
//                            }
//                            output.closeEntry();
//                        } finally {
//                            if (input != null) {
//                                try {
//                                    input.close();
//                                } catch (Exception logOrIgnore) {
//                                    logger.error(logOrIgnore.getMessage(), logOrIgnore);
//                                }
//                            }
//                        }
//                    }
//                    outputZipStream(output, file);
                    addDirToZipArchive(output, file, null);
                }
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

    private void outputZipStream(ZipOutputStream output, File file) throws FileNotFoundException, IOException {
        for (File currentFile : file.listFiles()) {

            InputStream input = null;
            byte[] buffer = new byte[DEFAULT_BUFFER_SIZE];
            try {
                if (currentFile.isDirectory()) {
                    outputZipStream(output, currentFile);
                } else {
                    input = new BufferedInputStream(new FileInputStream(currentFile), DEFAULT_BUFFER_SIZE);
                    output.putNextEntry(new ZipEntry(currentFile.getName()));
                    for (int length = 0; (length = input.read(buffer)) > 0;) {
                        output.write(buffer, 0, length);
                    }
                    output.closeEntry();
                }
            } finally {
                if (input != null) {
                    try {
                        input.close();
                    } catch (Exception logOrIgnore) {
                        logger.error(logOrIgnore.getMessage(), logOrIgnore);
                    }
                }
            }
        }
    }

    public static void addDirToZipArchive(ZipOutputStream zos, File fileToZip, String parrentDirectoryName) throws Exception {
        if (fileToZip == null || !fileToZip.exists()) {
            return;
        }

        String zipEntryName = fileToZip.getName();
        if (parrentDirectoryName != null && !parrentDirectoryName.isEmpty()) {
            zipEntryName = parrentDirectoryName + "/" + fileToZip.getName();
        }

        if (fileToZip.isDirectory()) {
            System.out.println("+" + zipEntryName);
            for (File file : fileToZip.listFiles()) {
                addDirToZipArchive(zos, file, zipEntryName);
            }
        } else {
            System.out.println("   " + zipEntryName);
            byte[] buffer = new byte[1024];
            FileInputStream fis = new FileInputStream(fileToZip);
            zos.putNextEntry(new ZipEntry(zipEntryName));
            int length;
            while ((length = fis.read(buffer)) > 0) {
                zos.write(buffer, 0, length);
            }
            zos.closeEntry();
            fis.close();
        }
    }
}
