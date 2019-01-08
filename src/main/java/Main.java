import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

public class Main {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_RED = "\u001b[31;1m";
    public static final String ANSI_GREEN = "\u001b[32;1m";

    private static List<Account> accountList;

    private static int accountCount;

    private static float AccountDefaultAmount;

    public static void main(String[] args) {
        accountCount = ConsoleView.getAccountsCount();
        AccountDefaultAmount = ConsoleView.getAccountDefaultAmount();
        accountList = AccountGenerator.generateAccountList(accountCount, AccountDefaultAmount);
        accountList.forEach(System.out::println);
        ExecutorService EXECUTOR_SERVICE = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        List<Callable<Transaction>> taskList = new ArrayList<>();
        Counter counter = new Counter(0);
        List<Transaction> transactions = new ArrayList<>();
        for(int i=0;i<50;i++) {
            taskList.add(new Task(accountCount, accountList, counter));
        }
        try {
            List<Future<Transaction>> results = EXECUTOR_SERVICE.invokeAll(taskList);
            for (Future<Transaction> future : results) {
                try {
                    transactions.add(future.get());
                }
                catch (ExecutionException ex) {
                    ex.printStackTrace();
                }
            }
        }
        catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        EXECUTOR_SERVICE.shutdown();
        for(Account account: accountList) {
            System.out.println(account.toString());
            System.out.println("Транзакции по аккаунту: ");
            for(Transaction transaction: transactions) {
                if (account == transaction.getRecipientAccount()) {
                    System.out.println(ANSI_GREEN + transaction.toString() + ANSI_RESET);
                }
                if (account == transaction.getSourceAccount()) {
                    System.out.println(ANSI_RED + transaction.toString() + ANSI_RESET);
                }
            }
            System.out.println("----------------------------");

        }
        System.out.println("Сумма на всех счетах после выполнения переводов: "
                + accountList.stream().mapToDouble(Account::getMoneyAmount).sum());
        System.out.println("Количество успешных транзакций: " + counter.getCount());
    }

}
