package Bank.dto;

import java.sql.Date;

public class SavingsAccountDto {
	private String Nationality,PanNumber,EmployeeStatus,AccountNumber,NomineeName,NomineeAddress,NomineeEmail,Email;
	private long AadhaarNumber,ExpectedMonthlyTranscations,Nominee_Phone_Number;
	private float amount;
	private int pin;
	public SavingsAccountDto(String nationality, String panNumber, String employeeStatus, String accountNumber,
			String nomineeName, String nomineeAddress, String nomineeEmail, String email, long aadhaarNumber,
			long expectedMonthlyTranscations, long nominee_Phone_Number, float amount, int pin) {
		super();
		Nationality = nationality;
		PanNumber = panNumber;
		EmployeeStatus = employeeStatus;
		AccountNumber = accountNumber;
		NomineeName = nomineeName;
		NomineeAddress = nomineeAddress;
		NomineeEmail = nomineeEmail;
		Email = email;
		AadhaarNumber = aadhaarNumber;
		ExpectedMonthlyTranscations = expectedMonthlyTranscations;
		Nominee_Phone_Number = nominee_Phone_Number;
		this.amount = amount;
		this.pin = pin;
	}
	public SavingsAccountDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getNationality() {
		return Nationality;
	}
	public void setNationality(String nationality) {
		Nationality = nationality;
	}
	public String getPanNumber() {
		return PanNumber;
	}
	public void setPanNumber(String panNumber) {
		PanNumber = panNumber;
	}
	public String getEmployeeStatus() {
		return EmployeeStatus;
	}
	public void setEmployeeStatus(String employeeStatus) {
		EmployeeStatus = employeeStatus;
	}
	public String getAccountNumber() {
		return AccountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		AccountNumber = accountNumber;
	}
	public String getNomineeName() {
		return NomineeName;
	}
	public void setNomineeName(String nomineeName) {
		NomineeName = nomineeName;
	}
	public String getNomineeAddress() {
		return NomineeAddress;
	}
	public void setNomineeAddress(String nomineeAddress) {
		NomineeAddress = nomineeAddress;
	}
	public String getNomineeEmail() {
		return NomineeEmail;
	}
	public void setNomineeEmail(String nomineeEmail) {
		NomineeEmail = nomineeEmail;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public long getAadhaarNumber() {
		return AadhaarNumber;
	}
	public void setAadhaarNumber(long aadhaarNumber) {
		AadhaarNumber = aadhaarNumber;
	}
	public long getExpectedMonthlyTranscations() {
		return ExpectedMonthlyTranscations;
	}
	public void setExpectedMonthlyTranscations(long expectedMonthlyTranscations) {
		ExpectedMonthlyTranscations = expectedMonthlyTranscations;
	}
	public long getNominee_Phone_Number() {
		return Nominee_Phone_Number;
	}
	public void setNominee_Phone_Number(long nominee_Phone_Number) {
		Nominee_Phone_Number = nominee_Phone_Number;
	}
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public int getPin() {
		return pin;
	}
	public void setPin(int pin) {
		this.pin = pin;
	}
	@Override
	public String toString() {
		return "SavingsAccountDto [Nationality=" + Nationality + ", PanNumber=" + PanNumber + ", EmployeeStatus="
				+ EmployeeStatus + ", AccountNumber=" + AccountNumber + ", NomineeName=" + NomineeName
				+ ", NomineeAddress=" + NomineeAddress + ", NomineeEmail=" + NomineeEmail + ", Email=" + Email
				+ ", AadhaarNumber=" + AadhaarNumber + ", ExpectedMonthlyTranscations=" + ExpectedMonthlyTranscations
				+ ", Nominee_Phone_Number=" + Nominee_Phone_Number + ", amount=" + amount + ", pin=" + pin + "]";
	}
	
}