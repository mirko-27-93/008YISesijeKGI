<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<jsp:useBean id='profileBean' class='programranje.yatospace.server.basic.bean.ProfileBean'></jsp:useBean>

<c:out value="${profileBean.init(pageContext.session)}"></c:out>

<c:if test="${profileBean.profilePictureAddress ne ''}">
	<img width='100%' height='200' alt='profile_image' src='${pageContext.request.contextPath}/ProfileImageServlet'/>
</c:if>