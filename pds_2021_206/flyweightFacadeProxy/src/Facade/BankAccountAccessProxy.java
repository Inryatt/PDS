package Ex2;

public class BankAccountAccessProxy implements BankAccount {
    private BankAccount bankAccount;

    public BankAccountAccessProxy(BankAccountImpl account) {
        this.bankAccount = account;
    }

    private boolean checkAccess() {
        return Company.user == User.OWNER;
    }

    @Override
    public void deposit(double amount) {
        bankAccount.deposit(amount);
    }

    @Override
    public boolean withdraw(double amount) {
        if(!checkAccess()) return false;
        else return withdraw(amount);
    }

    @Override
    public double balance() {
        if(checkAccess()) return bankAccount.balance();
        else return -1;
    }
}
