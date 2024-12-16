package Bank.dto;

public class GoldLoanDto 
{
	private String email,employeeType,currentJob,existingLoan,goldType;
	private float monthlyIncome,loanAmount,downPayment,creditScore,goldPurity,goldWeight;
	public GoldLoanDto(String email,String employeeType, String currentJob, String existingLoan, String goldType, float monthlyIncome, float loanAmount, float downPayment, float creditScore, float goldPurity, float goldWeight)
	{
		this.email=email;
		this.employeeType = employeeType;
		this.currentJob = currentJob;
		this.existingLoan = existingLoan;
		this.goldType = goldType;
		this.monthlyIncome = monthlyIncome;
		this.loanAmount = loanAmount;
		this.downPayment = downPayment;
		this.creditScore = creditScore;
		this.goldPurity = goldPurity;
		this.goldWeight = goldWeight;
	}
	
	public GoldLoanDto() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmployeeType() {
		return employeeType;
	}
	public void setEmployeeType(String employeeType) {
		this.employeeType = employeeType;
	}
	public String getCurrentJob() {
		return currentJob;
	}
	public void setCurrentJob(String currentJob) {
		this.currentJob = currentJob;
	}
	public String getExistingLoan() {
		return existingLoan;
	}
	public void setExistingLoan(String existingLoan) {
		this.existingLoan = existingLoan;
	}
	public String getGoldType() {
		return goldType;
	}
	public void setGoldType(String goldType) {
		this.goldType = goldType;
	}
	public float getMonthlyIncome() {
		return monthlyIncome;
	}
	public void setMonthlyIncome(float monthlyIncome) {
		this.monthlyIncome = monthlyIncome;
	}
	public float getLoanAmount() {
		return loanAmount;
	}
	public void setLoanAmount(float loanAmount) {
		this.loanAmount = loanAmount;
	}
	public float getDownPayment() {
		return downPayment;
	}
	public void setDownPayment(float downPayment) {
		this.downPayment = downPayment;
	}
	public float getCreditScore() {
		return creditScore;
	}
	public void setCreditScore(float creditScore) {
		this.creditScore = creditScore;
	}
	public float getGoldPurity() {
		return goldPurity;
	}
	public void setGoldPurity(float goldPurity) {
		this.goldPurity = goldPurity;
	}
	public float getGoldWeight() {
		return goldWeight;
	}
	public void setGoldWeight(float goldWeight) {
		this.goldWeight = goldWeight;
	}
	@Override
	public String toString() {
		return "GoldLoanDTO [employeeType=" + employeeType + ", currentJob=" + currentJob + ", existingLoan="
				+ existingLoan + ", goldType=" + goldType + ", monthlyIncome=" + monthlyIncome + ", loanAmount="
				+ loanAmount + ", downPayment=" + downPayment + ", creditScore=" + creditScore + ", goldPurity="
				+ goldPurity + ", goldWeight=" + goldWeight + "]";
	}
}
