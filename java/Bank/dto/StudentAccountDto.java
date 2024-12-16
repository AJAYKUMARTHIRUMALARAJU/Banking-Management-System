package Bank.dto;

public class StudentAccountDto
{
	private String instituteName,email,studentId,AccountNumber;
	private long AadharNumber;
	private int age,pin;
	private float amount;
	public StudentAccountDto(String instituteName, String email, String studentId, String accountNumber,
			long aadharNumber, int age, int pin, float amount) {
		super();
		this.instituteName = instituteName;
		this.email = email;
		this.studentId = studentId;
		AccountNumber = accountNumber;
		AadharNumber = aadharNumber;
		this.age = age;
		this.pin = pin;
		this.amount = amount;
	}
	public StudentAccountDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getInstituteName() {
		return instituteName;
	}
	public void setInstituteName(String instituteName) {
		this.instituteName = instituteName;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
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
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
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
		return "StudentAccountDto [instituteName=" + instituteName + ", email=" + email + ", studentId=" + studentId
				+ ", AccountNumber=" + AccountNumber + ", AadharNumber=" + AadharNumber + ", age=" + age + ", pin="
				+ pin + ", amount=" + amount + "]";
	}
	
}
