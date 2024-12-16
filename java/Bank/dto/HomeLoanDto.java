package Bank.dto;

public class HomeLoanDto
{
	private String email,employeeType,currentJob,existingLoan,propertyDetails;
	private float monthlyIncome,loanAmount,downPayment,creditScore;
	public HomeLoanDto(String email,String employeeType, String currentJob, String existingLoan, String propertyDetails, float monthlyIncome, float loanAmount, float downPayment, float creditScore) 
	{
		this.email=email;
		this.employeeType = employeeType;
		this.currentJob = currentJob;
		this.existingLoan = existingLoan;
		this.propertyDetails = propertyDetails;
		this.monthlyIncome = monthlyIncome;
		this.loanAmount = loanAmount;
		this.downPayment = downPayment;
		this.creditScore = creditScore;
	}
	
	public HomeLoanDto() {
		super();
		// TODO Auto-generated constructor stub
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
	public void setCurrentJob(String currentJob) 
	{
		this.currentJob = currentJob;
	}
	public String getExistingLoan() 
	{
		return existingLoan;
	}
	public void setExistingLoan(String existingLoan)
	{
		this.existingLoan = existingLoan;
	}
	public String getPropertyDetails()
	{
		return propertyDetails;
	}
	public void setPropertyDetails(String propertyDetails) 
	{
		this.propertyDetails = propertyDetails;
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
	@Override
	public String toString() {
		return "HomeLoanDto [employeeType=" + employeeType + ", currentJob=" + currentJob + ", existingLoan="
				+ existingLoan + ", propertyDetails=" + propertyDetails + ", monthlyIncome=" + monthlyIncome
				+ ", loanAmount=" + loanAmount + ", downPayment=" + downPayment + ", creditScore=" + creditScore + "]";
	}
	
}
