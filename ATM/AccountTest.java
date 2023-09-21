import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class AccountTest {
    private Account account;

    @Before
    public void setUp() {
        // Create an Account object with initial balances
        account = new Account(12345, 6789, 1000.0, 500.0);
    }

    @Test
    public void testCalcSavingWithdrawWithValidAmount() {
        // Withdraw 200.0 from the savings account
        double withdrawnAmount = account.calcSavingWithdraw(200.0);

        // The new balance should be 500.0 - 200.0 = 300.0
        assertEquals(300.0, withdrawnAmount, 0.001); // Use delta for double comparison
        assertEquals(300.0, account.getSavingBalance(), 0.001);
    }

    @Test
    public void testCalcSavingWithdrawWithInvalidAmount() {
        // Withdraw an amount greater than the balance
        double withdrawnAmount = account.calcSavingWithdraw(600.0);

        // The balance should remain unchanged, and the withdrawn amount should be 0.0
        assertEquals(0.0, withdrawnAmount, 0.001); // Use delta for double comparison
        assertEquals(500.0, account.getSavingBalance(), 0.001);
    }

    public static void main(String[] args) {
        Result result = JUnitCore.runClasses(AccountTest.class);

        for (Failure failure : result.getFailures()) {
            System.out.println(failure.toString());
        }

        if (result.wasSuccessful()) {
            System.out.println("All tests passed successfully.");
        } else {
            System.out.println("Some tests failed.");
        }
    }
}

