import java.util.InputMismatchException;
import java.util.Scanner;

public class ConsoleView {

    public static int getAccountsCount() {
        int accountsCount = 0;
        while(accountsCount <= 1) {
            try {
                accountsCount = ConsoleView.getValueScanner1();
                if (accountsCount <=1 ){
                    System.out.println("Значние должно быть больше единицы.");
                }
            }
            catch (InputMismatchException ex) {
                System.out.println("Неверный формат введеного значения. Попробуйте снова.");
            }
        }
        return accountsCount;
    }

    public static float getAccountDefaultAmount() {
        float accountDefaultAmount = 0;
        while(accountDefaultAmount <= 0) {
            try {
                accountDefaultAmount = ConsoleView.getValueScanner2();
                if (accountDefaultAmount <=0 ){
                    System.out.println("Значние должно быть больше нуля.");
                }
            }
            catch (InputMismatchException ex) {
                System.out.println("Неверный формат введеного значения. Попробуйте снова.");
            }
        }
        return accountDefaultAmount;
    }

    private static int getValueScanner1() throws InputMismatchException {
        System.out.println("Введите количество счетов (целое значение)");
        final Scanner scanner1= new Scanner(System.in);
        return scanner1.nextInt();
    }

    private static float getValueScanner2() throws InputMismatchException {
        System.out.println("Введите начальную сумму на счетах (целое или вещественное число)");
        final Scanner scanner2= new Scanner(System.in);
        return scanner2.nextFloat();
    }
}
