import java.util.List;
import java.util.Random;
import java.util.concurrent.Callable;

public class Task implements Callable<Transaction> {

    private final int DELAY_LOW_LIMIT = 1000;

    private int accountCount;

    List<Account> accountList;

    Counter counter;

    Task(int accountCount, List<Account> accountList, Counter counter) {
        this.accountCount = accountCount;
        this.accountList = accountList;
        this.counter = counter;
    }

    @Override
    public Transaction call() throws Exception {
        Random random = new Random();
        int delay = random.nextInt(1000) + DELAY_LOW_LIMIT;
        int[] twoValues = generateTwoRandomUniqueValues(accountCount);
        try {
            Thread.sleep(delay);
            Transaction transaction = new Transaction(accountList.get(twoValues[0]), accountList.get(twoValues[1]), delay);
            if (transaction.isAccepted()) {
                counter.addOne();
            }
            return transaction;
        } catch (InterruptedException e) {
            e.printStackTrace();
            return null;
        }

    }

    private static int[] generateTwoRandomUniqueValues(int bound) {
        Random random = new Random();
        int value1 = random.nextInt(bound);
        int value2;
        do {
            value2 = random.nextInt(bound);
        } while (value1==value2);
        int[] valueArray = {value1, value2};
        return valueArray;
    }
}