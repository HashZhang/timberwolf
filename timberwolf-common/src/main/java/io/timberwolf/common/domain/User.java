package io.timberwolf.common.domain;

/**
 * User POJO
 *
 * @author Hash Zhang
 * @version 0.0.0
 */
public class User {
    private String username;
    private String password;

    public String getUsername() {
        return username;
    }

    public User setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public User setPassword(String password) {
        this.password = password;
        return this;
    }
}
