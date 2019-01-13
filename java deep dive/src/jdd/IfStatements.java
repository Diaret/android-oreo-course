package jdd;

public class IfStatements {

	public static void main(final String[] args) {
		final int age = 18;

		if (2 > 1) {
			System.out.println("2 is greater than 1");
		}

		if (age > 18) {
			System.out.println("User is over 18");
		} else if (age < 18) {
			System.out.println("User is under 18");
		} else {
			System.out.println("User is right 18");
		}

		final int[] arr = {1, 2};

		if (arr[0] > arr[1]) {
			System.out.println("1st is greater than 2nd");
		} else {
			System.out.println("1st is not greater than 2nd");
		}
	}
}
