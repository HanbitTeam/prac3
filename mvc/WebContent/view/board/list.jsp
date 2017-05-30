<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<table border="">
	<tr>
		<td>번호</td>
		<td>제목</td>
		<td>작성자</td>
		<td>작성일</td>
		<td>조회수</td>
	</tr>
	<c:forEach var="dto" items="${forward.mainData }" varStatus="no">
	<tr>
		<td width= "50">${(forward.nowPage-1)*forward.limit+no.index+1 }</td>
		<td width="300"><a href="detail?id=${dto.id }&nowPage=${forward.nowPage}">
		
		<c:forEach var="i" begin="1" end="${dto.lev }">
		&nbsp; &nbsp;
		</c:forEach>
		
		
		<c:if test="${dto.lev>0 }">└</c:if>
		${dto.title }
		
		
		</a></td>
		<td width="100">${dto.pname }</td>
		<td>
		<fmt:formatDate value="${dto.reg_date }" pattern="yyyy-MM-dd"/>
		</td>
		<td width="100">${dto.cnt }</td>
	</tr>
	</c:forEach>
	<tr>
		<td colspan="5" align="right">
			<a href="insertForm">글쓰기</a>
		</td>
	</tr>
	<tr>
		<td colspan="5" align="center">
		<c:if test="${forward.startPage-1 >1}">
		<a href="?nowPage=1">처음으로</a>
		<a href="?nowPage=${forward.startPage-1 }"><</a>
		</c:if>
			 <c:forEach var="i" begin="${forward.startPage }" end="${forward.endPage }">
			 <c:choose>
			 <c:when test="${forward.nowPage==i }">
			 [${i }]
			 
			 </c:when>
			 
			 <c:otherwise>
			 
			 <a href="?nowPage=${i }">${i }</a>
			 </c:otherwise>
			 
			 </c:choose>
			 
			 
			
			</c:forEach>
			<c:if test="${forward.endPage<forward.total }">
			<a href="?nowPage=${forward.endPage+1 }">></a>
			<a href="?nowPage=${forward.total } ">마지막으로</a>
			</c:if>
		</td>
	</tr>
</table>
