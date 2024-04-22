public class Customer {
    private String customerId;
    private String name;
    private String address;
    private boolean hasLoadingDock;
    private String deliveryHours;
    private String email;
    private String password;

    public Customer(String customerId, String name, String address, boolean hasLoadingDock,
                    String deliveryHours, String email, String password) {
        this.customerId = customerId;
        this.name = name;
        this.address = address;
        this.hasLoadingDock = hasLoadingDock;
        this.deliveryHours = deliveryHours;
        this.email = email;
        this.password = password;
    }
    public String getCustomerId() {
        return customerId;
    }
    public String getName() {
        return name;
    }
    public String getAddress() {
        return address;
    }
    public boolean hasLoadingDock() {
        return hasLoadingDock;
    }
    public String getDeliveryHours() {
        return deliveryHours;
    }
    public String getEmail() {
        return email;
    }
}
