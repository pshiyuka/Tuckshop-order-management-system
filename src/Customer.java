import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class Customer extends User {

    public Customer(String username, String password, String email, String role) {
        super(username, password, role, email);
    }

    public static ArrayList<MenuItem> viewMenu() {
        ArrayList<MenuItem> items = new ArrayList<>();

        String connectionUrl = "jdbc:sqlserver://Lenovo\\SQLEXPRESS;databaseName=Tuckshop;integratedSecurity=true;encrypt=true;trustServerCertificate=true;";

        try (Connection connection = DriverManager.getConnection(connectionUrl)) {
            String query = "SELECT name, price, stock FROM menu_items";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                String name = resultSet.getString("name");
                double price = resultSet.getDouble("price");
                int stock = resultSet.getInt("stock");

                items.add(new MenuItem(name, price, stock));
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error loading menu: " + e.getMessage());
        }

        return items;
    }

    @Override
    public boolean login(String email, String password) {
        String connectionUrl = "jdbc:sqlserver://Lenovo\\SQLEXPRESS;databaseName=Tuckshop;integratedSecurity= true;encrypt= true; trustServerCertificate= true;";

        try {
            Connection connection = DriverManager.getConnection(connectionUrl);

            String query = "SELECT * FROM users WHERE email = ? AND password = ? AND role = 'Customer'";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);
            statement.setString(2, password);

            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                this.setEmail(email);
                this.setPassword(password);
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Invalid customer credentials!");
                return false;
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage());
            return false;
        }
    }




    @Override
    public String logout() {
        return "one of our customer has logged out!";
    }
}
