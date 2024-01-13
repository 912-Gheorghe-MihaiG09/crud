import java.util.List;
import java.util.Random;

public class TransactionHandler extends Thread {
    final List<BackAccount> accounts;
    final Integer threadId;
    private final Random random;

    public TransactionHandler(List<BackAccount> accounts, Integer threadId) {
        this.accounts = accounts;
        this.threadId = threadId;
        this.random = new Random(threadId);
    }

    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            // select the account randomly
            int inAccountIndex = random.nextInt(accounts.size());
            int outAccountIndex = random.nextInt(accounts.size());
            // make sure the accounts are different
            while(inAccountIndex == outAccountIndex){
                outAccountIndex = random.nextInt(accounts.size());
            }
            BackAccount inAccount = accounts.get(inAccountIndex);
            BackAccount outAccount = accounts.get(outAccountIndex);

//            _lockResources(inAccount, outAccount);

            inAccount = accounts.get(inAccountIndex);
            outAccount = accounts.get(outAccountIndex);

            System.out.println("Thread number : " + this.threadId + " choose accounts: ");
            System.out.println("in account: " + inAccount.toString());
            System.out.println("out account: " + outAccount.toString());
            Integer amount = random.nextInt(outAccount.balance);
            System.out.println("transaction amount: " + amount);

            try {
                outAccount.subtract(amount);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            inAccount.add(amount);

            System.out.println("result: ");
            System.out.println("in account: " + inAccount.toString());
            System.out.println("out account: " + outAccount.toString());
//            _unLockResources(inAccount, outAccount);

            int sleepTime = random.nextInt(100);

            try {
                sleep(sleepTime);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void _lockResources(BackAccount inAccount, BackAccount outAccount){
        if(inAccount.id > outAccount.id){
            inAccount.lock();
            outAccount.lock();
        } else {
            outAccount.lock();
            inAccount.lock();
        }
    }

    private void _unLockResources(BackAccount inAccount, BackAccount outAccount){
        if(inAccount.id > outAccount.id){
            outAccount.unLock();
            inAccount.unLock();
        } else {
            inAccount.unLock();
            outAccount.unLock();
        }
    }
}
