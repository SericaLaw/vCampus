package com.serica.demo.server.database;

import com.alibaba.fastjson.JSON;

import java.awt.*;
import java.sql.*;
import java.util.*;
import java.util.List;

public class DBHelper {

    private String DB_PATH;
    private String DB_URL;
    private Connection conn;
    private String jsonStr;
    public DBHelper() {
        // 获取项目路径
        // TODO:在下面配置数据库路径
        DB_PATH = System.getProperty("user.dir").replace('\\', '/') + "/database/Database.accdb";
        // 数据库URL
        DB_URL = "jdbc:Access:///"+DB_PATH;
        try {
            Class.forName("com.hxtt.sql.access.AccessDriver");
            this.conn = DriverManager.getConnection(DB_URL, "", "");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Connection getConn() {
        return this.conn;
    }
    // TODO:完成insert
    // 用于形如 POST /tableName, data 的API

    public void insert(String tableName, String... args) {
        try {
            switch (tableName) {
                case "DSA":
                    PreparedStatement stmt = conn.prepareStatement("INSERT INTO DSA (studentID, studenName, studentScore) VALUES ( ?, ?, ?) ");
                    for (int i = 0; i < args.length; i++)
                        stmt.setString(i + 1, args[i]);
                    stmt.execute();
                    break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // 暂时只支持一次改一个数据，查询条件也只有一个
    // 用于形如 PATCH /tableName/:key, params = data的API
    public void update(String tableName, String key, String value, String data) {
        System.out.println("in update");
        try {
            // jsonData to Map
            Map<String, String> map = JSON.parseObject(data, Map.class);

            assert map.size() == 1;
            String dataKey = null;
            String dataValue = null;
            for (Map.Entry<String, String> entry:map.entrySet()) {
                dataKey = entry.getKey();
                dataValue = entry.getValue();
            }
            String sql = "UPDATE "+ tableName +
                    " SET "+ dataKey + " = " + dataValue+
                    " WHERE " +key+" = "+value;
            System.out.println(sql);
            PreparedStatement stmt =
                    conn.prepareStatement(sql);
            stmt.execute();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    // 暂时只支持一个查询条件
    // 用于形如 GET /tableName/:key的API
    public String select(String tableName, String key, String value) {

        ArrayList<Map> res = new ArrayList();
        String jsonData = null;
        try {
            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM " + tableName + " WHERE " + key + "=" + value);
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
        } finally {
            return jsonData;
        }
    }

    // 用于形如 DELETE /... 的API
    // 一般形式
    public String excuteSQL(String sql) {
        try {
            System.out.println(sql);
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
}

