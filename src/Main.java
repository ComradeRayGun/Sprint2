//This is the main class for the program test test test test

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
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().createAndShowGUI());
    }
    public void createAndShowGUI() {
        JFrame frame = new JFrame("ACME System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 600);
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        JPanel buttonsPanel = new JPanel(new GridLayout(2, 3, 10, 10));
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
        frame.add(cardPanel);
        frame.setVisible(true);
    }
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
    private JPanel createNewCustomerPage() {
        JPanel newCustomerPanel = new JPanel();
        newCustomerPanel.setLayout(new BoxLayout(newCustomerPanel, BoxLayout.Y_AXIS));

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
    private JPanel createOrderFormPage() {
        JPanel orderFormPanel = new JPanel();

        orderFormPanel.setLayout(new BoxLayout(orderFormPanel, BoxLayout.Y_AXIS));

        JTextField salesRepField = createOrderFields(orderFormPanel, "Sales Representative:");
        JTextField statusField = createOrderFields(orderFormPanel, "Order Status:");
        JTextField deliveryDateField = createOrderFields(orderFormPanel, "Delivery Date:");
        JTextField orderDateField = createOrderFields(orderFormPanel, "Order Date:");
        JTextField customerField = createOrderFields(orderFormPanel, "Customer:");
        JTextField itemOneNameField = createOrderFields(orderFormPanel, "Item 1 name:");
        JTextField itemTwoNameField = createOrderFields(orderFormPanel, "Item 2 name:");
        JTextField itemThreeNameField = createOrderFields(orderFormPanel, "Item 3 name:");
        JTextField itemFourNameField = createOrderFields(orderFormPanel, "Item 4 name:");
        JTextField itemFiveNameField = createOrderFields(orderFormPanel, "Item 5 name:");
        JTextField itemSixNameField = createOrderFields(orderFormPanel, "Item 6 name:");
        JTextField itemSevenNameField = createOrderFields(orderFormPanel, "Item 7 name:");
        JTextField itemEightNameField = createOrderFields(orderFormPanel, "Item 8 name:");
        JTextField itemNineNameField = createOrderFields(orderFormPanel, "Item 9 name:");
        JTextField itemTenNameField = createOrderFields(orderFormPanel, "Item 10 name:");
        JTextField itemOneQuantityField = createOrderFields(orderFormPanel, "Item 1 quantity:");
        JTextField itemTwoQuantityField = createOrderFields(orderFormPanel, "Item 2 quantity:");
        JTextField itemThreeQuantityField = createOrderFields(orderFormPanel, "Item 3 quantity:");
        JTextField itemFourQuantityField = createOrderFields(orderFormPanel, "Item 4 quantity:");
        JTextField itemFiveQuantityField = createOrderFields(orderFormPanel, "Item 5 quantity:");
        JTextField itemSixQuantityField = createOrderFields(orderFormPanel, "Item 6 quantity:");
        JTextField itemSevenQuantityField = createOrderFields(orderFormPanel, "Item 7 quantity:");
        JTextField itemEightQuantityField = createOrderFields(orderFormPanel, "Item 8 quantity:");
        JTextField itemNineQuantityField = createOrderFields(orderFormPanel, "Item 9 quantity:");
        JTextField itemTenQuantityField = createOrderFields(orderFormPanel, "Item 10 quantity:");

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
                    itemThreeQuantityField.getText()/*,
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
                    itemTenQuantityField.getText()*/
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
    private JPanel createInventoryPage() {
        JPanel inventoryPanel = new JPanel(new BorderLayout());
        String[] columnNames = {
                "Product Status", "Product ID", "Item Description", "Supplier Name", "Brand Name",
                "Sub Name", "Product Description", "Container Name", "Size Description",
                "Extended Product Description", "Product Class Description", "On Hand", "Sales Current",
                "Date Last Received", "On Order"
        };
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
    private void writeCustomerToFile(Customer customer) {
        String customerData = customer.getCustomerId() + "," +
                customer.getName() + "," +
                customer.getAddress() + "," +
                customer.hasLoadingDock() + "," +
                customer.getDeliveryHours() + "," +
                customer.getEmail() + ",";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/Customers.txt", true))) {
            writer.write(customerData);
        } catch (IOException ex) {

        }
    }
    private JPanel createOrderHistoryPage() {
        JPanel orderHistoryPanel = new JPanel(new BorderLayout());
        String[] columnNames = {
                "Sales Rep Name", "Status", "Delivery Date", "Order Date", "Customer Name",
                "Item 1 Name", "Item 1 Quantity", "Item 2 Name", "Item 2 Quantity",
                "Item 3 Name", "Item 3 Quantity"
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
    private void writeOrderToFile(String salesRep, String status, String deliveryDate, String orderDate,
                                  String customer, String itemOneName, String itemOneQuantity,
                                  String itemTwoName, String itemTwoQuantity, String itemThreeName,
                                  String itemThreeQuantity) {
        String orderData = String.join(",",
                salesRep, status, deliveryDate, orderDate, customer,
                itemOneName, itemOneQuantity, itemTwoName, itemTwoQuantity,
                itemThreeName, itemThreeQuantity
        );
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/Orders.txt", true))) {
            writer.write(orderData);
            writer.newLine();
        } catch (IOException ex) {

        }
    }
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
    //Login Page Creation
  /*  private LoginPage() {
        JFrame loginFrame = new JFrame();
        JPanel loginPanel = new JPanel();

        loginFrame.setSize(350,200);
        loginFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        loginFrame.setVisible(true);
        loginFrame.add(loginPanel);

        loginPanel.setLayout(null);

        JLabel userLabel = new JLabel("User");
        userLabel.setBounds(10, 20, 80, 25);
        loginPanel.add(userLabel);

        JTextField userText = new JTextField();
        userText.setBounds(100, 20, 165, 25);
        loginPanel.add(userText);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(10, 50, 80, 25);
        loginPanel.add(passwordLabel);

        JPasswordField passwordTet = new JPasswordField();
        passwordTet.setBounds(100, 50, 165, 25);
        loginPanel.add(passwordTet);

        JButton signinButton = new JButton();
        signinButton.setBounds(10, 80, 80, 25);

        loginFrame.setVisible(true);
    } */
}

