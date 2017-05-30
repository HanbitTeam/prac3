<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
  



<form name ="frm"  method="post" >
<table border="">
	<tr>
		   <input type="hidden" name="id" value ="${param.id }" />
           <td>암호</td><td><input type="text" name="pw"/></td>
		
	</tr>
		<tr>
		<td colspan="2" align="right">
			<input type="button" value="삭제" onclick="submitFrm('deleteReg')"/>
			<a href="detail?id=${param.id }">뒤로</a>
		</td>
	</tr>
</table>
</form> 