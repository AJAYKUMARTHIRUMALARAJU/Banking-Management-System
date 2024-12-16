package Bank.dto;

public class PersonalLoanDto 
{
	private String employeeType,currentJob,existingLoan,email;
	private float monthlyIncome,loanAmount,downPayment,creditScore;
	public PersonalLoanDto(String email,String employeeType, String currentJob,String existingLoan, float monthlyIncome, float loanAmount, float downPayment, float creditScore)
	{
		this.email=email;
		this.employeeType = employeeType;
		this.currentJob = currentJob;
		this.existingLoan=existingLoan;
		this.monthlyIncome = monthlyIncome;
		this.loanAmount = loanAmount;
		this.downPayment = downPayment;
		this.creditScore = creditScore;
	}
	public PersonalLoanDto() {
		super();
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getEmployeeType()
	{
		return employeeType;
	}
	public void setEmployeeType(String employeeType)
	{
		this.employeeType = employeeType;
	}
	public String getCurrentJob()
	{
		return currentJob;
	}
	public void setExistingLoan(String existingLoan)
	{
		this.existingLoan=existingLoan;
	}
	public String getExistingLoan() 
	{
		return existingLoan;
	}
	public void setCurrentJob(String currentJob)
	{
		this.currentJob = currentJob;
	}
	public float getMonthlyIncome()
	{
		return monthlyIncome;
	}
	public void setMonthlyIncome(float monthlyIncome)
	{
		this.monthlyIncome = monthlyIncome;
	}
	public float getLoanAmount() 
	{
		return loanAmount;
	}
	public void setLoanAmount(float loanAmount) 
	{
		this.loanAmount = loanAmount;
	}
	public float getDownPayment() 
	{
		return downPayment;
	}
	public void setDownPayment(float downPayment) 
	{
		this.downPayment = downPayment;
	}
	public float getCreditScore()
	{
		return creditScore;
	}
	public void setCreditScore(float creditScore)
	{
		this.creditScore = creditScore;
	}
}
