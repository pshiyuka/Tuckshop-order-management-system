import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.UUID;

public class TuckshopGUI extends JFrame implements ActionListener {

    static ArrayList<Order> orders = new ArrayList<>();

    private JTextField emailField = new JTextField();
    private JPasswordField passwordField = new JPasswordField();
    private JButton loginButton = new JButton("Log in");
    private JButton signUpButton = new JButton("Sign up");
    private JCheckBox showPasswordCheckbox = new JCheckBox("Show Password");

    public TuckshopGUI() {
        // Create Main Frame
        JFrame welcome = new JFrame("Tuckshop");
        welcome.setSize(420, 420);
        welcome.setLayout(new BorderLayout());
        welcome.getContentPane().setBackground(new Color(0x000000));

        // Top Header Label
        JLabel Label = new JLabel("Our Tuckshop", SwingConstants.CENTER);
        Label.setFont(new Font("Serif", Font.BOLD, 36));
        Label.setForeground(Color.WHITE);
        Label.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));

        //Panel
        JPanel Panel = new JPanel();
        Panel.setLayout(null);
        Panel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        Panel.setBackground(Color.BLACK);

        // Email Label and Field
        JLabel emailLabel = new JLabel("Email:");
        emailLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        emailLabel.setForeground(Color.LIGHT_GRAY);
        emailField.setFont(new Font("Arial", Font.PLAIN, 14));
        emailField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        emailField.setBackground(Color.BLACK);
        emailField.setForeground(Color.WHITE);

        // Password Label and Field
        JLabel passwordLabel = new JLabel("Password:");
        passwordLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordLabel.setForeground(Color.LIGHT_GRAY);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));
        passwordField.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
        passwordField.setBackground(Color.BLACK);
        passwordField.setForeground(Color.WHITE);

        // Show Password Checkbox
        showPasswordCheckbox.setBackground(Color.BLACK);
        showPasswordCheckbox.setForeground(Color.LIGHT_GRAY);
        showPasswordCheckbox.setFocusable(false);
        showPasswordCheckbox.addActionListener(e -> {
            if (showPasswordCheckbox.isSelected()) {
                passwordField.setEchoChar((char) 0);
            } else {
                passwordField.setEchoChar('*');
            }
        });

        // Configure Login Button
        loginButton.setBackground(new Color(0x2196F3));
        loginButton.setForeground(Color.WHITE);
        loginButton.setFocusable(false);
        loginButton.setFont(new Font("Arial", Font.BOLD, 14));
        loginButton.addActionListener(this);


        //set bounds
        emailLabel.setBounds(50, 50, 100, 25);
        emailField.setBounds(160, 50, 180, 25);
        passwordLabel.setBounds(50, 100, 100, 25);
        passwordField.setBounds(160, 100, 180, 25);
        showPasswordCheckbox.setBounds(160, 130, 150, 20);
        loginButton.setBounds(100, 160, 80, 30);

        // Add components to the panel
        Panel.add(emailLabel);
        Panel.add(emailField);
        Panel.add(passwordLabel);
        Panel.add(passwordField);
        Panel.add(showPasswordCheckbox);
        Panel.add(loginButton);

        // Another Panel
        JPanel Panel2 = new JPanel();
        Panel2.setLayout(new FlowLayout());
        Panel2.setBorder(BorderFactory.createEmptyBorder(10, 50, 20, 50));
        Panel2.setBackground(Color.BLACK);

        JLabel signUpLabel = new JLabel("Don't have an account?");
        signUpLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        signUpLabel.setForeground(Color.LIGHT_GRAY);

        // Configure Sign Up Button
        signUpButton.setFont(new Font("Arial", Font.BOLD, 14));
        signUpButton.setForeground(new Color(0x2196F3));
        signUpButton.setContentAreaFilled(false);
        signUpButton.setBorderPainted(false);
        signUpButton.addActionListener(this);

        Panel2.add(signUpLabel);
        Panel2.add(signUpButton);

        // Adding Components to Main Frame
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBackground(Color.BLACK);
        mainPanel.add(Label, BorderLayout.NORTH);
        mainPanel.add(Panel, BorderLayout.CENTER);
        mainPanel.add(Panel2, BorderLayout.SOUTH);

        welcome.add(mainPanel);
        welcome.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        welcome.setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == loginButton) {
            handleLogin();
        } else if (e.getSource() == signUpButton) {
            showSignUpFrame();
        }
    }
    private void handleLogin() {
        loginButton.addActionListener(e -> {
            String email = emailField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();

            // Validate input fields
            if (email.isEmpty() || password.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Email and Password fields cannot be empty!");
                return;
            }

            // Determine the role based on the email
            if (email.endsWith("@tuck-shop.com")) {
                // Staff Login
                Staff staff = new Staff("", password, email, "Staff");
                if (staff.login(email, password)) {
                    showStaffDashboardGUI(staff);

                }
            } else {
                // Customer Login
                Customer customer = new Customer("", password, email, "Customer");
                if (customer.login(email, password)) {
                    showCustomerDashboardGUI(customer);

                }
            }
        });

    }


    private void showSignUpFrame() {
        JFrame signUpFrame = new JFrame("Sign Up");
        signUpFrame.setSize(400, 400);
        signUpFrame.setLayout(null);

        // Labels
        JLabel usernameLabel = new JLabel("Username:");
        JLabel passwordLabel = new JLabel("Password:");
        JLabel emailLabel = new JLabel("Email:");
        JLabel roleLabel = new JLabel("Role(staff/customer):");

        // Fields
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        JTextField emailField = new JTextField();
        JTextField roleField = new JTextField();

        // Button
        JButton signUp = new JButton("Sign Up");
        signUp.setBackground(new Color(0x4CAF50));
        signUp.setFocusPainted(false);

        JButton backButton = new JButton("Back");
        backButton.setBackground(new Color(0xE53935));
        backButton.setFocusPainted(false);


        // Set bounds for labels
        usernameLabel.setBounds(50, 50, 100, 20);
        passwordLabel.setBounds(50, 90, 100, 20);
        emailLabel.setBounds(50, 130, 100, 20);
        roleLabel.setBounds(40, 170, 150, 20);

        // Set bounds for fields (smaller size)
        usernameField.setBounds(160, 50, 150, 20);
        passwordField.setBounds(160, 90, 150, 20);
        emailField.setBounds(160, 130, 150, 20);
        roleField.setBounds(160, 170, 150, 20);

        // Set bounds for the button

        signUp.setBounds(160, 230, 80, 25);
        backButton.setBounds(250, 230, 80, 25);


        // Add components to frame
        signUpFrame.add(usernameLabel);
        signUpFrame.add(passwordLabel);
        signUpFrame.add(emailLabel);
        signUpFrame.add(roleLabel);
        signUpFrame.add(usernameField);
        signUpFrame.add(passwordField);
        signUpFrame.add(emailField);
        signUpFrame.add(roleField);
        signUpFrame.add(signUp);
        signUpFrame.add(backButton);

        // Set default close operation and make frame visible
        signUpFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        signUpFrame.setVisible(true);

        backButton.addActionListener(e -> {
            signUpFrame.dispose();

        });

        signUp.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            String email = emailField.getText();
            String role = roleField.getText();

            if (role.equalsIgnoreCase("Customer")) {
                Customer customer = new Customer(username, password, email, role);
                JOptionPane.showMessageDialog(signUpFrame, customer.signUp(username,password,email,role));
            } else if (role.equalsIgnoreCase("Staff")) {
                Staff staff = new Staff(username, password, email, role);
                JOptionPane.showMessageDialog(signUpFrame, staff.signUp(username, password, email, role));
            } else {
                JOptionPane.showMessageDialog(signUpFrame, User.signUp(username,password,email,role));
            }
        });

        signUpFrame.setVisible(true);
    }

    private void showCustomerDashboardGUI(Customer customer) {
        JFrame frame = new JFrame("Customer Dashboard");
        frame.setSize(420, 420);
        frame.setLayout(null);
        frame.getContentPane().setBackground(new Color(0xF5F5F5));

        JLabel welcome = new JLabel("Welcome! Customer Dashboard", JLabel.CENTER);
        welcome.setFont(new Font("Arial", Font.BOLD, 18));
        JButton placeOrder = new JButton("PlaceOrder");
        JButton logout = new JButton("Logout");

        placeOrder.setBounds(175, 100, 150, 25);
        placeOrder.setBackground(new Color(0x2196F3));
        placeOrder.setForeground(Color.WHITE);

        logout.setBounds(175,140,150,25);
        logout.setBackground(new Color(	0x96f321) );
        logout.setForeground(Color.WHITE);


        frame.add(welcome);
        frame.add(placeOrder);
        frame.add(logout);
        frame.setVisible(true);

        logout.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, customer.logout());
            frame.dispose();
        });

        placeOrder.addActionListener(e ->{

            ArrayList<MenuItem> items = Customer.viewMenu();
            placeCustomerOrder(items,orders, customer.getEmail());
        });

}

    private void placeCustomerOrder(ArrayList<MenuItem> items, ArrayList<Order> orders,String customerEmail) {
        JFrame orderFrame = new JFrame("Place Order");
        orderFrame.setSize(400, 500);
        orderFrame.setLayout(new BorderLayout());

        Order order = new Order();

        // Panel to hold the menu item buttons vertically
        JPanel menuPanel = new JPanel();
        menuPanel.setLayout(new BoxLayout(menuPanel, BoxLayout.Y_AXIS));

        for (int i = 0; i < items.size(); i++) {
            MenuItem item = items.get(i);
            if (item.getStock() > 0) {
                JButton orderButton = new JButton("Order " + item.getName() + " - $" + item.getPrice() + " (Stock: " + item.getStock() + ")");
                orderButton.setMaximumSize(new Dimension(350, 30));
                menuPanel.add(orderButton);

                // Action listener for each item button
                orderButton.addActionListener(e -> {
                    String str = JOptionPane.showInputDialog("Enter quantity for " + item.getName() + ":");
                    if (str == null || str.isEmpty()) return;

                    try {
                        int qty = Integer.parseInt(str.trim());
                        if (qty <= 0 || qty > item.getStock()) {
                            JOptionPane.showMessageDialog(orderFrame, "Invalid quantity. Available: " + item.getStock());
                            return;
                        }
                        for (int j = 0; j < qty; j++) {
                            order.addItem(item.getName(), item.getPrice());
                        }
                        JOptionPane.showMessageDialog(orderFrame, qty + " x " + item.getName() + " added.");
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(orderFrame, "Please enter a valid number.");
                    }
                });
            }
        }

        // Panel to hold action buttons horizontally
        JPanel actionPanel = new JPanel();
        actionPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));

        // View order button
        JButton viewOrder = new JButton("View");
        viewOrder.setPreferredSize(new Dimension(80, 30));
        viewOrder.addActionListener(e -> {
            StringBuilder summary = new StringBuilder("Your current order:\n");
            ArrayList<String> seen = new ArrayList<>();
            for (String name : order.getItems()) {
                if (!seen.contains(name)) {
                    int qty = order.countItem(name);
                    summary.append("- ").append(name).append(" x ").append(qty).append("\n");
                    seen.add(name);
                }
            }
            summary.append("Total: $").append(String.format("%.2f", order.calculateTotal()));
            JOptionPane.showMessageDialog(orderFrame, summary.toString());
        });

        // Remove item button
        JButton removeItem = new JButton("Remove");
        removeItem.setPreferredSize(new Dimension(80, 30));
        removeItem.addActionListener(e -> {
            String itemToRemove = JOptionPane.showInputDialog("Enter item name to remove:");
            if (itemToRemove != null && !itemToRemove.trim().isEmpty()) {
                order.removeItem(itemToRemove.trim());
            }
        });

        // Cancel order button
        JButton cancelOrder = new JButton("Cancel");
        cancelOrder.setPreferredSize(new Dimension(80, 30));
        cancelOrder.addActionListener(e -> {
            JOptionPane.showMessageDialog(orderFrame, "Order cancelled.");
            orderFrame.dispose();
        });

        // Finish order button
        JButton finishOrder = new JButton("Finish");
        finishOrder.setPreferredSize(new Dimension(80, 30));
        finishOrder.addActionListener(e -> {
            String[] paymentOptions = {"Cash", "Card"};
            String paymentMethod = (String) JOptionPane.showInputDialog(orderFrame, "Select Payment Method:", "Payment", JOptionPane.QUESTION_MESSAGE, null, paymentOptions, paymentOptions[0]);

            if (paymentMethod != null) {
                for (MenuItem item : items) {
                    int qtyOrdered = order.countItem(item.getName());
                    if (qtyOrdered > 0) {
                        boolean success = item.decreaseStock(qtyOrdered);
                        if (!success) {
                            JOptionPane.showMessageDialog(orderFrame, "Failed to update stock for " + item.getName());
                        }
                    }
                }

                if (paymentMethod.equals("Card")) {
                    // Collect card details and process payment
                    String cardholderName = JOptionPane.showInputDialog("Enter cardholder name:");
                    String cardType = JOptionPane.showInputDialog("Enter card type (e.g., Visa, MasterCard):");
                    String cardNumber = JOptionPane.showInputDialog("Enter card number:");
                    String cardExpiry = JOptionPane.showInputDialog("Enter card expiry date (MM/YY):");
                    String paymentGateway = "MockGateway";
                    String transactionId = UUID.randomUUID().toString();
                    String cardMask = "**** **** **** " + cardNumber.substring(cardNumber.length() - 4);
                    String confirmationId = "CONF-" + System.currentTimeMillis();

                    // Create and display card payment
                    cardPayment cardPayment = new cardPayment(
                            "PAY-" + System.currentTimeMillis(),
                            String.valueOf(order.getOrderID()),
                            order.calculateTotal(),
                            LocalDateTime.now(),
                            transactionId,
                            cardType,
                            cardMask,
                            cardExpiry,
                            cardholderName,
                            paymentGateway,
                            confirmationId,
                            "Card"
                    );
                    String info = cardPayment.displayPaymentInfo();
                    JOptionPane.showMessageDialog(null, info, "Card Payment Info", JOptionPane.INFORMATION_MESSAGE);
                }

                if (paymentMethod.equals("Cash")) {
                    // Generate the cash payment record
                    cashPayment cashPayment = new cashPayment(
                            "PAY-" + System.currentTimeMillis(),
                            String.valueOf(order.getOrderID()),
                            LocalDateTime.now(),
                            order.calculateTotal(),
                            order.calculateTotal(),
                            "Cash"
                    );

                    // Call the displayPaymentInfo method to show the cash payment details
                    String info = cashPayment.displayPaymentInfo();
                    JOptionPane.showMessageDialog(orderFrame, info, "Cash Payment Info", JOptionPane.INFORMATION_MESSAGE);
                }


                JOptionPane.showMessageDialog(orderFrame, "Payment method: " + paymentMethod + "\nTotal: N$" + order.calculateTotal());
                orders.add(order);
                order.setCustomerEmail(customerEmail);
                JOptionPane.showMessageDialog(orderFrame, "Order placed successfully!");
            }
            orderFrame.dispose();
        });

        // Add action buttons to the action panel
        actionPanel.add(viewOrder);
        actionPanel.add(removeItem);
        actionPanel.add(finishOrder);
        actionPanel.add(cancelOrder);

        // Add the menu panel and action panel to the frame
        orderFrame.add(menuPanel, BorderLayout.CENTER);
        orderFrame.add(actionPanel, BorderLayout.SOUTH);

        // Set frame visibility
        orderFrame.setVisible(true);
    }

    private void showStaffDashboardGUI(Staff staff) {
        JFrame frame = new JFrame("Staff Dashboard");
        frame.setSize(500, 400);
        frame.setLayout(null);

        // Welcome Label
        JLabel welcome = new JLabel("Welcome! Staff Dashboard!");
        welcome.setFont(new Font("Arial", Font.BOLD, 18));
        welcome.setBounds(100, 20, 300, 30);

        // manageOrder Buttons
        JButton manageOrders = new JButton("Manage Orders");
        manageOrders.setBounds(175, 100, 150, 25);
        manageOrders.setBackground(new Color(0x2196F3));

        //logout button
        JButton logout = new JButton("Logout");
        logout.setBounds(175, 180, 150, 25);
        logout.setBackground(new Color(0xE53935));

        // Add components to frame
        frame.add(welcome);
        frame.add(manageOrders);
        frame.add(logout);

        // Set default close operation and make frame visible
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        manageOrders.addActionListener(e -> {
        showManageOrdersGUI();
        });

        logout.addActionListener(e -> {
            JOptionPane.showMessageDialog(frame, staff.logout());
            frame.dispose();
        });


    }
    private void showManageOrdersGUI() {
        JFrame manageFrame = new JFrame("Manage Orders");
        manageFrame.setSize(420, 420);
        manageFrame.setLayout(null);

        if (orders.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No orders to manage.");
            return;
        }

        Staff staff = new Staff("pandu", "pax", "pandu@tuck-shop.com", "staff");
        JOptionPane.showMessageDialog(manageFrame, staff.manageOrders(orders));

        manageFrame.getContentPane().removeAll();

        int yOffset = 10;
        for (Order order : new ArrayList<>(orders)) {
            JPanel orderPanel = new JPanel();
            orderPanel.setLayout(null);
            orderPanel.setBounds(10, yOffset, 380, 60);

            // Display order details
            JTextArea orderDetails = new JTextArea("Order ID: " + order.getOrderID() + "\nTotal: N$" + String.format("%.2f", order.calculateTotal()));
            orderDetails.setEditable(false);
            orderDetails.setBounds(10, 10, 280, 40);
            orderPanel.add(orderDetails);

            JButton removeButton = new JButton("Remove");
            removeButton.setBounds(300, 10, 100, 25);
            removeButton.addActionListener(e -> {
                int choice = JOptionPane.showConfirmDialog(manageFrame,
                        "Mark Order ID " + order.getOrderID() + " as Collected and Remove?",
                        "Confirm Collection", JOptionPane.YES_NO_OPTION);

                if (choice == JOptionPane.YES_OPTION) {
                    orders.remove(order);
                    JOptionPane.showMessageDialog(manageFrame, "Order ID " + order.getOrderID() + " marked as collected and removed.");
                    manageFrame.dispose();
                    showManageOrdersGUI();
                }
            });
            orderPanel.add(removeButton);

            manageFrame.add(orderPanel);

            yOffset += 70;
        }

        manageFrame.setVisible(true);
    }



}