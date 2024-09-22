package account;

import java.util.HashMap;
import java.util.Map;

public class Account {
	private String accName;
	private String accNo;
	private float balance = 0;
	private Map<String, Float> accHistory = new HashMap<String, Float>();
	
	/**
	* Creates an account with a name and account number which tracks balance &
	* transaction history
	*
	* @param  accName  Name of account holder
	* @param  accNo    Account number
	*/
	public Account(String accName, String accNo) {
		this.setAccName(accName);
		this.setAccNo(accNo);
	}
	
	private boolean validTransaction(String ref, float amount) {
		if (amount < 0) {
			System.out.println("Invalid: cannot withdraw/deposit a negative value");
			System.out.println("No change has been made");
			return false;
		}
		if (accHistory.keySet().contains(ref)) {
			System.out.println("Invalid: Provided transaction reference is not unique");
			System.out.println("No change has been made");
			return false;
		}
		return true;
	}
	
	/**
	* Deposit money into the account
	*
	* @param  ref       Unique reference for transaction
	* @param  amount    Amount to deposit into account
	*/
	public void deposit(String ref, float amount) {
		
		if (this.validTransaction(ref, amount)) {
			this.setBalance(this.getBalance() + amount);
			this.accHistory.put(ref, amount);
			System.out.println(String.format("%.2f was successfully deposited into account %s",amount,this.accNo));
		}
	}
	/**
	* Withdraw money from the account
	*
	* @param  ref       Unique reference for transaction
	* @param  amount    Amount to withdraw from the account
	*/
	public void withdraw(String ref, float amount) {
		if (this.validTransaction(ref, amount)) {
			this.setBalance(this.getBalance() - amount);
			this.accHistory.put(ref, -amount);
			System.out.println(String.format("%.2f was successfully withdrawn from account %s",amount,this.accNo));
		}
		
	}

	/**
	 * Produce an account statement with account name, number, full history of
	 * transactions and current balance
	 * @return An account statement
	 */
	public String produceAccountStatement() {
		String statement = String.format("Name: %s\nAccount: %s\n\n",this.getAccName(),this.getAccNo());
		statement = statement.concat("Account History\n---------------\n");
		String[] refs = this.accHistory.keySet().toArray(new String[0]);
		for (String r:refs) {
			statement = statement.concat(String.format("%s %.2f\n",r,this.accHistory.get(r)));
		}
		statement = statement.concat(String.format("\nBalance: %.2f",this.getBalance()));
		return statement;
	}
	
	// Getters & Setters
	public String getAccName() {
		return accName;
	}

	public void setAccName(String accName) {
		this.accName = accName;
	}

	public float getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public String getAccNo() {
		return accNo;
	}

	public void setAccNo(String accNo) {
		this.accNo = accNo;
	}
}
