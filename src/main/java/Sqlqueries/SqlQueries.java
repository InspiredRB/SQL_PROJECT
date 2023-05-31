package Sqlqueries;

import java.sql.*;

public class SqlQueries {

    private final String url = "jdbc:mysql://localhost:3306/testbankaccount";
    private final String user = "root";
    private final String password = "InspiredPassword123";

    private String address = "street ,house_number, zip_code, city, country ";

    public void CustomerAddress(int ID) {
        String SelectFromCustomers = "SELECT street, house_number, zip_code, city, country FROM customers WHERE customer_id = " + ID;
        // Establish the connection to database
        try (Connection conn = DriverManager.getConnection(this.url , this.user , this.password);
             PreparedStatement ps = conn.prepareStatement(SelectFromCustomers);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                String street = rs.getString("street");
                String houseNumber = rs.getString("house_number");
                String zipCode = rs.getString("zip_code");
                String city = rs.getString("city");
                String country = rs.getString("country");

                System.out.println("This Address belongs to to Customer ID:" + ID +
                        "\n\nAddress: " +street+", "+houseNumber+", "+zipCode+", "+city+", "+country);
            }
        }
            catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public void TotalAccountBalance(int ID){
        String SelectFromCustomers = "SELECT SUM(current_balance) FROM accounts WHERE customer_id = " + ID;

        try(Connection conn = DriverManager.getConnection(this.url, this.user, this.password);
        PreparedStatement ps = conn.prepareStatement(SelectFromCustomers);
        ResultSet rs = ps.executeQuery()){

            while (rs.next()){

                double balance = rs.getDouble("SUM(current_balance)");
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }

    public void AllTransactions(int id){
        String SelectFromCustomers ="SELECT accounts.account_number , transactions.transaction_date , transactions.amount , transactions.transaction_type FROM accounts\n" +
                "INNER JOIN transactions\n" +
                "ON accounts.account_number = transactions.account_number\n" +
                "WHERE accounts.account_type = 'Savings'\n" +
                "AND accounts.customer_id = " + id;

        try (Connection conn = DriverManager.getConnection(this.url, this.user , this.password);
        PreparedStatement ps = conn.prepareStatement(SelectFromCustomers);
        ResultSet rs = ps.executeQuery()){

            System.out.println("The following Information belongs to Customer ID: " + id);

            while (rs.next()){
                String accountNumber = rs.getString("account_number");
                String transactionDate = String.valueOf(rs.getDate("transaction_date"));
                double amount = rs.getDouble("amount");
                boolean transactionType = rs.getBoolean("transaction_type");

                String transType;
                if (transactionType) {
                    transType = "Deposit";
                } else {
                    transType = "Withdrawal";
                }

                System.out.println("\n Account Number : " + accountNumber +
                                        "\nDate : " + transactionDate +
                                        "\nTransaction Type: " + transactionType + "\n");
            }
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }

    }
    public int TotalNumberOfCustomer(){
        String SelectFromCustomers = "SELECT count(*) FROM customers";
        int count = 0;

        try (Connection conn = DriverManager.getConnection(this.url, this.user , this.password);
             PreparedStatement ps = conn.prepareStatement(SelectFromCustomers);
             ResultSet rs = ps.executeQuery()){

            rs.next();
            count = rs.getInt("count(*)");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return count;
    }
    public int ChecksThatTheUserWithFirstNameSarahHasTwoAccounts(){
        String SelectFromCustomers = "SELECT count(account_number) FROM customers WHERE customer_id = 1";
        int count = 0;

        try (Connection conn = DriverManager.getConnection(this.url, this.user , this.password);
             PreparedStatement ps = conn.prepareStatement(SelectFromCustomers);
             ResultSet rs = ps.executeQuery()){

            rs.next();
            count = rs.getInt("count(account_number)");
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return count;
    }
    public double totalOfAllDepositTransactions(String accountNumber){
        String SelectFromCustomers = "Select amount FROM transactions WHERE transaction_type = 'DEPOSIT' AND account_number = " +accountNumber+ "" ;

        double amount = 0 ;
        double total = 0;

        try (Connection conn = DriverManager.getConnection(this.url, this.user , this.password);
             PreparedStatement ps = conn.prepareStatement(SelectFromCustomers);
             ResultSet rs = ps.executeQuery()){

            while (rs.next()){
                amount  =rs.getDouble("amount");

                total = total + amount;
            }

        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return total;
    }
    public double totalOfAllWithdrawalTransactions(String accountNumber){
        String SelectFromCustomers = "Select amount FROM transactions WHERE transaction_type = 'WITHDRAWAL' AND account_number = " +accountNumber+ "" ;

        double amount = 0;
        double total = 0;

        try (Connection conn = DriverManager.getConnection(this.url, this.user , this.password);
             PreparedStatement ps = conn.prepareStatement(SelectFromCustomers);
             ResultSet rs = ps.executeQuery()){

            while (rs.next()){
                amount  =rs.getDouble("amount");

                total = total + amount;
            }

        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        return total;
    }
}


