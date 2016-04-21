public class BadTransactionException extends Exception {
	public int money;

	public BadTransactionException(int badtry){
		super("Invalid transaction money:"+badtry);
		money = badtry;
	}
}