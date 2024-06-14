import java.util.ArrayList;
import java.util.List;

public class Order {
    private List<Product> items;

    public Order() {
        items = new ArrayList<>();
    }

    public void addItem(Product item) {
        items.add(item);
    }

    public double getTotalPrice() {
        double total = 0;
        for (Product item : items) {
            total += item.getPrice();
        }
        return total;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (Product item : items) {
            sb.append(item.toString()).append("\n");
        }
        sb.append("Total: $").append(getTotalPrice());
        return sb.toString();
    }
}
