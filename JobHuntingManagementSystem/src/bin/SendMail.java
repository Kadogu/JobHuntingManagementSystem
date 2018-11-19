package bin;

import static dto.MailAccount.*;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMail {

	/** メール送信をするためのもの
	 *  @param title - メールタイトル
	 *  @param message - メール本文
	 *  @param receiverMail_Address - 受信者のメールアドレス
	 *  @param receiverName - 受信者名
	 *  @return flg - メールの送信処理が成功したか失敗したかを判断するためのもの
	 */
	public static boolean sendMail(String title, String message, String receiverMail_Address, String receiverName){
		try{
			/*メール送信に必要な処理 (サンプルコード参照)*/
			Properties property = new Properties();

			property.put("mail.smtp.host","smtp.gmail.com");

			//GmailのSMTPを使う場合
			property.put("mail.smtp.auth", "true");
			property.put("mail.smtp.starttls.enable", "true");
			property.put("mail.smtp.host", "smtp.gmail.com");
			property.put("mail.smtp.port", "587");
			property.put("mail.smtp.debug", "true");

			Session session = Session.getInstance(property, new javax.mail.Authenticator(){
				protected PasswordAuthentication getPasswordAuthentication(){
					return new PasswordAuthentication(getMailAddress(), getAccountPassword());
				}
			});

			MimeMessage mimeMessage = new MimeMessage(session);

			/*メール宛先*/
			InternetAddress toAddress =
					new InternetAddress(receiverMail_Address, receiverName);
			mimeMessage.setRecipient(Message.RecipientType.TO, toAddress);

			/*メール送信元*/
			InternetAddress fromAddress =
					new InternetAddress(getMailAddress(), getAccountName());

			mimeMessage.setFrom(fromAddress);

			mimeMessage.setSubject(title, "ISO-2022-JP");

			mimeMessage.setText(message, "ISO-2022-JP");

			Transport.send(mimeMessage);
			/*メール送信に必要な処理ここまで*/

			return true;
		}
		catch(Exception e){
			/*メール送信ロジックでエラーがあった場合*/
			return false;
		}
	}
}