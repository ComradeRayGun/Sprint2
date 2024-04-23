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
    private String itemFourName;
    private String itemFiveName;
    private String itemSixName;
    private String itemSevenName;
    private String itemEightName;
    private String itemNineName;
    private String itemTenName;
    private String itemOneQuantity;
    private String itemTwoQuantity;
    private String itemThreeQuantity;
    private String itemFourQuantity;
    private String itemFiveQuantity;
    private String itemSixQuantity;
    private String itemSevenQuantity;
    private String itemEightQuantity;
    private String itemNineQuantity;
    private String itemTenQuantity;
    public Order(HashMap<String, Integer> items, String salesRep, String status,
                 String deliveryDate, String orderDate, String customer, String itemOneName, String itemTwoName,
                 String itemThreeName, String itemFourName, String itemFiveName, String itemSixName, String itemSevenName,
                 String itemEightName, String itemNineName, String itemTenName, String itemOneQuantity, String itemTwoQuantity,
                 String itemThreeQuantity, String itemFourQuantity, String itemFiveQuantity, String itemSixQuantity,
                 String itemSevenQuantity, String itemEightQuantity, String itemNineQuantity, String itemTenQuantity) {
        this.salesRep = salesRep;
        this.status = status;
        this.deliveryDate = deliveryDate;
        this.orderDate = orderDate;
        this.customer = customer;
        this.itemOneName = itemOneName;
        this.itemTwoName = itemTwoName;
        this.itemThreeName = itemThreeName;
        this.itemFourName = itemFourName;
        this.itemFiveName = itemFiveName;
        this.itemSixName = itemSixName;
        this.itemSevenName = itemSevenName;
        this.itemEightName = itemEightName;
        this.itemNineName = itemNineName;
        this.itemTenName = itemTenName;

        this.itemOneQuantity = itemOneQuantity;
        this.itemTwoQuantity = itemTwoQuantity;
        this.itemThreeQuantity = itemThreeQuantity;
        this.itemFourQuantity = itemFourQuantity;
        this.itemFiveQuantity = itemFiveQuantity;
        this.itemSixQuantity = itemSixQuantity;
        this.itemSevenQuantity = itemSevenQuantity;
        this.itemEightQuantity = itemEightQuantity;
        this.itemNineQuantity = itemNineQuantity;
        this.itemTenQuantity = itemTenQuantity;
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
