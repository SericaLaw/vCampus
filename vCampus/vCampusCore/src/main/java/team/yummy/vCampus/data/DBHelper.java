package team.yummy.vCampus.data;

import com.alibaba.fastjson.JSON;
import team.yummy.vCampus.util.Logger;

import java.io.File;
import java.net.URI;
import java.sql.*;
import java.util.*;

/**
 * @author Serica
 * DBHelper
 * TODO: 防SQL注入
 */
public class DBHelper {
    private String url = null;
    private Connection conn = null;
    private PreparedStatement stmt = null;
    private Logger logger = new Logger("DBHelper");

    /**
     * 构造函数，用于配置数据库路径
     * 每次创建一个DBHelper实例时，都会打印相关日志信息
     */
    public DBHelper(String url) {
        this.url = url;
        try {
            // 连接数据库
            Class.forName("com.hxtt.sql.access.AccessDriver");
            logger.log("... Connecting to Database: " + url);
            this.conn = DriverManager.getConnection(url, "", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 向数据表中插入数据条目
     *
     * @param tableName 要执行插入操作的数据表名
     * @param jsonData  待插入项的JSON形式数据
     * @return 执行成功与否
     */
    public boolean insert(String tableName, String jsonData) {
        try {
            // jsonData to Map
            Map<String, String> mapData = JSON.parseObject(jsonData, Map.class);
            String dataKey = null;
            String dataValue = null;
            String sql = "INSERT INTO " + tableName + " (";
            for (Map.Entry<String, String> entry : mapData.entrySet()) {
                dataKey = entry.getKey();
                sql += dataKey + ", ";
            }
            sql = sql.substring(0, sql.length() - 2); // 去除多余的逗号
            sql += ") VALUES (";
            for (Map.Entry<String, String> entry : mapData.entrySet()) {
                dataValue = entry.getValue();
                sql += "'" + dataValue + "', ";
            }
            sql = sql.substring(0, sql.length() - 2); // 去除多余的逗号
            sql += ")";
            // log
            logger.log("Executing SQL: " + sql);
            stmt = conn.prepareStatement(sql);
            stmt.execute();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // 用于形如 PATCH /tableName/:key, params = data的API

    /**
     * 更新数据表中数据
     * @param tableName 要执行插入操作的数据表名
     * @param key 用于定位数据项的key
     * @param value 用于定位数据项的value
     * @param jsonData 待修改项的JSON形式数据
     * @return 执行成功与否
     */
    public boolean update(String tableName, String key, String value, String jsonData) {
        try {
            // jsonData to Map
            Map<String, String> map = JSON.parseObject(jsonData, Map.class);

            String dataKey = null;
            String dataValue = null;

            String sql = "UPDATE "+ tableName +
                    " SET ";

            for (Map.Entry<String, String> entry:map.entrySet()) {
                dataKey = entry.getKey();
                dataValue = entry.getValue();
                sql += String.format("%s = '%s', ", dataKey, dataValue);
            }
            sql = sql.substring(0,sql.length() - 2); // 去除多余的逗号
            sql += String.format(" WHERE %s = '%s'", key, value);

            logger.log("Executing SQL: " + sql);
            PreparedStatement stmt =
                    conn.prepareStatement(sql);
            stmt.execute();
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }

    }
    // 暂时只支持一个查询条件
    // 用于形如 GET /tableName/:key的API

    /**
     * 从数据表中选取一个数据（通常是唯一的，如选一个用户）
     *
     * @param tableName 要执行查询操作的数据表名
     * @param key       用于定位数据的key
     * @param value     用于定位数据的value
     * @return 查询结果的JSON形式数据
     */
    public String selectOne(String tableName, String key, String value) {
//        String jsonData;
        String sql = "SELECT TOP 1 * FROM " + tableName + " WHERE " + key + "=" + "'" + value + "'";
        return selectOne(sql);
//        try {
//            String sql = "SELECT TOP 1 * FROM " + tableName + " WHERE " + key + "=" + "'" + value + "'";
//            // log
//            logger.log("Executing SQL: " + sql);
//            stmt = conn.prepareStatement(sql);
//
//            // 得到ResultSet
//            ResultSet rs = stmt.executeQuery();
//            jsonData = JSON.toJSONString(convertRsToMap(rs));
//            return jsonData;
//        } catch (SQLException e) {
//            e.printStackTrace();
//            return null;
//        }
    }

    public String selectOne(String sql) {
        try {
            String jsonData;
            logger.log("Executing SQL: " + sql);
            stmt = conn.prepareStatement(sql);

            // 得到ResultSet
            ResultSet rs = stmt.executeQuery();
            jsonData = JSON.toJSONString(convertRsToMap(rs));
            return jsonData;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }

    }

    /**
     * 适用于查询结果只有一条的情况，如果有多条，也只会返回第一条
     * @param rs
     * @return
     */
    private Map<String,String> convertRsToMap(ResultSet rs) {
        ResultSetMetaData data;
        try {
            data = rs.getMetaData();
            Map<String, String> item = new HashMap();
            if (rs.next()) {
                for (int i = 1; i <= data.getColumnCount(); i++) {
                    item.put(data.getColumnName(i), rs.getString(i));
                }
            }
            return item;

        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }


    }

    private List<Map<String, String >> convertRsToListMap(ResultSet rs) {
        List<Map<String, String>> res = new ArrayList();
        ResultSetMetaData data;
        try {
            data = rs.getMetaData();
            while (rs.next()) {
                Map<String, String> item = new HashMap();
                for (int i = 1; i <= data.getColumnCount(); i++) {
                    item.put(data.getColumnName(i), rs.getString(i));
                }
                res.add(item);
            }
            return res;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }


    }
    public String select(String sql) {
        String jsonData;
        try {
            // log
            logger.log("Executing SQL: " + sql);
            stmt = conn.prepareStatement(sql);
            // 得到ResultSet
            ResultSet rs = stmt.executeQuery();
            jsonData = JSON.toJSONString(convertRsToListMap(rs));
            return jsonData;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 从数据表中选取前 count 个数据
     * @param tableName
     * @param count
     * @return
     */
    public String select(String tableName, int count) {
        String sql = String.format("SELECT TOP %d * FROM %s", count, tableName);
        return select(sql);
    }

    /**
     * 从数据表中选取一串数据（如同一个学生选的所有课程）
     *
     * @param tableName 要执行查询操作的数据表名
     * @param key       用于定位数据的key
     * @param value     用于定位数据的value
     * @return 查询结果的JSON形式字符串
     */
    public String select(String tableName, String key, String value) {
        String sql = "SELECT * FROM " + tableName + " WHERE " + key + "=" + "'" + value + "'";
        return select(sql);
    }

    public String search(String tableName, String field, String keyword) {
        String jsonData;
        try {
            String sql = "SELECT * FROM " + tableName + " WHERE " + field + " LIKE " + "'%" + keyword + "%'";
            // log
            logger.log("Executing SQL: " + sql);
            stmt = conn.prepareStatement(sql);
            // 得到ResultSet
            ResultSet rs = stmt.executeQuery();
            jsonData = JSON.toJSONString(convertRsToListMap(rs));
            return jsonData;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // 仅查询某一字段
//    public String select(String tableName, String key, String value, String query) {
//
//        ArrayList<Map> res = new ArrayList();
//        String jsonData = null;
//        try {
//            String sql = "SELECT "+ query +"  FROM " + tableName + " WHERE " + key + "=" + "'" + value + "'";
//            System.out.println("Executing SQL: " + sql + "\n");
//            PreparedStatement stmt = conn.prepareStatement("SELECT "+ query +" FROM " + tableName + " WHERE " + key + "=" + "'" + value + "'");
//            // 得到ResultSet
//            ResultSet rs = stmt.executeQuery();
//            ResultSetMetaData data = rs.getMetaData();
//            while(rs.next()) {
//
//                Map<String, String> item = new HashMap();
//                for(int i = 1; i <= data.getColumnCount(); i++) {
//                    item.put(data.getColumnName(i),rs.getString(i));
//                }
//                item.put(key, value);
//                res.add(item);
//            }
//            jsonData = JSON.toJSONString(res);
//            return jsonData;
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//    }
    // 用于形如 DELETE /... 的API
    public boolean delete(String sql) {
        logger.log("Executing SQL: " + sql);
        try {
            stmt = conn.prepareStatement(sql);
            stmt.executeUpdate();
            if (stmt.getUpdateCount() == 0)
                return false;
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 从数据表中删除数据项
     *
     * @param tableName 要执行删除操作的数据表名
     * @param key       用于定位数据的key
     * @param value     用于定位数据的value
     * @return 执行成功与否
     */
    public boolean delete(String tableName, String key, String value) {
        String sql = "DELETE FROM " + tableName + " WHERE " + key + "= '" + value + "'";
        return delete(sql);
    }

    public boolean delete(String tableName, String f1, String v1, String f2, String v2) {
        String sql = String.format("DELETE FROM %s WHERE %s = '%s' and %s = '%s'",tableName, f1, v1, f2, v2);
        return delete(sql);
    }
    // 一般形式
    public String excuteSQL(String sql) {
        try {
            logger.log("Executing SQL: " + sql);
            PreparedStatement stmt =
                    conn.prepareStatement(sql);
            stmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {


    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

