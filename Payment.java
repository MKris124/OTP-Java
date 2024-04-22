import java.time.LocalDateTime;

public class Payment {
    private int webshopId;
    private int customerId;
    private String paymentMethod;
    private int amount;
    private String accountNumber;
    private String cardNumber;
    private LocalDateTime paymentDate;
    //<editor-fold desc="getters-setters">

    public void setWebshopId(int webshopId) {
        this.webshopId = webshopId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setPaymentDate(LocalDateTime paymentDate) {
        this.paymentDate = paymentDate;
    }

    public int getWebshopId() {
        return webshopId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public int getAmount() {
        return amount;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public LocalDateTime getPaymentDate() {
        return paymentDate;
    }
    //</editor-fold>
    public Payment(int webshopId, int customerId, String paymentMethod, int amount, String accountNumber, String cardNumber, LocalDateTime paymentDate) {
        this.webshopId = webshopId;
        this.customerId = customerId;
        this.paymentMethod = paymentMethod;
        this.amount = amount;
        this.accountNumber = accountNumber;
        this.cardNumber = cardNumber;
        this.paymentDate=paymentDate;
    }
}
