package jdd;
import java.util.Arrays;
public class SplitString {

	public static void main(String[] args) {
		String myString = "Nick x Ash x Mike";
		String[] splitString = myString.split(" x ");
		System.out.println(Arrays.toString(splitString));
		
		String river = "Mississipi";
		String[] splitString2 = river.split("s");
		System.out.println(Arrays.toString(splitString2));
		
		String riverPart = river.substring(2, 5);
		System.out.println(riverPart);
		
	}

}
