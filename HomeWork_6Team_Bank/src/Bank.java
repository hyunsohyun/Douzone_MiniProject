import java.util.ArrayList;
import java.util.List;

public class Bank {
	private List<Account> accounts;
	private int totalAccount;

	//생성자함수
	public Bank() {
		this.accounts = new ArrayList<Account>();
		this.totalAccount = accounts.size();
	}
	
	//계좌생성
	public void addAccount(String accountNo, String name) {
		this.accounts.add(new Account(accountNo,name));
	}

	//계좌번호로 정보 가져오기
	public Account getAccount(String accountNo) {
		Account findaccount = null;
		for (Account account : accounts) {
			if(accountNo.equals(accountNo)) {
				findaccount = account;
			}
		}
		return findaccount;
	}
	
	//이름으로 계좌찾기
	public List<Account> findAccounts(String name) {
		ArrayList<Account> findAccounts = new ArrayList<Account>();
		for (Account account : accounts) {
			if(name.equals(account.getName())) {
				findAccounts.add(account);
			}
		}
		return findAccounts;
	}
	
	//get함수
	public List<Account> getAccounts() {
		return accounts;
	}

	public int getTotalAccount() {
		return totalAccount;
	}

	
}
