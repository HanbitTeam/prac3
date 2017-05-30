<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<c:set var="dto" value="${forward.mainData }"/>
<table border="">
	<tr>
		<td>id</td><td>${dto.id }</td>
	</tr><tr>
		<td>제목</td><td>${dto.title }</td>
	</tr><tr>
		<td>작성자</td><td>${dto.pname }</td>
	</tr><tr>
		<td>작성일</td><td>
		<fmt:formatDate value="${dto.reg_date }" pattern="yyyy-MM-dd"/>
		</td>
	</tr><tr>
		<td>조회수</td><td>${dto.cnt }</td>
	</tr><tr>
		<td>내용</td><td>${dto.contentBr }</td>
	<c:if test="${dto.upfile!=null }">
	</tr><tr>
		<td>파일</td><td>
		<c:choose>
			<c:when test="${dto.extImg}">
			<img alt="" src="../up/${dto.upfile}">
			</c:when>
			<c:otherwise>
			<a href="download?ff=${dto.upfile}">${dto.upfile}</a>
			
			</c:otherwise>
		</c:choose>
		
		
		</td>
	</c:if>
	</tr>
	
	<tr>
		<td colspan="2" align="right">
			<a href="modifyForm?id=${dto.id }">수정</a>
			<a href="deleteForm?id=${dto.id }">삭제</a>
			<a href="replyForm?id=${dto.id }">답변</a>
			<a href="list">뒤로</a>
		</td>
	</tr>
</table>
