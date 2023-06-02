package org.example;

import Sqlqueries.SqlQueries;

import java.util.Scanner;

public class Main {
    public static void main(String[] args){
        SqlQueries sql =  new SqlQueries();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter Customer ID :");
        int id = scanner.nextInt();

         sql.CustomerAddress(id);
//       sql.TotalAccountBalance(id);
//       sql.AllTransactions(id);
    }
}