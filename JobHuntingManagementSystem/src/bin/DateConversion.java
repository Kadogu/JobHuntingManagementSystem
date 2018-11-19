package bin;

import java.sql.Date;
import java.time.LocalDate;

public class DateConversion {

	/** LocalDateからsql.Dateに変換するために使用
	 *  @param localDate - 変換したいLocalDate型のインスタンス
	 *  @return date - 変換した後のsql.Date型のインスタンス
	 */
	public static Date dateConversion(LocalDate localDate){
		if(localDate != null){	//localDateがnullじゃない場合
			return Date.valueOf(localDate);
		}else{	//nullの時
			return null;
		}
	}

	/** sql.DateからLocalDateに変換するために使用
	 *  @param date - 変換したいsql.Date型のインスタンス
	 *  @return localDate - 変換した後のLocalDate型のインスタンス
	 */
	public static LocalDate localDateConversion(Date date){
		if(date != null){	//dateがnullじゃない場合
			return date.toLocalDate();
		}else{	//nullの時
			return null;
		}
	}

	/** input type dateで取得した値をLocalDateに変換するために使用
	 *  @param date - 変換したいString型のインスタンス
	 *  @return localDate - 変換後のLocalDate型のインスタンス
	 */
	public static LocalDate input_dateConversion(String date){
		if(date != null){
			String[] split = date.split("-");
			int[] work = new int[3];
			for(int i = 0; i < 3; i++){
				work[i] = Integer.parseInt(split[i]);
			}
			return LocalDate.of(work[0], work[1], work[2]);
		}else{
			return null;
		}
	}
}