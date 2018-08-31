package team.yummy.vCampus.web;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.util.Hashtable;
import java.util.List;

public class WebRequest implements Serializable {
    // 请求方式
    private RequestMethod type = null;

    // /tableName/field/:value/query/ 例如/account/user/213160000
    private String route = null;

    // 在名为tableName的数据表中查找
    private String tableName = null;

    // 查找数据表字段
    public String field = null;

    // CRUD所使用的值
    private String value = null;

    // 在查到的行中选取名为query列
    private String query = null;

    // 客户端Session ID
    private Integer sessionId = null;

    // 客户端权限信息
    private Hashtable<String, byte[]> auth = null;

    // post, patch需要使用data，使用JSON字符串
    private String jsonData = null;

    // 对应的sql命令语句，如果普通的方法无法完成则进行配置
    private String sql = null;

    // GET, DELETE
    public WebRequest(RequestMethod type, String route) {
        this.type = type;
        this.route = route;
        parse();
    }

    // POST, PATCH
    public WebRequest(RequestMethod type, String route, String jsonData) {
        this.type = type;
        this.route = route;
        this.jsonData = jsonData;
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

    public String getJsonData() {
        return jsonData;
    }

    public String getKey() {
        return field;
    }

    public String getQuery() {
        return query;
    }

    public Integer getSessionId() { return sessionId; }

    public void setSessionId(Integer sessionId) { this.sessionId = sessionId; }

    // TODO:这里需要修改以适合有缺省项的route
    private void parse() {
        String[] p = route.split("/");
        // tableName
        if (p.length >= 2)
            tableName = p[1].substring(0, 1).toUpperCase() + p[1].substring(1);
        // key
        if (p.length >= 3)

        // value

            field = p[2].substring(0, 1).toUpperCase() + p[2].substring(1);
        if (p.length >= 4)
            value = p[3];
        // query
        if (p.length >= 5)
            query = p[4];
    }

    public <T> T data(Class<T> clazz) {
        String jsonData = getJsonData();
        // 将字符串转为T类
        T parsedData = JSON.parseObject(jsonData, clazz);
        return parsedData;
    }

    public <T> List<T>  dataList(Class<T> clazz) {
        String jsonData = getJsonData();
        List<T> list = JSON.parseArray(jsonData, clazz);
        return list;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    @Override
    public String toString() {
        return type + " <-- request url: " + route + "\n[parsed]: tableName = " + tableName +", key = " + field + ", value = " + value +", query = " + query +"\n[ jsonData =  " + jsonData +" ]\n[ sessionId = " + sessionId + " ]\n";
    }

    public Hashtable<String, byte[]> getAuth() {
        return auth;
    }

    public void setAuth(Hashtable<String, byte[]> auth) {
        this.auth = auth;
    }

    public String getAuthString(String key) {
        if (auth.containsKey(key)) {
            return new String(auth.get(key));
        } else {
            return null;
        }
    }

}
