package jdd;

import java.util.ArrayList;
import java.util.List;

public class Loops {

	public static void main(String[] args) {
		int x = 1;
		while (x <= 10) {
			System.out.println(x);
			x += 2;
		}

		for (int y = 1; y <= 5; y++) {
			System.out.println(y*2);
		}

		for (int y = 5; y >= 0; y--) {
			System.out.println(y*2);
		}

		for (int y = 1; y <= 10; y++) {
			System.out.println(y*(y+1)/2);
		}


		String[] arr = {"Dima", "Sveta", "Masha"};
		for (String s: arr) {
			System.out.println(s);
		}
		
		List arr2 = new ArrayList();
		arr2.add("Rob");
		arr2.add("Dylan");
		arr2.add("Mike");
		arr2.add("Nelly");
		for (Object s: arr2) {
			System.out.println(s.toString());
		}
	}

}
