import java.util.List;
import java.util.Map;
import java.util.Random;

public class ConsistencyChecker extends Thread{
    final List<BackAccount> accounts;
    final Integer threadId;
    private final Random random;

    public ConsistencyChecker(List<BackAccount> accounts, Integer threadId) {
        this.accounts = accounts;
        this.threadId = threadId;
        this.random = new Random(threadId);
    }

    @Override
    public void run() {
        while(true){
            try {
                sleep(random.nextInt(100, 1000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            _lockResources();
            try {
                _checkLogs();
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
            _unlockResources();
        }
    }

    private void _lockResources(){
        for(BackAccount account : accounts){
            account.lock();
        }
    }

    private void _unlockResources(){
        for(BackAccount account : accounts){
            account.unLock();
        }
    }

    private void _checkLogs() throws Exception {
        for(BackAccount account : accounts){
            Integer value = account.initialBalance;
            for (Map.Entry<TransactionType, Integer> logEntry : account.logs.entrySet()) {
                TransactionType type = logEntry.getKey();
                Integer amount = logEntry.getValue();
                switch (type){
                    case in -> value += amount;
                    case out -> value -= amount;
                }
            }
            if(account.balance.intValue() != value.intValue()){
                throw new Exception("check failed for bank account: " + account.toString() + " determined value from logs:" + value.toString());
            }
        }
    }
}
