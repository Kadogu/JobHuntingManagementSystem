package test.bin;

import bin.SendMail;

public class SendMailTest {

	public static void main(String[] args) {
		String receiverMail_Address = "mj.se.oy@gmail.com";
		String receiverName = "受信者";

		String title = "テスト";
		String message = "メール送信のテストです。\n気にしないで下さい。";

		boolean flg = SendMail.sendMail(title, message, receiverMail_Address, receiverName);
		if(flg){
			System.out.println("メール送信成功");
		}else{
			System.out.println("メール送信失敗");
		}
	}
}