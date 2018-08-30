package team.yummy.vCampus.server;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.sql.Timestamp;
import java.util.Hashtable;

public class Session {
    // Session ID
    private Integer id;

    // 当前会话的最后活跃时间
    private Timestamp lastActive;

    // 会话过期所需时间
    private Timestamp timeout;

    // 数据以字节串形式保存，用字符串索引
    private Hashtable<String, byte[]> data = new Hashtable<String, byte[]>();

    // 每次索引data都会导致最后活跃时间更新
    private Hashtable<String, byte[]> getData() {
        lastActive.setTime(System.currentTimeMillis());
        return data;
    }

    public Session(Integer id, Timestamp timeout) {
        this.id = id;
        this.timeout = timeout;
        this.lastActive = new Timestamp(System.currentTimeMillis());
    }

    public Integer getSessionId() {
        return id;
    }

    public String getString(String key) {
        if (data.contains(key)) {
            return new String(getData().get(key));
        } else {
            return null;
        }
    }

    public void setString(String key, String value) {
        getData().put(key, value.getBytes());
    }

    public Integer getInteger(String key) {
        if (data.contains(key)) {
            return new BigInteger(getData().get(key)).intValue();
        } else {
            return null;
        }
    }

    public void setInteger(String key, Integer value) {
        getData().put(key, ByteBuffer.allocate(4).putInt(value).array());
    }
}
