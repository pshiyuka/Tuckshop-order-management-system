import javax.swing.*;
import java.sql.*;
import java.util.ArrayList;

public class Staff extends User {

    public Staff(String username, String password, String email, String role) {
        super(username, password, email, role);
    }
    public String manageOrders(ArrayList<Order> orders) {
        StringBuilder stringBuilder = new StringBuilder("----- All Orders -----\n\n");

        if (orders.isEmpty()) {
            stringBuilder.append("No orders to manage.\n");
            return stringBuilder.toString();
        }

        int index = 1;
        for (Order order : orders) {
            stringBuilder.append(String.format("Order #%d\n", index++));
            stringBuilder.append(String.format("Order ID: %d\n", order.getOrderID()));
            stringBuilder.append(String.format("Customer email: %s\n", order.getCustomerEmail()));
            stringBuilder.append("Items:\n");

            ArrayList<String> seen = new ArrayList<>();
            for (String item : order.getItems()) {
                if (!seen.contains(item)) {
                    int count = order.countItem(item);
                    stringBuilder.append("  - ").append(item).append(" x ").append(count).append("\n");
                    seen.add(item);
                }
            }

            stringBuilder.append(String.format("Total: N$%.2f\n", order.calculateTotal()));
            stringBuilder.append("------------------------\n");
        }

        return stringBuilder.toString();
    }


    @Override
    public boolean login(String email, String password) {
        final String ALLOWED_DOMAIN = "tuck-shop.com";

        if (!email.endsWith("@" + ALLOWED_DOMAIN)) {
            JOptionPane.showMessageDialog(null, "Only tuck shop related emails are allowed for staff login!");
            return false;
        }

        String connectionUrl = "jdbc:sqlserver://Lenovo\\SQLEXPRESS;databaseName=Tuckshop;integratedSecurity=true;encrypt=true;trustServerCertificate=true;";
        try {
            Connection connection = DriverManager.getConnection(connectionUrl);

            String query = "SELECT * FROM users WHERE email = ? AND password = ? AND role = 'Staff' ";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, email);
            statement.setString(2, password);


            ResultSet resultSet = statement.executeQuery();

            if (resultSet.next()) {
                this.setEmail(email);
                this.setPassword(password);
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Invalid staff credentials!");
                return false;
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Database error: " + e.getMessage());
            return false;
        }
    }

    @Override
    public String logout() {

        return "one of the staff member has logged out!";

    }
}