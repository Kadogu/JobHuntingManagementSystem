<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>報告書作成(1/3) | モリジョビ就活管理システム</title>
	</head>
	<body>
		<div>
			<h1>報告書1/3</h1>

			<form action="Report" method="post">
				<label>試験結果:
					<select name="sorf">
						<option disabled selected></option>
						<option value="s">合格</option>
						<option value="f">不合格</option>
					</select>
				</label><br>

				<label>合否期日:<input type="date" name="sfdate"></label><br>

				<label>業種:
					<select name="type_of_industry">
						<option disabled selected></option>
						<option value="it">IT</option>
					</select>
				</label><br>

				<label>職種:
					<select name="occupations">
						<option disabled selected></option>
						<option value="se">システムエンジニア</option>
					</select>
				</label><br>

				<label>応募形態:
					<select name="application_form">
						<option disabled selected></option>
						<option>学校</option>
						<option>自己開拓</option>
						<option>縁故</option>
						<option>その他</option>
					</select>
				</label><br>

				<input type="hidden" name="" value="">
				<input type="submit" value="次へ" id="save">
				<input type="submit" value="保存して止める" id="save">
			</form>
		</div>
	</body>
</html>