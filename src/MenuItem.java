import java.sql.*;

public class MenuItem {
    // Private attributes
    private String name;
    private double price;
    private int stock;

    // Constructor
    public MenuItem(String name, double price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    // Getters
    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getStock() {
        return stock;
    }

    // Decreasing the stock in the database and updating the object stock
    public boolean decreaseStock(int amount) {
        if (amount <= 0) {
            System.out.println("Amount must be positive.");
            return false;
        }

        if (stock >= amount) {
            // Connect to the database
            String connectionUrl = "jdbc:sqlserver://Lenovo\\SQLEXPRESS;databaseName=Tuckshop;integratedSecurity=true;encrypt=true;trustServerCertificate=true;";

            try {
                Connection connection = DriverManager.getConnection(connectionUrl);

                // Update stock in the database
                String updateQuery = "UPDATE menu_items SET stock = stock - ? WHERE name = ?";
                PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);
                preparedStatement.setInt(1, amount);
                preparedStatement.setString(2, this.name);

                int rowsAffected = preparedStatement.executeUpdate();

                if (rowsAffected > 0) {
                    this.stock -= amount;
                    return true;
                } else {
                    System.out.println("Item not found in the database.");
                    return false;
                }
            } catch (SQLException e) {
                System.out.println("Error while updating stock: " + e.getMessage());
                return false;
            }
        } else {
            System.out.println("Not enough stock to complete");
            return false;
        }
    }

    // toString method for easy display
    @Override
    public String toString() {
        return name + " - $" + price + " (" + stock + " left)";
    }
}
