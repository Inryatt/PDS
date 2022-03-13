package Ex1a;

public class Person {
    private String name;
    private BankAccountAccessProxy bankAccountProxy;
    private User user = User.OWNER;

    public Person(String n) {
        name = n;
        BankAccountImpl bankAccount = new BankAccountImpl("PeDeMeia",0);
        this.bankAccountProxy = new BankAccountAccessProxy(bankAccount);
    }
    public String getName() {
        return name;
    }
    public BankAccount getBankAccount() {
        return bankAccountProxy;
    }
}
