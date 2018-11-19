package test.bin;

import java.util.ArrayList;

import bin.Cast;
import dto.Company;

public class CastTest {

	public static void main(String[] args) {
		ArrayList<Company> list = new ArrayList<Company>();
		list.add(new Company("fasega","株式会社","090-4747","岩手県盛岡市","098-747-1642"));

		System.out.println(list.get(0).getCompany_id());
		System.out.println(list.get(0).getCompany_name());
		System.out.println(list.get(0).getPostal_code());
		System.out.println(list.get(0).getAddress());
		System.out.println(list.get(0).getPhone_number() + "\n");

		Object obj = (Object) list;
		ArrayList<Company> cast = Cast.autoCast(obj);

		System.out.println(cast.get(0).getCompany_id());
		System.out.println(cast.get(0).getCompany_name());
		System.out.println(cast.get(0).getPostal_code());
		System.out.println(cast.get(0).getAddress());
		System.out.println(cast.get(0).getPhone_number());
	}
}