package jdd;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Arrays {

	public static void main(String[] args) {
		int[] primeNumbers = {2, 3, 5, 7, 11, 13};
		
		System.out.println(primeNumbers[2]);
		System.out.println(primeNumbers.length);
		
		List myList = new ArrayList();
		myList.add(2);
		myList.add(3);
		myList.add(5);
		
		System.out.println(myList.toString());
		
		myList.remove(1);
		
		System.out.println(myList.get(1));
		
		List countries = new ArrayList();
		countries.add("England");
		countries.add("Russia");
		countries.add("China");
		
		countries.remove(2);
		
		System.out.println(countries.toString());
		
		Map map = new HashMap();
		map.put("Father", "Rob");
		System.out.println(map.get("Father"));
	}

}
