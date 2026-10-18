import java.math.BigDecimal;

public class Account {

    // 
    private String accountId;

    private String ownerName;

    private BigDecimal balance = BigDecimal.ZERO;

    // constructor
    Account(String accountId, String ownerName, BigDecimal balance) {
        if (accountId == null || ownerName == null) {
            System.out.println("error!!");
        }
        this.accountId = accountId;
        this.ownerName = ownerName;
        this.balance = balance;
        System.out.println("create a Account of accountId:" + this.accountId + "and ownername:" + this.ownerName);
    }

    // 
    String getAccountId() {
        return this.accountId;
    }

    //
    String getOwnerName() {
        return this.ownerName;
    }

    //
    BigDecimal getBalance() {
        return this.balance;
    }

    void infoPrint() {
        System.out.println("--------------------------------------------------");
        System.out.println("AccountId:" + this.accountId);
        System.out.println("Owner Name:" + this.ownerName);
        System.out.println("Account Balance:" + this.balance);
        System.out.println("--------------------------------------------------");
    }

    // 
    void deposit(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Deposit amount must be greater than zero.");
        }
        this.balance = this.balance.add(amount);
        System.out.println("After deposit: " + this.getBalance());
    }

    //
    void withdraw(BigDecimal amount) {
        if (amount == null || amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be greater than zero.");
        }
        if (amount.compareTo(this.balance) > 0) {
            throw new IllegalArgumentException("Withdrawal amount cannot exceed the available balance.");
        }
        this.balance = this.balance.subtract(amount);
        System.out.println("After withdrawal: " + this.getBalance());
    }
}
