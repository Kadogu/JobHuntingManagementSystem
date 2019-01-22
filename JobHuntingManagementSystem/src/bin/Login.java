package bin;

import java.time.LocalDateTime;

import dao.AccountDAO;
import dao.LogDAO;
import dto.Log;

public class Login {

	/** ログイン成功か失敗かを判断するもの
	 *  @param user_id - 入力されたユーザーID
	 *  @param pw - 入力されたパスワード
	 *  @return flg - {@code true} 成功 or {@code false} 失敗
	 */
	public static boolean login(String user_id, String pw){
		String hashPW = AccountDAO.hashPW(pw);
		String pwDB = AccountDAO.login(user_id);

		boolean flg = false;

		if(pwDB != null && pwDB.equals(hashPW)){ //IDが存在してパスワードが一致
			//ログイン成功
			flg = true;
		}

		LocalDateTime log_datetime = LocalDateTime.now();

		Log log = new Log(0, log_datetime, user_id, hashPW, flg);

		int row = LogDAO.addLog(log);

		if(row == 0){	//ログ格納に失敗した場合
			flg = false;
		}

		return flg;
	}
}