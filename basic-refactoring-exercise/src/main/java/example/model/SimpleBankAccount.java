package example.model;

/**
 * This class represent a particular instance of a BankAccount.
 * In particular, a Simple Bank Account allows always the deposit
 * while the withdrawal is allowed only if the balance greater or equal the withdrawal amount
 */
public class SimpleBankAccount implements BankAccount {

    public static final int WITHDRAWAL_FEE = 1;
    private double balance;
    private final AccountHolder holder;

    public SimpleBankAccount(final AccountHolder holder, final double balance) {
        this.holder = holder;
        this.balance = balance;
    }
    @Override
    public AccountHolder getHolder(){
        return this.holder;
    }

    @Override
    public double getBalance() {
        return this.balance;
    }

    @Override
    public void deposit(final int userID, final double amount) {
        if (checkUser(userID)) {
            this.balance += amount;
        } else throw new IllegalArgumentException("User " + userID + " is not the bank account holder");
    }

    @Override
    public void withdraw(final int userID, final double amount) {
        if (!checkUser(userID)) throw new IllegalArgumentException("User " + userID + " is not the bank account holder");
        if (isWithdrawAllowed(amount)) {
            this.balance -= amount + WITHDRAWAL_FEE;
        } else throw new IllegalArgumentException("Insufficient account balance!");
    }

    private boolean isWithdrawAllowed(final double amount){
        return this.balance >= amount + WITHDRAWAL_FEE;
    }

    private boolean checkUser(final int id) {
        return this.holder.getId() == id;
    }
}
