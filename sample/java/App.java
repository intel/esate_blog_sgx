// Copyright (c) 2024, Intel Corporation. All rights reserved.<BR>
// SPDX-License-Identifier: BSD-3-Clause

class App {
	public static void main(String[] args) {
		GlobalErrorHandler globalErrorHandler = new GlobalErrorHandler();
		Thread.setDefaultUncaughtExceptionHandler(globalErrorHandler);
		App.run();
	}

	private static void run() {
		// Default count to number.
		int max_count = 10;

		// Check environment variables for a different max_count.
		String max_count_env_var = System.getenv("max_count");

		if ( max_count_env_var != null && !max_count_env_var.isEmpty()) {
			try {
				max_count = Integer.parseInt(max_count_env_var);
				if (max_count < 1) {
					System.out.println("\nOnly positive numbers can be used for the count.\n");
					System.err.println("\n*** Application will terminate. ***\n");
					System.exit(1);
				}
			} catch (NumberFormatException  ex) {
				System.out.println("\nNumberFormatException: Error while parsing max_count environment variable. Make sure it is an integer and try again.\n");
				System.err.println("\n*** Application will terminate. ***\n");
				System.exit(1);
			}
		}

		int c = 0;
		System.out.printf("Application will count to %d.\n\n", max_count);
		while (c < max_count) {
			c++;
			System.out.printf("Current count is %d out of %d.\n", c, max_count);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException ex) {
				System.out.println("\nInterruptedException: Error occurred while trying to wait for next count...\n");
				System.err.println("\n*** Application will terminate. ***\n");
				System.exit(1);
			}

			if (c == max_count) {
				System.out.println("\n\nCycle complete. Restarting count...\n\n");
				c = 0;
			}
		}
	}
}