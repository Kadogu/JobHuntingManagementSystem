package bin;

import java.util.Random;

public class CreateID {
	private static final String[] str = {"A","B","C","D","E","F","G","H","I","J","K","L","M",
			"N","O","P","Q","R","S","T","U","V","W","X","Y","Z",
			"a","b","c","d","e","f","g","h","i","j","k","l","m",
			"n","o","p","q","r","s","t","u","v","w","x","y","z",
			"0","1","2","3","4","5","6","7","8","9"};
	private static Random rd = new Random();

	/** 指定された桁数のランダムなIDを作成する
	 *  @param digits - 作成したいIDの桁数
	 *  @return id - 作成したID
	 */
	public static String createID(int digits){
		StringBuilder sb = new StringBuilder(digits);
		for(int i = 0; i < digits; i++){
			sb.append(str[rd.nextInt(62)]);
		}
		return sb.toString();
	}
}