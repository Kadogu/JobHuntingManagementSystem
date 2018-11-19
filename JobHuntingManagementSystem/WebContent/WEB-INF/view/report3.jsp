<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>報告書作成(3/3) | モリジョビ就活管理システム</title>
	</head>
	<body>
		<div>
			<h1>報告書3/3</h1>

			<form action="Report" method="post">
				<label>試験の内容:
					<textarea name="content_of_test"></textarea>
				</label><br>

				<label>後輩へのアドバイス:
					<textarea name="advice_to_junior"></textarea>
				</label><br>

				<input type="hidden" name="" value="">
				<input type="submit" value="次へ" id="save">
				<input type="submit" value="保存して止める" id="save">
			</form>
		</div>
	</body>
</html>