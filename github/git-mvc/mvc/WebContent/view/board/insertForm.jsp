<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<form action="insertReg" method="post" enctype="multipart/form-data">
<table border="">
	<tr>
		
		<td>제목</td><td><input type="text" name="title"/></td>
	</tr><tr>
		<td>작성자</td><td><input type="text" name="pname"/></td>
	</tr><tr>
		<td>암호</td><td><input type="text" name="pw"/></td>
	</tr><tr>
		<td>파일</td><td><input type="file" name="upfile"/></td>
	</tr><tr>
		<td>내용</td><td><textarea cols=50 rows=5 name="content">남기실말</textarea></td>
	</tr>
		<tr>
		<td colspan="2" align="right">
			<input type="submit" value="글쓰기"/>
			<a href="list">뒤로</a>
		</td>
	</tr>
</table>
</form>