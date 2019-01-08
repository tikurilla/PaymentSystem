import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.ThreadLocalRandom;

import java.util.List;

public class AccountGenerator {

    private static final int MAX_ACCOUNT_ID = 999999;

    public static List<Account> generateAccountList(int accountCount, float accountDefaultAmount) {
        List<String> accountIDs = generateAccountIDs(accountCount);
        List<Account> accountsList = new ArrayList<>();
        for(int i=0;i<accountCount;i++) {
            accountsList.add(new Account(accountIDs.get(i), accountDefaultAmount));
        }
        return accountsList;
    }

    private static List<String> generateAccountIDs(int accountCount) {
        Iterator iterator = ThreadLocalRandom.
                            current().
                            ints(0, MAX_ACCOUNT_ID).
                            distinct().
                            iterator();
        List<String> listIDs = new ArrayList<>();
        for (int i = 0; i < accountCount; i++) {
            listIDs.add(iterator.next().toString());
        }
        return listIDs;
    }

    public static int getMaxAccountId() {
        return MAX_ACCOUNT_ID;
    }
}
