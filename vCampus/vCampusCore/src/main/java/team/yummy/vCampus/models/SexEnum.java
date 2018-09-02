package team.yummy.vCampus.models;

/**
 * @author Serica
 * 性别枚举类
 * 手动初始化StuInfo时使用
 */
public enum  SexEnum {
    MALE("男"),
    FEMALE("女");

    private String name;
    SexEnum(String name) {
        this.name = name;
    }

    String getName() {
        return name;
    }
}
