<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">

	function submitFrm(goUrl)
	{
		if(confirm('실행하시겠습니까?'))
			{
				frm.action = goUrl;
				frm.submit();		
			}
	}

</script>


</head>
<body>
난 template
<table border="">
	<tr>
		<td width="600">
			<jsp:include page="inc/top.jsp"/>
		</td>
	</tr>
	<tr>
		<td>
			<jsp:include page="board/${forward.url }.jsp"/>
		</td>
	</tr>
	<tr>
		<td>
			<jsp:include page="inc/bottom.jsp"/>
		</td>
	</tr>
</table>
</body>
</html>