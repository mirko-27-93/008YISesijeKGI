<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id='messageBean' class='programranje.yatospace.server.basic.bean.MessageBean' scope='session'></jsp:useBean>

<c:if test="${messageBean.type eq 'INFO'}">
	<jsp:include page="/WEB-INF/message-pages/standard-info-message.jsp"></jsp:include>
</c:if>

<c:if test="${messageBean.type eq 'SUCCESS'}">
	<jsp:include page="/WEB-INF/message-pages/success-message.jsp"></jsp:include>
</c:if>

<c:if test="${messageBean.type eq 'ERROR'}">
	<jsp:include page="/WEB-INF/message-pages/error-message.jsp"></jsp:include>
</c:if>