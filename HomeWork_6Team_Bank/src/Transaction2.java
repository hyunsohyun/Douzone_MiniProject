import java.time.LocalDate;
import java.time.LocalTime;

public class Transaction2 {
    private String transactionDate;
    private String transactionTime;
    private String kind;
    private long amount;
    private long balance;

    public Transaction2(String kind, long amount, long balance) {
        this.transactionDate = String.valueOf(LocalDate.now());
        this.transactionTime = String.valueOf(LocalTime.now());
        this.kind = kind;
        this.amount = amount;
        this.balance = balance;
    }

    public String getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }

    public String getTransactionTime() {
        return transactionTime;
    }

    public void setTransactionTime(String transactionTime) {
        this.transactionTime = transactionTime;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public long getAmount() {
        return amount;
    }

    public void setAmount(long amount) {
        this.amount = amount;
    }

//    public long getBalance() {
//        return balance;
//    }
//
//    public void setBalance(long balance) {
//        this.balance = balance;
//    }


    //현재시간, 현재날짜
}
