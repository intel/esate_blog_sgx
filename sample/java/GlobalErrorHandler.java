public class GlobalErrorHandler implements Thread.UncaughtExceptionHandler {
	public void uncaughtException(Thread thead, Throwable ex) {
		System.out.println("\nUnexpected event while running the application. Exiting...\n");
		System.err.println("\n*** Application will terminate. ***\n");
		System.exit(1);
	}
}