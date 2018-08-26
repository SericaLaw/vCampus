package vCampus.models;

public class Account {
    private String campusCardID;
    private String username;
    private String password;
    private String lastName;
    private String firstName;

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


    @Override
    public String toString() {
        return String.format("Class Account [ campusCardID = %s, username = %s, " +
                "password = %s, lastName = %s, firstName = %s ]",
                campusCardID, username, password, lastName, firstName);
    }

    @Override
    public boolean equals(Object obj) {
        if(this == obj)
            return true;
        if(obj instanceof Account) {
            Account another = (Account) obj;
            return this.campusCardID.equals(another.campusCardID) && this.firstName.equals(another.firstName)
                    && this.lastName.equals(another.lastName) && this.username.equals(another.username)
                    && this.password.equals(another.password);
        }
        return false;
    }
}
