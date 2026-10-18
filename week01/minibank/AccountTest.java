import java.math.BigDecimal;

public class AccountTest {

    public static void main(String[] args) {
        Account account = new Account(
            "10001",
            "Alice",
            new BigDecimal("1000.00")
        );

        // Test 1: Valid deposit
        account.deposit(new BigDecimal("500.00"));
        checkBalance(account, "1500.00", "Deposit");

        // Test 2: Valid withdrawal
        account.withdraw(new BigDecimal("200.00"));
        checkBalance(account, "1300.00", "Withdrawal");

        // Invalid transactions must throw and leave the balance unchanged.
        expectFailure(account,
            () -> account.deposit(new BigDecimal("-100")),
            "Negative deposit"
        );
        expectFailure(account,
            () -> account.withdraw(new BigDecimal("5000")),
            "Insufficient funds"
        );
        expectFailure(account,
            () -> account.withdraw(BigDecimal.ZERO),
            "Zero withdrawal"
        );
        expectFailure(account,
            () -> account.deposit(new BigDecimal("0.00")),
            "Zero deposit"
        );
        expectFailure(account,
            () -> account.withdraw(new BigDecimal("-100")),
            "Negative withdrawal"
        );
        expectFailure(account,
            () -> account.deposit(null),
            "Null deposit"
        );
        expectFailure(account,
            () -> account.withdraw(null),
            "Null withdrawal"
        );

        checkBalance(account, "1300.00", "Balance after rejected transactions");

        // Withdrawing the exact balance is allowed, even with a different scale.
        account.withdraw(new BigDecimal("1300.000"));
        checkBalance(account, "0.00", "Full balance withdrawal");
        expectFailure(account,
            () -> account.withdraw(new BigDecimal("0.01")),
            "Withdrawal from empty account"
        );

        System.out.println("All account tests PASSED");
    }

    private static void checkBalance(
            Account account,
            String expected,
            String testName) {

        BigDecimal target = new BigDecimal(expected);

        if (account.getBalance().compareTo(target) != 0) {
            throw new AssertionError(
                testName + " FAILED: expected " + target
                    + ", got " + account.getBalance()
            );
        }

        System.out.println(testName + " PASSED");
    }

    private static void expectFailure(
            Account account,
            Runnable operation,
            String testName) {

        BigDecimal balanceBefore = account.getBalance();

        try {
            operation.run();
            throw new AssertionError(testName + " FAILED: expected IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            if (account.getBalance().compareTo(balanceBefore) != 0) {
                throw new AssertionError(testName + " FAILED: balance changed");
            }
            System.out.println(testName + " PASSED");
        }
    }
}
