package test.bin;

import java.sql.Date;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import bin.DateConversion;

public class DateConvertionTest {

	public static void main(String[] args) {
		LocalDate localDate = LocalDate.now();
//		LocalDate localDate = null;
		Date date = DateConversion.dateConversion(localDate);
		if(date != null){
			System.out.println(date.toString());
		}else{
			System.out.println("nullです");
		}

		date = Date.valueOf(LocalDate.of(2018, 12, 31));
//		date = null;
		localDate = DateConversion.localDateConversion(date);
		if(localDate != null){
			DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
			System.out.println(localDate.format(f));
		}else{
			System.out.println("nullです");
		}

		String input_date = "2018-11-07";
//		String input_date = null;
		localDate = DateConversion.input_dateConversion(input_date);
		if(localDate != null){
			DateTimeFormatter f = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
			System.out.println(localDate.format(f));
		}else{
			System.out.println("nullです");
		}
	}
}