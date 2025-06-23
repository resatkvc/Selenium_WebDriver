package config;

public class UserConfig {
    public static class User {
        public final String username;
        public final String password;
        public User(String username, String password) {
            this.username = username;
            this.password = password;
        }
    }
    public static final User STANDARD_USER = new User("standard_user", "secret_sauce");
    public static final User LOCKED_OUT_USER = new User("locked_out_user", "secret_sauce");
    public static final User PROBLEM_USER = new User("problem_user", "secret_sauce");
    public static final User INVALID_USER = new User("invalid_user", "wrong_password");
} 