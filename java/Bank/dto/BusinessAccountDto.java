package Bank.dto;

import java.sql.Date;

public class BusinessAccountDto {
	private String Nationality,PanNumber,BussinessName,AccountNumber,Email;
	private long AadhaarNumber,GSTIN;
	private int pin;
	private float amount;
	public BusinessAccountDto(String nationality, String panNumber, String bussinessName, String accountNumber,
			String email, long aadhaarNumber, long gSTIN, int pin, float amount) {
		super();
		Nationality = nationality;
		PanNumber = panNumber;
		BussinessName = bussinessName;
		AccountNumber = accountNumber;
		Email = email;
		AadhaarNumber = aadhaarNumber;
		GSTIN = gSTIN;
		this.pin = pin;
		this.amount = amount;
	}
	public BusinessAccountDto() {
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
	public String getBussinessName() {
		return BussinessName;
	}
	public void setBussinessName(String bussinessName) {
		BussinessName = bussinessName;
	}
	public String getAccountNumber() {
		return AccountNumber;
	}
	public void setAccountNumber(String accountNumber) {
		AccountNumber = accountNumber;
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
	public long getGSTIN() {
		return GSTIN;
	}
	public void setGSTIN(long gSTIN) {
		GSTIN = gSTIN;
	}
	public int getPin() {
		return pin;
	}
	public void setPin(int pin) {
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
		return "BusinessAccountDto [Nationality=" + Nationality + ", PanNumber=" + PanNumber + ", BussinessName="
				+ BussinessName + ", AccountNumber=" + AccountNumber + ", Email=" + Email + ", AadhaarNumber="
				+ AadhaarNumber + ", GSTIN=" + GSTIN + ", pin=" + pin + ", amount=" + amount + "]";
	}
}
