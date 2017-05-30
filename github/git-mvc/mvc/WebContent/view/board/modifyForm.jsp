<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="dto" value="${forward.mainData }"/>
<form action="modifyReg" method="post" >
<table border="">
   <tr>
      <input type="hidden" name="id" value ="${dto.id }" />
      <td>제목</td><td><input type="text" name="title" value="${dto.title }"/></td>
   </tr><tr>
      <td>작성자</td><td><input type="text" name="pname" value="${dto.pname }"/></td>
   </tr><tr>
      <td>암호</td><td><input type="text" name="pw"/></td>
   </tr><tr>
      <td>내용</td><td><textarea cols=50 rows=5 name="content" value="${dto.content }">남기실말</textarea></td>
   </tr>
      <tr>
      <td colspan="2" align="right">
         <input type="submit" value="수정"/>
         <input type="reset" value="초기화"/>
         <a href="detail?id=${dto.id }">뒤로</a>
      </td>
   </tr>
</table>
</form>