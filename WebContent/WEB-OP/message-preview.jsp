<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import='programranje.yatospace.server.basic.bean.MessageBean' %>
<%@ page import='programranje.yatospace.server.basic.lang.PreviewException' %>


<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<jsp:useBean id='messageBean' class='programranje.yatospace.server.basic.bean.MessageBean' scope='session'></jsp:useBean>

<% 
	MessageBean mb = (MessageBean) request.getSession().getAttribute("messageBean"); 
	if(mb.getException()==null) throw new PreviewException();
	else throw mb.getException(); 
%>