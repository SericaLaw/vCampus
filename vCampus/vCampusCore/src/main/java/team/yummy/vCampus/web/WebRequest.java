package team.yummy.vCampus.web;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.util.Hashtable;
import java.util.List;

/**
 * 请求类，用于前端向后端发送交互请求
 * @author Serica
 * @author Vigilans
 */
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

    private String queryValue = null;

    // 客户端Session ID
    private Integer sessionId = null;

    // post, patch需要使用data，使用JSON字符串
    private String body = null;

    // 对应的sql命令语句，如果普通的方法无法完成则进行配置
    private String sql = null;

    // GET, DELETE
    public WebRequest(RequestMethod type, String route) {
        this.type = type;
        this.route = route;
        parse();
    }

    // POST, PATCH
    public WebRequest(RequestMethod type, String route, String body) {
        this.type = type;
        this.route = route;
        this.body = body;
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

    public String getBody() {
        return body;
    }

    public String getField() {
        return field;
    }

    public String getQuery() {
        return query;
    }

    public Integer getSessionId() { return sessionId; }

    public void setSessionId(Integer sessionId) { this.sessionId = sessionId; }

    private void parse() {
        String[] p = route.split("/");
        // tableName
        if (p.length >= 2)
            tableName = p[1].substring(0, 1).toUpperCase() + p[1].substring(1);
        // key
        if (p.length >= 3)
            field = p[2].substring(0, 1).toUpperCase() + p[2].substring(1);
        // value
        if (p.length >= 4)
            value = p[3];
        // query
        if (p.length >= 5)
            query = p[4].substring(0, 1).toUpperCase() + p[4].substring(1);
        // query value
        if (p.length >= 6)
            queryValue = p[5];
    }

    public <T> T deserializeBody(Class<T> clazz) {
        return JSON.parseObject(getBody(), clazz);
    }

    public <T> List<T> deserializeList(Class<T> clazz) {
        return JSON.parseArray(getBody(), clazz);
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    /**
     * 用于Logger打印请求信息
     * @return 请求日志信息
     */
    @Override
    public String toString() {
        return type + " <-- request url: " + route + "\n[ parsed ]: tableName = " + tableName +", key = " + field + ", value = " + value +", query = " + query +"\n[ body =  " + body +" ]\n[ sessionId = " + sessionId + " ]\n";
    }

    public String getQueryValue() {
        return queryValue;
    }
}
