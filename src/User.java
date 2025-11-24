import java.sql.*;
abstract class User {
    private String username;
    private String password;
    private String role;
    private String email;

    public User(String username, String password, String role, String email) {
        this.username = username;
        this.password = password;
        this.role = role;
        this.email = email;

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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    public static String signUp(String username, String password, String email, String role) {

        if (username.isEmpty() || password.isEmpty() || email.isEmpty() || role.isEmpty()) {
            return "All fields are required!";
        } else if (!email.contains("@")) {
            return "Invalid email";
        } if (role.equalsIgnoreCase("staff") && !email.toLowerCase().endsWith("@tuck-shop.com")) {
            return "Only staff with a '@tuck-shop.com' email can register!";
        }

        String connectionUrl = "jdbc:sqlserver://Lenovo\\SQLEXPRESS;databaseName=Tuckshop;integratedSecurity=true;encrypt=true;trustServerCertificate=true;";

        try (Connection connection = DriverManager.getConnection(connectionUrl)) {

            // Check if username already exists
            String checkUsername = "SELECT 1 FROM users WHERE username = ?";
            try (PreparedStatement ps = connection.prepareStatement(checkUsername)) {
                ps.setString(1, username);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return "Username already exists!";
                    }
                }
            }

            // Check if email already exists
            String checkEmail = "SELECT 1 FROM users WHERE email = ?";
            try (PreparedStatement ps = connection.prepareStatement(checkEmail)) {
                ps.setString(1, email);
                try (ResultSet rs = ps.executeQuery()) {
                    if (rs.next()) {
                        return "Email already exists!";
                    }
                }
            }

            // Insert new user
            String insertQuery = "INSERT INTO users(username, password, email, role) VALUES (?, ?, ?, ?)";
            try (PreparedStatement insert = connection.prepareStatement(insertQuery)) {
                insert.setString(1, username);
                insert.setString(2, password);
                insert.setString(3, email);
                insert.setString(4, role);
                insert.executeUpdate();
            }

            return "User registered successfully!";

        } catch (SQLException e) {
            e.printStackTrace();
            return "Error: " + e.getMessage();
        }
    }

    public abstract boolean login(String email,String password);

    public abstract String logout();

}
