<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id='serverBean' class='programranje.yatospace.server.basic.bean.ServerBean' scope='session'></jsp:useBean>
<jsp:useBean id='messageBean' class='programranje.yatospace.server.basic.bean.MessageBean' scope='session'></jsp:useBean>

<div class='error'>
	&nbsp;<span id='server_time'><c:out value="${serverBean.init().serverTimeRecord}"></c:out></span>
	&nbsp;&nbsp;&nbsp;&nbsp;<a href='${pageContext.request.contextPath}/WEB-OP/message-reset.jsp'>X</a>
	&nbsp;<a href='${pageContext.request.contextPath}/WEB-OP/message-preview.jsp' target='_blank'>V</a>
	&nbsp;&nbsp;&nbsp;&nbsp;<div>&nbsp;<c:out value="${messageBean.message}"></c:out></div>
</div>