<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:useBean id='messageBean' class='programranje.yatospace.server.basic.bean.MessageBean' scope='session'></jsp:useBean>
<jsp:useBean id='webPageTrasitiveBean' class='programranje.yatospace.server.basic.bean.WebPageBean' scope='session'></jsp:useBean>

<c:out value="${messageBean.initDefault()==null?'':''}"></c:out>


<c:if test='${webPageTrasitiveBean eq null || webPageTrasitiveBean.backPage eq null || webPageTrasitiveBean.backPage.trim().length()==0}'> 
	<c:redirect url = "./../index.jsp"/>
</c:if>

<c:if test='${webPageTrasitiveBean.backPage ne null}'> 
	<c:redirect url = "./../../${webPageTrasitiveBean.backPage.substring(1)}"/>
</c:if>

