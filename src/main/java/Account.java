public class Account {

    private final String ID;

    private float moneyAmount;

    private boolean accountIsOccupied;

    Account(String ID, float moneyAmount) {
        this.ID = ID;
        this.moneyAmount = moneyAmount;
    }

    @Override
    public String toString() {
        return "Счёт №" + ID + "; Остаток на счёте: " + moneyAmount + " р.";
    }

    synchronized boolean withdrawMoney(float sum) {
        if (moneyAmount >= sum) {
            moneyAmount = moneyAmount - sum;
            return true;
        }
        else
            return false;
    }

    synchronized void creditMoney(float sum) {
        moneyAmount = moneyAmount + sum;
    }

    public String getID() {
        return ID;
    }

    public float getMoneyAmount() {
        return moneyAmount;
    }
}
