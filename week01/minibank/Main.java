import java.math.BigDecimal;

public class Main {
    public static void main(String[] args) {
        Account account = new Account(
            "10001", 
            "ChenJialiang", 
            new BigDecimal(1000.00));

        account.infoPrint();
        account.deposit(new BigDecimal("500.00"));

        account.withdraw(new BigDecimal("200.00"));
        account.infoPrint();
    }
}
