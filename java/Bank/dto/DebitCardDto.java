package Bank.dto;

public class DebitCardDto {
	private String name,email;
	private Long cardNumber;
	private int cvv;
	public DebitCardDto(String name, String email, Long cardNumber, int cvv) {
		super();
		this.name = name;
		this.email = email;
		this.cardNumber = cardNumber;
		this.cvv = cvv;
	}
	public DebitCardDto() {
		super();
		// TODO Auto-generated constructor stub
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public Long getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(Long cardNumber) {
		this.cardNumber = cardNumber;
	}
	public int getCvv() {
		return cvv;
	}
	public void setCvv(int cvv) {
		this.cvv = cvv;
	}
	@Override
	public String toString() {
		return "DebitCardDto [name=" + name + ", email=" + email + ", cardNumber=" + cardNumber + ", cvv=" + cvv + "]";
	}
	
}
