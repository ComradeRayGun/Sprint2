import java.util.HashMap;
public class Order {
    private String salesRep;
    private String status;
    private String deliveryDate;
    private String orderDate;
    private String customer;
    private String itemOneName;
    private String itemTwoName;
    private String itemThreeName;
    private String itemOneQuantity;
    private String itemTwoQuantity;
    private String itemThreeQuantity;
    public Order(HashMap<String, Integer> items, String salesRep, String status,
                 String deliveryDate, String orderDate, String customer, String itemOneName, String itemTwoName,
                 String itemThreeName, String itemOneQuantity, String itemTwoQuantity, String itemThreeQuantity) {
        this.salesRep = salesRep;
        this.status = status;
        this.deliveryDate = deliveryDate;
        this.orderDate = orderDate;
        this.customer = customer;
        this.itemOneName = itemOneName;
        this.itemTwoName = itemTwoName;
        this.itemThreeName = itemThreeName;
        this.itemOneQuantity = itemOneQuantity;
        this.itemTwoQuantity = itemTwoQuantity;
        this.itemThreeQuantity = itemThreeQuantity;
    }
    public String getCustomer() {
        return customer;
    }
    public void setCustomer(String customer) {
        this.customer = customer;
    }
    public String getOrderDate() {
        return orderDate;
    }
    public void setOrderDate(String orderDate) {
        this.orderDate = orderDate;
    }
    public String getDeliveryDate() {
        return deliveryDate;
    }
    public String getStatus() {
        return status;
    }
    public String getSalesRep() {
        return salesRep;
    }
}
