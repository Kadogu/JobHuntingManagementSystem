package test.bin;

import bin.CreateID;

public class CreateIDTest {

	public static void main(String[] args) {
		int digits = 8;
		String id = CreateID.createID(digits);
		System.out.println(id);
	}
}