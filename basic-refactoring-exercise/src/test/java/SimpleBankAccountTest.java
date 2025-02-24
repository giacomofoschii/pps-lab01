import example.model.AccountHolder;
import example.model.BankAccount;
import example.model.SimpleBankAccount;

import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The test suite for testing the SimpleBankAccount implementation
 */
class SimpleBankAccountTest {

    public static final int INITIAL_AMOUNT = 100;
    public static final int EMPTY_BALANCE = 0;
    public static final int WITHDRAW_AMOUNT = 70;
    public static final int DEPOSIT_AMOUNT = 50;
    public static final int USER_ID1 = 1;
    public static final int USER_ID2 = 2;
    private AccountHolder accountHolder;
    private BankAccount bankAccount;

    @BeforeEach
    void setUpEnviroment(){
        accountHolder = new AccountHolder("Mario", "Rossi", USER_ID1);

        bankAccount = new SimpleBankAccount(accountHolder, EMPTY_BALANCE);
    }

    @Test
    void testInitialBalance() {
        assertEquals(EMPTY_BALANCE, bankAccount.getBalance());
    }

    @Test
    void testDeposit() {
        bankAccount.deposit(accountHolder.getId(), INITIAL_AMOUNT);
        assertEquals(INITIAL_AMOUNT, bankAccount.getBalance());
    }

    @Test
    void testWrongUserDeposit() {
        bankAccount.deposit(accountHolder.getId(), INITIAL_AMOUNT);
        bankAccount.deposit(USER_ID2, DEPOSIT_AMOUNT);
        assertEquals(INITIAL_AMOUNT, bankAccount.getBalance());
    }

    @Test
    void testWithdraw() {
        bankAccount.deposit(accountHolder.getId(), INITIAL_AMOUNT);
        bankAccount.withdraw(accountHolder.getId(), WITHDRAW_AMOUNT);
        assertEquals(INITIAL_AMOUNT - WITHDRAW_AMOUNT - SimpleBankAccount.FEE, bankAccount.getBalance());
    }

    @Test
    void testWrongUserWithdraw() {
        bankAccount.deposit(accountHolder.getId(), INITIAL_AMOUNT);
        bankAccount.withdraw(USER_ID2, WITHDRAW_AMOUNT);
        assertEquals(INITIAL_AMOUNT, bankAccount.getBalance());
    }
}
