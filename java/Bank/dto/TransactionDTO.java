package Bank.dto;

import java.sql.Timestamp;

public class TransactionDTO
{
	private long id;
    private String accountNumber;
    private double amount;
    private String transactionType;
    private Timestamp timestamp;
	public TransactionDTO(long id, String accountNumber, double amount, String transactionType, Timestamp timestamp2) 
	{
		this.id = id;
		this.accountNumber = accountNumber;
		this.amount = amount;
		this.transactionType = transactionType;
		this.timestamp = timestamp2;
	}
	public long getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}
	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	
	@Override
	public String toString() 
	{
		return "TransactionDTO \nid=" + id + ", \naccountNumber=" + accountNumber + ", \namount=" + amount
				+ ", \ntransactionType=" + transactionType + ", \ntimestamp=" + timestamp + "\n";
	}
    
}
