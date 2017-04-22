/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.com;

import hapax.Template;
import hapax.TemplateDataDictionary;
import hapax.TemplateDictionary;
import hapax.TemplateException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

/**
 *
 * @author chieuvh
 */
public class LoginServlet extends BaseServlet {
    
    private static final Logger logger = Logger.getLogger(LoginServlet.class);
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            TemplateDataDictionary dic = TemplateDictionary.create();
            Template template = getCTemplate("admin/login");
            String data = template.renderToString(dic);
            outContent(data, response);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if (AppConfig.isLive) {
            if (!request.isSecure()) {
                doGet(request, response);
                logger.info("!request.isSecure()");
                return;
            }
        }
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
            doGet(request, response);
            logger.info("username=" + username + ",password=" + password);
            return;
        }
        HttpSession session = request.getSession();
        session.setAttribute("user", "hihi");
        response.sendRedirect("/");
    }
    
}
