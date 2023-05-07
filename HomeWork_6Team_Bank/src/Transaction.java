import java.time.LocalDate;
import java.time.LocalTime;

public class Transaction {
	private String transactionDate;
	private String transactionTime;
	private String kind;
	private long amount;
	private long balance;
	
	public Transaction (String kind, long amount, long balance) {
		this.kind = kind;
		this.amount = amount ;
		this.balance = balance;
		this.transactionDate = String.valueOf(LocalDate.now());
		this.transactionTime = String.valueOf(LocalTime.now());
	}
	
	@Override
	public String toString() {
		   return "[거래금액:" + amount + "원 , 잔액: " + balance +  "원 / " + transactionDate + '\'' + " " + transactionTime + '\'' + ", 거래종류 :  " + kind+']';
	}

}
