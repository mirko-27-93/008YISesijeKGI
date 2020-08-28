<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:useBean id='stateDatabaseUser' class='programranje.yatospace.server.basic.bean.SessionBean' scope='session'></jsp:useBean>

<c:if test='${stateDatabaseUser.isLogged(pageContext.session)}'>
	<c:out value='${stateDatabaseUser.logout(pageContext.session)}'></c:out>
</c:if>

<c:redirect url = "../index.jsp"/>