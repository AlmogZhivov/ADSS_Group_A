package Bussiness;

enum PaymentMethod{
    CASH,
    CREDIT_CARD,
    CHECK,
    BANK_TRANSFER
}

public class Supplier {

    private int supplierId;
    private String name;
    private String compNumber;
    private String bankNumber;
    private PaymentMethod payment;
    private Contact contact;

    public Supplier(int supplierId, String name, String compNumber, String bankNumber, PaymentMethod payment, Contact contact) {
        this.supplierId = supplierId;
        this.name = name;
        this.compNumber = compNumber;
        this.bankNumber = bankNumber;
        this.payment = payment;
        this.contact = contact;
    }

    public int getSupplierId() {
        return supplierId;
    }

    public String getName() {
        return name;
    }

    public String getCompNumber() {
        return compNumber;
    }

    public String getBankNumber() {
        return bankNumber;
    }

    public Contact getContact() {
        return contact;
    }
}
