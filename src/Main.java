import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
public class Main {
    //Theme Colors
    Color buttonColor = new Color(156,4,4);
    Color backgroundColor = new Color(216,216,217);
    private CardLayout cardLayout;
    private JPanel cardPanel;
    // Main method
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().createAndShowGUI());
    }
    // Create and show GUI
    public void createAndShowGUI() {
        JFrame frame = new JFrame("ACME System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 600);
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        JPanel buttonsPanel = new JPanel(new GridLayout(2, 3, 10, 10));
        // Add panels
        cardPanel.add(createLoginPage(), "LoginPage"); //login testing
        cardPanel.add(buttonsPanel, "ButtonsPanel");
        cardPanel.add(createNewPage("NewCustomerPage"), "NewCustomerPage");
        cardPanel.add(createNewPage("OrderFormPage"), "OrderFormPage");
        cardPanel.add(createNewPage("InventoryPage"), "InventoryPage");
        cardPanel.add(createNewPage("CustomerManagementPage"), "CustomerManagementPage");
        cardPanel.add(createOrderFormPage(), "OrderFormPage");
        cardPanel.add(createInventoryPage(), "InventoryPage");
        cardPanel.add(createOrderFormPage(), "OrderFormPage");
        cardPanel.add(createOrderHistoryPage(), "OrderHistoryPage");
        buttonsPanel.add(createButtonPanel("New Customer", "Input Customer Information", "NewCustomerPage", buttonColor, "src/gui/newCustomer.png"));
        buttonsPanel.add(createButtonPanel("Order Form", "Create Order", "OrderFormPage", buttonColor, "src/gui/orderForm.png"));
        buttonsPanel.add(createButtonPanel("Inventory", "Check, Manage & Remove Items From Inventory", "InventoryPage", buttonColor, "src/gui/inventory.png"));
        buttonsPanel.add(createButtonPanel("Customer Management", "Edit, Remove, & View Customers", "CustomerManagementPage", buttonColor, "src/gui/customerManagement.png"));
        buttonsPanel.add(createButtonPanel("Order History", "View order history details", "OrderHistoryPage", buttonColor, "src/gui/orderHistory.png"));
        cardPanel.add(createCustomerManagementPage(), "CustomerManagementPage");
        cardLayout.show(cardPanel, "Login Page"); //login testing
        frame.add(cardPanel);
        frame.setVisible(true);
    }
    //login page
    private JPanel createLoginPage() {
        JPanel loginPanel = new JPanel();
        loginPanel.setLayout(new BoxLayout(loginPanel, BoxLayout.Y_AXIS));

        //Image
        ImageIcon imageIcon = new ImageIcon("src/gui/logo.png");
        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        imageLabel.setAlignmentY(Component.CENTER_ALIGNMENT);

        loginPanel.add(imageLabel);

        JTextField usernameField = createOrderFields(loginPanel, "Username:");
        JPasswordField passwordField = createPasswordFields(loginPanel);

        JButton loginButton = new JButton("Login");
        loginButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        loginButton.setAlignmentY(Component.CENTER_ALIGNMENT);

        loginButton.setBackground(buttonColor);
        loginButton.setForeground(Color.WHITE);
        loginButton.addActionListener(e -> {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            //check the entered credentials
            if (username.equals("admin") && password.equals("password")) {
                //if credentials match navigate to the main page
                cardLayout.show(cardPanel, "ButtonsPanel");
            } else {
                JOptionPane.showMessageDialog(loginPanel, "Invalid username or password!");
            }
        });

        loginPanel.add(loginButton);
        return loginPanel;
    }

    // Creates the different panels
    private JPanel createButtonPanel(String title, String description, String pageName, Color buttonColor, String imagePath) {
        //Container Panel Settings
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        panel.setBackground(backgroundColor);

        //Image
        ImageIcon imageIcon = new ImageIcon(imagePath);
        JLabel imageLabel = new JLabel(imageIcon);
        imageLabel.setPreferredSize(panel.getSize());
        imageLabel.setHorizontalAlignment(SwingConstants.CENTER);
        imageLabel.setVerticalAlignment(SwingConstants.CENTER);

        //Menu Button Settings
        JButton button = new JButton("Go Now");
        button.addActionListener(e -> cardLayout.show(cardPanel, pageName));
        button.setBackground(buttonColor);
        button.setForeground(Color.WHITE);

        //Button Title Settings
        JLabel titleLabel = new JLabel(title, JLabel.LEFT);
        titleLabel.setFont(titleLabel.getFont().deriveFont(Font.BOLD, 14f));

        //Description Settings
        JLabel descriptionLabel = new JLabel(description, JLabel.LEFT);

        panel.add(imageLabel);
        panel.add(titleLabel, BorderLayout.WEST);
        panel.add(descriptionLabel, BorderLayout.CENTER);
        panel.add(button, BorderLayout.SOUTH);
        return panel;
    }
    // Creats new page
    private JPanel createNewPage(String pageName) {
        if ("NewCustomerPage".equals(pageName)) {
            return createNewCustomerPage();
        }
        JPanel page = new JPanel();
        page.add(new JLabel("Content for " + pageName));
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "ButtonsPanel"));
        page.add(backButton);
        return page;
    }
    // Creates page to create new customer
    private JPanel createNewCustomerPage() {
        JPanel newCustomerPanel = new JPanel();
        newCustomerPanel.setLayout(new BoxLayout(newCustomerPanel, BoxLayout.Y_AXIS));
        //Fields
        JTextField customerIdField = createOrderFields(newCustomerPanel, "Customer ID:");
        JTextField nameField = createOrderFields(newCustomerPanel, "Name:");
        JTextField addressField = createOrderFields(newCustomerPanel, "Address:");

        JTextField emailField = createOrderFields(newCustomerPanel, "Email:");
        JPasswordField passwordField = createPasswordFields(newCustomerPanel);

        JCheckBox hasLoadingDockCheck = createCheckFields(newCustomerPanel, "Has Loading Dock: ");
        JTextField deliveryHoursField = createOrderFields(newCustomerPanel, "Delivery Hours:");

        JButton submitButton = new JButton("Add Customer");
        submitButton.setBackground(buttonColor);
        submitButton.setForeground(Color.WHITE);
        submitButton.addActionListener(e -> {
            try {
                String customerId = customerIdField.getText();
                String name = nameField.getText();
                String address = addressField.getText();
                boolean hasLoadingDock = hasLoadingDockCheck.isSelected();
                String deliveryHours = deliveryHoursField.getText();
                String email = emailField.getText();
                String password = new String(passwordField.getPassword());
                Customer newCustomer = new Customer(customerId, name, address, hasLoadingDock,
                        deliveryHours, email, password);
                writeCustomerToFile(newCustomer);
                JOptionPane.showMessageDialog(newCustomerPanel, "New Customer Added: " + name);
            } catch (Exception e1) {

            }
        });

        JButton backButton = new JButton("Back");
        backButton.setBackground(buttonColor);
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "ButtonsPanel"));
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(submitButton);
        bottomPanel.add(backButton);
        newCustomerPanel.add(bottomPanel);
        return newCustomerPanel;
    }
    // Creates new order page
    private JPanel createOrderFormPage() {
        JPanel orderFormPanel = new JPanel();

        orderFormPanel.setLayout(new BoxLayout(orderFormPanel, BoxLayout.Y_AXIS));
        //Fields
        JTextField salesRepField = createOrderFields(orderFormPanel, "Sales Representative:");
        JTextField statusField = createOrderFields(orderFormPanel, "Order Status:");
        JTextField deliveryDateField = createOrderFields(orderFormPanel, "Delivery Date:");
        JTextField orderDateField = createOrderFields(orderFormPanel, "Order Date:");
        JTextField customerField = createOrderFields(orderFormPanel, "Customer:");

        JTextField[] item1 = createItemOrderPanel(orderFormPanel);
        JTextField itemOneNameField = item1[0];
        JTextField itemOneQuantityField = item1[1];

        JTextField[] item2 = createItemOrderPanel(orderFormPanel);
        JTextField itemTwoNameField = item1[0];
        JTextField itemTwoQuantityField = item1[1];

        JTextField[] item3 = createItemOrderPanel(orderFormPanel);
        JTextField itemThreeNameField = item1[0];
        JTextField itemThreeQuantityField = item1[1];

        JTextField[] item4 = createItemOrderPanel(orderFormPanel);
        JTextField itemFourNameField = item1[0];
        JTextField itemFourQuantityField = item1[1];

        JTextField[] item5 = createItemOrderPanel(orderFormPanel);
        JTextField itemFiveNameField = item1[0];
        JTextField itemFiveQuantityField = item1[1];

        JTextField[] item6 = createItemOrderPanel(orderFormPanel);
        JTextField itemSixNameField = item1[0];
        JTextField itemSixQuantityField = item1[1];

        JTextField[] item7 = createItemOrderPanel(orderFormPanel);
        JTextField itemSevenNameField = item1[0];
        JTextField itemSevenQuantityField = item1[1];

        JTextField[] item8 = createItemOrderPanel(orderFormPanel);
        JTextField itemEightNameField = item1[0];
        JTextField itemEightQuantityField = item1[1];

        JTextField[] item9 = createItemOrderPanel(orderFormPanel);
        JTextField itemNineNameField = item1[0];
        JTextField itemNineQuantityField = item1[1];

        JTextField[] item10 = createItemOrderPanel(orderFormPanel);
        JTextField itemTenNameField = item1[0];
        JTextField itemTenQuantityField = item1[1];

        //Submit button settings
        JButton submitButton = new JButton("Submit Order");
        submitButton.setBackground(buttonColor);
        submitButton.setForeground(Color.WHITE);

        //Submit button writing
        submitButton.addActionListener(e -> {
            writeOrderToFile(
                    salesRepField.getText(),
                    statusField.getText(),
                    deliveryDateField.getText(),
                    orderDateField.getText(),
                    customerField.getText(),
                    itemOneNameField.getText(),
                    itemOneQuantityField.getText(),
                    itemTwoNameField.getText(),
                    itemTwoQuantityField.getText(),
                    itemThreeNameField.getText(),
                    itemThreeQuantityField.getText(),
                    itemFourNameField.getText(),
                    itemFourQuantityField.getText(),
                    itemFiveNameField.getText(),
                    itemFiveQuantityField.getText(),
                    itemSixNameField.getText(),
                    itemSixQuantityField.getText(),
                    itemSevenNameField.getText(),
                    itemSevenQuantityField.getText(),
                    itemEightNameField.getText(),
                    itemEightQuantityField.getText(),
                    itemNineNameField.getText(),
                    itemNineQuantityField.getText(),
                    itemTenNameField.getText(),
                    itemTenQuantityField.getText()
            );
            JOptionPane.showMessageDialog(orderFormPanel, "Order Submitted!");
        });

        //Back button settings
        JButton backButton = new JButton("Back");
        backButton.setBackground(buttonColor);
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "ButtonsPanel")); // Assuming "ButtonsPanel" is your main page

        JPanel bottomPanel = new JPanel();
        bottomPanel.add(submitButton);
        bottomPanel.add(backButton);
        orderFormPanel.add(bottomPanel, BorderLayout.SOUTH);
        return orderFormPanel;
    }
    // Creates inventory page
    private JPanel createInventoryPage() {
        JPanel inventoryPanel = new JPanel(new BorderLayout());
        String[] columnNames = {
                "Product Status", "Product ID", "Item Description", "Supplier Name", "Brand Name",
                "Sub Name", "Product Description", "Container Name", "Size Description",
                "Extended Product Description", "Product Class Description", "On Hand", "Sales Current",
                "Date Last Received", "On Order"
        };
        // Reads data from Inventory.txt
        ArrayList<String[]> data = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("src/Inventory.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                data.add(line.split(",(?=(?:[^\"]*\"[^\"]*\")*[^\"]*$)", -1));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        String[][] dataArray = new String[data.size()][];
        for (int i = 0; i < data.size(); i++) {
            dataArray[i] = data.get(i);
        }
        JTable table = new JTable(dataArray, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        inventoryPanel.add(scrollPane, BorderLayout.CENTER);

        JButton backButton = new JButton("Back");
        backButton.setBackground(buttonColor);
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "ButtonsPanel")); // Assuming "ButtonsPanel" is the main page
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(backButton);
        inventoryPanel.add(bottomPanel, BorderLayout.SOUTH);

        return inventoryPanel;
    }
    // When a new customer is created it is written to customers.txt
    private void writeCustomerToFile(Customer customer) {
        String customerData = customer.getCustomerId() + "," +
                customer.getName() + "," +
                customer.getAddress() + "," +
                customer.hasLoadingDock() + "," +
                customer.getDeliveryHours() + "," +
                customer.getEmail() + "\n";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/Customers.txt", true))) {
            writer.write(customerData);
        } catch (IOException ex) {

        }
    }
    // Creates order history page
    private JPanel createOrderHistoryPage() {
        JPanel orderHistoryPanel = new JPanel(new BorderLayout());
        String[] columnNames = {
                "Sales Rep Name", "Status", "Delivery Date", "Order Date", "Customer Name",
                "Item 1 Name", "Item 1 Quantity", "Item 2 Name", "Item 2 Quantity",
                "Item 3 Name", "Item 3 Quantity", "Item 4 Name", "Item 4 Quantity",
                "Item 5 Name", "Item 5 Quantity", "Item 6 Name", "Item 6 Quantity",
                "Item 7 Name", "Item 7 Quantity", "Item 8 Name", "Item 8 Quantity",
                "Item 9 Name", "Item 9 Quantity", "Item 10 Name", "Item 10 Quantity"
        };
        ArrayList<String[]> data = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("src/Orders.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                data.add(line.split(",", -1));
            }
        } catch (IOException ex) {
        }
        String[][] dataArray = new String[data.size()][];
        for (int i = 0; i < data.size(); i++) {
            dataArray[i] = data.get(i);
        }
        JTable table = new JTable(dataArray, columnNames);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        orderHistoryPanel.add(scrollPane, BorderLayout.CENTER);

        JButton backButton = new JButton("Back");
        backButton.setBackground(buttonColor);
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "ButtonsPanel")); // Assuming "ButtonsPanel" is the main page
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(backButton);
        orderHistoryPanel.add(bottomPanel, BorderLayout.SOUTH);

        return orderHistoryPanel;
    }
    // Creates customer management page
    private JPanel createCustomerManagementPage() {
        JPanel customerManagementPanel = new JPanel(new BorderLayout());
        String[] columnNames = {
                "Customer ID", "Customer Name", "Address", "Has Loading Dock", "Delivery Hours", "Email"
        };

        DefaultTableModel model = new DefaultTableModel(columnNames, 0); // column names with zero rows initially
        try (BufferedReader reader = new BufferedReader(new FileReader("src/Customers.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                model.addRow(line.split(",", -1));
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        JTable table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        customerManagementPanel.add(scrollPane, BorderLayout.CENTER);
        JButton removeButton = new JButton("Remove Customer");
        removeButton.setBackground(buttonColor);
        removeButton.setForeground(Color.WHITE);
        removeButton.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row >= 0) {
                model.removeRow(row);
                updateCustomerFile(model);
                JOptionPane.showMessageDialog(customerManagementPanel, "Customer removed successfully.");
            } else {
                JOptionPane.showMessageDialog(customerManagementPanel, "Please select a customer to remove.");
            }
        });
        JButton backButton = new JButton("Back");
        backButton.setBackground(buttonColor);
        backButton.setForeground(Color.WHITE);
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "ButtonsPanel")); // Navigate back to the main panel
        JPanel bottomPanel = new JPanel();
        bottomPanel.add(removeButton);
        bottomPanel.add(backButton);
        customerManagementPanel.add(bottomPanel, BorderLayout.SOUTH);
        return customerManagementPanel;
    }
    // When a new order is created it is written to orders.txt
    private void writeOrderToFile(String salesRep, String status, String deliveryDate, String orderDate,
                                  String customer, String itemOneName, String itemOneQuantity,
                                  String itemTwoName, String itemTwoQuantity, String itemThreeName,
                                  String itemThreeQuantity, String itemFourName, String itemFourQuantity, String itemFiveName,
                                  String itemFiveQuantity, String itemSixName, String itemSixQuantity, String itemSevenName,
                                  String itemSevenQuantity, String itemEightName, String itemEightQuantity,
                                  String itemNineName, String itemNineQuantity, String itemTenName, String itemTenQuantity) {
        String orderData = String.join(",",
                salesRep, status, deliveryDate, orderDate, customer,
                itemOneName, itemOneQuantity, itemTwoName, itemTwoQuantity,
                itemThreeName, itemThreeQuantity, itemFourName, itemFourQuantity, itemFiveName,
                itemFiveQuantity, itemSixName, itemSixQuantity, itemSevenName,
                itemSevenQuantity, itemEightName, itemEightQuantity,
                itemNineName, itemNineQuantity, itemTenName, itemTenQuantity
        );
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/Orders.txt", true))) {
            writer.write(orderData);
            writer.newLine();
        } catch (IOException ex) {

        }
    }
    // Updates Customers.txt
    private void updateCustomerFile(DefaultTableModel model) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/Customers.txt", true))) {
            for (int i = 0; i < model.getRowCount(); i++) {
                Object[] row = new Object[model.getColumnCount()];
                for (int j = 0; j < model.getColumnCount(); j++) {
                    row[j] = model.getValueAt(i, j);
                }
                writer.write(String.join(",", Arrays.stream(row).map(Object::toString).toArray(String[]::new)));
                writer.newLine();
            }
        } catch (IOException ex) {

        }
    }

    //Stylized fields
    private JTextField createOrderFields(JPanel master, String label) {
        JPanel Panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        Panel.setBackground(backgroundColor);
        JLabel Label = new JLabel(label);
        Label.setPreferredSize(new Dimension(150, Label.getPreferredSize().height));
        JTextField Field = new JTextField(25);
        Panel.add(Label);
        Panel.add(Field);
        master.add(Panel);
        return Field;
    }
    // Create item order panel
    private JTextField[] createItemOrderPanel(JPanel master) {
        JPanel Panel = new JPanel(new FlowLayout(FlowLayout.CENTER));

        JTextField itemName = createOrderFields(Panel, "Item Name");

        Panel.add(itemName);

        JTextField itemQuantity = createOrderFields(Panel, "Quantity");

        Panel.add(itemQuantity);

        master.add(Panel);

        JTextField[] itemArr = new JTextField[2];
        itemArr[0] = itemName;
        itemArr[1] = itemQuantity;

        return itemArr;
    }
    // Check field
    private JCheckBox createCheckFields(JPanel master, String label) {
        JPanel Panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        Panel.setBackground(backgroundColor);
        JLabel Label = new JLabel(label);
        Label.setPreferredSize(new Dimension(150, Label.getPreferredSize().height));
        JCheckBox Field = new JCheckBox();
        Panel.add(Label);
        Panel.add(Field);
        master.add(Panel);
        return Field;
    }
    // Creates password field
    private JPasswordField createPasswordFields(JPanel master){
        JPanel Panel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        Panel.setBackground(backgroundColor);
        JLabel Label = new JLabel("Password:");
        Label.setPreferredSize(new Dimension(150, Label.getPreferredSize().height));
        JPasswordField Field = new JPasswordField(25);
        Panel.add(Label);
        Panel.add(Field);
        master.add(Panel);
        return Field;
    }
}

