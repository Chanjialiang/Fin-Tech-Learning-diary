import java.math.BigDecimal;

public class Account {

    // 
    String accountId;

    String ownerName;

    private BigDecimal balance = BigDecimal.ZERO;

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

    void deposit(BigDecimal amount) {
       this.balance = this.balance.add(amount);
    }

    void withdraw(BigDecimal amount) {
       this.balance = this.balance.subtract(amount);
    }
}