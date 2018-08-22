package com.serica.demo.server.http;

import java.io.Serializable;
public class HttpRequest implements Serializable {
    // 请求方式
    private RequestMethod type;
    // /tableName/:key/query/ 例如/student/09016401/shoppingList
    private String route;
    // 在名为tableName的数据表中查找
    private String tableName;
    // 查找条件key=value, key不体现在route里，需要在注册API时显示指定
    public String key;
    private String value;
    // 在查到的行中选取名为query列
    private String query = null;
    // post, patch需要使用data，使用JSON字符串
    private String data = null;
    // 对应的sql命令语句，如果普通的方法无法完成则进行配置
    private String sql = null;
    public HttpRequest(RequestMethod type, String route, String key) {
        this.type = type;
        this.route = route;
        this.key = key;
        parse();
    }
    public HttpRequest(RequestMethod type, String route, String key, String data) {
        this.type = type;
        this.route = route;
        this.key = key;
        this.data = data;
        parse();
    }
    public String getRoute() {
        return route;
    }

    public RequestMethod getType() {
        return type;
    }

    public String getTableName() {
        return tableName;
    }

    public String getValue() {
        return value;
    }

    public String getData() {
        return data;
    }

    public String getKey() {
        return key;
    }

    public String getQuery() {
        return query;
    }
    // TODO:这里需要修改以适合有缺省项的route
    private void parse() {
        String[] p = route.split("/");
        if(p.length >= 2)
            tableName = p[1].substring(0, 1).toUpperCase() + p[1].substring(1);
        if(p.length >= 3)
            value = p[2];
        if(p.length >= 4)
            query = p[3];
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    @Override
    public String toString() {
        return type + " " + route + ": tableName = " + tableName +", key = " + key + ", value = " + value +", query = " + query;
    }
}
