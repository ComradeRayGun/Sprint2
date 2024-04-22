import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    private JFrame frame;
    private CardLayout cardLayout;
    private JPanel cardPanel;
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new Main().createAndShowGUI());
    }
    public void createAndShowGUI() {
        frame = new JFrame("ACME System");
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
        buttonsPanel.add(createButtonPanel("Customer Management", "Edit, Remove, & View Customers", "CustomerManagementPage"));
        buttonsPanel.add(createButtonPanel("New Customer", "Input Customer Information", "NewCustomerPage"));
        buttonsPanel.add(createButtonPanel("Order Form", "Create Order", "OrderFormPage"));
        buttonsPanel.add(createButtonPanel("Inventory", "Check, Manage & Remove Items From Inventory", "InventoryPage"));
        buttonsPanel.add(createButtonPanel("Order History", "View order history details", "OrderHistoryPage"));
        cardPanel.add(createCustomerManagementPage(), "CustomerManagementPage");
        frame.add(cardPanel);
        frame.setVisible(true);
    }
    private JPanel createButtonPanel(String title, String description, String pageName) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JButton button = new JButton("Go Now");
        button.addActionListener(e -> cardLayout.show(cardPanel, pageName));
        JLabel titleLabel = new JLabel(title, JLabel.CENTER);
        JLabel descriptionLabel = new JLabel(description, JLabel.CENTER);
        panel.add(titleLabel, BorderLayout.NORTH);
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
        JTextField customerIdField = new JTextField(20);
        JTextField nameField = new JTextField(20);
        JTextField addressField = new JTextField(20);
        JCheckBox hasLoadingDockCheck = new JCheckBox();
        JTextField deliveryHoursField = new JTextField(20);
        JTextField emailField = new JTextField(20);
        JPasswordField passwordField = new JPasswordField(20);

        newCustomerPanel.add(new JLabel("Customer ID:"));
        newCustomerPanel.add(customerIdField);
        newCustomerPanel.add(new JLabel("Name:"));
        newCustomerPanel.add(nameField);
        newCustomerPanel.add(new JLabel("Address:"));
        newCustomerPanel.add(addressField);
        newCustomerPanel.add(new JLabel("Has Loading Dock:"));
        newCustomerPanel.add(hasLoadingDockCheck);
        newCustomerPanel.add(new JLabel("Delivery Hours:"));
        newCustomerPanel.add(deliveryHoursField);
        newCustomerPanel.add(new JLabel("Email:"));
        newCustomerPanel.add(emailField);
        newCustomerPanel.add(new JLabel("Password:"));
        newCustomerPanel.add(passwordField);

        JButton submitButton = new JButton("Add Customer");
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
        JTextField salesRepField = new JTextField(20);
        JTextField statusField = new JTextField(20);
        JTextField deliveryDateField = new JTextField(20);
        JTextField orderDateField = new JTextField(20);
        JTextField customerField = new JTextField(20);
        JTextField itemOneNameField = new JTextField(20);
        JTextField itemTwoNameField = new JTextField(20);
        JTextField itemThreeNameField = new JTextField(20);
        JTextField itemOneQuantityField = new JTextField(20);
        JTextField itemTwoQuantityField = new JTextField(20);
        JTextField itemThreeQuantityField = new JTextField(20);
        orderFormPanel.add(new JLabel("Sales Representative:"));
        orderFormPanel.add(salesRepField);
        orderFormPanel.add(new JLabel("Order Status:"));
        orderFormPanel.add(statusField);
        orderFormPanel.add(new JLabel("Delivery Date:"));
        orderFormPanel.add(deliveryDateField);
        orderFormPanel.add(new JLabel("Order Date:"));
        orderFormPanel.add(orderDateField);
        orderFormPanel.add(new JLabel("Customer:"));
        orderFormPanel.add(customerField);
        orderFormPanel.add(new JLabel("Item 1 Name:"));
        orderFormPanel.add(itemOneNameField);
        orderFormPanel.add(new JLabel("Item 2 Name:"));
        orderFormPanel.add(itemTwoNameField);
        orderFormPanel.add(new JLabel("Item 3 Name:"));
        orderFormPanel.add(itemThreeNameField);
        orderFormPanel.add(new JLabel("Item 1 Quantity:"));
        orderFormPanel.add(itemOneQuantityField);
        orderFormPanel.add(new JLabel("Item 2 Quantity:"));
        orderFormPanel.add(itemTwoQuantityField);
        orderFormPanel.add(new JLabel("Item 3 Quantity:"));
        orderFormPanel.add(itemThreeQuantityField);
        JButton submitButton = new JButton("Submit Order");
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
                    itemThreeQuantityField.getText()
            );
            JOptionPane.showMessageDialog(orderFormPanel, "Order Submitted!");
        });
        JButton backButton = new JButton("Back");
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
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("UPDATE WITH FILE LOCATION", true))) {
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
        try (BufferedReader reader = new BufferedReader(new FileReader("UPDATE WITH FILE LOCATION"))) {
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
        backButton.addActionListener(e -> cardLayout.show(cardPanel, "ButtonsPanel"));
        orderHistoryPanel.add(backButton, BorderLayout.SOUTH);
        return orderHistoryPanel;
    }
    private JPanel createCustomerManagementPage() {
        JPanel customerManagementPanel = new JPanel(new BorderLayout());
        String[] columnNames = {
                "Customer ID", "Customer Name", "Address", "Has Loading Dock", "Delivery Hours", "Email"
        };

        DefaultTableModel model = new DefaultTableModel(columnNames, 0); // column names with zero rows initially
        try (BufferedReader reader = new BufferedReader(new FileReader("UPDATE WITH FILE LOCATION"))) {
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
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("UPDATE WITH FILE LOCATION", true))) {
            writer.write(orderData);
            writer.newLine();
        } catch (IOException ex) {

        }
    }
    private void updateCustomerFile(DefaultTableModel model) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("UPDATE WITH FILE LOCATION"))) {
            for (int i = 0; i < model.getRowCount(); i++) {
                Object[] row = new Object[model.getColumnCount()];
                for (int j = 0; j < model.getColumnCount(); j++) {
                    row[j] = model.getValueAt(i, j);
                }
                writer.write(String.join(",", Arrays.asList(row).stream().map(Object::toString).toArray(String[]::new)));
                writer.newLine();
            }
        } catch (IOException ex) {

        }
    }
}