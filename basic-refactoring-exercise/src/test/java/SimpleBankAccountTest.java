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
    public static final int WRONG_WITHDRAW_AMOUNT = 200;
    private AccountHolder accountHolder;
    private BankAccount bankAccount;

    @BeforeEach
    void setUpEnviroment(){
        accountHolder = new AccountHolder("Mario", "Rossi", 1);

        bankAccount = new SimpleBankAccount(accountHolder, EMPTY_BALANCE);
    }

    private int getWrongID(int userId) {
        return userId + 1;
    }

    @Test
    void testInitialBalance() {
        assertAll(
                () -> assertEquals(EMPTY_BALANCE, bankAccount.getBalance()),
                () -> assertEquals(accountHolder, bankAccount.getHolder())
        );
    }

    @Test
    void testDeposit() {
        bankAccount.deposit(accountHolder.getId(), INITIAL_AMOUNT);
        assertEquals(INITIAL_AMOUNT, bankAccount.getBalance());
    }

    @Test
    void testWrongUserDeposit() {
        bankAccount.deposit(accountHolder.getId(), INITIAL_AMOUNT);
        bankAccount.deposit(getWrongID(bankAccount.getHolder().getId()), DEPOSIT_AMOUNT);
        assertEquals(INITIAL_AMOUNT, bankAccount.getBalance());
    }

    @Test
    void testWithdraw() {
        bankAccount.deposit(accountHolder.getId(), INITIAL_AMOUNT);
        bankAccount.withdraw(accountHolder.getId(), WITHDRAW_AMOUNT);
        assertEquals(INITIAL_AMOUNT - WITHDRAW_AMOUNT - SimpleBankAccount.WITHDRAWAL_FEE, bankAccount.getBalance());
    }

    @Test
    void testWrongUserWithdraw() {
        bankAccount.deposit(accountHolder.getId(), INITIAL_AMOUNT);
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(getWrongID(bankAccount.getHolder().getId()), WITHDRAW_AMOUNT));
    }

    @Test
    void testWrongAmountWithdraw() {
        bankAccount.deposit(accountHolder.getId(), INITIAL_AMOUNT);
        assertThrows(IllegalArgumentException.class, () -> bankAccount.withdraw(accountHolder.getId(), WRONG_WITHDRAW_AMOUNT));
    }
}
