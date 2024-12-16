package Bank.dto;

public class VehicleLoanDto {
    private String email, employeeType, currentJob, existingLoan, vehicleType, vehicleModel;
    private float monthlyIncome, loanAmount, downPayment;
    private int creditScore;

    public VehicleLoanDto(String email, String employeeType, String currentJob, String existingLoan,
                          String vehicleType, String vehicleModel, float monthlyIncome, float loanAmount,
                          float downPayment, int creditScore) {
        this.email = email;
        this.employeeType = employeeType;
        this.currentJob = currentJob;
        this.existingLoan = existingLoan;
        this.vehicleType = vehicleType;
        this.vehicleModel = vehicleModel;
        this.monthlyIncome = monthlyIncome;
        this.loanAmount = loanAmount;
        this.downPayment = downPayment;
        this.creditScore = creditScore;
    }

    public VehicleLoanDto() {}

    // Getters and Setters
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getEmployeeType() { return employeeType; }
    public void setEmployeeType(String employeeType) { this.employeeType = employeeType; }
    public String getCurrentJob() { return currentJob; }
    public void setCurrentJob(String currentJob) { this.currentJob = currentJob; }
    public String getExistingLoan() { return existingLoan; }
    public void setExistingLoan(String existingLoan) { this.existingLoan = existingLoan; }
    public String getVehicleType() { return vehicleType; }
    public void setVehicleType(String vehicleType) { this.vehicleType = vehicleType; }
    public String getVehicleModel() { return vehicleModel; }
    public void setVehicleModel(String vehicleModel) { this.vehicleModel = vehicleModel; }
    public float getMonthlyIncome() { return monthlyIncome; }
    public void setMonthlyIncome(float monthlyIncome) { this.monthlyIncome = monthlyIncome; }
    public float getLoanAmount() { return loanAmount; }
    public void setLoanAmount(float loanAmount) { this.loanAmount = loanAmount; }
    public float getDownPayment() { return downPayment; }
    public void setDownPayment(float downPayment) { this.downPayment = downPayment; }
    public int getCreditScore() { return creditScore; }
    public void setCreditScore(int creditScore) { this.creditScore = creditScore; }

    @Override
    public String toString() {
        return "VehicleLoanDto [email=" + email + ", employeeType=" + employeeType + ", currentJob=" + currentJob
                + ", existingLoan=" + existingLoan + ", vehicleType=" + vehicleType + ", vehicleModel=" + vehicleModel
                + ", monthlyIncome=" + monthlyIncome + ", loanAmount=" + loanAmount + ", downPayment=" + downPayment
                + ", creditScore=" + creditScore + "]";
    }
}
