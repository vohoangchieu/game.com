/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package game.com;

import game.com.entity.CategoryEntity;
import game.com.entity.GameEntity;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import org.apache.log4j.Logger;

/**
 *
 * @author chieuvh
 */
public class DataAccess {

    private static final Logger logger = Logger.getLogger(DataAccess.class);
    private static Connection conn = null;
    private static String user = null;
    private static String pass = null;
    private static String url = null;

    public static void init(String url, String user, String pass) {
        DataAccess.user = user;
        DataAccess.pass = pass;
        DataAccess.url = url;

    }

    public static void getConnection() throws ClassNotFoundException, SQLException {
        String JDBC_DRIVER = "com.mysql.jdbc.Driver";

        Class.forName(JDBC_DRIVER);
//        String url = "jdbc:mysql://" + host + ":" + port + "/" + dbname + "?useUnicode=true&characterEncoding=UTF-8&";
        conn = DriverManager.getConnection(url, user, pass);
    }

    public static void closeConnection() {
        try {
            if (conn != null && conn.isClosed() == false) {
                conn.close();
                conn = null;
            }
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        }
    }

    public static int insertGame(GameEntity gameEntity) throws SQLException {
        logger.info("insert " + gameEntity.toJsonString());
        String sql = "INSERT INTO `game`("
                + " `url`, `name`, `name_vn`, `short_desc`, "
                + "`long_desc`, `order_weight`, `is_promote`, "
                + "`is_fearture`, "
                + " `link_youtube`, `is_active`) values ("
                + " :url, :name, :name_vn, :short_desc, "
                + ":long_desc, :order_weight, :is_promote, "
                + ":is_fearture, "
                + " :link_youtube, :is_active)";
        int row = 0;
        try {
            getConnection();
            NamedParameterStatement statement = new NamedParameterStatement(conn, sql, Statement.RETURN_GENERATED_KEYS);
//            statement.setInt("id", gameEntity.id);
            statement.setString("url", gameEntity.url);
            statement.setString("name", gameEntity.name);
            statement.setString("name_vn", gameEntity.name_vn);
            statement.setString("short_desc", gameEntity.short_desc);
            statement.setString("long_desc", gameEntity.long_desc);
            statement.setInt("order_weight", gameEntity.order_weight);
            statement.setInt("is_promote", gameEntity.is_promote ? 1 : 0);
            statement.setInt("is_fearture", gameEntity.is_fearture ? 1 : 0);
            statement.setString("link_youtube", gameEntity.link_youtube);
            statement.setInt("is_active", gameEntity.is_active ? 1 : 0);

            logger.info(statement.toString());
            row = statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                row = rs.getInt(1);
            }
            gameEntity.id = row;
            setGameCategory(gameEntity.id, gameEntity.category_set);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        } finally {
            closeConnection();
        }
        return row;
    }

    public static int updateGame(GameEntity gameEntity) throws SQLException {
        logger.info("update " + gameEntity.toJsonString());
        String sql = "update `game` set "
                + "`url`= :url, `name`=:name, `name_vn`=:name_vn,"
                + "`short_desc`= :short_desc, "
                + "`long_desc`=:long_desc, `order_weight`=:order_weight,"
                + " `is_promote`=:is_promote, "
                + " `is_fearture`=:is_fearture, "
                + " `link_youtube`=:link_youtube, `is_active`=:is_active "
                + " where `id`=:id";
        int row = 0;
        try {
            getConnection();
            NamedParameterStatement statement = new NamedParameterStatement(conn, sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt("id", gameEntity.id);
            statement.setString("url", gameEntity.url);
            statement.setString("name", gameEntity.name);
            statement.setString("name_vn", gameEntity.name_vn);
            statement.setString("short_desc", gameEntity.short_desc);
            statement.setString("long_desc", gameEntity.long_desc);
            statement.setInt("order_weight", gameEntity.order_weight);
            statement.setInt("is_promote", gameEntity.is_promote ? 1 : 0);
            statement.setInt("is_fearture", gameEntity.is_fearture ? 1 : 0);
            statement.setString("link_youtube", gameEntity.link_youtube);
            statement.setInt("is_active", gameEntity.is_active ? 1 : 0);

            logger.info(statement.toString());
            row = statement.executeUpdate();
            setGameCategory(gameEntity.id, gameEntity.category_set);
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        } finally {
            closeConnection();
        }
        return row;
    }

    public static GameEntity getGame(int id) throws SQLException {
        String sql = "select * from `game` where id=:id";
        GameEntity result = null;
        try {
            getConnection();
            NamedParameterStatement statement = new NamedParameterStatement(conn, sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt("id", id);
            logger.info(statement.toString());
            ResultSet rs = statement.executeQuery();
            if (!rs.next()) {
                return result;
            }
            result = new GameEntity();
            result.id = id;
            result.url = rs.getString("url");
            result.name = rs.getString("name");
            result.name_vn = rs.getString("name_vn");
            result.short_desc = rs.getString("short_desc");
            result.long_desc = rs.getString("long_desc");
            result.order_weight = rs.getInt("order_weight");
            result.is_promote = rs.getInt("is_promote") == 1;
            result.is_fearture = rs.getInt("is_fearture") == 1;
            result.link_youtube = rs.getString("link_youtube");
            result.is_active = rs.getInt("is_active") == 1;
            result.category_set = getGameCategory(id);
            return result;
        } catch (Exception ex) {
            logger.error(ex.getMessage(), ex);
        } finally {
            closeConnection();
        }
        return result;
    }
//
//    public List<TramBTSEntity> getAllTramBTS() throws SQLException {
//        List<TramBTSEntity> result = new ArrayList();
//        String sql = "select  "
//                + "`MaSo`, `TenTram`, `NgayLapDat`, `DiaChiLapDat`, `TinhThanhLD`, "
//                + "`QuanHuyenLD`, `PhuongXaLD`, `TrangThai`, `DonViThue`,"
//                + " `ToaDoKD`, `ToaDoVD`, `LoaiTram`, `LoaiCotAT`, `ChieuCao`,"
//                + " `DTSanLap`, `DienThoaiDVQL`, "
//                + " `ThueDDTuNgay`, `ThueDDDenNgay`, "
//                + "`NgayDuaVaoSuDung`, "
//                + " `DaKiemTra`,`GhiChu`,`TenDVQL` "
//                + " from `thongtin_trambts` where IsActive=1";
//
//        try {
//            getConnection();
//            NamedParameterStatement statement = new NamedParameterStatement(conn, sql);
//            logger.info(statement.toString());
//            ResultSet resultSet = statement.executeQuery();
//            while (resultSet.next()) {
//                TramBTSEntity entity = new TramBTSEntity();
//                entity.MaSo = resultSet.getInt("MaSo");
//                entity.TenTram = resultSet.getString("TenTram");
//                entity.NgayLapDat = resultSet.getTimestamp("NgayLapDat");
//                entity.DiaChiLapDat = resultSet.getString("DiaChiLapDat");
//                entity.TinhThanhLD = resultSet.getInt("TinhThanhLD");
//                entity.QuanHuyenLD = resultSet.getInt("QuanHuyenLD");
//                entity.PhuongXaLD = resultSet.getInt("PhuongXaLD");
//                entity.TrangThai = resultSet.getInt("TrangThai");
//                entity.DonViThue = resultSet.getString("DonViThue");
//                entity.ToaDoKD = resultSet.getFloat("ToaDoKD");
//                entity.ToaDoVD = resultSet.getFloat("ToaDoVD");
//                entity.LoaiTram = resultSet.getInt("LoaiTram");
//                entity.LoaiCotAT = resultSet.getString("LoaiCotAT");
//                entity.ChieuCao = resultSet.getFloat("ChieuCao");
//                entity.DTSanLap = resultSet.getFloat("DTSanLap");
//                entity.ThueDDTuNgay = resultSet.getTimestamp("ThueDDTuNgay");
//                entity.ThueDDDenNgay = resultSet.getTimestamp("ThueDDDenNgay");
//                entity.NgayDuaVaoSuDung = resultSet.getTimestamp("NgayDuaVaoSuDung");
//                entity.DaKiemTra = resultSet.getInt("DaKiemTra") == 1;
//                entity.GhiChu = resultSet.getString("GhiChu");
//                entity.TenDVQL = resultSet.getString("TenDVQL");
//                result.add(entity);
//            }
//
//        } catch (Exception ex) {
//            logger.error(ex.getMessage(), ex);
//        } finally {
////            closeConnection();
//        }
//        return result;
//    }
//
//    public List<TuyenCapEntity> getAllTuyenCap() throws SQLException {
//        List<TuyenCapEntity> result = new ArrayList();
//        String sql = "select  "
//                + " * "
//                + " from `tuyencap`";
//
//        try {
//            getConnection();
//            NamedParameterStatement statement = new NamedParameterStatement(conn, sql);
//            logger.info(statement.toString());
//            ResultSet resultSet = statement.executeQuery();
//            while (resultSet.next()) {
//                TuyenCapEntity entity = new TuyenCapEntity();
//                entity.Id = resultSet.getInt("Id");
//                entity.Ten = resultSet.getString("Ten");
//                entity.X1 = resultSet.getFloat("X1");
//                entity.Y1 = resultSet.getFloat("Y1");
//                entity.X2 = resultSet.getFloat("X2");
//                entity.Y2 = resultSet.getFloat("Y2");
//                result.add(entity);
//            }
//
//        } catch (Exception ex) {
//            logger.error(ex.getMessage(), ex);
//        } finally {
////            closeConnection();
//        }
//        return result;
//    }
//
//    public int updateTramBTS(TramBTSEntity tramBTSEntity) throws SQLException {
//        int row = -1;
//        try {
//            getConnection();
//            String sql = "update thongtin_trambts "
//                    + "set TenTram=:TenTram,"
//                    + "NgayLapDat=:NgayLapDat,DiaChiLapDat=:DiaChiLapDat, "
//                    + "TinhThanhLD=:TinhThanhLD,QuanHuyenLD=:QuanHuyenLD, "
//                    + "PhuongXaLD=:PhuongXaLD,TrangThai=:TrangThai, "
//                    + "DonViThue=:DonViThue,TenDVQL=:TenDVQL, "
//                    + "ToaDoKD=:ToaDoKD,ToaDoVD=:ToaDoVD,ChieuCao=:ChieuCao "
//                    + "where MaSo=:MaSo";
//            NamedParameterStatement statement = new NamedParameterStatement(conn, sql);
//            statement.setInt("MaSo", tramBTSEntity.MaSo);
//            statement.setString("TenTram", tramBTSEntity.TenTram);
//            statement.setTimestamp("NgayLapDat", new Timestamp(tramBTSEntity.NgayLapDat.getTime()));
//            statement.setString("DiaChiLapDat", tramBTSEntity.DiaChiLapDat);
//            statement.setInt("TinhThanhLD", tramBTSEntity.TinhThanhLD);
//            statement.setInt("QuanHuyenLD", tramBTSEntity.QuanHuyenLD);
//            statement.setInt("PhuongXaLD", tramBTSEntity.PhuongXaLD);
//            statement.setInt("TrangThai", tramBTSEntity.TrangThai);
//            statement.setString("DonViThue", tramBTSEntity.DonViThue);
//            statement.setString("TenDVQL", tramBTSEntity.TenDVQL);
//            statement.setFloat("ToaDoVD", tramBTSEntity.ToaDoVD);
//            statement.setFloat("ToaDoKD", tramBTSEntity.ToaDoKD);
//            statement.setFloat("ChieuCao", tramBTSEntity.ChieuCao);
//            logger.info(statement.toString());
//            row = statement.executeUpdate();
//        } catch (ClassNotFoundException cnfex) {
//            logger.error(cnfex.getMessage(), cnfex);
//        } finally {
////            closeConnection();
//        }
//        return row;
//    }
//
//    public int deleteTramBTS(int MaSo) throws SQLException {
//        int row = -1;
//        try {
//            getConnection();
//            String sql = "delete from thongtin_trambts "
//                    + "where MaSo=:MaSo";
//            NamedParameterStatement statement = new NamedParameterStatement(conn, sql);
//            statement.setInt("MaSo", MaSo);
//            logger.info(statement.toString());
//            row = statement.executeUpdate();
//        } catch (ClassNotFoundException cnfex) {
//            logger.error(cnfex.getMessage(), cnfex);
//        } finally {
////            closeConnection();
//        }
//        return row;
//    }

    public static List<CategoryEntity> getCategoryList() throws SQLException {
        List<CategoryEntity> result = new ArrayList();
        try {
            getConnection();
            String sql = "select * from category order by order_weight desc";
            NamedParameterStatement statement = new NamedParameterStatement(conn, sql);
            logger.info(statement.toString());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                CategoryEntity categoryEntity = new CategoryEntity();
                categoryEntity.id = resultSet.getInt("id");
                categoryEntity.url = resultSet.getString("url");
                categoryEntity.name = resultSet.getString("name");
                categoryEntity.name_vn = resultSet.getString("name_vn");
                categoryEntity.short_desc = resultSet.getString("short_desc");
                categoryEntity.long_desc = resultSet.getString("long_desc");
                categoryEntity.order_weight = resultSet.getInt("order_weight");
                result.add(categoryEntity);
            }

        } catch (ClassNotFoundException cnfex) {
            logger.error(cnfex.getMessage(), cnfex);
        } finally {
            closeConnection();
        }
        return result;
    }

    public static HashMap<Integer, CategoryEntity> getCategoryMap() throws SQLException {
        HashMap<Integer, CategoryEntity> result = new HashMap();
        List<CategoryEntity> categoryList = getCategoryList();
        for (CategoryEntity categoryEntity : categoryList) {
            result.put(categoryEntity.id, categoryEntity);
        }
        return result;

    }

    public static int[] setGameCategory(int gameID, HashSet<Integer> categorySet) throws SQLException {
        int[] row = {};
        try {
            getConnection();
            String sqlDelete = "delete from game_category where game_id=:game_id ";
            NamedParameterStatement statementDelete = new NamedParameterStatement(conn, sqlDelete);
            statementDelete.setInt("game_id", gameID);
            statementDelete.executeUpdate();
            String sql = "insert IGNORE into game_category(game_id,category_id) values(:game_id,:category_id) ";
            NamedParameterStatement statement = new NamedParameterStatement(conn, sql);
            for (Integer categoryID : categorySet) {
                statement.setInt("game_id", gameID);
                statement.setInt("category_id", categoryID);
                statement.addBatch();
            }
            logger.info(statement.toString());
            row = statement.executeBatch();
        } catch (ClassNotFoundException cnfex) {
            logger.error(cnfex.getMessage(), cnfex);
        } finally {
            closeConnection();
        }
        return row;
    }

    public static HashSet<Integer> getGameCategory(int gameID) throws SQLException {
        HashSet<Integer> result = new HashSet();

        try {
            getConnection();
            String sql = "select  category_id from game_category where game_id=:game_id ";
            NamedParameterStatement statement = new NamedParameterStatement(conn, sql);
            statement.setInt("game_id", gameID);
            logger.info(statement.toString());
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Integer categoryID = resultSet.getInt("category_id");
                result.add(categoryID);
            }
        } catch (ClassNotFoundException cnfex) {
            logger.error(cnfex.getMessage(), cnfex);
        } finally {
            closeConnection();
        }
        return result;
    }
}
