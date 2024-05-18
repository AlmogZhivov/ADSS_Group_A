package Business;

public class Supplier {
    public enum PaymentMethod {
        CASH,
        CREDIT_CARD,
        CHECK,
        BANK_TRANSFER
    }

    private int supplierId;
    private int contactId = 0;
    private String name;
    private String compNumber;
    private String bankNumber;
    private PaymentMethod payment;
    private Contact contact;
    //private final SupplierAgreement supplierAgreement;

    public Supplier(int supplierId, String name, String compNumber, String bankNumber, PaymentMethod payment, Contact contact) {
        this.supplierId = supplierId;
        this.name = name;
        this.compNumber = compNumber;
        this.bankNumber = bankNumber;
        this.payment = payment;
        this.contact = contact;
        this.contactId += 1;
    }

    public Supplier(int supplierId, String name, String compNumber, String bankNumber, PaymentMethod payment) {
        this.supplierId = supplierId;
        this.name = name;
        this.compNumber = compNumber;
        this.bankNumber = bankNumber;
        this.payment = payment;
    }

    public Supplier(Supplier supplier) {
        this.name = supplier.getName();
        this.supplierId = supplier.getSupplierId();
        this.bankNumber = supplier.getBankNumber();
        this.compNumber = supplier.getCompNumber();
        // Distinguish betweeen fixed delivery days and demand orders?
        //this.shouldDeliver = supplier.getShouldDeliver();
        this.payment = supplier.getPayment();
        this.contact = supplier.getContact();
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

    public PaymentMethod getPayment() {
        return payment;
    }

    public void setName(String newName) {
        this.name = newName;
    }

    public void setBankNumber(String newBankAccount) {
        this.bankNumber = newBankAccount;
    }

    public void setPayment(PaymentMethod newPaymentMethod) {
        this.payment = newPaymentMethod;
    }

    // Adds contact to supplier
    public void addContact(String contactName, String phoneNumber) {
        this.contact = new Contact(contactName, phoneNumber, contactId++);
    }
}
