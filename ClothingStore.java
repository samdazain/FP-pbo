import java.util.ArrayList;
import java.util.List;
public class ClothingStore {
    private List<Product> products;
    private List<Order> orders;
    public ClothingStore() {
        products = new ArrayList<>();
        orders = new ArrayList<>();
    }
    public void addProduct(Product product) {
        products.add(product);
    }
    public List<Product> getProducts() {
        return products;
    }
    public List<Order> getOrders() {
        return orders;
    }
    public void placeOrder(Order order) {
        orders.add(order);
    }
    public double getTotalRevenue() {
        double total = 0;
        for (Order order : orders) {
            total += order.getTotalPrice();
        }
        return total;
    }
}