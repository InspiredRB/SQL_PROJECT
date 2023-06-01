package sql;

import Sqlqueries.SqlQueries;
import org.example.Main;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SqlTest {

    // Retrieve the number of customers from the database
    // Check that the total number of customers is equal to 2
    @Test
    public void getAllCustomers_checkNumber_shouldBeTwo() {
        SqlQueries sql = new SqlQueries();

        assertEquals(2,sql.TotalNumberOfCustomer(), "Total number of Customers are not equal to 2 ");
    }

    // Retrieve all accounts for customer Sarah from the database
    // Check that the total number of accounts for her is 2
    @Test
    public void getAccountsForSarah_checkNumber_shouldBeTwo() {
        SqlQueries sql = new SqlQueries();
        assertEquals(2,sql.ChecksThatTheUserWithFirstNameSarahHasTwoAccounts(),"Sarah does not have 2 accounts");
    }

    // A test that checks that the total of all transactions is equal to 0 for a given account.
    // Doing this for a single account is enough,
    // and you’re allowed to use the account ID for that account in the query
    @Test
    public void retrieveTransactionsForAccount_checkTotalBalance_shouldBeZero()
    {
        SqlQueries sql = new SqlQueries();

        String accountNumber = "83983800";

        double totalDeposits = sql.totalOfAllDepositTransactions(accountNumber);

        double totalWithdrawals = sql.totalOfAllWithdrawalTransactions(accountNumber);

        double balance = totalDeposits - totalWithdrawals;

        assertEquals(20, balance , "Balance was not 20");

    }
}

