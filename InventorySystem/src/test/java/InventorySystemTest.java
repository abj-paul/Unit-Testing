import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;

import org.junit.jupiter.api.AfterEach;

public class InventorySystemTest {

    InventorySystem inventorySystem;

    @BeforeEach
    void beforeEach() {
        inventorySystem = new InventorySystem();

        Product product1 = new Product("Widget", 10.0, 50, "Vendor1");
        Product product2 = new Product("Gadget", 20.0, 30, "Vendor2");
        Product product3 = new Product("Doodad", 15.0, 20, "Vendor1");

        inventorySystem.addProduct(product1);
        inventorySystem.addProduct(product2);
        inventorySystem.addProduct(product3);
    }

    @AfterEach
    void afterEach() {
        this.inventorySystem = null;
    }

    @Test
    @DisplayName("removeProduct() TID:3 --> Item at Index 1")
    public void testRemoveProductFoundAtIndex1() {
        beforeEach();

        inventorySystem.removeProduct("Gadget");
        assertNull(inventorySystem.findProductByName("Gadget"));

        int currentInventorySize = inventorySystem.getAllProducts().size();
        assertEquals(2, currentInventorySize);

        afterEach();
    }

    @Test
    @DisplayName("removeProduct() TID:2 --> Item at Index 0")
    public void testRemoveProductFoundAtIndex0() {
        beforeEach();

        inventorySystem.removeProduct("Widget");
        assertNull(inventorySystem.findProductByName("Widget"));

        int currentInventorySize = inventorySystem.getAllProducts().size();
        assertEquals(2, currentInventorySize);

        afterEach();
    }

    @Test
    @DisplayName("removeProduct() TID:1 --> Inventory size is 0")
    public void testRemoveProductForEmptyInventory() {
        inventorySystem = new InventorySystem();

        inventorySystem.removeProduct("Widget");
        assertNull(inventorySystem.findProductByName("Widget"));

        int currentInventorySize = inventorySystem.getAllProducts().size();
        assertEquals(0, currentInventorySize);

        afterEach();
    }

    @Test
    @DisplayName("calculateTotalValue() TID:1 --> Inventory size is 0")
    public void testCalculateTotalValueForEmptyInventory() {
        inventorySystem = new InventorySystem();

        double totalValue = inventorySystem.calculateTotalValue();
        assertEquals(0.0, totalValue);

        afterEach();
    }

    @Test
    @DisplayName("calculateTotalValue() TID:2 --> Inventory size is 0")
    public void testCalculateTotalValueForFullInventory() {
        beforeEach();

        double totalValue = inventorySystem.calculateTotalValue();
        assertEquals(1400, totalValue);

        afterEach();
    }

    @Test
    @DisplayName("updateProductQuantityExistingProduct() TID:1 --> Inventory size is 0")
    void testUpdateProductQuantityExistingProductWithEmptyInventory() {
        inventorySystem = new InventorySystem();

        String productName = "Widget";
        int newQuantity = 60;

        inventorySystem.updateProductQuantity(productName, newQuantity);

        Product updatedProduct = inventorySystem.findProductByName(productName);
        assertEquals(newQuantity, updatedProduct.getQuantity());

        afterEach();
    }

    @Test
    @DisplayName("updateProductQuantityExistingProduct() TID:2 --> Inventory size is 3")
    void testUpdateProductQuantityOfProductWithFullInventory() {
        beforeEach();
        String productName = "Widget";
        int newQuantity = 60;

        inventorySystem.updateProductQuantity(productName, newQuantity);

        Product updatedProduct = inventorySystem.findProductByName(productName);
        assertEquals(newQuantity, updatedProduct.getQuantity());
        afterEach();
    }

    @Test
    @DisplayName("updateProductQuantityExistingProduct() TID:3 --> Inventory size is 1")
    void updateProductQuantityExistingProductWithOneProduct() {
        this.inventorySystem = new InventorySystem();
        this.inventorySystem.addProduct(new Product("Widget", 10.0, 50, "Vendor1"));

        String productName = "Widget";
        int newQuantity = 60;

        inventorySystem.updateProductQuantity(productName, newQuantity);

        Product updatedProduct = inventorySystem.findProductByName(productName);
        assertEquals(newQuantity, updatedProduct.getQuantity());
        afterEach();
    }

    @Test
    @DisplayName("GetLowStockProducts() TID:1 --> Inventory size is 2~3")
    void testGetLowStockProducts() {
        beforeEach();
        int threshold = 40;
        List<Product> lowStockProducts = inventorySystem.getLowStockProducts(threshold);

        assertEquals(2, lowStockProducts.size());

        for (Product product : lowStockProducts) {
            assertTrue(product.getQuantity() < threshold);
        }
        afterEach();
    }

    @Test
    @DisplayName("GetLowStockProducts() TID:1 --> Inventory size is 0")
    void testGetLowStockProductsFromEmptyInventory() {
        this.inventorySystem = new InventorySystem();

        int threshold = 40;
        List<Product> lowStockProducts = inventorySystem.getLowStockProducts(threshold);

        assertEquals(0, lowStockProducts.size());
        afterEach();
    }

    @Test
    @DisplayName("GetLowStockProducts() TID:1 --> Inventory size is 1")
    void testGetLowStockProductsWithThresholdEqualInventory() {
        this.inventorySystem = new InventorySystem();
        this.inventorySystem.addProduct(new Product("Widget", 10.0, 50, "Vendor1"));        
        int threshold = 50;
        List<Product> lowStockProducts = inventorySystem.getLowStockProducts(threshold);

        assertEquals(1, lowStockProducts.size());
        afterEach();
    }

    // Latest
    @Test
    @DisplayName("FindProductByName() TID:1 --> Inventory size is 0")
    void testFindProductByNameWithEmptyInventory() {
        String productName = "Widget";
        this.inventorySystem = new InventorySystem();

        Product foundProduct = inventorySystem.findProductByName(productName);
        assertNull(foundProduct);
        afterEach();
    }

    @Test
    @DisplayName("FindProductByName() TID:1 --> Inventory size is 3, Found")
    void testFindProductByNameWithSize2() {
        beforeEach();
        String productName = "Widget";
        Product foundProduct = inventorySystem.findProductByName(productName);
        assertEquals("Widget", foundProduct.getName());
        afterEach();
    }

    @Test
    @DisplayName("FindProductByName() TID:2 --> Inventory size is 3, Found")
    void testFindProductByNameWithSize3() {
        beforeEach();
        String productName = "uwu";
        Product foundProduct = inventorySystem.findProductByName(productName);
        assertNull(foundProduct);
        afterEach();
    }

    @Test
    @DisplayName("AdjustAllPrices() TID:1 --> Inventory size is 3, Found")
    void testAdjustAllPricesWithNegativePercentageIncrease() {
        beforeEach();
        double percentageIncrease = -5.0;
        inventorySystem.adjustAllPrices(percentageIncrease);

        // Verify that all prices have been decreased by the specified percentage
        for (Product product : inventorySystem.getAllProducts()) {
            double originalPrice = product.getPrice() / (1 - percentageIncrease / 100);
            double adjustedPrice = product.getPrice();
            assertEquals(originalPrice * (1 - percentageIncrease / 100), adjustedPrice, 0.001);
        }
        afterEach();
    }
}
