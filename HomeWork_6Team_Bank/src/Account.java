import java.util.ArrayList;
import java.util.List;

public class Account {
	private String accountNo;
	private String name;
	private long balance;
	private List<Transaction> transactions;
	
	//생성자함수
	public Account(String accountNo,String name) {
		this.accountNo = accountNo;
		this.name = name;
		this.balance = 0;
		this.transactions = new ArrayList<Transaction>();
	}

	//입금
	public void deposit(long amount) {
		this.balance = balance + amount;
		this.transactions.add(new Transaction("입금", amount, this.balance));
	}
	
	//출금
	public void withdraw(long amount) {
		if(balance < amount){
			transactions.add(new Transaction("출금실패", amount, this.balance));
			System.out.println("출금실패");
		}else {
			this.balance = balance - amount;
			transactions.add(new Transaction("출금", amount, this.balance));	
		}
	}
	
	//get함수
	public long getBalance() {
		return this.balance;
	}
	
	public List<Transaction> getTransactions() {
		return this.transactions;
	}
	
	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return " [계좌번호 : " + accountNo + ", 소유자 명 : " + name + ", 잔액 :" + balance + "원]";
	}
	
}
