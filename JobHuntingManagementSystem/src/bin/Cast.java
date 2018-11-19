package bin;

public class Cast {

	/** Object型からArrayListなどに警告を出さずにCastするためのもの
	 *  @param obj - CastしたいObject
	 *  @return T - 任意のCastしたい先のクラス
	 */
	@SuppressWarnings("unchecked")
	public static <T> T autoCast(Object obj){
		T castObj = (T) obj;
		return castObj;
	}
}