/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.com;

import game.com.entity.AjaxResponseEntity;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author chieuvh
 */
public class AjaxDeleteGameServlet extends BaseServlet {

    private static final Logger logger = Logger.getLogger(AjaxDeleteGameServlet.class);

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
        DataAccess dataAccess = null;
        AjaxResponseEntity responseObject = new AjaxResponseEntity();
        try {
            handle(request, responseObject, dataAccess);
            outContent(responseObject.toJsonString(), response);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        } finally {
            if (dataAccess != null) {
                dataAccess.closeConnection();
            }
        }

    }

    private void handle(HttpServletRequest request, AjaxResponseEntity responseObject, DataAccess dataAccess) throws Exception {
        GsonBuilder gsonBuilder = new GsonBuilder().setDateFormat(AppConfig.dateFormat);
        String MaSo = Util.getParameter(request, "MaSo");
        if (StringUtils.isEmpty(MaSo)) {
            responseObject.returnMessage = "Mã số trạm không hợp lệ";
            return;
        }
       
        dataAccess.getConnection();
        
       
        int maso = 0;
        try {
            maso = Integer.parseInt(MaSo);
            if (maso < 1) {
                responseObject.returnMessage = "Mã số trạm không hợp lệ";
                return;
            }
        } catch (Exception ex) {
            responseObject.returnMessage = "Mã số trạm không hợp lệ";
            return;
        }
//      
//        int row = dataAccess.deleteTramBTS(maso);
//        if (row >= 0) {
//            responseObject.returnCode = 1;
//            responseObject.returnMessage = "Xóa trạm thành công";
//        } else {
//            responseObject.returnMessage = "Lỗi hệ thống, vui lòng thử lại sau";
//        }

    }

  

}
