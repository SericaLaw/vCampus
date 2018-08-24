package vCampus.server.database;

import com.alibaba.fastjson.JSON;

import java.sql.*;
import java.util.*;

public class DBHelper {
    private String DB_DIR = "/test/database/Database.accdb";
    private String DB_PATH;
    private String DB_URL;
    private Connection conn;
    private String jsonStr;
    public DBHelper() {
        // 获取项目路径
        // TODO:在下面配置数据库路径
        DB_PATH = System.getProperty("user.dir").replace('\\', '/') + DB_DIR;
        // 数据库URL
        DB_URL = "jdbc:Access:///"+DB_PATH;
        try {
            Class.forName("com.hxtt.sql.access.AccessDriver");
            System.out.println("... Connecting to Database: " + DB_URL);
            this.conn = DriverManager.getConnection(DB_URL, "", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // TODO:完成insert
    // 用于形如 POST /tableName, data 的API

    public boolean insert(String tableName, String jsonData) {
        try {

            // jsonData to Map
            Map<String, String> mapData = JSON.parseObject(jsonData, Map.class);
            String dataKey = null;
            String dataValue = null;
            String sql = "INSERT INTO " + tableName + " (";
            for (Map.Entry<String, String> entry:mapData.entrySet()) {
                dataKey = entry.getKey();
                sql += dataKey + ", ";
            }
            sql = sql.substring(0,sql.length() - 2); // 去除多余的逗号
            sql += ") VALUES (";
            for (Map.Entry<String, String> entry:mapData.entrySet()) {
                dataValue = entry.getValue();
                sql += "'" + dataValue + "', ";
            }
            sql = sql.substring(0,sql.length() - 2); // 去除多余的逗号
            sql += ")";
            System.out.println("Executing SQL: " + sql + "\n");
            PreparedStatement stmt = conn.prepareStatement(sql);

            stmt.execute();
            return true;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    // 暂时只支持一次改一个数据，查询条件也只有一个
    // 用于形如 PATCH /tableName/:key, params = data的API

    /**
     * @param tableName
     * @param key
     * @param value
     * @param jsonData jsonData
     * @return
     */
    public boolean update(String tableName, String key, String value, String jsonData) {
        try {
            // jsonData to Map
            Map<String, String> map = JSON.parseObject(jsonData, Map.class);

            assert map.size() == 1;
            String dataKey = null;
            String dataValue = null;
            for (Map.Entry<String, String> entry:map.entrySet()) {
                dataKey = entry.getKey();
                dataValue = entry.getValue();
            }
            String sql = "UPDATE "+ tableName +
                    " SET "+ dataKey + " = " + dataValue + " WHERE "
                    + key + " = '" + value + "'";
            System.out.println("Executing SQL: " + sql + "\n");
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
    public String selectOne(String tableName, String key, String value) {

        ArrayList<Map> res = new ArrayList();
        String jsonData = null;
        try {
            String sql = "SELECT * FROM " + tableName + " WHERE " + key + "=" + "'" + value + "'";
            System.out.println("Executing SQL: " + sql + "\n");
            PreparedStatement stmt = conn.prepareStatement(sql);
            // 得到ResultSet
            ResultSet rs = stmt.executeQuery();
            ResultSetMetaData data = rs.getMetaData();
            Map<String, String> item = new HashMap();
            if(rs.next()) {
                for(int i = 1; i <= data.getColumnCount(); i++) {
                    item.put(data.getColumnName(i),rs.getString(i));
                }
                item.put(key, value);
            }
            jsonData = JSON.toJSONString(item);
            return jsonData;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public String select(String tableName, String key, String value) {

        ArrayList<Map> res = new ArrayList();
        String jsonData = null;
        try {
            String sql = "SELECT * FROM " + tableName + " WHERE " + key + "=" + "'" + value + "'";
            System.out.println("Executing SQL: " + sql + "\n");
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM " + tableName + " WHERE " + key + "=" + "'" + value + "'");
            // 得到ResultSet
            ResultSet rs = stmt.executeQuery();
            ResultSetMetaData data = rs.getMetaData();
            while(rs.next()) {

                Map<String, String> item = new HashMap();
                for(int i = 1; i <= data.getColumnCount(); i++) {
                    item.put(data.getColumnName(i),rs.getString(i));
                }
                item.put(key, value);
                res.add(item);
            }
            jsonData = JSON.toJSONString(res);
            return jsonData;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    // 仅查询某一字段
    public String select(String tableName, String key, String value, String query) {

        ArrayList<Map> res = new ArrayList();
        String jsonData = null;
        try {
            String sql = "SELECT "+ query +"  FROM " + tableName + " WHERE " + key + "=" + "'" + value + "'";
            System.out.println("Executing SQL: " + sql + "\n");
            PreparedStatement stmt = conn.prepareStatement("SELECT "+ query +" FROM " + tableName + " WHERE " + key + "=" + "'" + value + "'");
            // 得到ResultSet
            ResultSet rs = stmt.executeQuery();
            ResultSetMetaData data = rs.getMetaData();
            while(rs.next()) {

                Map<String, String> item = new HashMap();
                for(int i = 1; i <= data.getColumnCount(); i++) {
                    item.put(data.getColumnName(i),rs.getString(i));
                }
                item.put(key, value);
                res.add(item);
            }
            jsonData = JSON.toJSONString(res);
            return jsonData;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    // 用于形如 DELETE /... 的API
    // 一般形式
    public String excuteSQL(String sql) {
        try {
            System.out.println("Executing SQL: " + sql + "\n");
            PreparedStatement stmt =
                    conn.prepareStatement(sql);
            stmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    public static void main(String[] args) {

        System.out.println(new DBHelper().DB_URL);
        DBHelper dbHelper = new DBHelper();
        String jsondata = dbHelper.select("Account", "campusCardID", "213160000", "password");
        System.out.println(jsondata);
    }
}

