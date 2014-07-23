package com.abc.rates;

import com.abc.Account;
import com.abc.AccountType;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SavingsInterestRateCalculatorTest {

    @Test
    public void shouldHaveAFlatRateOfZeroPointOnePercentForTheFirstThousandDollars() throws Exception {
        final double[] amounts = {0.01, 0.02, 0.03, 0.04, 0.05, 0.06, 0.07, 0.08, 0.09, 0.10, 0.99, 1.00, 1000.00};
        for (double amount : amounts) {
            final Account account = new Account(AccountType.Savings);
            account.deposit(amount);
            assertThat("Invalid interest earned after depositing " + amount, account.interestEarned()/0.001, is(account.currentBalance()));
        }
    }

    @Test
    public void shouldHaveAFlatRateOfZeroPointTwoPercentAfterTheFirstThousandDollars() throws Exception {
        final double[] amounts = {1000.01, 1000.03, 1000.05, 1001.00, 1000000.00};
        for (double amount : amounts) {
            final Account account = new Account(AccountType.Savings);
            account.deposit(amount);
            assertThat("Invalid interest earned after depositing " + amount, ((account.interestEarned() - 1)/0.002) + 1000.00, is(account.currentBalance()));
        }
    }

}
