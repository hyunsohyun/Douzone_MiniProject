import java.util.ArrayList;

public class Account2 {
    private String accountNo;
    private String name;
    private long balance;
    private ArrayList transactions;

    //처음 계좌생성시
    public Account2(String accountNo, String name) {
        this.accountNo = accountNo;
        //잔고는 0으로
        this.balance = 0;
        this.name = name;
        this.transactions = new ArrayList<Transaction>();
    }

    //getter setter
    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //****메서드 정의

    public void deposit(long amount){
        transactions.add(new Transaction2("deposit", amount, balance+amount));
        balance = balance+amount;
        System.out.println(amount+ "원 입금하셨습니다.");
        System.out.println("현재 잔액은" + getBalance()+ "원 입금하셨습니다.");

    }

    public void withdraw(long amount){
        transactions.add(new Transaction2("withdraw", amount, balance-amount));
        balance = balance-amount;
        System.out.println(amount+ "원 출금하셨습니다.");
        System.out.println("현재 잔액은" + getBalance()+ "원 입금하셨습니다.");
    }

    public long getBalance() {
        transactions.add(new Transaction("getBalance", 0, balance));
        return balance;
    }

    public ArrayList getTransactions() {
        return transactions;
    }

}
