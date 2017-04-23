/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.com;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Enumeration;
import java.util.Properties;
import java.util.logging.Level;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import org.apache.log4j.Logger;

/**
 *
 * @author chieuvh
 */
public class AppContextListener implements ServletContextListener {

    private static final Logger logger = Logger.getLogger(AppContextListener.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        try {
            int a;
            logger.info("contextInitialized");
            AppConfig.contextPath = sce.getServletContext().getContextPath();
            Properties config = new Properties();
            try {
                String fileName = "WEB-INF/classes/appconfig.properties";
                InputStream input = sce.getServletContext().getResourceAsStream(fileName);
                config.load(input);
            } catch (Exception ex) {
                logger.error(ex.getMessage(), ex);
            }
            Enumeration en = config.keys();
            while (en.hasMoreElements()) {
                String key = (String) en.nextElement();
                String value = (String) config.get(key);
                logger.info(key + " => " + value);

            }
            AppConfig.staticVersion = config.getProperty("staticVersion");
            AppConfig.webTitle = config.getProperty("webTitle");
            AppConfig.databaseUrl = config.getProperty("databaseUrl");
            AppConfig.databaseUser = config.getProperty("databaseUser");
            AppConfig.databasePassword = config.getProperty("databasePassword");
            AppConfig.maxCategoryInTopNav = Integer.parseInt(config.getProperty("maxCategoryInTopNav"));
            AppConfig.dateFormat = config.getProperty("dateFormat");
            AppConfig.OPENSHIFT_DATA_DIR = config.getProperty("OPENSHIFT_DATA_DIR");

            String OPENSHIFT_MYSQL_DB_HOST = System.getenv("OPENSHIFT_MYSQL_DB_HOST");
            if (OPENSHIFT_MYSQL_DB_HOST != null) {
                AppConfig.isLive = true;
                String OPENSHIFT_MYSQL_DB_PORT = System.getenv("OPENSHIFT_MYSQL_DB_PORT");
                String OPENSHIFT_MYSQL_DB_USERNAME = System.getenv("OPENSHIFT_MYSQL_DB_USERNAME");
                String OPENSHIFT_MYSQL_DB_PASSWORD = System.getenv("OPENSHIFT_MYSQL_DB_PASSWORD");
                String databaseUrl = "jdbc:mysql://" + OPENSHIFT_MYSQL_DB_HOST + ":" + OPENSHIFT_MYSQL_DB_PORT
                        + "/game.com?useUnicode=true&characterEncoding=UTF-8&";
                AppConfig.databaseUrl = databaseUrl;
                AppConfig.databaseUser = OPENSHIFT_MYSQL_DB_USERNAME;
                AppConfig.databasePassword = OPENSHIFT_MYSQL_DB_PASSWORD;
                AppConfig.OPENSHIFT_DATA_DIR = System.getenv("OPENSHIFT_DATA_DIR");
                logger.info("OPENSHIFT_DATA_DIR " + AppConfig.OPENSHIFT_DATA_DIR);
            }
            logger.info("databaseUrl " + AppConfig.databaseUrl);
            DataAccess.init(AppConfig.databaseUrl, AppConfig.databaseUser, AppConfig.databasePassword);
            AppConfig.categoryList = DataAccess.getCategoryEntityList();
//        String host = "127.0.0.1";
//        String port = "3306";
//        String dbname = "chuyennganhso4t";
            String JDBC_DRIVER = "com.mysql.jdbc.Driver";
            try {
                Class.forName(JDBC_DRIVER);
                Connection conn = DriverManager.getConnection(AppConfig.databaseUrl, AppConfig.databaseUser, AppConfig.databasePassword);
                conn.createStatement();
                conn.close();
                logger.info("connect to db success");
            } catch (Exception ex) {
                logger.error("connect to db exception " + ex.getMessage(), ex);
                logger.info("databaseUrl=" + AppConfig.databaseUrl);
                logger.info("databaseUser=" + AppConfig.databaseUser);
                logger.info("databasePassword=" + AppConfig.databasePassword);
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
            System.exit(0);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        logger.info("contextDestroyed");
    }

}
