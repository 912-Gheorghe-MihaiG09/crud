// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.

import java.util.*;

public class Main {
    // generate random account: id(index), balance, logs
    // create threads
    // threads transfers money between accounts : 1. lock accounts depending on id
    //                                            2. transfer the money
    //                                            3. unlock in reverse
    //                                            4. sleep at random

    // main thread => start the application

    // consistency check thread => lock all account => check logs at random moments => undo logs and see if it comes back to the initial sum
    public static void main(String[] args) {
        List<BackAccount> accounts = new ArrayList<>();
        Random random = new Random();

        // Create 10 random BankAccounts
        for (int i = 1; i <= 10; i++) {
            Integer initialBalance = random.nextInt(1000); // Random initial balance
            BackAccount account = new BackAccount(i, initialBalance);
            accounts.add(account);
        }

        // Create and start transaction handling threads
        TransactionHandler handler1 = new TransactionHandler(accounts, 0);
        TransactionHandler handler2 = new TransactionHandler(accounts, 1);
        TransactionHandler handler3 = new TransactionHandler(accounts, 2);
        ConsistencyChecker consistencyChecker = new ConsistencyChecker(accounts, 3);

        handler1.start();
        handler2.start();
        handler3.start();
        consistencyChecker.start();
    }

}