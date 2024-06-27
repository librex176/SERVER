package backend.model;

public class User {

    private int id;
    private String username;
    private String password;
    private int userTypeId;

    public User() {
        // Constructor sin parámetros
    }

    public User(int id, String username, String password, int userTypeId) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.userTypeId = userTypeId;
    }

    public User(String username, String password, int userTypeId) {
        this.username = username;
        this.password = password;
        this.userTypeId = userTypeId;
    }

    // Getters and Setters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getUserTypeId() {
        return userTypeId;
    }

    public void setUserTypeId(int userTypeId) {
        this.userTypeId = userTypeId;
    }
}
