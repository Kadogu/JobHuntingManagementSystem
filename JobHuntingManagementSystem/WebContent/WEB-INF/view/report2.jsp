<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<title>報告書作成(2/3) | モリジョビ就活管理システム</title>
	</head>
	<body>
		<div>
			<h1>報告書2/3</h1>

			<form action="Report" method="post">
				<h2>一次試験</h2>

				<label>日付:<input type="date" name="start_date"></label><br>

				<p>試験時間:
					<label>
						<select name="start_hour">
							<option>8</option>
							<option>9</option>
						</select>
					時</label>
					<label>
						<select name="start_minute">
							<option>0</option>
							<option>5</option>
						</select>
					分～</label>
					<label>
						<select name="last_hour">
							<option>8</option>
							<option>9</option>
						</select>
					時</label>
					<label>
						<select name="last_minute">
							<option>0</option>
							<option>5</option>
						</select>
					分</label>
				</p>

				<p>試験区分:
					<label><input type="checkbox" name="test_category" value="writing">筆記</label>
					<label><input type="checkbox" name="test_category" value="interview">面接</label>
				</p>

				<p>筆記試験内容:
					<label><input type="checkbox" name="writing" value="">一般常識</label>
					<label><input type="checkbox" name="writing" value="spi">SPI</label>
					<label><input type="checkbox" name="writing" value="">適性(性格)</label>
					<label><input type="checkbox" name="writing" value="">作文</label>
					<label><input type="checkbox" name="writing" value="other">その他</label>
					<input type="text" name="">
				</p>

				<label>面接時間:<input type="number" name="view_time" min="5">分間</label><br>

				<label>面接官:<input type="number" name="viewer_no">名</label><br>

				<p>面接試験内容:
					<label><input type="radio" name="view_content" value="">個人面接</label>
					<label><input type="radio" name="view_content" value="">集団面接</label>
					<label><input type="number" name="groop_no">名</label>
					<label><input type="radio" name="view_content" value="">グループディスカッション</label>
					<label><input type="number" name="discussion_no">名</label>
					<input type="text" name="">
				</p>

				<input type="hidden" name="" value="">
				<input type="submit" value="次へ" id="save">
				<input type="submit" value="保存して止める" id="save">
			</form>
		</div>
	</body>
</html>