package bin;

import java.util.Random;

public class CreatePassCode {
	private static final int LENGTH = 8;	//パスコードの長さ
	private static final Random RD = new Random();

	/** パスコードを作成するもの
	 *  @return passcode - 作成されたパスコード
	 */
	public static String createPassCode(){
		StringBuilder sb = new StringBuilder(LENGTH);

		for(int i = 0; i < LENGTH; i++){
			sb.append(RD.nextInt(10));
		}

		return sb.toString();
	}
}