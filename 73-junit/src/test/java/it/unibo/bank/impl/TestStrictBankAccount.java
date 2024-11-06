package it.unibo.bank.impl;

import it.unibo.bank.api.AccountHolder;
import it.unibo.bank.api.BankAccount;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.fail;

import org.junit.jupiter.api.Assertions;

/**
 * Test class for the {@link StrictBankAccount} class.
 */
class TestStrictBankAccount {

    // Create a new AccountHolder and a StrictBankAccount for it each time tests are executed.
    private AccountHolder mRossi;
    private BankAccount bankAccount;

    private static final int AMOUNT = 100;
    public static final double TRANSACTION_FEE = 0.1;
    public static final double MANAGEMENT_FEE = 5;

    /**
     * Prepare the tests.
     */
    @BeforeEach
    public void setUp() {
        this.mRossi = new AccountHolder("Mario","Rossi", 1);
        this.bankAccount = new StrictBankAccount(mRossi, 0.0);
    }

    /**
     * Test the initial state of the StrictBankAccount.
     */
    @Test
    public void testInitialization() {
        assertEquals(0.0, bankAccount.getBalance());
        assertEquals(0, bankAccount.getTransactionsCount());
        assertEquals(mRossi, bankAccount.getAccountHolder());
    }

    /**
     * Perform a deposit of 100€, compute the management fees, and check that the balance is correctly reduced.
     */
    @Test
    public void testManagementFees() {
        bankAccount.deposit(mRossi.getUserID(), AMOUNT);
        double feeAmount = MANAGEMENT_FEE + bankAccount.getTransactionsCount() * TRANSACTION_FEE;
        double expected = bankAccount.getBalance() - feeAmount;      
        bankAccount.chargeManagementFees(mRossi.getUserID());
        assertEquals(expected, bankAccount.getBalance());
    }

    /**
     * Test that withdrawing a negative amount causes a failure.
     */
    @Test
    public void testNegativeWithdraw() {
        double initialBalance = bankAccount.getBalance();
        try{  
            bankAccount.withdraw(mRossi.getUserID(), -AMOUNT);
            Assertions.fail("Withdrawing negative amount should have thrown an exception");
        }catch(IllegalArgumentException e){
            assertEquals(initialBalance, bankAccount.getBalance());
            assertNotNull(e.getMessage());
            assertFalse(e.getMessage().isBlank());
        }
        
    }

    /**
     * Test that withdrawing more money than it is in the account is not allowed.
     */
    @Test
    public void testWithdrawingTooMuch() {
        double initialBalance = bankAccount.getBalance();
        try{  
            bankAccount.withdraw(mRossi.getUserID(), AMOUNT);
            Assertions.fail("Withdrawing more than the current balance should have thrown an exception");
        }catch(IllegalArgumentException e){
            assertEquals(initialBalance, bankAccount.getBalance());
            assertNotNull(e.getMessage());
            assertFalse(e.getMessage().isBlank());
        }
    }
}
