package team.yummy.vCampus.models;

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
