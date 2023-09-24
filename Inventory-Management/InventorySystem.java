import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InventorySystem {
    private List<Product> inventory;
    private Map<String, Double> vendorDiscounts;

    public InventorySystem() {
        inventory = new ArrayList<>();
        vendorDiscounts = new HashMap<>();
    }

    public void addProduct(Product product) {
        inventory.add(product);
    }

    public double calculateTotalValue() {
        double totalValue = 0;
        for (Product product : inventory) {
            totalValue += product.getPrice() * product.getQuantity();
        }
        return totalValue;
    }

     public void removeProduct(String productName) {
        for (int i = 0; i < inventory.size(); i++) {
            if (inventory.get(i).getName().equalsIgnoreCase(productName)) {
                inventory.remove(i);
                break;
            }
        }
    }

    public void updateProductQuantity(String productName, int newQuantity) {
        for (Product product : inventory) {
            if (product.getName().equalsIgnoreCase(productName)) {
                product.setQuantity(newQuantity);
                break;
            }
        }
    }

    public List<Product> getLowStockProducts(int threshold) {
        List<Product> lowStockProducts = new ArrayList<>();
        for (Product product : inventory) {
            if (product.getQuantity() < threshold) {
                lowStockProducts.add(product);
            }
        }
        return lowStockProducts;
    }

    public Product findProductByName(String productName) {
        for (Product product : inventory) {
            if (product.getName().equalsIgnoreCase(productName)) {
                return product;
            }
        }
        return null;
    }

    public void adjustAllPrices(double percentageIncrease) {
        for (Product product : inventory) {
            product.adjustPrice(percentageIncrease);
        }
    }

    public void applyDiscountByVendor(String vendor, double discountPercentage, int minQuantityForDiscount) {
        if (vendorDiscounts.containsKey(vendor)) {
            // A discount for this vendor already exists, so we'll update it.
            double existingDiscount = vendorDiscounts.get(vendor);
            vendorDiscounts.put(vendor, Math.min(existingDiscount, discountPercentage));
        } else {
            vendorDiscounts.put(vendor, discountPercentage);
        }
    
        List<Product> discountedProducts = new ArrayList<>();
        for (Product product : inventory) {
            if (product.getVendor().equalsIgnoreCase(vendor) && product.getQuantity() >= minQuantityForDiscount) {
                double discountedPrice = product.getPrice() * (1 - discountPercentage / 100);
                product.adjustPrice(discountedPrice - product.getPrice());
                discountedProducts.add(product);
            }
        }
    
        if (!discountedProducts.isEmpty()) {
            System.out.println("Discount applied to the following products from vendor " + vendor + ":");
            for (Product discountedProduct : discountedProducts) {
                System.out.println("Product: " + discountedProduct.getName() +
                        ", Original Price: $" + discountedProduct.getPrice() +
                        ", Discounted Price: $" + (discountedProduct.getPrice() - discountedProduct.getPrice()));
            }
        } else {
            System.out.println("No products from vendor " + vendor + " meet the minimum quantity requirement for the discount.");
        }
    }

    public List<Product> getProductsByPriceRange(double minPrice, double maxPrice) {
        List<Product> productsInRange = new ArrayList<>();
        for (Product product : inventory) {
            double price = product.getPrice();
            if (price >= minPrice && price <= maxPrice) {
                productsInRange.add(product);
            }
        }
        return productsInRange;
    }

    public List<Product> getProductsByAuthor(String author) {
        List<Product> productsByAuthor = new ArrayList<>();
        for (Product product : inventory) {
            if (product.getName().equalsIgnoreCase(author)) {
                productsByAuthor.add(product);
            }
        }
        return productsByAuthor;
    }

    public int countProducts() {
        return inventory.size();
    }

    public void clearInventory() {
        inventory.clear();
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(inventory);
    }

    public static void main(String[] args) {
        InventorySystem inventorySystem = new InventorySystem();
    
        Product product1 = new Product("Widget", 10.0, 50, "Vendor1");
        Product product2 = new Product("Gadget", 20.0, 30, "Vendor2");
        Product product3 = new Product("Doodad", 15.0, 20, "Vendor1");
    
        inventorySystem.addProduct(product1);
        inventorySystem.addProduct(product2);
        inventorySystem.addProduct(product3);
    
        inventorySystem.removeProduct("Gadget");
    
        double totalValue = inventorySystem.calculateTotalValue();
    
        inventorySystem.updateProductQuantity("Widget", 60);
    
        List<Product> lowStockProducts = inventorySystem.getLowStockProducts(25);
    
        Product foundProduct = inventorySystem.findProductByName("Doodad");
    
        inventorySystem.adjustAllPrices(5);
    
        inventorySystem.applyDiscountByVendor("Vendor1", 10.0, 5);
    
        List<Product> productsInRange = inventorySystem.getProductsByPriceRange(10.0, 15.0);
    
        List<Product> productsByAuthor = inventorySystem.getProductsByAuthor("Author");
    
        int productCount = inventorySystem.countProducts();
    
        inventorySystem.clearInventory();
    
        List<Product> currentInventory = inventorySystem.getAllProducts();
    }
    
}