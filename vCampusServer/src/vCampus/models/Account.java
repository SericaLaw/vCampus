package vCampus.models;

public class Account {
    private String campusCardID;
    private String username;
    private String password;
    private String lastName;
    private String firstName;
    private String selectCourse;
    private String teachCourseID;

    public Account(String campusCardID, String username, String password, String lastName, String firstName) {
        setCampusCardID(campusCardID);
        setUsername(username);
        setPassword(password);
        setLastName(lastName);
        setFirstName(firstName);
    }
    public String getCampusCardID() {
        return campusCardID;
    }

    public void setCampusCardID(String campusCardID) {
        this.campusCardID = campusCardID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSelectCourse() {
        return selectCourse;
    }

    public void setSelectCourse(String selectCourse) {
        this.selectCourse = selectCourse;
    }

    public String getTeachCourseID() {
        return teachCourseID;
    }

    public void setTeachCourseID(String teachCourseID) {
        this.teachCourseID = teachCourseID;
    }

    @Override
    public String toString() {
        return String.format("Class Account [ campusCardID = %s, username = %s, " +
                "password = %s, lastName = %s, firstName = %s, " +
                "selectCourse = %s, teachCourseID = %s ]",
                campusCardID, username, password, lastName, firstName, selectCourse, teachCourseID);
    }
}
