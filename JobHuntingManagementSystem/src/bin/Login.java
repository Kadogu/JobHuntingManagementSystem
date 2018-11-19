package bin;

import dao.AccountDAO;

public class Login {

	/** ログイン成功か失敗かを判断するBin
	 *  String user_id //入力されたユーザーID
	 *  String pw //入力されたパスワード
	 *  戻り値 boolean //true(成功) or false(失敗)
	 */
	public static boolean login(String user_id, String pw){
		String hashPW = AccountDAO.hashPW(pw);
		String pwDB = AccountDAO.login(user_id);

		if(pwDB == null){ //IDが存在しない
			//ログイン失敗
			return false;
		}else if(pwDB.equals(hashPW)){ //IDが存在してパスワードが一致
			//ログイン成功
			return true;
		}else{ //IDは存在するがパスワードが一致しない
			//ログイン失敗
			return false;
		}
	}
}