import java.time.LocalDateTime;

import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.LogManager;

public class Transaction {

    private final Account sourceAccount;

    private final Account recipientAccount;

    private final float transactionAmount;

    private LocalDateTime transactionTime;

    private boolean accepted;

    private final static Logger logger = LogManager.getLogger(Transaction.class);

    Transaction(Account sourceAccount,
                Account recipientAccount,
                float transactionAmount) {
        this.sourceAccount = sourceAccount;
        this.recipientAccount = recipientAccount;
        this.transactionAmount = transactionAmount;
        makeTransaction();
    }

    @Override
    public String toString() {
        StringBuilder transactionStatus = new StringBuilder();
        if (accepted)
            transactionStatus.append(Float.toString(transactionAmount));
        else
            transactionStatus.append("операция отклонена");
        return getDateTime() + " Номер счёта отправителя: " + sourceAccount.getID()
                + "; номер счёта получателя: " + recipientAccount.getID()
                + "; сумма перевода: " + transactionStatus;
    }

    public Account getSourceAccount() {
        return sourceAccount;
    }

    public Account getRecipientAccount() {
        return recipientAccount;
    }

    public float getTransactionAmount() {
        return transactionAmount;
    }

    public LocalDateTime getTransactionTime() {
        return transactionTime;
    }

    public boolean isAccepted() {
        return accepted;
    }

    private String getDateTime() {
        return makeNormalFormat(transactionTime.getHour())
                + ":" + makeNormalFormat(transactionTime.getMinute())
                + ":" + makeNormalFormat(transactionTime.getSecond())
                + " " + makeNormalFormat(transactionTime.getDayOfMonth())
                + "." + makeNormalFormat(transactionTime.getMonth().getValue())
                + "." + transactionTime.getYear();
    }

    private void makeTransaction() {
        transactionTime = LocalDateTime.now();
        if (sourceAccount.withdrawMoney(transactionAmount)) {
            recipientAccount.creditMoney(transactionAmount);
            this.accepted = true;
        }
        else {
            this.accepted = false;
        }
        logger.info(this::toString);
    }

    private String makeNormalFormat(int value) {
        String valueStr = Integer.toString(value);
        if (value <= 9)
            return "0" + valueStr;
        else
            return valueStr;
    }
}