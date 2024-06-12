public class Product {
    private String name;
    private double price;
    private String size;
    private String color;
    private String material;
    public Product(String name, double price, String size, String color, String
            material) {
        this.name = name;
        this.price = price;
        this.size = size;
        this.color = color;
        this.material = material;
    }
    public String getName() {
        return name;
    }
    public double getPrice() {
        return price;
    }
    public String getSize() {
        return size;
    }
    public String getColor() {
        return color;
    }
    public String getMaterial() {
        return material;
    }
    @Override
    public String toString() {
        return name + " - $" + price + " (Size: " + size + ", Warna: " + color +
                ", Material: " + material + ")";
    }
}