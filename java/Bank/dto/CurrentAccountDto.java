package Bank.dto;

public class CurrentAccountDto {
	private String SourceOfIncome,Email,PanNumber,AccountNumber;
	private long AadharNumber;
	private String pin;
	private float amount;
	public CurrentAccountDto(String sourceOfIncome, String email, String panNumber, String accountNumber,
			long aadharNumber, String pin, float amount) {
		super();
		SourceOfIncome = sourceOfIncome;
		Email = email;
		PanNumber = panNumber;
		AccountNumber = accountNumber;
		AadharNumber = aadharNumber;
		this.pin = pin;
		this.amount = amount;
	}
	public CurrentAccountDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getSourceOfIncome() {
		return SourceOfIncome;
	}
	public void setSourceOfIncome(String sourceOfIncome) {
		SourceOfIncome = sourceOfIncome;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getPanNumber() {
		return PanNumber;
	}
	public void setPanNumber(String panNumber) {
		PanNumber = panNumber;
	}
	public String getAccountNumber() {
		return AccountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		AccountNumber = accountNumber;
	}
	public long getAadharNumber() {
		return AadharNumber;
	}
	public void setAadharNumber(long aadharNumber) {
		AadharNumber = aadharNumber;
	}
	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	@Override
	public String toString() {
		return "CurrentAccountDto [SourceOfIncome=" + SourceOfIncome + ", Email=" + Email + ", PanNumber=" + PanNumber
				+ ", AccountNumber=" + AccountNumber + ", AadharNumber=" + AadharNumber + ", pin=" + pin + ", amount="
				+ amount + "]";
	}
	
}
