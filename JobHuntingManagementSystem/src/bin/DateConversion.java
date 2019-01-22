package bin;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

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

	/** LocalDateTimeからsql.Timestampに変換するために使用
	 *  @param localDatetime - 変換したいLocalDateTime型のインスタンス
	 *  @return timestamp - 変換した後のsql.Timestamp型のインスタンス
	 */
	public static Timestamp timestampConversion(LocalDateTime localDateTime){
		if(localDateTime != null){	//localDateTimeがnullじゃない場合
			return Timestamp.valueOf(localDateTime);
		}else{	//nullの時
			return null;
		}
	}

	/** sql.TimestampからLocalDateTimeに変換するために使用
	 *  @param timestamp - 変換したいsql.Timestamp型のインスタンス
	 *  @return localDateTime - 変換した後のLocalDateTime型のインスタンス
	 */
	public static LocalDateTime localDateTimeConversion(Timestamp timestamp){
		if(timestamp != null){	//timestampがnullじゃない場合
			return timestamp.toLocalDateTime();
		}else{	//nullの時
			return null;
		}
	}
}