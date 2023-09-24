import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

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

    @Test
    public void testRemoveProduct() {
        // Verify that the product "Gadget" is removed
        inventorySystem.removeProduct("Gadget");
        assertNull(inventorySystem.findProductByName("Gadget"));

        // Verify that the product list size is reduced by 1
        int currentInventorySize = inventorySystem.getAllProducts().size();
        assertEquals(2, currentInventorySize);

        // Verify that a non-existent product does not cause an issue
        inventorySystem.removeProduct("NonExistentProduct");
        assertEquals(2, currentInventorySize);
    }

    // Other test methods...

}
