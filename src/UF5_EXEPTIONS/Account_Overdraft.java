package UF5_EXEPTIONS;

public class Account_Overdraft extends Exception {
    public Account_Overdraft(String message) {
        super(message);
    }
}
