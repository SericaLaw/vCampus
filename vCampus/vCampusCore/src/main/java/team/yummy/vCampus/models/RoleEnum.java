package team.yummy.vCampus.models;

/**
 * @author Serica
 * 角色枚举类
 * 手动初始化Account时使用
 */
public enum RoleEnum {
    STUDENT("student"),
    TEACHER("teacher"),
    ADMIN("admin");

    private String name;
    RoleEnum(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
