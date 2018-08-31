package team.yummy.vCampus.models;

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
