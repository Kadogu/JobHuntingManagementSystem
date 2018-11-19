package test.bin;

import bin.Login;

public class LoginTest {

	public static void main(String[] args) {
		String user_id = "cccc";
		String pw = "cccc";
		boolean flg = Login.login(user_id, pw);
		if(flg){
			System.out.println("ログイン成功");
		}else{
			System.out.println("ログイン失敗");
		}
	}
}