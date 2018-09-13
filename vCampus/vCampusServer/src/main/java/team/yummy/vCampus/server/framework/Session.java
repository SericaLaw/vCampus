package team.yummy.vCampus.server.framework;

import java.math.BigInteger;
import java.nio.ByteBuffer;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Hashtable;
import java.util.Random;

/**
 * 会话控制类
 */
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

    /**
     * 索引取得会话数据，同时更新最后活跃时间
     * @return 取得的会话数据
     */
    private Hashtable<String, byte[]> getData() {
        lastActive.setTime(System.currentTimeMillis());
        return data;
    }

    /**
     * 构造函数
     * @param id 会话ID
     * @param timeout 会话过期时间
     */
    public Session(Integer id, Timestamp timeout) {
        this.id = id;
        this.timeout = timeout;
        this.lastActive = new Timestamp(System.currentTimeMillis());
    }

    /**
     * 判断会话是否已经过期
     * @return 将会话上一次活跃时间与有效时长与当前时间对比，确认会话是否已经过期
     */
    public boolean hasExpired() {
        return new Timestamp(lastActive.getTime() + timeout.getTime())
                .before(new Timestamp(System.currentTimeMillis()));
    }

    /**
     * 取得当前会话的ID
     * @return 当前会话的ID
     */
    public Integer getSessionId() {
        return id;
    }

    /**
     * 取得当前会话数据(string)
     * @param key 所需取的会话数据键值
     * @return 若含有key，返回对应会话数据
     */
    public String getString(String key) {
        if (data.containsKey(key)) {
            return new String(getData().get(key));
        } else {
            return null;
        }
    }

    /**
     * 写入会话数据(string)
     * @param key 所需取的会话数据键值
     * @param value 所需写入会话数据值
     */
    public void setString(String key, String value) {
        getData().put(key, value.getBytes());
    }

    /**
     * 取得当前会话数据(integer)
     * @param key 所需取的会话数据键值
     * @return 若含有key，返回对应会话数据
     */
    public Integer getInteger(String key) {
        if (data.containsKey(key)) {
            return new BigInteger(getData().get(key)).intValue();
        } else {
            return null;
        }
    }

    /**
     * 写入会话数据(integer)
     * @param key 所需取的会话数据键值
     * @param value 所需写入会话数据值
     */
    public void setInteger(String key, Integer value) {
        getData().put(key, ByteBuffer.allocate(4).putInt(value).array());
    }

    /**
     * 将会话对应信息转化为显式的字符串信息
     * @return 会话信息，包括sessionID, username, password
     */
    public String toString() {
        return String.format("Session [ sessionId = %s, username = %s, password = %s ]", id.toString(), getString("username"), getString("password"));
    }


}
