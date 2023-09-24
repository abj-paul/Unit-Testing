public class Product {
    private String name;
    private double price;
    private int quantity;
    private String vendor;

    public Product(String name, double price, int quantity, String vendor) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
        this.vendor = vendor;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getVendor() {
        return vendor;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void adjustPrice(double percentageIncrease) {
        this.price *= (1 + percentageIncrease / 100);
    }
}
