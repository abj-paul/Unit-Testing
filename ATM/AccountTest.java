import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class AccountTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private final InputStream originalIn = System.in;
    private Account account;

    @Before
    public void setUpStreams() {
        // Redirect System.out to capture printed messages
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        // Restore original System.out and close resources
        System.setOut(originalOut);
        System.setIn(originalIn);
    }


    @Before
    public void setUp() {
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

    @Test
    public void testCalcCheckingWithdrawWithValidAmount() {
        // Withdraw 200.0 from the checking account
        double withdrawnAmount = account.calcCheckingWithdraw(200.0);

        // The new balance should be 1000.0 - 200.0 = 800.0
        assertEquals(800.0, withdrawnAmount, 0.001); // Use delta for double comparison
        assertEquals(800.0, account.getCheckingBalance(), 0.001);
    }

    @Test
    public void testCalcCheckingWithdrawWithInvalidAmount() {
        // Withdraw an amount greater than the balance
        double withdrawnAmount = account.calcCheckingWithdraw(1200.0);

        // The balance should remain unchanged, and the withdrawn amount should be 0.0
        assertEquals(0.0, withdrawnAmount, 0.001); // Use delta for double comparison
        assertEquals(1000.0, account.getCheckingBalance(), 0.001);
    }

    @Test
    public void testGetCheckingWithdrawInputWithValidAmount() {
        // Simulate user input with a valid amount (e.g., "200.0")
        ByteArrayInputStream in = new ByteArrayInputStream("200.0\n".getBytes());
        System.setIn(in);

        Account account = new Account();
        account.getCheckingWithdrawInput();

        // Check if the output contains the new balance
        assertTrue(outContent.toString().contains("Current Checkings Account Balance: $800.00"));
    }

    @Test
    public void testGetCheckingWithdrawInputWithInvalidAmount() {
        // Simulate user input with an amount greater than the balance (e.g., "1200.0")
        ByteArrayInputStream in = new ByteArrayInputStream("1200.0\n".getBytes());
        System.setIn(in);

        Account account = new Account();
        account.getCheckingWithdrawInput();

        // Check if the output contains the "Balance Cannot be Negative" message
        assertTrue(outContent.toString().contains("Balance Cannot be Negative."));
    }

    @Test
    public void testGetCheckingWithdrawInputWithInvalidInput() {
        // Simulate user input with an invalid input (e.g., "invalid")
        ByteArrayInputStream in = new ByteArrayInputStream("invalid\n".getBytes());
        System.setIn(in);

        Account account = new Account();
        account.getCheckingWithdrawInput();

        // Check if the output contains the "Invalid Choice" message
        assertTrue(outContent.toString().contains("Invalid Choice."));
    }

@Test
    public void testGetSavingDepositInputWithValidAmount() {
        // Simulate user input with a valid amount (e.g., "200.0")
        ByteArrayInputStream in = new ByteArrayInputStream("200.0\n".getBytes());
        System.setIn(in);

        Account account = new Account();
        account.getSavingDepositInput();

        // Check if the output contains the new balance
        assertTrue(outContent.toString().contains("Current Savings Account Balance: $200.00"));
    }

    @Test
    public void testGetSavingDepositInputWithInvalidAmount() {
        // Simulate user input with a negative amount (e.g., "-100.0")
        ByteArrayInputStream in = new ByteArrayInputStream("-100.0\n".getBytes());
        System.setIn(in);

        Account account = new Account();
        account.getSavingDepositInput();

        // Check if the output contains the "Balance Cannot be Negative" message
        assertTrue(outContent.toString().contains("Balance Cannot Be Negative."));
    }

    @Test
    public void testGetSavingDepositInputWithInvalidInput() {
        // Simulate user input with an invalid input (e.g., "invalid")
        ByteArrayInputStream in = new ByteArrayInputStream("invalid\n".getBytes());
        System.setIn(in);

        Account account = new Account();
        account.getSavingDepositInput();

        // Check if the output contains the "Invalid Choice" message
        assertTrue(outContent.toString().contains("Invalid Choice."));
    }

@Test
    public void testGetTransferInputWithValidTransferFromCheckingsToSavings() {
        // Simulate user input to transfer from Checkings to Savings
        ByteArrayInputStream in = new ByteArrayInputStream("1\n200.0\n".getBytes());
        System.setIn(in);

        Account account = new Account(12345, 6789, 1000.0, 500.0);
        account.getTransferInput("Checkings");

        // Check if the output contains the new balances
        assertTrue(outContent.toString().contains("Current Checkings Account Balance: $800.00"));
        assertTrue(outContent.toString().contains("Current Savings Account Balance: $700.00"));
    }

    @Test
    public void testGetTransferInputWithValidTransferFromSavingsToCheckings() {
        // Simulate user input to transfer from Savings to Checkings
        ByteArrayInputStream in = new ByteArrayInputStream("1\n200.0\n".getBytes());
        System.setIn(in);

        Account account = new Account(12345, 6789, 1000.0, 500.0);
        account.getTransferInput("Savings");

        // Check if the output contains the new balances
        assertTrue(outContent.toString().contains("Current Checkings Account Balance: $1200.00"));
        assertTrue(outContent.toString().contains("Current Savings Account Balance: $300.00"));
    }

    @Test
    public void testGetTransferInputWithInvalidTransferAmount() {
        // Simulate user input to transfer an invalid amount
        ByteArrayInputStream in = new ByteArrayInputStream("1\n1500.0\n".getBytes());
        System.setIn(in);

        Account account = new Account(12345, 6789, 1000.0, 500.0);
        account.getTransferInput("Checkings");

        // Check if the output contains the "Balance Cannot Be Negative" message
        assertTrue(outContent.toString().contains("Balance Cannot Be Negative."));
    }

    @Test
    public void testGetTransferInputWithInvalidChoice() {
        // Simulate user input with an invalid choice
        ByteArrayInputStream in = new ByteArrayInputStream("3\n".getBytes());
        System.setIn(in);

        Account account = new Account(12345, 6789, 1000.0, 500.0);
        account.getTransferInput("Checkings");

        // Check if the output contains the "Invalid Choice" message
        assertTrue(outContent.toString().contains("Invalid Choice."));
    }

    @Test
    public void testGetTransferInputWithExitOption() {
        // Simulate user choosing to exit
        ByteArrayInputStream in = new ByteArrayInputStream("2\n".getBytes());
        System.setIn(in);

        Account account = new Account(12345, 6789, 1000.0, 500.0);
        account.getTransferInput("Checkings");

        // Check if the output does not contain balance messages
        assertFalse(outContent.toString().contains("Current Checkings Account Balance:"));
        assertFalse(outContent.toString().contains("Current Savings Account Balance:"));
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
