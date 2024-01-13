import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class BackAccount {
    final public Integer id;
    public Integer balance;
    Map<TransactionType, Integer> logs = new HashMap<TransactionType, Integer>();
    private final Lock _lock = new ReentrantLock();

    final public Integer initialBalance;

    public BackAccount(Integer id, Integer initialBalance) {
        this.id = id;
        this.balance = initialBalance;
        this.initialBalance = initialBalance;
    }

    public void add(Integer amount) {
        this.balance += amount;
        this.logs.putIfAbsent(TransactionType.in, 0);
        this.logs.put(TransactionType.in, this.logs.get(TransactionType.in) + amount);
    }

    public void subtract(Integer amount) throws Exception {
        if (this.balance < amount) {
            throw new Exception("invalid amount");
        }
        this.balance -= amount;
        this.logs.putIfAbsent(TransactionType.out, 0);
        this.logs.put(TransactionType.out, this.logs.get(TransactionType.out) + amount);
    }

    public void lock(){
        _lock.lock();
    }

    public void unLock(){
        _lock.unlock();
    }

    @Override
    public String toString() {
        return "BackAccount{" +
                "id=" + id +
                ", balance=" + balance +
                ", logs=" + logs +
                ", initialBalance=" + initialBalance +
                ", _lock=" + _lock +
                '}';
    }
}
enum TransactionType{
    in,
    out
}