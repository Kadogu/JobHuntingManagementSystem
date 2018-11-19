package dto;

import java.io.Serializable;

/**
 * <p>お問い合わせなどのシステム上からメールを送信する時に使用するアカウント情報を保持しているクラスです。</p>
 *
 * <p>本システムはJava Mailを使用しているのでメールアカウントのセキュリティに穴を開けとかないとエラーになります。</p>
 *
 * <p>そのため、メールアカウントを変更する際は忘れずにメールアカウントのセキュリティに穴を開けといて下さい。</p>
 *
 * <p>なお、その必要があるので普段使いしていない本システム専用のメールアカウントの使用を推奨しています。</p>
 *
 * <p>予めご了承下さい。</p>
 *
 * {@code MAIL_ADDRESS - 送信者のメールアドレス}<br>
 * {@code ACCOUNT_PASSWORD - 送信者のメールアドレスのパスワード}<br>
 * {@code ACCOUNT_NAME - 送信者名(表示される名前)※アカウント名と異なっても大丈夫です。}
 * */
public class MailAccount implements Serializable{
	private static final String MAIL_ADDRESS = "jyobi.h30.gradwork.k@gmail.com";
	private static final String ACCOUNT_PASSWORD = "Grad_work";
	private static final String ACCOUNT_NAME = "モリジョビ就活管理システム";

	public static String getMailAddress() {
		return MAIL_ADDRESS;
	}

	public static String getAccountPassword() {
		return ACCOUNT_PASSWORD;
	}

	public static String getAccountName() {
		return ACCOUNT_NAME;
	}
}